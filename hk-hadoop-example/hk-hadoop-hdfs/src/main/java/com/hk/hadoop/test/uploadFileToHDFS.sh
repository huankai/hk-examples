#!/bin/bash

#set java env
export JAVA_HOME=/usr/java/default
export JAR_HOME=${JAVA_HOME}/jre
export CLASSPATH=.:${JAVA_HOME}/bin:${JAVA_HOME}/lib
export PATH=${JAVA_HOME}/bin:$PATH

# SET HADOOP ENV
export HADOOP_HOME=/usr/local/hadoop
export PATH=${HADOOP_HOME}/bin:${HADOOP_HOME}/sbin:$PATH

# log storage dir
log_src_dir=/var/logs/log/

# 待上传文件存储目录
log_toupload_dir=/var/logs/toupload/

#日志文件上传到 hadoop 根目录
date1=`date -d last-day +%Y_%m_%d`
hdfs_root_dir=/data/clickLog/$date1

#echo  打印环境变量信息
echo "envs: hadoop_home -> $HADOOP_HOME"

#读取日志文件目录，判断是否有需要上传的文件
echo "log_src_dir -> $log_src_dir"

ls $log_src_dir | while read fileName
do
    if [["$fileName" == access.log.*]]; then # 文件名是以access.log开头
        date=`date +%Y_%m_%d_%H_%M_%S`
        # 打印信息
        echo "moving $log_src_dir$fileName To $log_toupload_dir"xxxxx_click_log_$fileName"$date"

        #将文件移动到待上传目录并重命名
        mv $log_src_dir$fileName  $log_toupload_dir"xxxxx_click_log_$fileName"$date

        #将待上传的文件写入到一个列表文件 willDoing
        echo $log_toupload_dir"xxxxx_click_log_$fileName"$date >> $log_toupload_dir"willDoing.$fileName"$date
    fi
done

#找到列表文件
ls $log_toupload_dir | grep will | grep -v "_COPY_" | grep -v "_DONE_" | while read line
do
    echo "toupload is in file :"$line

    #将待上传文件的列表改为wilDoing_COPY_
    mv $log_toupload_dir$line $log_toupload_dir$line"_COPY_"
    cat $log_toupload_dir$line"_COPY_" | while read line
    do
       echo "puting ...$line to hdfs path ... $hdfs_root_dir"
       hadoop fs -mkdir -p $hdfs_root_dir #创建 hdfs目录
       hadoop fs -put $line $hdfs_root_dir  # 上传文件
    done
    mv $log_toupload_dir"_COPY_" $log_toupload_dir"_DONE_"

done



