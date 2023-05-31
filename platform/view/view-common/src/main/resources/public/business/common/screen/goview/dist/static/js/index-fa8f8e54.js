import{aE as a,cP as u,aF as r,d as P,a7 as T,aD as l,Y as S,r as D,u as t,o as c,c as I,e as b,w as O,D as _,a2 as d}from"./index-c6cc3b14.js";import{l as v}from"./index-af8cb105.js";/* empty css                                                                       */import{S as g}from"./SettingItemBox-cf908047.js";import{c as h}from"./chartEditStore-3ee495ca.js";import{u as q}from"./useTargetData.hook-5f5f1f2c.js";var N=(e=>(e[e.NULL=0]="NULL",e[e.SUCCESS=1]="SUCCESS",e[e.FAILURE=2]="FAILURE",e))(N||{}),U=(e=>(e.FILTER="数据过滤",e.MAPPING="数据映射",e.CONTENT="数据内容",e))(U||{}),i=(e=>(e.STATIC="静态数据",e.AJAX="动态请求",e.Pond="公共接口",e))(i||{});const j=[{label:a.GET,value:a.GET,style:{color:"greenyellow",fontWeight:"bold"}},{label:a.POST,value:a.POST,style:{color:"skyblue",fontWeight:"bold"}},{label:a.PUT,value:a.PUT,style:{color:"goldenrod",fontWeight:"bold"}},{label:a.PATCH,value:a.PATCH,style:{color:"violet",fontWeight:"bold"}},{label:a.DELETE,value:a.DELETE,disabled:!0,style:{fontWeight:"bold"}}],B=[{label:u[r.SECOND],value:r.SECOND},{label:u[r.MINUTE],value:r.MINUTE},{label:u[r.HOUR],value:r.HOUR},{label:u[r.DAY],value:r.DAY}],L={key:0,class:"go-chart-configurations-data"},k=P({__name:"index",setup(e){const m=v(()=>T(()=>import("./index-abd05681.js"),["static/js/index-abd05681.js","static/js/index-9631b53b.js","static/js/index-c6cc3b14.js","static/css/index-9c2eb289.css","static/js/chartEditStore-3ee495ca.js","static/js/plugin-0dcb0bd6.js","static/js/icon-8314374d.js","static/js/useTargetData.hook-5f5f1f2c.js","static/js/EditorWorker-df02f0e7.js","static/js/editorWorker-43a98755.js","static/css/EditorWorker-97fea4eb.css","static/js/http-abbaebe7.js","static/js/fileTypeEnum-21359a08.js","static/css/index-b75b8879.css","static/js/index-af8cb105.js","static/css/index-83eadabc.css","static/js/SettingItemBox-cf908047.js","static/css/SettingItemBox-462ae6ed.css","static/css/index-4dd462f0.css"])),A=v(()=>T(()=>import("./index-3e0f9861.js"),["static/js/index-3e0f9861.js","static/js/index-c6cc3b14.js","static/css/index-9c2eb289.css","static/js/icon-8314374d.js","static/js/SettingItem-ec2fd044.js","static/css/SettingItemBox-462ae6ed.css","static/js/SettingItemBox-cf908047.js","static/js/chartEditStore-3ee495ca.js","static/js/plugin-0dcb0bd6.js","static/js/pondIndex.vue_vue_type_style_index_0_scoped_de860d6d_lang-5e6f8b0d.js","static/js/useTargetData.hook-5f5f1f2c.js","static/js/EditorWorker-df02f0e7.js","static/js/editorWorker-43a98755.js","static/css/EditorWorker-97fea4eb.css","static/css/pondIndex-61161fee.css","static/js/http-abbaebe7.js","static/js/index-9631b53b.js","static/js/fileTypeEnum-21359a08.js","static/css/index-b75b8879.css","static/js/index-af8cb105.js","static/css/index-83eadabc.css","static/css/index-2b79e72d.css"])),y=v(()=>T(()=>import("./index-184d248e.js"),["static/js/index-184d248e.js","static/js/index-c6cc3b14.js","static/css/index-9c2eb289.css","static/js/icon-8314374d.js","static/js/http-abbaebe7.js","static/js/SettingItemBox-cf908047.js","static/css/SettingItemBox-462ae6ed.css","static/js/chartEditStore-3ee495ca.js","static/js/plugin-0dcb0bd6.js","static/js/noData-9e194391.js","static/js/useTargetData.hook-5f5f1f2c.js","static/js/pondIndex.vue_vue_type_style_index_0_scoped_de860d6d_lang-5e6f8b0d.js","static/js/SettingItem-ec2fd044.js","static/js/EditorWorker-df02f0e7.js","static/js/editorWorker-43a98755.js","static/css/EditorWorker-97fea4eb.css","static/css/pondIndex-61161fee.css","static/js/lodash-c32566df.js","static/js/index-9631b53b.js","static/js/fileTypeEnum-21359a08.js","static/css/index-b75b8879.css","static/js/index-af8cb105.js","static/css/index-83eadabc.css","static/css/index-e14f9969.css"])),{targetData:o}=q(),C=[{label:i.STATIC,value:l.STATIC},{label:i.AJAX,value:l.AJAX},{label:i.Pond,value:l.Pond}],f=S(()=>{var p,s,n;return((p=o.value.chartConfig)==null?void 0:p.chartFrame)===h.STATIC||typeof((n=(s=o.value)==null?void 0:s.option)==null?void 0:n.dataset)=="undefined"});return(p,s)=>{const n=D("n-select");return t(o)?(c(),I("div",L,[b(t(g),{name:"请求方式",alone:!0},{default:O(()=>[b(n,{value:t(o).request.requestDataType,"onUpdate:value":s[0]||(s[0]=E=>t(o).request.requestDataType=E),disabled:f.value,options:C},null,8,["value","disabled"])]),_:1}),t(o).request.requestDataType===t(l).STATIC?(c(),_(t(m),{key:0})):d("",!0),t(o).request.requestDataType===t(l).AJAX?(c(),_(t(A),{key:1})):d("",!0),t(o).request.requestDataType===t(l).Pond?(c(),_(t(y),{key:2})):d("",!0)])):d("",!0)}}}),J=Object.freeze(Object.defineProperty({__proto__:null,default:k},Symbol.toStringTag,{value:"Module"}));export{N as D,U as T,j as a,J as i,B as s};
