"""
Docstring for etl_test 3
"""
from prefect import task, flow 
import requests

@task(retries=2)
def extract_data():
    url = "https://jsonplaceholder.typicode.com/posts"
    response = requests.get(url)
    data = response.json()
    return data
    
@task
def transform_data(data):
    transformed_data = [item['title'] for item in data]
    return transformed_data
 
@task
def load_data(transformed_data):
    for title in (transformed_data)[:5]: # just prints the first 5
        print(f"Loading title: {title}")

@flow
def etl_flow(name="ETL flow for testing"):
    data = extract_data()
    transformed_data = transform_data(data)
    load_data(transformed_data)

if __name__ == "__main__":
    etl_flow()
