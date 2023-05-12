curl --location --request POST 'http://localhost:8080/api/v1/author/add' \
--header 'Content-Type: application/json' \
--data-raw '{
    "authorName": "Dzung"
}'

curl --location --request GET 'http://localhost:8080/api/v1/author/1'

curl --location --request POST 'http://localhost:8080/api/v1/category/add' \
--header 'Content-Type: application/json' \
--data-raw '{
    "categoryName": "Java"
}'

curl --location --request GET 'http://localhost:8080/api/v1/category/1'

curl --location --request POST 'http://localhost:8080/api/v1/book/add' \
--header 'Content-Type: application/json' \
--data-raw '{
    "releaseTime": "2021-02-06T16:19:44.184",
    "releaseDate": "2021-02-06",
    "price": 200000,
    "authorId": 1,
    "bookName": "Kotlin In Action",
    "categoryId": 1
}'

curl --location --request POST 'http://localhost:8080/api/v1/book/add' \
--header 'Content-Type: application/json' \
--data-raw '{
    "releaseTime": "2021-02-06T16:19:44.184",
    "releaseDate": "2021-02-06",
    "price": 200000,
    "authorId": 1,
    "bookName": "Java In Action",
    "categoryId": 1
}'

curl --location --request POST 'http://localhost:8080/api/v1/book/add/1000'

curl --location --request GET 'http://localhost:8080/api/v1/book/list'

curl --location --request GET 'http://localhost:8080/api/v1/book/list-by-offset?offset=15990&limit=10'

curl --location --request GET 'http://localhost:8080/api/v1/book/list-by-cursor?next_page_token=1742160938:15095&limit=10'

curl --location --request GET 'http://localhost:8080/api/v1/books/1'

curl --location --request GET 'http://localhost:8080/api/v1/books?size=30'

curl --location --request GET 'http://localhost:8080/api/v1/books?size=30&lower_than=Kotlin'

curl --location --request GET 'http://localhost:8080/api/v1/books?size=30&upper_than=Java'