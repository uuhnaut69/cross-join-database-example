# Predicate PushDown Practice
![Travis CI](https://travis-ci.com/uuhnaut69/predicate-pushdown.svg?branch=master)

# Notes

- Issues in real world:

  - Network traffic (sending data over the network)
    
  - I/O ( reading disk files are bounded to disk reading speed)
    
  - Time (product SLA goals — processing time agreed upon)

- Predicate Pushdown gets its name from the fact that portions of SQL statements, ones that filter data, are referred to as predicates. 

- It can improve query performance by reducing the amount of data read (I/O) from Storage files. 
  
  
# Examples

1. Installation mysql && enable mysql bin-log

2. Go to directory & run command for generate some dummy data

```bash
cd dummy-data/
sh dummy-data.sh
```

3. Add some reviews of product:

```http request
[POST] localhost:8080/products/1/reviews

{
    "reviewContent": "review test",
    "author": "author test",
    "rating": 4
}

```

4. Query example 1:

```sql
 SELECT * FROM mysql.product A JOIN mongodb.review B
 ON A.id = B.productId
 WHERE A.price > price_param AND B.rating > rating_param
```

Split above query in 2 path:

```sql
select * from mysql.product A where A.price > price
```

```bash
db.review.find({rating: {$gt: rating_param}})
```

Make a join 2 result set using CqEngine to get final query result

# Note Reference
[Predicate PushDown](https://medium.com/microsoftazure/data-at-scale-learn-how-predicate-pushdown-will-save-you-money-7063b80878d7) 