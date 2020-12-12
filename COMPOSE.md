# Using Docker Compose

Using docker compose can speed up the process of building and deploying tightly coupled container groups (such as you servers for 311). This will also make it far easier to scale your backend servers. Docker compose is a separate app to Docker, but on Mac and Windows, will be installed with Docker by default (on Linux, you can download it separately).

To build all of the containers specified within the `docker-compose.yml` file, just run the following command from the same directory as the compose file:
```bash
docker-compose build
```
> You must do this every time you want to rebuild your code!

Then, to launch your container group, just run:
```bash
docker-compose up -d --scale backend=5
```
> The `--scale` here will create 5 instances of `backend`

To see a list of created containers, you can just use the regular Docker CLI (`docker ps`).

To see the output from the containers, run:
```bash
docker-compose logs -f
```
> `Ctrl-C` will stop you seeing the output

To scale a container to a set number of instances, run:
```bash
docker-compose scale backend=3
```
> `3` can be any +ve integer, here this will stop 2 of the original 5 backends

To stop and remove all the containers, run:
```bash
docker-compose down
```

---

## Demo

![demo](./readme-assets/compose-example.gif)