a = [1, 2, 2, 3, 17, 29]
b = [2, 2, 29]
c = []
ai, bi = 0, 0
while True:
	if ai >= len(a) or bi >= len(b):
		break
	if a[ai] < b[bi]:
		ai += 1
	elif a[ai] > b[bi]:
		bi += 1
	else:
		print(a[ai], end=' ')
		ai += 1
		bi += 1
