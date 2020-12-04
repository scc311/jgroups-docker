# Docker JGroups

This repo contains the Dockerfile that can create a JGroups Docker image. This image can then be used as a base for your coursework to run in.

Also, you'll find here an example of how to use the JGroups RPC Dispatcher! Please try to use Docker to run it.

## Using as a Base Image 🐳

To use a base image, simply add this to the top of your Dockerfile:

```docker
FROM ghcr.io/scc311/jgroups:v3.6.20
```
> Tags `v4.2.4` and `5.0.0` are also available

The images can be found on the Github Container Registry [here](https://github.com/orgs/scc311/packages/container/jgroups).

## Building Your Own 🔨

You can build your own version of the base image too! Make any change to the Dockerfile and just run build.

```bash
docker build --rm -f Dockerfile --build-arg JGROUPS_VERSION=3.6.20 -t jgroups:v3.6.20 .
```

> Feel free to set the JGroups version by changing the build argument (and the tag too)

## Example Usage

This contains a simple frontend server and backend server. The frontend server binds to an RMI Registry & it also joins the JGroups Channel. In the example code, the channel name is set via the environment variable `CHANNEL`. This env var is set with the flag `-e CHANNEL=<channel_name>` when running a container.

The backend server in this example only connects to the JGroups Channel, so it can only be contacted by the frontend server, not any clients.

As JGroups does a lot with networking, for this, just use the flag `--network host` when running containers so that all containers just bind to the host machines network, much they would not running in docker. They will also need to have the `--privileged` flag.

To run this, just build both the frontend example and backed example Docker images, then create a single instance of the frontend example, and as many instances you like of the backend example. You should be starting the frontend with an interactive terminal (`-it`) and the backends with a non-interactive terminal (`-t`).

If you need any more help with Docker, have a look at the Docker Tutorial [here](https://github.com/scc311/docker-tutorial).

## Improve Me

Feel free to submit a PR if you fix any issues with this 🙂
