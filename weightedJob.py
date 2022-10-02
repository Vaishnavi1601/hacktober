from functools import cmp_to_key

class Job:
     
    def __init__(self, start, finish, profit):
         
        self.start = start
        self.finish = finish
        self.profit = profit

def jobComparator(s1, s2):
     
    return s1.finish < s2.finish
  
def latestNonConflict(arr, i):
     
    for j in range(i - 1, -1, -1):
        if arr[j].finish <= arr[i - 1].start:
            return j
             
    return -1

def findMaxProfitRec(arr, n):

    if n == 1:
        return arr[n - 1].profit
 
    inclProf = arr[n - 1].profit
    i = latestNonConflict(arr, n)
     
    if i != -1:
        inclProf += findMaxProfitRec(arr, i + 1)

    exclProf = findMaxProfitRec(arr, n - 1)
    return max(inclProf, exclProf)

def findMaxProfit(arr, n):
     
    arr = sorted(arr, key = cmp_to_key(jobComparator))
    return findMaxProfitRec(arr, n)
 
values = [ (3, 10, 20), (1, 2, 50),
           (6, 19, 100), (2, 100, 200) ]
arr = []
for i in values:
    arr.append(Job(i[0], i[1], i[2]))
     
n = len(arr)
 
print("The optimal profit is", findMaxProfit(arr, n))
