var l=Object.defineProperty,f=Object.defineProperties;var c=Object.getOwnPropertyDescriptors;var a=Object.getOwnPropertySymbols;var g=Object.prototype.hasOwnProperty,C=Object.prototype.propertyIsEnumerable;var p=(t,o,i)=>o in t?l(t,o,{enumerable:!0,configurable:!0,writable:!0,value:i}):t[o]=i,m=(t,o)=>{for(var i in o||(o={}))g.call(o,i)&&p(t,i,o[i]);if(a)for(var i of a(o))C.call(o,i)&&p(t,i,o[i]);return t},e=(t,o)=>f(t,c(o));var r=(t,o,i)=>(p(t,typeof o!="symbol"?o+"":o,i),i);import{aJ as _,a5 as n}from"./index-c6cc3b14.js";import{d}from"./chartEditStore-3ee495ca.js";import{h as s}from"./index-68969097.js";import"./plugin-0dcb0bd6.js";import"./icon-8314374d.js";import"./SettingItem-ec2fd044.js";/* empty css                                                                       */import"./SettingItemBox-cf908047.js";import"./CollapseItem.vue_vue_type_script_setup_true_lang-81d1b4a3.js";import"./index.esm.min-4d91f069.js";import"./http-abbaebe7.js";import"./lodash-c32566df.js";import"./fileTypeEnum-21359a08.js";const h={color_type:1,o_color:"#0a7ae2",i_color:"#119bfa",line_class:"svg_ani_flow"};class q extends d{constructor(){super(...arguments);r(this,"key",s.key);r(this,"attr",e(m({},_),{w:15,h:500,zIndex:-1}));r(this,"chartConfig",n(s));r(this,"option",n(h))}}export{q as default,h as option};