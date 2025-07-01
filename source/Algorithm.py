import sys

def read_array():
    line = input().strip()
    line = line.replace('[', '').replace(']', '').strip()
    tokens = line.replace(',', ' ').split()
    return [int(tok) for tok in tokens if tok.strip().isdigit()]

class Algorithm:
    def next_permutation(self, arr: list[int]) -> list[int]:
        n = len(arr)
        
        if n == 0 or n == 1:
            return arr
        
        point = -1
        for i in range(n - 2, -1, -1):
            if arr[i] < arr[i + 1]:
                point = i
                break

        if point == -1:
            arr.reverse()
            return arr

        min_pos = point + 1
        for i in range(n - 1, point + 1, -1):
            if arr[i] > arr[point]:
                min_pos = i
                break

        arr[point], arr[min_pos] = arr[min_pos], arr[point]

        l, r = point + 1, n - 1

        while l < r:
            arr[l], arr[r] = arr[r], arr[l]
            l += 1
            r -= 1

        return arr

    def rotate_matrix(self, M: list[list[int]]) -> None:
        n = len(M)
           
        # reverse transpose
        for i in range(0, n):
            for j in range(0, n - i):
                p = n - 1 - j       
                q = n - 1 - i
                
                M[i][j], M[p][q] = M[p][q], M[i][j]

        # row-wise reverse
        for i in range(0, n):
            l, r = 0, n - 1
            
            while l < r:
                M[i][l], M[i][r] = M[i][r], M[i][l]
                l += 1
                r -= 1

def main():
    sys.stdin = open('./data/input.txt', 'r', encoding='utf-8')
    sys.stdout = open('./data/output.txt', 'w', encoding='utf-8')

    t = int(input())
    for z in range(0, t):
        n = int(input())
        M: list[list[int]] = []

        for i in range(0, n):
            A = read_array()
            M.append(A)

        algo = Algorithm()
        algo.rotate_matrix(M)

        for l in M:
            print(l)
            

if __name__ == '__main__':
    main()