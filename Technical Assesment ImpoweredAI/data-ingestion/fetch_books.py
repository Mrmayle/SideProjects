import requests
import json

SUBJECTS = ["science_fiction", "mystery", "fantasy", "thriller" ]

def fetch_subject(subject):
    url = f"https://openlibrary.org/subjects/{subject}.json?limit=25"
    response = requests.get(url)
    response.raise_for_status()
    data =response.json()
    return data.get("works", [])

def normalize_books(work, subject):
    title = work.get("title")
    authors = work.get("authors", [])
    author = authors[0]["name"] if authors else "Unknown"
    year = work.get("first_published_year")

    return{
        "title": title,
        "author": author,
        "first_published_year": year,
        "subject": subject
    }

def main():
    all_books = []

    for subject in SUBJECTS:
        works = fetch_subject(subject)
        for work in works:
            book = normalize_books(work, subject)
            all_books.append(book)


        output = {"books": all_books}

        with open("books.json", "w") as f:
            json.dump(output, f, indent=2)

        print("Saved books.json with", len(all_books), "books")

if __name__ == "__main__":
    main()

