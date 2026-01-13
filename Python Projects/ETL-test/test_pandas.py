"""
Docstring for etl_test with pandas
"""

import requests
import pandas as pd
from sqlalchemy import create_engine

def extract()->dict:
    API_URL = "http://universities.hipolabs.com/search?country=United+States"
    data = requests.get(API_URL).json()
    return data

def transform(data:dict) -> pd.DataFrame:                           #transforms the dataset into desired structure and filters
    df = pd.DataFrame(data)                                         # datafram is like a CSV with rows and columns within memory
    print(f"Total Number of universities from API {len(data)}")
    df = df[df["name"].str.contains("California")]                  #filtering by those that contain California 
    print(f"Number of universities in california {len(df)}")
    df['domain'] = [','.join(map(str, l)) for l in df['domains']]
    df['web_pages'] = [','.join(map(str, l)) for l in df['web_pages']]
    df = df.reset_index(drop=True)
    return df[["domains", "country", "web_pages", "name"]]

def load(df:pd.DataFrame) -> None:                                  #loads into sqllite database
    disk_engine = create_engine('sqlite://my_lite_store.db')
    df.to_sql('cal_uni', disk_engine, if_exists='replace')          #cal_uni is the table name, disk_engine is place, if exist replaces if needed


data = extract()
df = transform(data)
load(df)