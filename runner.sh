#! /bin/zsh

# brew install fswatch
fswatch -or --event=Updated ./src | xargs -n1 -I{} docker-compose restart insta_spring