let a = [1,2,8,10,3];

for(let i = 0; i < a.length -1; i++) {
  for(j = i+1; j < a.length; j++) {
    if(a[i] > aj[j]) {
      let t = a[i];
      a[i] = a[j]
      a[j] = t;
    }
  }
}

// sorted array
console.log('a :: ', a);