# Docker JGroups

This repo contains the Dockerfile that can create a JGroups Docker image. This image can then be used as a base for your coursework to run in.

## Building

To build the base image, just run:

```bash
docker build --rm -f Dockerfile -t jgroups:base .
```

(You can set the JGroups version with `--build-arg JGROUPS_VERSION='version.number'` too)


## Usage

Then future dockerfiles can use:
```docker
FROM jgroups:base
```
and both Java 8 and JGroups will already be there.

For convenience, the image is also prebuilt and you can just use `ghcr.io/scc311/jgroups:v3.6.20`

See [here](https://github.com/orgs/scc311/packages/container/jgroups) for supported version images!

## Improve Me

Feel free to submit a PR if you fix any issues with this 🙂
