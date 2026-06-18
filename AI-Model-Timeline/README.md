# Frontier AI Model Timeline

This note tracks the AI model timeline shown in the root README. It is a study reference, not a legal or safety policy source.

## Timeline Snapshot

| Year | Models |
|---|---|
| 2020 | GPT-3, T5, GShard |
| 2021 | Jurassic-1, Codex, LaMDA, MT-NLG |
| 2022 | Chinchilla, PaLM, OPT, BLOOM, InstructGPT, ChatGPT 3.5 |
| 2023 | LLaMA 1, GPT-4, Claude 1, PaLM 2, Claude 2, Llama 2, Mistral 7B, GPT-4 Turbo, Gemini 1.0, Mixtral 8x7B |
| 2024 | Gemini 1.5, Claude 3, DBRX, Llama 3, GPT-4o, Claude 3.5, Mistral Large 2, Grok-2, OpenAI o1, Llama 3.2, Gemini 2.0 |
| 2025 | DeepSeek-R1, GPT-4.5, Claude 3.7, Gemini 2.5 Pro, Llama 4, GPT-5, Grok 4, Claude Opus 4.5, GPT-5.1, GPT-5.2, Gemini 3 Pro, DeepSeek V3.2 |
| 2026 Jan-Jun | GPT-5.3 Codex, GPT-5.4, Gemini 3.1 Pro, Claude Opus 4.6, Claude Opus 4.7, DeepSeek V4, GPT-5.5 Max 60, GPT-5.5 High 59, Claude 4.7 Max 57 |

## Restricted / Banned Watchlist

| Model | Provider | Status | Why It Matters |
|---|---|---|---|
| Claude Mythos / Mythos 5 | Anthropic | Reported restricted or disabled | Reports described government pressure to revoke access because of national-security and export-control concerns. |
| Fable 5 | Anthropic | Reported restricted or disabled | Reports described access being pulled after concerns about jailbreak vulnerabilities and possible access to stronger cyber capabilities. |

Important: the word "banned" can mean different things in AI news: export controlled, unavailable to foreign nationals, taken offline by the provider, unavailable to public users, or blocked by a platform. This repo records the practical status as `restricted or disabled` unless a primary legal order is available.

## AI Concepts To Know

| Concept | Short Meaning | Interview / Engineering Use |
|---|---|---|
| LLM | Large language model trained to predict and generate text-like tokens. | Prompting, reasoning, coding, summarization, chat assistants. |
| Transformer | Neural architecture based on attention. | Foundation of most modern language and multimodal models. |
| Token | Unit of text processed by the model. | Impacts context length, cost, latency, and truncation. |
| Context window | Maximum input plus output tokens a model can attend to. | Determines how much code, logs, or documents can fit in one request. |
| Fine-tuning | Additional training on task-specific examples. | Improves style, classification, extraction, and domain behavior. |
| RAG | Retrieval-augmented generation. | Ground model answers in external docs, databases, or search results. |
| Agents | Model-driven systems that plan and call tools. | Useful for coding, browsing, data workflows, and multi-step automation. |
| Multimodal AI | Models that handle text, images, audio, video, or files together. | Useful for UI inspection, OCR, diagrams, and richer assistants. |
| Reasoning model | Model optimized to spend more compute on step-by-step problem solving. | Useful for math, code, planning, and difficult debugging. |
| Red teaming | Testing models against misuse, jailbreaks, and safety failures. | Helps evaluate deployment risk before broad release. |
| Alignment | Techniques that steer models toward helpful and safe behavior. | Core to model policy, reliability, and user trust. |
| Evaluation | Benchmarking model quality, safety, cost, and latency. | Helps pick the right model for production tasks. |

## Public AI Sources

| Source | Link |
|---|---|
| OpenAI | https://openai.com/news/ |
| Anthropic | https://www.anthropic.com/news |
| Google DeepMind | https://deepmind.google/discover/blog/ |
| Meta AI | https://ai.meta.com/blog/ |
| Mistral AI | https://mistral.ai/news/ |
| DeepSeek | https://www.deepseek.com/ |
| xAI | https://x.ai/news |
| Artificial Analysis Intelligence Index | https://artificialanalysis.ai/ |
| WIRED report on Mythos / Fable access restrictions | https://www.wired.com/story/sk-telecom-anthropic-mythos-export-controls/ |
| The Verge report on Anthropic export-control restrictions | https://www.theverge.com/ai-artificial-intelligence/951703/anthropic-shutdown-export-controls |

## Maintenance Rule

Update this page whenever the root README AI timeline changes. For unstable model-ranking numbers, prefer a dated source note instead of silently overwriting old values.
