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
        P = algo.majorityElementTwo(A)
        print(A)
        print(P)


if __name__ == "__main__":
    main()
