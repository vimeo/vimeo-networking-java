# Vimeo-Android Git Hooks

## Enabling

To enable these hooks, run:

```bash
git config core.hooksPath .githooks
```

## Notes

To make commits without the pre-commit hooks running, use the `-n` (`--no-verify`) flag.

eg:
```bash
git commit -n -m "my commit"
```

