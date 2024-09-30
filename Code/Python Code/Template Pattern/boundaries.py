def sumOfSquares(x: int) -> int:
	sum = 0
	for i in range(x):
		sum += (i*i)
	return sum

def max(x: int,y: int) -> int:
	if x > y:
		return x
	else:
		return y

def main():
	cube = 100*100*100
	sOS = sumOfSquares(100)

	print(max(triple,sOS))

if __name__ == '__main__':
	main()