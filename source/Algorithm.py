import sys


def read_int_array():
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


class Algorithm:
    def findTripletBF(self, A: list[int]) -> tuple[int, int, int]:
        n = len(A)
        for i in range(0, n):
            for j in range(i + 1, n):
                for k in range( j + 1, n):
                    if A[i] + A[j] == A[k]:
                        print(A[i], A[j], A[k])
                        return i, j, k
        return -1, -1, -1
    
    def findTriplet(self, A: list[int]) -> bool:
        A.sort()
        n = len(A)
        
        for k in range(n - 1, -1, -1):
            i, j = 0, k - 1
            
            while i < j:
                s = A[k] - A[i] - A[j]

                if s == 0:
                    return True
                elif s < 0:
                    curr_j = A[j]
                    while j > i and A[j] == curr_j:
                        j -= 1
                elif s > 0:
                    curr_i = A[i]
                    while i < j and A[i] == curr_i:
                        i += 1

            curr_k = A[k]
            while k > -1 and A[k] == curr_k:
                k -= 1
            
        return False
    
    def elemExists(self, arr: list[int], num: int) -> int:
        l, r = 0, len(arr) - 1
        while l <= r:
            m = l + ((r - l) >> 1)
            if arr[m] == num: 
                return m
            elif arr[m] < num:
                l = m + 1
            elif arr[m] > num:
                r = m - 1
        return -1
              
    def commonElements(self, arr1: list[int], arr2: list[int], arr3: list[int]) -> list[int]:
        p, q, r = len(arr1), len(arr2), len(arr3)
        res: list[int] = []
        
        # make arr1 the smallest length array
        if p < q:
            if p < r:
                pass # arr1 has the smallest length, do nothing
            else:
                arr3, arr1 = arr1, arr3
                r, p = p, r
        else:
            if q < r:
                arr1, arr2 = arr2, arr1
                p, q = q, p
            else:
                arr3, arr1 = arr1, arr3
                r, p = p, r
        
        i = 0
        
        while i < p:
            curr_i = arr1[i]
            e2 = self.elemExists(arr2, curr_i)
            e3 = self.elemExists(arr3, curr_i)
            
            if e2 != -1 and e3 != -1:
                res.append(curr_i)
                
            while i < p and curr_i == arr1[i]:
                i += 1
        
        return res
    
    def pythagoreanTriplet(self, A: list[int]) -> bool:
        A.sort()
        n = len(A)
        
        for i in range(n - 1, -1, -1):
            j, k = 0, i - 1
            
            c2 = A[i] * A[i]
            
            while j < k:
                a2 = A[j] * A[j]
                b2 = A[k] * A[k]
                d = c2 - a2 - b2
                if d == 0:
                    return True
                elif d > 0:
                    j += 1
                elif d < 0:
                    k -= 1

        return False
    
    def majorityElementTwo(self, nums: list[int]) -> list[int]:
        cnt_1, cnt_2 = 0, 0
        num_1, num_2 = float("-inf"), float("-inf")

        for n in nums:
            if n == num_1:
                cnt_1 += 1
            elif n == num_2:
                cnt_2 += 1
            elif cnt_1 == 0 and n != num_2:
                num_1 = n
                cnt_1 = 1
            elif cnt_2 == 0 and n != num_1:
                num_2 = n
                cnt_2 = 1
            else:
                cnt_1 -= 1
                cnt_2 -= 1

        cnt_1, cnt_2 = 0, 0

        for n in nums:
            if n == num_1:
                cnt_1 += 1
            if n == num_2:
                cnt_2 += 1

        threshold = len(nums) // 3
        ans: list[int] = []

        if cnt_1 > threshold:
            ans.append(num_1)
        if cnt_2 > threshold:
            ans.append(num_2)

        if len(ans) == 2 and num_1 > num_2:
            ans[0], ans[1] = ans[1], ans[0]

        return ans


def main():
    in_file = "./data/input.txt"
    out_file = "./data/output.txt"
    
    sys.stdin = open(in_file, "r", encoding="utf-8")
    sys.stdout = open(out_file, "w", encoding="utf-8")

    t = int(input())
    for z in range(0, t):
        A = read_int_array()
        algo = Algorithm()
        P = algo.findTriplet(A)
        Q = algo.findTripletBF(A)
        print(P)
        print(Q)


if __name__ == "__main__":
    main()
