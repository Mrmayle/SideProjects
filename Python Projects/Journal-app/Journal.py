import datetime
import os

JOURNAL_DIR = "journal_entrires"

def create_journal_directory():
    if not os.path.exists(JOURNAL_DIR):
        os.makedirs(JOURNAL_DIR)
        
def get_today_filename():
    today = datetime.date.today().isoformat()
    return os.path.join(JOURNAL_DIR, f"{today}.txt")

def write_entry(entry_text):
    filename = get_today_filename()
    with open(filename,"a", encoding="utf-8") as file:
        timestamp = datetime.datetime.now().strftime("%H:%M:%S")
        file.write(f"[{timestamp}] {entry_text}\n")
        
def read_today_entries():
    filename = get_today_filename()
    if not os.path.exists(filename):
        print("No enteries for today yet.") 
        return
    with open(filename, "r",encoding="utf-8") as file:
        content = file.read()
        print("\n---Today's Entries---")
        print(content)
        
def display_menu():
    print("\nDaily Journal Menu:")
    print("1. Add new entry")
    print("2. View today's entries")
    print("3. Exit")
    
def run_journal_app():
    create_journal_directory()
    while True:
        display_menu()
        choice = input("Choosen an option (1/2/3): ")
        
        if choice == '1':
            entry = input("Wrtie your entry: ")
            write_entry(entry)
            print("Entry Saved!") 
        elif choice == '2':
            read_today_entries
        elif choice == '3':
            print("Goodbye! Have a reflective day!")
            break
        else:
            print("Invalid choice. Please try again.")
            
if __name__ == "__main__":
    run_journal_app()
    
    