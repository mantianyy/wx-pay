#!/bin/bash

#rm -rf /deploy-jar/post.log  >> /deploy-jar/post.log

#echo "1. docker's image [pay-integration] build successfully！" >> /deploy-jar/post.log

docker stop pay-integration  >> /deploy-jar/post.log

echo "1. docker's image [pay-integration] build successfully！" >> /deploy-jar/post.log

docker rm -f pay-integration  >> /deploy-jar/post.log

echo "2. docker's image [pay-integration] build successfully！" >> /deploy-jar/post.log

docker rmi pay-integration  >> /deploy-jar/post.log

echo "3. docker's image [pay-integration] build successfully！" >> /deploy-jar/post.log

docker build -t pay-integration  . >> /deploy-jar/post.log

docker rm -f  pay-integration >> /deploy-jar/post.log

echo "3.1 docker'image [pay-integration] delete successfully！ "

echo "4. docker's image [pay-integration] build successfully！" >> /deploy-jar/post.log

docker run -d -p 8089:8089 --name pay-integration  -v /var/log/:/var/log pay-integration  >> /deploy-jar/post.log

echo "5. docker's image [pay-integration] deploy successfully! " >> /deploy-jar/post.log

rm -rf Dockerfile >> /deploy-jar/post.log

echo "6. old Dockerfile delete successfully! " >> /deploy-jar/post.log

rm -rf *.jar >> /deploy-jar/post.log

echo "7. old jar/ delete successfully! " >> /deploy-jar/post.log