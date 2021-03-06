# JGroups [+ Docker 🐳]

This is to be used for SCC311 - Coursework 3 if using JGroups!

This repo contains the Dockerfile that can create a JGroups Docker image. This image can then be used as a base for your coursework to build and run in.

Also, you'll find here an example of how to use the JGroups RPC Dispatcher! Please try to use Docker to run it.

---

## Using as a Base Image 🐳

To use a base image, simply add this to the top of your Dockerfile:

```docker
FROM ghcr.io/scc311/jgroups:v3.6.20
```
> Tags `v4.2.4` and `v5.0.0` are also available (all use Java 8)

Any code you now compile in the Dockerfile or specify to run on entry in the built container can use both the standard java libraries and jgroups with no need to alter the classpath.

The images can be found on the Github Container Registry [here](https://github.com/orgs/scc311/packages/container/jgroups).

---
All prebuilt images are available on the following platforms 💻:
 - amd64 (x86_64)
 - i386 (x86)
 - arm64/v8
 - arm/v7
 - arm/v6
---

## Building Your Own 🔨

You can build your own version of the base image too! Make any changes to the Dockerfile and just run a docker build:

```bash
docker build --rm -f Dockerfile --build-arg JGROUPS_VERSION=3.6.20 -t jgroups:v3.6.20 .
```

> Feel free to set the JGroups version by changing the build argument (and the tag too)

---

## Example Usage

This contains a simple frontend server and backend server. The frontend server binds to an RMI Registry & it also joins the JGroups Channel. In the example code, the channel name is set via the environment variable `CHANNEL`. This env var is set with the flag `-e CHANNEL=<channel_name>` when running a container.

The backend server in this example only connects to the JGroups Channel, so it can only be contacted by the frontend server, not any clients.

As JGroups does a lot with networking, for this, just use the flag `--network host` when running containers so that all containers just bind to the host machines network, much they would not running in docker. They will also need to have the `--privileged` flag.

> There are ways to not use the host network, see the compose file

To run this, just build both the frontend example and backed example Docker images, then create a single instance of the frontend example and as many instances you like of the backend example. You should be starting the frontend and backends with a terminal (`-t`).

If you need any more help with Docker, have a look at the Docker Tutorial [here](https://github.com/scc311/docker-tutorial).

**To use Docker Compose with this example, see [here](./COMPOSE.md)**

---

## CI [Github Actions] 🚀

This project uses Github Actions to automatically build the container images for all the specified architectures and push them to the container registry. See how that works in the workflow file [here](./.github/workflows/docker-rolling.yml).

---

## Improve Me ⚙️

If you notice an problem with the code in this repository and don't know how to fix it, submit an issue via Github. Otherwise, if you want to fix something yourself (or improve something), submit a pull request via Github! 🙂
