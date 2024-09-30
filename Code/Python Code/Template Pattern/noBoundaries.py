cube = 100*100*100

sum = 0
for i in range(100):
	sum += (i*i)

if cube > sum:
	max = cube
else:
	max = sum

print(max)
