APP_NAME="transactions-routine"

echo "Building $APP_NAME"
docker build -t $APP_NAME .

if [ $? -eq 0 ]; then
    echo "Success. Restarting container..."
    docker stop $APP_NAME 2>/dev/null || true
    docker rm $APP_NAME 2>/dev/null || true
    docker run -d -p 8080:8080 --name $APP_NAME $APP_NAME
    docker logs -f $APP_NAME
else
    echo "Build failed."
    exit 1
fi