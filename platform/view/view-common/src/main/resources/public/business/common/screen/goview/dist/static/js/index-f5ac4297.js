/* empty css                                                                       */import{d as _,r as c,o as p,D as y,w as o,e as a,u as n,c as x,F as I,s as V,E as w,f,U as h,a2 as H,h as D}from"./index-c6cc3b14.js";import"./chartEditStore-3ee495ca.js";import{S as v}from"./SettingItemBox-cf908047.js";import{i as U}from"./icon-8314374d.js";import{_ as $}from"./StylesSetting.vue_vue_type_script_setup_true_lang-d13d0eca.js";import{u as j}from"./useTargetData.hook-5f5f1f2c.js";import"./plugin-0dcb0bd6.js";import"./SettingItem-ec2fd044.js";import"./CollapseItem.vue_vue_type_script_setup_true_lang-81d1b4a3.js";const q=_({__name:"NameSetting",props:{chartConfig:{type:Object,required:!0}},setup(e){const t=e;let r="";const l=()=>{r=t.chartConfig.title},s=()=>{t.chartConfig.title.length||(window.$message.warning("请输入至少一个字符!"),t.chartConfig.title=r)};return(d,m)=>{const A=c("n-input");return p(),y(n(v),{name:"名称",alone:!0},{default:o(()=>[a(A,{type:"text",maxlength:"12",minlength:"1",placeholder:"请输入图表名称",size:"small",clearable:"","show-count":"",value:e.chartConfig.title,"onUpdate:value":m[0]||(m[0]=i=>e.chartConfig.title=i),onFocus:l,onBlur:s},null,8,["value"])]),_:1})}}}),E=_({__name:"PositionSetting",props:{canvasConfig:{type:Object,required:!0},chartAttr:{type:Object,required:!0}},setup(e){const t=e,{AlignHorizontalLeftIcon:r,AlignVerticalCenterIcon:l,AlignVerticalTopIcon:s,AlignHorizontalCenterIcon:d,AlignHorizontalRightIcon:m,AlignVerticalBottomIcon:A}=U.carbon,i=[{key:"AlignHorizontalLeftIcon",lable:"局左",icon:h(r)},{key:"AlignVerticalCenterIcon",lable:"X轴居中",icon:h(l)},{key:"AlignHorizontalRightIcon",lable:"局右",icon:h(m)},{key:"AlignVerticalTopIcon",lable:"顶部",icon:h(s)},{key:"AlignHorizontalCenterIcon",lable:"Y轴居中",icon:h(d)},{key:"AlignVerticalBottomIcon",lable:"底部",icon:h(A)}],z=C=>{switch(C){case i[0].key:t.chartAttr.x=0;break;case i[1].key:t.chartAttr.y=(t.canvasConfig.height-t.chartAttr.h)/2;break;case i[2].key:t.chartAttr.x=t.canvasConfig.width-t.chartAttr.w;break;case i[3].key:t.chartAttr.y=0;break;case i[4].key:t.chartAttr.x=(t.canvasConfig.width-t.chartAttr.w)/2;break;case i[5].key:t.chartAttr.y=t.canvasConfig.height-t.chartAttr.h;break}};return(C,g)=>{const B=c("n-divider"),G=c("n-button"),S=c("n-space"),b=c("n-text"),k=c("n-input-number");return p(),x(I,null,[a(B,{style:{margin:"10px 0"}}),a(S,{size:8,justify:"space-between",style:{"margin-top":"10px"}},{default:o(()=>[(p(),x(I,null,V(i,u=>a(G,{secondary:"",key:u.key,onClick:O=>z(u.key)},{icon:o(()=>[(p(),y(w(u.icon)))]),_:2},1032,["onClick"])),64))]),_:1}),a(n(v),{name:"位置"},{default:o(()=>[a(k,{value:e.chartAttr.y,"onUpdate:value":g[0]||(g[0]=u=>e.chartAttr.y=u),min:0,size:"small",placeholder:"px"},{prefix:o(()=>[a(b,{depth:"3"},{default:o(()=>[f("上")]),_:1})]),_:1},8,["value"]),a(k,{value:e.chartAttr.x,"onUpdate:value":g[1]||(g[1]=u=>e.chartAttr.x=u),min:0,size:"small",placeholder:"px"},{prefix:o(()=>[a(b,{depth:"3"},{default:o(()=>[f("左")]),_:1})]),_:1},8,["value"])]),_:1})],64)}}}),F=_({__name:"SizeSetting",props:{chartAttr:{type:Object,required:!0},isGroup:{type:Boolean,required:!1}},setup(e){return(t,r)=>{const l=c("n-text"),s=c("n-input-number");return p(),y(n(v),{name:"尺寸"},{default:o(()=>[a(s,{value:e.chartAttr.w,"onUpdate:value":r[0]||(r[0]=d=>e.chartAttr.w=d),min:50,disabled:e.isGroup,size:"small",placeholder:"px"},{prefix:o(()=>[a(l,{depth:"3"},{default:o(()=>[f("宽度")]),_:1})]),_:1},8,["value","disabled"]),a(s,{value:e.chartAttr.h,"onUpdate:value":r[1]||(r[1]=d=>e.chartAttr.h=d),min:50,disabled:e.isGroup,size:"small",placeholder:"px"},{prefix:o(()=>[a(l,{depth:"3"},{default:o(()=>[f("高度")]),_:1})]),_:1},8,["value","disabled"])]),_:1})}}}),L={key:0,class:"go-chart-configurations-setting"},N=_({__name:"index",setup(e){const{targetData:t,chartEditStore:r}=j();return(l,s)=>n(t)?(p(),x("div",L,[a(n(q),{chartConfig:n(t).chartConfig},null,8,["chartConfig"]),a(n(F),{isGroup:n(t).isGroup,chartAttr:n(t).attr},null,8,["isGroup","chartAttr"]),a(n(E),{chartAttr:n(t).attr,canvasConfig:n(r).getEditCanvasConfig},null,8,["chartAttr","canvasConfig"]),a(n($),{isGroup:n(t).isGroup,chartStyles:n(t).styles},null,8,["isGroup","chartStyles"]),(p(),y(w(n(t).chartConfig.conKey),{optionData:n(t).option},null,8,["optionData"]))])):H("",!0)}});const Z=D(N,[["__scopeId","data-v-246c8460"]]);export{Z as default};
