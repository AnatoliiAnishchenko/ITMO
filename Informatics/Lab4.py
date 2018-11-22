import re

fin = open("Schedule.json", "r")
fout = open("Schedule.proto", "w")


def getText(arr):
    text = ""
    for i in range(len(arr)):
        text += arr[i].rstrip().lstrip()
    return text


def sequence(*funcs):
    if len(funcs) == 0:
        def result(src):
            yield (), src
        return result
    def result(src):
        for arg1, src in funcs[0](src):
            for others, src in sequence(*funcs[1:])(src):
                yield (arg1,) + others, src
    return result

numberRegex = re.compile(r"(-?(?:0|[1-9]\d*)(?:\.\d+)?(?:[eE][+-]?\d+)?)\s*(.*)", re.DOTALL)

def parseNumber(src):
    match = numberRegex.match(src)
    if match is not None:
        number, src = match.groups()
        yield eval(number), src

stringRegex = re.compile(r'''("(?:[^\\']|\\['\\/bfnrt]|\\u[0-9a-fA-F]{4})*?")\s*(.*)''', re.DOTALL)

def parseString(src):
    match = stringRegex.match(src)
    if match is not None:
        string, src = match.groups()
        yield eval(string), src

def parseWord(word, value=None):
    l = len(word)
    def result(src):
        if src.startswith(word):
            yield value, src[l:].lstrip()
    result.__name__ = "parse_%s" % word
    return result

parseTrue = parseWord("true", True)
parseFalse = parseWord("false", False)
parseNull = parseWord("null", None)

def parseValue(src):
    for match in parseString(src):
        yield match
    for match in parseNumber(src):
        yield match
    for match in parse_array(src):
        yield match
    for match in parseObject(src):
        yield match
    for match in parseTrue(src):
        yield match
    for match in parseFalse(src):
        yield match
    for match in parseNull(src):
        yield match

parseLeftSquareBracket = parseWord("[")
parsRightSquareBracket = parseWord("]")
parseEmptyArray = sequence(parseLeftSquareBracket, parsRightSquareBracket)

def parse_array(src):
    for _, src in parseEmptyArray(src):
        yield [], src
        return

    for (_, items, _), src in sequence(
        parseLeftSquareBracket,
        parseCommaSeparatedValues,
        parsRightSquareBracket,
    )(src):
        yield items, src

parseComma = parseWord(",")

def parseCommaSeparatedValues(src):
    for (value, _, values), src in sequence(
        parseValue,
        parseComma,
        parseCommaSeparatedValues
    )(src):
        yield [value] + values, src
        return

    for value, src in parseValue(src):
        yield [value], src

parseLeftCurlyBracket = parseWord("{")
parseRightCurlyBracket = parseWord("}")
parseEmptyObject = sequence(parseLeftCurlyBracket, parseRightCurlyBracket)

def parseObject(src):
    for _, src in parseEmptyObject(src):
        yield {}, src
        return
    for (_, items, _), src in sequence(
        parseLeftCurlyBracket,
        parseCommaSeparatedKeyvalues,
        parseRightCurlyBracket,
    )(src):
        yield items, src

parseColon = parseWord(":")

def parseKeyValue(src):
    for (key, _, value), src in sequence(
        parseString,
        parseColon,
        parseValue
    )(src):
        yield {key: value}, src

def parseCommaSeparatedKeyvalues(src):
    for (keyvalue, _, keyvalues), src in sequence(
        parseKeyValue,
        parseComma,
        parseCommaSeparatedKeyvalues,
    )(src):
        keyvalue.update(keyvalues)
        yield keyvalue, src
        return

    for keyvalue, src in parseKeyValue(src):
        yield keyvalue, src

def parse(s):
    s = s.strip()
    match = list(parseValue(s))
    if len(match) != 1:
        raise ValueError("not a valid JSON string")
    result, src = match[0]
    if src.strip():
        raise ValueError("not a valid JSON string")
    return result


def jsonToProtobuf(jsonObj, name="text"):
    result = "message " + name + " {"  
    result += processObject(jsonObj)
    return result
    
def processObject(jsonObj, linePadding=""):
    result = ""
    index = 0
    for key in jsonObj:
        itemType = type(jsonObj[key])
        if itemType is dict or itemType is list:
            result += addObjectsArrayMessage(key, jsonObj[key], index, linePadding + "\t")
        else:
            result += getKeyLabels(key, jsonObj[key], index, linePadding + "\t")
        index += 1
    return result + "\n" + linePadding + "}\n"

def addObjectsArrayMessage(item, jsonObj, index, linePadding):
    if type(jsonObj) is list:
        return addArrayMessage(item, jsonObj, index, linePadding)
    else:
        return addObjectMessage(item, jsonObj, linePadding) + getKeyLabels(item, jsonObj, index, linePadding)

def addObjectMessage(key, value, linePadding):
    key = key[1::] if key[0] == '?' else key
    newMessage = "\n\n" + linePadding +"message " + key.upper() + " {" + processObject(value, linePadding)
    return newMessage
    
def addArrayMessage(key, value, index, linePadding):
    objectsType = type(value[0])
    sameTypeArray = True
    for item in value:
        sameTypeArray = sameTypeArray and (type(item) == objectsType)
    if sameTypeArray:
        if objectsType is not list and objectsType is not dict:
            return "\n" + linePadding + "repeated" + ("float" if objectsType is int or objectsType is float else "bool" if objectsType == bool else "string") + " " + key + " = " + index
        else:
            return addObjectMessage(key, value[0], linePadding) + "\n" + linePadding + "repeated " + key.upper() + " " + key + " = " + str(index)
    else:
        return "\nError - Cannot parse. Array is having different typed elements."
            
def getKeyLabels(item, value, index, linePadding):
    variableType = "optional" if item[0] == '?' else "required"
    item = item[1::] if item[0] == '?' else item
    if type(value) is dict or type(value) is list:
        return "\n" + linePadding + variableType + " " + item.upper() + " " + item + " = " + str(index)
    else:
        return "\n" + linePadding + variableType + " "+ ("string" if type(value) is str else "double" if type(value) is int or type(value) is float else "bool") + " " + item + " = " + str(index)
        

text = getText(fin.readlines())
shedule = parse(text)
print(jsonToProtobuf(shedule))
print(jsonToProtobuf(shedule), file=fout)

fin.close()
fout.close()