import * as fs from "fs";
import * as readline from "readline";
import { z } from "zod";
import { generateText } from "ai";
import { openai } from "@ai-sdk/openai";
import { tool } from "ai"
import { streamText } from "ai";

// Load books.json
const books = JSON.parse(fs.readFileSync("books.json", "utf8")).books;

// Setup CLI
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

console.log("📚 Book Recommendation Agent\n");


// TOOL DEFINITION
const getBookTool = {
  description: "Return a book from the dataset for a given genre",
  parameters: z.object({
    genre: z.string(),
  }),
  execute: async ({ genre }: { genre: string }) => {
    const matches = books.filter(
      (b: any) => b.subject.toLowerCase() === genre.toLowerCase()
    );

    if (matches.length === 0) {
      return { error: "No books found for that genre." };
    }

    const book = matches[Math.floor(Math.random() * matches.length)];
    return book;
  },
};


// LLM CLIENT
const client = openai(process.env.OPENAI_API_KEY || "sk-abcdef1234567890abcdef1234567890abcdef12");


// HANDLE USER INPUT
async function handleUserInput(input: string) {
  console.log("Agent: Thinking...\n");

  const { textStream } = await streamText({
    model: openai.chat("gpt-4o-mini"),
    prompt: input,
    tools: {
      getBookRecommendation: getBookTool,
    },
  });

  for await (const textPart of textStream) {
    process.stdout.write(textPart);
  }

  console.log("\n");
}


// CLI LOOP
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
