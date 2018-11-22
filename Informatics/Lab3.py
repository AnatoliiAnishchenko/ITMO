fin = open("input.txt", "r")
fout = open("output.txt", "w")

strCnt = 8
numCnt = 5

def from2to16(a2):      # 2 -> 16
    a10 = int(a2, 2)    # 2 -> 10
    a16 = hex(a10)[2:]  # 10 -> 16
    return a16

def fromM10to10(aM10):  # -10 -> 10
    a10 = 0
    aM10 = aM10[::-1]
    cnt = 1;
    for digit in aM10:
        a10 += int(digit) * cnt
        cnt = -10 * cnt
    return a10


for i in range(strCnt):
    st = fin.readline().split()
    base1 = int(st[0])
    base2 = int(st[1])
    arr = st[2::]

    ans = []
    
    if (base1 == 2) and (base2 == 16):
        for a in arr:
            ans.append(from2to16(a))
    elif (base1 == -10) and (base2 == 10):
        for a in arr:
            ans.append(fromM10to10(a))
    else:
        print(i + 1, "line has incorrect bases:", base1, base2, file=fout)

    print(base1, "->", base2, file=fout, end=" : ")
    for j in range(numCnt):
        print(arr[j], "->", ans[j], file=fout, end="")
        if ((j + 1) != numCnt):
            print(file=fout, end=" | ")
        else:
            print(file=fout)

fin.close()
fout.close()
