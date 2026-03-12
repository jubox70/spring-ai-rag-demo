# spring-ai-rag-demo

Spring AI + RAG demo with a lightweight hexagonal architecture.

## Architecture

- `domain`: core models (`ChatProvider`, knowledge entries)
- `application`: use cases and ports
- `adapter/in/web`: REST controllers and HTTP exception mapping
- `adapter/out/ai`: Spring AI chat integration
- `adapter/out/vectorstore`: PgVector integration
- `config`: Spring bean configuration

## API

- `GET /api/chat/{provider}?question=...`
- `GET /api/chat/{provider}/knowledge?question=...`
- `POST /api/knowledge/ingest`

Supported `{provider}` values:

- `ollama`
- `openai`
- `anthropic`

## Configuration

Use environment variables for credentials:

- `OPENAI_API_KEY`
- `ANTHROPIC_API_KEY`
- `DB_USERNAME`
- `DB_PASSWORD`

## Run

Start PostgreSQL + PgVector and your model providers, then run with Maven.
