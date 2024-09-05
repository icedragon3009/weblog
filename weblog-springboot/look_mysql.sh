#!/bin/bash

CONTAINER_ID="44ced7133921"  # 替换为你的容器ID
LOG_DIR="/data/docker/file/mysql_log"  # 替换为你想保存日志的目录路径

while true; do
    # 获取当前日期
    CURRENT_DATE=$(date "+%Y-%m-%d")
    OUTPUT_FILE="$LOG_DIR/mysql_stats_$CURRENT_DATE.log"  # 每天生成一个新的日志文件

    # 如果日志文件不存在，则添加表头
    if [ ! -f "$OUTPUT_FILE" ]; then
        echo -e "Timestamp\tContainer Name\tCPU Usage\tMemory Usage" > $OUTPUT_FILE
    fi

    if docker ps -q --filter "id=$CONTAINER_ID" | grep -q .; then
        # 容器正在运行，记录资源使用情况
        TIMESTAMP=$(date "+%Y-%m-%d %H:%M:%S")  # 获取当前时间戳
        STATS=$(docker stats --no-stream --format "{{.Name}}\t{{.CPUPerc}}\t{{.MemUsage}}" $CONTAINER_ID)
        echo -e "$TIMESTAMP\t$STATS" >> $OUTPUT_FILE
    else
        # 容器已经停止，记录停止时间并退出循环
        echo -e "$(date "+%Y-%m-%d %H:%M:%S")\tContainer $CONTAINER_ID stopped" >> $OUTPUT_FILE
        break
    fi
    sleep 10  # 每10秒钟检查一次
done
