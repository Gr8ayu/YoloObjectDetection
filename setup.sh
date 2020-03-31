echo ">>>>>>>>>> Downloading dependencies <<<<<<<<<"

pip3 install -r requirement.txt 

echo ">>>>>>>>>> Checking for weights <<<<<<<<<"

if [ ! -f yolov3.weights ]; then
    echo "Downloading weights"
    wget https://pjreddie.com/media/files/yolov3.weights
fi

if [  -f yolov3.weights ]; then
    echo "weights already available"
    
fi

echo ">>>>>>>>>> Compiling Source code <<<<<<<<<"
javac Main2.java
echo ">>>>>>>>>> Executing Program <<<<<<<<<"
java Main2

