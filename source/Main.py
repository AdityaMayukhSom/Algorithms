import sys

class IO:
    @staticmethod
    def read_int_array() -> list[int]:
        line = (
            input()
            .replace('"', "")
            .replace("[", "")
            .replace("]", "")
            .replace(",", " ")
            .strip()
        )
        tokens = line.split()
        return [int(tok.strip()) for tok in tokens]

    @staticmethod
    def write_grid(mat: list[list[int]]) -> None:
        n = len(mat)
        for i in range(n):
            m = len(mat[i])
            for j in range(m):
                print(mat[i][j], end=" ")
            print()


class Solution:
    def isPrime(self, n: int) -> bool:
        if n <= 1:
            return False
        if n <= 3:
            return True
        if n % 2 == 0 or n % 3 == 0:
            return False

        i = 5
        while i * i <= n:
            if n % i == 0:
                return False
            if n % (i + 2) == 0:
                return False
            i += 6
        return True

    def listGcd(self, A: list[int]) -> int:
        g: int = A[0]
        for n in A:
            g = self.gcd(g, n)
        return g

    def gcd(self, a: int, b: int) -> int:
        while a > 0 and b > 0:
            if a > b:
                a = a % b
            else:
                b = b % a
        return b if a == 0 else a

    def isAllSame(self, A: list[int]) -> bool:
        n = len(A)
        if n == 0 or n == 1:
            return True
        e1 = A[0]
        for i in range(1, n):
            if not e1 == A[i]:
                return False
        return True

    def solve(self, s: str, n: int) -> tuple[int, int]:
        oc = sum(1 for c in s if c == "1")
        mx = min(2 * oc, n)

        nno: list[int] = []
        S = [c for c in s]
        for i in range(n - 1, -1, -1):
            if S[i] == "1":
                nno.append(i)

        swap: int = 0
        oc, zc = 0, 0
        for i in range(n):
            if S[i] == "1":
                nno.pop()
                oc += 1
            else:
                zc += 1

            if zc > oc:
                # we need to swap one
                if len(nno) == 0:
                    # no more ones left
                    break
                noi = nno.pop()
                swap += noi - i
                S[noi] = "0"
                zc -= 1
                oc += 1
        return mx, swap


def main():
    in_file = "./data/input.txt"
    out_file = "./data/output.txt"
    
    sys.stdin = open(in_file, "r", encoding="utf-8")
    sys.stdout = open(out_file, "w", encoding="utf-8")

    t: int = int(input())
    while t > 0:
        n: int = int(input())
        A = read_int_array()
        B = read_int_array()
        print(expectedBinarySearch(n, A, B))
        t -= 1


if __name__ == "__main__":
    main()
