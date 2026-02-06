import fs from "fs"
import readline from "readline";

const books = JSON.parse(fs.readFileSync("books.json", "utf8")).books;

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
})

console.log("Book Recommendation Agent\n")

async function handleUserInput(input: string) { 
    console.log("Agent: You said →", input); 
}

function search() {
    rl.question("You: ", async (input) => {
        if (input.toLowerCase() === "exit") {
            rl.close();
            return;
        }

        await handleUserInput(input);
        search();
    });
}

search();


import { z } from "zod";

const getBookTool = {
  name: "getBookRecommendation",
  description: "Return a book from the dataset for a given genre",
  parameters: z.object({
    genre: z.string()
  }),
  execute: ({ genre }) => {
    const matches = books.filter(
      (b: any) => b.subject.toLowerCase() === genre.toLowerCase()
    );

    if (matches.length === 0) {
      return { error: "No books found for that genre." };
    }

    const book = matches[Math.floor(Math.random() * matches.length)];
    return book;
  }
};

import { generateText } from "ai";
import { openai } from "@ai-sdk/openai";

const client = openai("YOUR_OPENAI_API_KEY");











async function handleUserInput(input: string) {
  console.log("Agent: Thinking...\n");

  const response = await generateText({
    model: client("gpt-4o-mini"), // or any model you prefer
    prompt: input,
    tools: {
      getBookRecommendation: getBookTool
    },
    stream: true
  });

  for await (const textPart of response.textStream) {
    process.stdout.write(textPart);
  }

  console.log("\n");
}
