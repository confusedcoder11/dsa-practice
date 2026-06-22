package com.dsa.dp.countways;

/*
Your solution has a **fundamental modeling mistake**, not just a small bug.

Let’s fix your thinking first — because this exact confusion happens a lot in interviews.

---

# ❌ What’s Wrong in Your Code

You are treating
Combination Sum IV

like **subset / combination (pick / not pick)**.

But this problem is:

> 🔥 **Permutation DP (order matters)**

---

## ⚠️ Main Mistakes

### ❌ 1. You introduced `index`

```java
helper(nums, index, sum)
```

👉 This forces you to **avoid reusing previous elements in different order**

But in this problem:

```text
[1,2] and target = 3

Valid:
1+2
2+1  ← THIS is also valid
```

👉 Your approach **misses permutations**

---

### ❌ 2. Wrong recursion transitions

```java
ways += helper(nums, index, sum - nums[index]);
ways += helper(nums, index+1, sum - nums[index]); ❌ WRONG
ways += helper(nums, index+1, sum);
```

👉 This is a **hybrid of subset + unbounded knapsack** → incorrect model

---

### ❌ 3. Double counting / invalid counting

That second line:

```java
helper(nums, index+1, sum - nums[index])
```

👉 You're:

* picking `nums[index]`
* but moving to `index+1`

👉 This is logically inconsistent

---

# ✅ Correct Way to Think

### 🔑 State should be ONLY:

```java
dp[target] = number of ways to form target
```

👉 No index needed

---

# 🧠 Correct Recurrence

```text
dp[target] = sum(dp[target - num]) for all num
```

---

# ✅ Correct Recursive Solution (Memoized)

```java
class Solution {
    public int combinationSum4(int[] nums, int target) {
        Integer[] dp = new Integer[target + 1];
        return helper(nums, target, dp);
    }

    private int helper(int[] nums, int target, Integer[] dp) {
        if (target == 0) return 1;
        if (target < 0) return 0;

        if (dp[target] != null) return dp[target];

        int ways = 0;

        for (int num : nums) {
            ways += helper(nums, target - num, dp);
        }

        return dp[target] = ways;
    }
}
```

---

# 🔥 Why This Works

At each step:

👉 “Which number can I pick NEXT?”

```text
target = 4
choices:
- pick 1 → solve for 3
- pick 2 → solve for 2
- pick 3 → solve for 1
```

---

# ⚡ Key Insight (VERY IMPORTANT)

| Problem Type                             | State       |
| ---------------------------------------- | ----------- |
| Subset (like Partition Equal Subset Sum) | index + sum |
| Permutation (this problem)               | only target |

---

# 🧠 Mental Shortcut (Interview Gold)

👉 If **order matters**:

❌ Don’t use `index`
✅ Use only `target`

---
Good — this is exactly where most strong candidates separate from average ones.

Let’s go **deep enough that you’ll never confuse these again**.

---

# 🔥 1. ROOT CONFUSION: Combination vs Permutation

Take
Combination Sum IV

### Example:

```text
nums = [1,2]
target = 3
```

### All VALID answers:

```text
1+1+1
1+2
2+1   ← different
```

👉 Answer = **3**

---

## ⚠️ Now compare with subset-style thinking

If order didn’t matter:

```text
1+2 == 2+1
```

👉 Answer would be **2**

---

# 🧠 KEY DIFFERENCE

| Type                 | Order matters? | Use index? |
| -------------------- | -------------- | ---------- |
| Subset / Combination | ❌ No           | ✅ Yes      |
| Permutation          | ✅ Yes          | ❌ No       |

---

# ⚡ 2. WHY YOUR APPROACH FAILS

Your recursion:

```java
helper(nums, index, sum)
```

👉 This enforces:

```text
You can only move forward in array
```

So you generate:

```text
1+2  ✅
2+1  ❌ (impossible because you can't go back)
```

---

# 🔥 3. CORRECT MODEL (TREE VISUALIZATION)

Think like this:

```text
target = 3

                (3)
        /        \
     pick1      pick2
      (2)         (1)
    /    \          \
 pick1  pick2       pick1
  (1)    (0)         (0)
   |
 pick1
  (0)
```

👉 Each level = **choose ANY number again**

---

# 🧠 4. LOOP ORDER = THE SECRET

This is the most important trick for interviews.

---

## ✅ PERMUTATION (correct for this problem)

```java
for (int t = 1; t <= target; t++) {
    for (int num : nums) {
        if (t - num >= 0) {
            dp[t] += dp[t - num];
        }
    }
}
```

👉 Meaning:

> “For each target, try all choices”

---

## ❌ COMBINATION (WRONG for this problem)

```java
for (int num : nums) {
    for (int t = num; t <= target; t++) {
        dp[t] += dp[t - num];
    }
}
```

👉 This removes order → gives subset answer

---

# 🧪 5. PROOF BY EXAMPLE

### nums = [1,2], target = 3

---

## PERMUTATION LOOP

```text
dp[0] = 1

t=1:
  dp[1] = dp[0] = 1

t=2:
  dp[2] = dp[1] + dp[0] = 2

t=3:
  dp[3] = dp[2] + dp[1] = 3 ✅
```

---

## COMBINATION LOOP

```text
dp[0] = 1

num=1:
  dp = [1,1,1,1]

num=2:
  dp[2] += dp[0] → 2
  dp[3] += dp[1] → 2 ❌
```

👉 Result = 2 (WRONG)

---

# 🔥 6. UNIVERSAL RULE (MEMORIZE THIS)

```text
Outer loop = what you are building
Inner loop = choices
```

---

## If you're building TARGET → permutation

```text
for target:
    for choices:
```

---

## If you're building using ITEMS → combination

```text
for items:
    for target:
```

---

# 🧠 7. HOW TO IDENTIFY IN INTERVIEW

### Ask this:

> “Does 2+1 differ from 1+2?”

---

### If YES → permutation

* no index
* use full array every time

---

### If NO → combination

* use index
* avoid revisiting previous elements

---

# 🔥 8. CONNECTION TO OTHER PROBLEMS

Now see the pattern clearly:

---

## Same as:

* Number of Dice Rolls With Target Sum
* Climbing Stairs

👉 All are:

```text
dp[target] = sum of previous states
```

---

## Different from:

* Partition Equal Subset Sum

👉 This is:

```text
dp[i][sum] = take / not take
```

---

# ⚡ 9. FINAL MENTAL MODEL (VERY IMPORTANT)

Whenever you see:

```text
"number of ways to form target"
```

👉 Immediately ask:

### Q1:

Does order matter?

* YES → 1D DP
* NO → 2D DP (index + target)

---

# 🚀 10. INTERVIEW LEVEL INSIGHT

If interviewer twists problem:

### Variant:

“Use each number at most once”

👉 Now becomes:

* subset DP
* need index

---

### Variant:

“Order matters + unlimited use”

👉 current problem

---

### Variant:

“Order doesn’t matter + unlimited use”

👉 coin change combination

---

# 🎯 Final Takeaway

Your mistake:

```text
You tried to solve a PERMUTATION problem
using a COMBINATION mindset
```


* */
public class LC377CombinationSumIV {
    class Solution {
        public int combinationSum4(int[] nums, int target) {
            // what define my unique state
            // where i come from
            // base condition
            int n=nums.length;
            Integer[] dp = new Integer[target+1];
            dp[0]=1;
            for(int t=1;t<=target;t++){
                dp[t]=0;
                for(int num : nums){
                    if(t-num>=0){
                        dp[t]+=dp[t-num];
                    }
                }
            }
            return dp[target];
            // return helper(nums,target,dp);
        }
        private int helper(int[] nums, int target, Integer[] dp){
            if(target==0) return 1;
            if(target<0) return 0;
            if(dp[target]!=null) return dp[target];

            int ways=0;
            for(int num : nums){
                ways+=helper(nums,target-num,dp);
            }
            return dp[target]=ways;
        }
    }
}
