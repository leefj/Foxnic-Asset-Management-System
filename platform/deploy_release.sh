#!/bin/sh

release_dir="/tmp/app_deploy"
method="local"
hostname=""
if [[ -n "$1" ]];then
  hostname=$1
  method="ops"
  release_dir="/root/workspace/eam_deploy/out"
  mkdir -p /root/workspace/eam_deploy/out
else
  hostname=`hostname`
fi
echo "hostname:$hostname"


cur_dir=$(cd `dirname $0`; pwd)
conf_file=$cur_dir/app.conf
jar_dir=`cat $conf_file|grep ${hostname}.jar_dir|awk -F "=" '{print $2}'`
bpm_jar_dir=`cat $conf_file|grep ${hostname}.bpm_jar_dir|awk -F "=" '{print $2}'`
deploy_dir=`cat $conf_file|grep ${hostname}.deploy_dir|awk -F "=" '{print $2}'`
if [[ -d $release_dir ]];then
  rm -rf $release_dir
fi
mkdir -p $release_dir
echo "jar_dir:$jar_dir"
echo "deploy_dir:$deploy_dir"
echo "conf_file:$conf_file"
echo "release_dir:$release_dir"
cd $release_dir
cp -r $deploy_dir/* .

version=2.5.0
# process app.jar
echo "$release_dir/app/app/lib/"
mkdir -p $release_dir/app/app/lib/
ls -rtl $jar_dir/lib/
cp -r $jar_dir/lib/* $release_dir/app/app/lib/
cp $jar_dir/wrapper-all-${version}.jar  $release_dir/app/app/app.jar

# process bpm.jar
#cp -r $jar_dir/lib/* $release_dir/app/bpm/lib/
ls $bpm_jar_dir/wrapper-camunda-${version}.RELEASE.jar
ls $release_dir/app/bpm
cp $bpm_jar_dir/wrapper-camunda-${version}.jar $release_dir/app/bpm/bpm.jar
echo "start to ys"
tar -zcvf app_release_last.tar.gz ./*
if [[ $method == "local" ]];then
  open .
fi


#upload

exit 0





