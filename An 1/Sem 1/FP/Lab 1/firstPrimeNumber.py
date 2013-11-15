def isPrime(x):
	prime = True;
	for p in range(2, x/2+1):
		if x % p == 0:
			prime = False
			return prime
	return prime

def firstPrimeNumberLargerThanN():
	n = input("n=")
	prime = False
	x = n + 1
	while prime == False:
		if isPrime(x):
			prime = True
		else:
			x = x + 1
	print x

firstPrimeNumberLargerThanN()