"""
Docstring for etl_test 2
"""
from prefect import task, flow 

@task
def say_hello():
    print("Hello, World")

@task
def say_prefect():
    print("Its a Prefect World")

@flow
def my_flow():
    say_hello()
    say_prefect()

if __name__ == "__main__":
    my_flow()
