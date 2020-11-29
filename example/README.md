# Example Server

This is simply a test application to show how a server supporting RMI and JGroups communication can run in Docker.

## Build

To build the test application, simply run the following command from this `example` directory:

```bash
docker build --rm -f Dockerfile -t scc311:jgroups-demo .
```

## Run

This can differ depending on how you will use Docker networks. On most systems, life will be easier here with `--network host`, but some Windows 10 systems may not like this.

```bash
docker run --rm -it --network host scc311:jgroups-demo
```

There is no client for this, but the is very little code so it should be easy to see what is going on.
