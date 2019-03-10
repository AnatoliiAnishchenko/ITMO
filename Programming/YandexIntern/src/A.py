n = int(input())
arr = [int(s) for s in input().split()]
res = []
for t in arr:
	res.append(t)
	res.sort()
	if (len(res) > 5):
		res = res[0:5]
	print(*res[::-1])