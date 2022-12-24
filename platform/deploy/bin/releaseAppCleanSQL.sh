#!/bin/sh
cur_dir=$(cd `dirname $0`; pwd)
app_dir=$cur_dir/..
cd $cur_dir
sh backupAppDB.sh foxnic
sh runSql.sh $app_dir/backup/db/db.sql foxnic_demo
sh runSql.sh sql/cleardata.sql foxnic_demo
#sh runSql.sh sql/settingapp.sql foxnic_demo
sh backupAppDB.sh foxnic_demo
if [[ -f "/app/app/backup/db/db.sql" ]];then
  cd /app/app/backup/db
  tar zcvf db.tar.gz db.sql
  echo "build sql suces,file:/app/app/backup/db/db.tar.gz"
fi
exit 0
