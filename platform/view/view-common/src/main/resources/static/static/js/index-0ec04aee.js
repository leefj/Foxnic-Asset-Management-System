      38, ${l(i)-11} 41, ${l(i)-8} 73, ${l(i)-8} 75, ${l(i)-10} 81, ${l(i)-10}
      85, ${l(i)-6} ${l(n)-85}, ${l(i)-6} ${l(n)-81}, ${l(i)-10} ${l(n)-75}, ${l(i)-10}
      ${l(n)-73}, ${l(i)-8} ${l(n)-41}, ${l(i)-8} ${l(n)-38}, ${l(i)-11}
      ${l(n)-24}, ${l(i)-11} ${l(n)-13}, ${l(i)-21} ${l(n)-13}, ${l(i)-24}
      ${l(n)-10}, ${l(i)-27} ${l(n)-10}, 27 ${l(n)-13}, 25 ${l(n)-13}, 21
      ${l(n)-24}, 11 ${l(n)-38}, 11 ${l(n)-41}, 8 ${l(n)-73}, 8 ${l(n)-75}, 10
      ${l(n)-81}, 10 ${l(n)-85}, 6 85, 6 81, 10 75, 10 73, 8 41, 8 38, 11 24, 11 13, 21 13, 24`},null,8,Zm)],8,Jm)),(R(),e1(S1,null,Y1(t,f=>j("svg",{width:l(n),height:l(i),key:f,class:F5(`border-item ${f}`)},[j("polygon",{fill:l(a)[0],points:"6,66 6,18 12,12 18,12 24,6 27,6 30,9 36,9 39,6 84,6 81,9 75,9 73.2,7 40.8,7 37.8,10.2 24,10.2 12,21 12,24 9,27 9,51 7.8,54 7.8,63"},[j("animate",{attributeName:"fill",values:`${l(a)[0]};${l(a)[1]};${l(a)[0]}`,dur:`${l(r)}s`,begin:"0s",repeatCount:"indefinite"},null,8,Qm)],8,Xm),j("polygon",{fill:l(a)[1],points:"27.599999999999998,4.8 38.4,4.8 35.4,7.8 30.599999999999998,7.8"},[j("animate",{attributeName:"fill",values:`${l(a)[1]};${l(a)[0]};${l(a)[1]}`,dur:`${l(r)}s`,begin:"0s",repeatCount:"indefinite"},null,8,eg)],8,Km),j("polygon",{fill:l(a)[0],points:"9,54 9,63 7.199999999999999,66 7.199999999999999,75 7.8,78 7.8,110 8.4,110 8.4,66 9.6,66 9.6,54"},[j("animate",{attributeName:"fill",values:`${l(a)[0]};${l(a)[1]};transparent`,dur:`${l(r)+1}s`,begin:"0s",repeatCount:"indefinite"},null,8,ng)],8,tg)],10,qm)),64))]))}});const og=u2(ig,[["__scopeId","data-v-708ac670"]]),ag=Object.freeze(Object.defineProperty({__proto__:null,default:og},Symbol.toStringTag,{value:"Module"})),rg={class:"go-border-box"},sg=["width","height"],lg=["fill","stroke","d"],ug=["stroke"],cg=["stroke"],dg=["stroke","d"],fg=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){const e=o,{w:t,h:n}=A1(e.chartConfig.attr),{colors:i,backgroundColor:a}=A1(e.chartConfig.option);return(r,s)=>(R(),e1("div",rg,[(R(),e1("svg",{width:l(t),height:l(n)},[j("path",{fill:l(a),stroke:l(i)[0],d:`
          M 5 20 L 5 10 L 12 3  L 60 3 L 68 10
          L ${l(t)-20} 10 L ${l(t)-5} 25
          L ${l(t)-5} ${l(n)-5} L 20 ${l(n)-5}
          L 5 ${l(n)-20} L 5 20
        `},null,8,lg),j("path",{fill:"transparent","stroke-w":"3","stroke-linecap":"round","stroke-dasharray":"10, 5",stroke:l(i)[0],d:"M 16 9 L 61 9"},null,8,ug),j("path",{fill:"transparent",stroke:l(i)[1],d:"M 5 20 L 5 10 L 12 3  L 60 3 L 68 10"},null,8,cg),j("path",{fill:"transparent",stroke:l(i)[1],d:`M ${l(t)-5} ${l(n)-30} L ${l(t)-5} ${l(n)-5} L ${l(t)-30} ${l(n)-5}`},null,8,dg)],8,sg))]))}});const pg=u2(fg,[["__scopeId","data-v-fc7dd111"]]),hg=Object.freeze(Object.defineProperty({__proto__:null,default:pg},Symbol.toStringTag,{value:"Module"})),s6=o=>(W4("data-v-b07bae32"),o=o(),H4(),o),mg={class:"go-border-box"},gg=["width","height"],vg=s6(()=>j("feMorphology",{operator:"dilate",radius:"1",in:"SourceAlpha",result:"thicken"},null,-1)),_g=s6(()=>j("feGaussianBlur",{in:"thicken",stdDeviation:"2",result:"blurred"},null,-1)),yg=["flood-color"],bg=["values"],xg=s6(()=>j("feComposite",{in:"glowColor",in2:"blurred",operator:"in",result:"softGlowColored"},null,-1)),Sg=s6(()=>j("feMerge",null,[j("feMergeNode",{in:"softGlowColored"}),j("feMergeNode",{in:"SourceGraphic"})],-1)),Mg=["fill","stroke","d"],Ag=["filter","stroke"],wg=["filter","stroke","d"],Cg=["filter","stroke","d"],Tg=["filter","stroke","d"],Eg=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){const e=o,t=`border-box-03-filterId-${U4()}`,{w:n,h:i}=A1(e.chartConfig.attr),{colors:a,backgroundColor:r}=A1(e.chartConfig.option);return(s,u)=>(R(),e1("div",mg,[(R(),e1("svg",{width:l(n),height:l(i)},[j("defs",null,[j("filter",{id:t,height:"150%",width:"150%",x:"-25%",y:"-25%"},[vg,_g,j("feFlood",{"flood-color":l(q5)(l(a)[1],.7),result:"glowColor"},[j("animate",{attributeName:"flood-color",values:`
                ${l(q5)(l(a)[1],.7)};
                ${l(q5)(l(a)[1],.3)};
                ${l(q5)(l(a)[1],.7)};
              `,dur:"3s",begin:"0s",repeatCount:"indefinite"},null,8,bg)],8,yg),xg,Sg])]),l(n)&&l(i)?(R(),e1("path",{key:0,fill:l(r),"stroke-width":"2",stroke:l(a)[0],d:`
          M15 5 L ${l(n)-15} 5 Q ${l(n)-5} 5, ${l(n)-5} 15
          L ${l(n)-5} ${l(i)-15} Q ${l(n)-5} ${l(i)-5}, ${l(n)-15} ${l(i)-5}
          L 15, ${l(i)-5} Q 5 ${l(i)-5} 5 ${l(i)-15} L 5 15
          Q 5 5 15 5
        `},null,8,Mg)):p2("",!0),j("path",{"stroke-width":"2",fill:"transparent","stroke-linecap":"round",filter:`url(#${t})`,stroke:l(a)[1],d:"M 20 5 L 15 5 Q 5 5 5 15 L 5 20"},null,8,Ag),j("path",{"stroke-width":"2",fill:"transparent","stroke-linecap":"round",filter:`url(#${t})`,stroke:l(a)[1],d:`M ${l(n)-20} 5 L ${l(n)-15} 5 Q ${l(n)-5} 5 ${l(n)-5} 15 L ${l(n)-5} 20`},null,8,wg),j("path",{"stroke-width":"2",fill:"transparent","stroke-linecap":"round",filter:`url(#${t})`,stroke:l(a)[1],d:`
          M ${l(n)-20} ${l(i)-5} L ${l(n)-15} ${l(i)-5}
          Q ${l(n)-5} ${l(i)-5} ${l(n)-5} ${l(i)-15}
          L ${l(n)-5} ${l(i)-20}
        `},null,8,Cg),j("path",{"stroke-width":"2",fill:"transparent","stroke-linecap":"round",filter:`url(#${t})`,stroke:l(a)[1],d:`
          M 20 ${l(i)-5} L 15 ${l(i)-5}
          Q 5 ${l(i)-5} 5 ${l(i)-15}
          L 5 ${l(i)-20}
        `},null,8,Tg)],8,gg))]))}});const Dg=u2(Eg,[["__scopeId","data-v-b07bae32"]]),Ig=Object.freeze(Object.defineProperty({__proto__:null,default:Dg},Symbol.toStringTag,{value:"Module"})),A4=o=>(W4("data-v-4dd6360b"),o=o(),H4(),o),Rg={class:"go-border-box"},Og=["width","height"],Pg=A4(()=>j("feMorphology",{operator:"dilate",radius:"2",in:"SourceAlpha",result:"thicken"},null,-1)),Lg=A4(()=>j("feGaussianBlur",{in:"thicken",stdDeviation:"3",result:"blurred"},null,-1)),zg=["flood-color"],kg=A4(()=>j("feComposite",{in:"glowColor",in2:"blurred",operator:"in",result:"softGlowColored"},null,-1)),Ng=A4(()=>j("feMerge",null,[j("feMergeNode",{in:"softGlowColored"}),j("feMergeNode",{in:"SourceGraphic"})],-1)),Ug=["fill","points"],Fg=["fill","stroke","filter","points"],Bg=["stroke","points"],Gg=["stroke","points"],Vg=["stroke","fill","filter","points"],jg=["filter","fill","points"],Wg=A4(()=>j("animate",{attributeName:"opacity",values:"1;0.7;1",dur:"2s",begin:"0s",repeatCount:"indefinite"},null,-1)),Hg=[Wg],$g=["filter","fill","points"],Yg=A4(()=>j("animate",{attributeName:"opacity",values:"0.7;0.4;0.7",dur:"2s",begin:"0s",repeatCount:"indefinite"},null,-1)),Jg=[Yg],Zg=["filter","fill","points"],qg=A4(()=>j("animate",{attributeName:"opacity",values:"0.5;0.2;0.5",dur:"2s",begin:"0s",repeatCount:"indefinite"},null,-1)),Xg=[qg],Qg=["filter","fill","points"],Kg=A4(()=>j("animate",{attributeName:"opacity",values:"1;0.7;1",dur:"2s",begin:"0s",repeatCount:"indefinite"},null,-1)),ev=[Kg],tv=["filter","fill","points"],nv=A4(()=>j("animate",{attributeName:"opacity",values:"0.7;0.4;0.7",dur:"2s",begin:"0s",repeatCount:"indefinite"},null,-1)),iv=[nv],ov=["filter","fill","points"],av=A4(()=>j("animate",{attributeName:"opacity",values:"0.5;0.2;0.5",dur:"2s",begin:"0s",repeatCount:"indefinite"},null,-1)),rv=[av],sv=["x","y","fill","font-size"],lv=["fill","filter","points"],uv=["fill","filter","points"],cv=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){const e=o,t=`border-box-04-filterId-${U4()}`,{w:n,h:i}=A1(e.chartConfig.attr),{colors:a,borderTitle:r,borderTitleColor:s,borderTitleSize:u,borderTitleHeight:c,borderTitleWidth:f,backgroundColor:p}=A1(e.chartConfig.option);return(h,m)=>(R(),e1("div",Rg,[(R(),e1("svg",{width:l(n),height:l(i)},[j("defs",null,[j("filter",{id:t,height:"150%",width:"150%",x:"-25%",y:"-25%"},[Pg,Lg,j("feFlood",{"flood-color":l(a)[1],result:"glowColor"},null,8,zg),kg,Ng])]),j("polygon",{fill:l(p),points:`
        20, 32 ${l(n)*.5-l(f)/2}, 32 ${l(n)*.5-l(f)/2+20}, 53
        ${l(n)*.5+l(f)/2-20}, 53 ${l(n)*.5+l(f)/2}, 32
        ${l(n)-20}, 32 ${l(n)-8}, 48 ${l(n)-8}, ${l(i)-25} ${l(n)-20}, ${l(i)-8}
        20, ${l(i)-8} 8, ${l(i)-25} 8, 50
      `},null,8,Ug),j("polyline",{fill:l(p),stroke:l(a)[0],filter:`url(#${t})`,points:`
          ${(l(n)-l(f))/2}, 30
          20, 30 7, 50 7, ${50+(l(i)-167)/2}
          13, ${55+(l(i)-167)/2} 13, ${135+(l(i)-167)/2}
          7, ${140+(l(i)-167)/2} 7, ${l(i)-27}
          20, ${l(i)-7} ${l(n)-20}, ${l(i)-7} ${l(n)-7}, ${l(i)-27}
          ${l(n)-7}, ${140+(l(i)-167)/2} ${l(n)-13}, ${135+(l(i)-167)/2}
          ${l(n)-13}, ${55+(l(i)-167)/2} ${l(n)-7}, ${50+(l(i)-167)/2}
          ${l(n)-7}, 50 ${l(n)-20}, 30 ${(l(n)+l(f))/2}, 30
          ${(l(n)+l(f))/2-20}, 7 ${(l(n)-l(f))/2+20}, 7
          ${(l(n)-l(f))/2}, 30 ${(l(n)-l(f))/2+20}, 52
          ${(l(n)+l(f))/2-20}, 52 ${(l(n)+l(f))/2}, 30
        `},null,8,Fg),j("polygon",{stroke:l(a)[0],fill:"transparent",points:`
          ${(l(n)+l(f))/2-5}, 30 ${(l(n)+l(f))/2-21}, 11
          ${(l(n)+l(f))/2-27}, 11 ${(l(n)+l(f))/2-8}, 34
        `},null,8,Bg),j("polygon",{stroke:l(a)[0],fill:"transparent",points:`
          ${(l(n)-l(f))/2+5}, 30 ${(l(n)-l(f))/2+22}, 49
          ${(l(n)-l(f))/2+28}, 49 ${(l(n)-l(f))/2+8}, 26
        `},null,8,Gg),j("polygon",{stroke:l(a)[0],fill:l(a)[1],filter:`url(#${t})`,points:`
          ${(l(n)+l(f))/2-11}, 37 ${(l(n)+l(f))/2-32}, 11
          ${(l(n)-l(f))/2+23}, 11 ${(l(n)-l(f))/2+11}, 23
          ${(l(n)-l(f))/2+33}, 49 ${(l(n)+l(f))/2-22}, 49
        `},null,8,Vg),j("polygon",{filter:`url(#${t})`,fill:l(a)[0],opacity:"1",points:`
          ${(l(n)-l(f))/2-10}, 37 ${(l(n)-l(f))/2-31}, 37
          ${(l(n)-l(f))/2-25}, 46 ${(l(n)-l(f))/2-4}, 46
        `},Hg,8,jg),j("polygon",{filter:`url(#${t})`,fill:l(a)[0],opacity:"0.7",points:`
          ${(l(n)-l(f))/2-40}, 37 ${(l(n)-l(f))/2-61}, 37
          ${(l(n)-l(f))/2-55}, 46 ${(l(n)-l(f))/2-34}, 46
        `},Jg,8,$g),j("polygon",{filter:`url(#${t})`,fill:l(a)[0],opacity:"0.5",points:`
          ${(l(n)-l(f))/2-70}, 37 ${(l(n)-l(f))/2-91}, 37
          ${(l(n)-l(f))/2-85}, 46 ${(l(n)-l(f))/2-64}, 46
        `},Xg,8,Zg),j("polygon",{filter:`url(#${t})`,fill:l(a)[0],opacity:"1",points:`
          ${(l(n)+l(f))/2+30}, 37 ${(l(n)+l(f))/2+9}, 37
          ${(l(n)+l(f))/2+3}, 46 ${(l(n)+l(f))/2+24}, 46
        `},ev,8,Qg),j("polygon",{filter:`url(#${t})`,fill:l(a)[0],opacity:"0.7",points:`
          ${(l(n)+l(f))/2+60}, 37 ${(l(n)+l(f))/2+39}, 37
          ${(l(n)+l(f))/2+33}, 46 ${(l(n)+l(f))/2+54}, 46
        `},iv,8,tv),j("polygon",{filter:`url(#${t})`,fill:l(a)[0],opacity:"0.5",points:`
          ${(l(n)+l(f))/2+90}, 37 ${(l(n)+l(f))/2+69}, 37
          ${(l(n)+l(f))/2+63}, 46 ${(l(n)+l(f))/2+84}, 46
        `},rv,8,ov),j("text",{x:`${l(n)/2}`,y:l(c),fill:l(s),"font-size":l(u),"text-anchor":"middle","dominant-baseline":"middle"},D2(l(r)),9,sv),j("polygon",{fill:l(a)[0],filter:`url(#${t})`,points:`
          7, ${53+(l(i)-167)/2} 11, ${57+(l(i)-167)/2}
          11, ${133+(l(i)-167)/2} 7, ${137+(l(i)-167)/2}
        `},null,8,lv),j("polygon",{fill:l(a)[0],filter:`url(#${t})`,points:`
          ${l(n)-7}, ${53+(l(i)-167)/2} ${l(n)-11}, ${57+(l(i)-167)/2}
          ${l(n)-11}, ${133+(l(i)-167)/2} ${l(n)-7}, ${137+(l(i)-167)/2}
        `},null,8,uv)],8,Og))]))}});const dv=u2(cv,[["__scopeId","data-v-4dd6360b"]]),fv=Object.freeze(Object.defineProperty({__proto__:null,default:dv},Symbol.toStringTag,{value:"Module"})),pv=["width","height"],hv=["fill","points"],mv=["width","height"],gv=["fill"],vv=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){const e=o,t=["left-top","right-top","left-bottom","right-bottom"],{w:n,h:i}=A1(e.chartConfig.attr),{colors:a,backgroundColor:r}=A1(e.chartConfig.option);return(s,u)=>(R(),e1("div",{class:"go-border-box",style:f2(`box-shadow: inset 0 0 25px 3px ${l(a)[0]}`)},[(R(),e1("svg",{width:l(n),height:l(i)},[j("polygon",{fill:l(r),points:`
        4, 0 ${l(n)-4}, 0 ${l(n)}, 4 ${l(n)}, ${l(i)-4} ${l(n)-4}, ${l(i)}
        4, ${l(i)} 0, ${l(i)-4} 0, 4
      `},null,8,hv)],8,pv)),(R(),e1(S1,null,Y1(t,c=>j("svg",{width:l(n),height:l(i),key:c,class:F5(`border-item ${c}`)},[j("polygon",{fill:l(a)[1],points:"40, 0 5, 0 0, 5 0, 16 3, 19 3, 7 7, 3 35, 3"},null,8,gv)],10,mv)),64))],4))}});const _v=u2(vv,[["__scopeId","data-v-37a601de"]]),yv=Object.freeze(Object.defineProperty({__proto__:null,default:_v},Symbol.toStringTag,{value:"Module"})),bv=["width","height"],xv=["stroke"],Sv=["stroke","points"],Mv=["stroke","points"],Av=["stroke","points"],wv=["stroke"],Cv=["stroke","points"],Tv=["stroke","points"],Ev=["stroke","points"],Dv=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){const e=o,{w:t,h:n}=A1(e.chartConfig.attr),{colors:i,backgroundColor:a}=A1(e.chartConfig.option);return(r,s)=>(R(),e1("div",{class:"go-border-box",style:f2(`
      box-shadow: inset 0 0 40px ${l(i)[0]}; 
      border: 1px solid ${l(i)[1]};
      background-color: ${l(a)};
    `)},[(R(),e1("svg",{width:l(t),height:l(n)},[j("polyline",{class:"go-border-line-w-2",stroke:l(i)[0],points:"0, 25 0, 0 25, 0"},null,8,xv),j("polyline",{class:"go-border-line-w-2",stroke:l(i)[0],points:`${l(t)-25}, 0 ${l(t)}, 0 ${l(t)}, 25`},null,8,Sv),j("polyline",{class:"go-border-line-w-2",stroke:l(i)[0],points:`${l(t)-25}, ${l(n)} ${l(t)}, ${l(n)} ${l(t)}, ${l(n)-25}`},null,8,Mv),j("polyline",{class:"go-border-line-w-2",stroke:l(i)[0],points:`0, ${l(n)-25} 0, ${l(n)} 25, ${l(n)}`},null,8,Av),j("polyline",{class:"go-border-line-w-5",stroke:l(i)[1],points:"0, 10 0, 0 10, 0"},null,8,wv),j("polyline",{class:"go-border-line-w-5",stroke:l(i)[1],points:`${l(t)-10}, 0 ${l(t)}, 0 ${l(t)}, 10`},null,8,Cv),j("polyline",{class:"go-border-line-w-5",stroke:l(i)[1],points:`${l(t)-10}, ${l(n)} ${l(t)}, ${l(n)} ${l(t)}, ${l(n)-10}`},null,8,Tv),j("polyline",{class:"go-border-line-w-5",stroke:l(i)[1],points:`0, ${l(n)-10} 0, ${l(n)} 10, ${l(n)}`},null,8,Ev)],8,bv))],4))}});const Iv=u2(Dv,[["__scopeId","data-v-9d777740"]]),Rv=Object.freeze(Object.defineProperty({__proto__:null,default:Iv},Symbol.toStringTag,{value:"Module"})),mr=o=>(W4("data-v-1e74db59"),o=o(),H4(),o),Ov={class:"go-border-box"},Pv=["width","height"],Lv=mr(()=>j("animate",{attributeName:"x1",values:"0%;100%;0%",dur:"10s",begin:"0s",repeatCount:"indefinite"},null,-1)),zv=mr(()=>j("animate",{attributeName:"x2",values:"100%;0%;100%",dur:"10s",begin:"0s",repeatCount:"indefinite"},null,-1)),kv=["stop-color"],Nv=["values"],Uv=["stop-color"],Fv=["values"],Bv=["points"],Gv=["points"],Vv=["points"],jv=["points"],Wv=["points"],Hv=["points"],$v=["points"],Yv=["points"],Jv=["points"],Zv=["fill","points"],qv=["width","height","fill","mask"],Xv=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){const e=o,t=`border-box-07-filter-${U4()}`,n=`border-box-07-mask-${U4()}`,{w:i,h:a}=A1(e.chartConfig.attr),{colors:r,backgroundColor:s}=A1(e.chartConfig.option);return(u,c)=>(R(),e1("div",Ov,[(R(),e1("svg",{width:l(i),height:l(a)},[j("defs",null,[j("linearGradient",{id:t,x1:"0%",y1:"0%",x2:"100%",y2:"100%"},[Lv,zv,j("stop",{offset:"0%","stop-color":l(r)[0]},[j("animate",{attributeName:"stop-color",values:`${l(r)[0]};${l(r)[1]};${l(r)[0]}`,dur:"10s",begin:"0s",repeatCount:"indefinite"},null,8,Nv)],8,kv),j("stop",{offset:"100%","stop-color":l(r)[1]},[j("animate",{attributeName:"stop-color",values:`${l(r)[1]};${l(r)[0]};${l(r)[1]}`,dur:"10s",begin:"0s",repeatCount:"indefinite"},null,8,Fv)],8,Uv)]),j("mask",{id:n},[j("polyline",{stroke:"#fff","stroke-width":"3",fill:"transparent",points:`8, ${l(a)*.4} 8, 3, ${l(i)*.4+7}, 3`},null,8,Bv),j("polyline",{fill:"#fff",points:`8, ${l(a)*.15} 8, 3, ${l(i)*.1+7}, 3
              ${l(i)*.1}, 8 14, 8 14, ${l(a)*.15-7}
            `},null,8,Gv),j("polyline",{stroke:"#fff","stroke-width":"3",fill:"transparent",points:`${l(i)*.5}, 3 ${l(i)-3}, 3, ${l(i)-3}, ${l(a)*.25}`},null,8,Vv),j("polyline",{fill:"#fff",points:`
              ${l(i)*.52}, 3 ${l(i)*.58}, 3
              ${l(i)*.58-7}, 9 ${l(i)*.52+7}, 9
            `},null,8,jv),j("polyline",{fill:"#fff",points:`
              ${l(i)*.9}, 3 ${l(i)-3}, 3 ${l(i)-3}, ${l(a)*.1}
              ${l(i)-9}, ${l(a)*.1-7} ${l(i)-9}, 9 ${l(i)*.9+7}, 9
            `},null,8,Wv),j("polyline",{stroke:"#fff","stroke-width":"3",fill:"transparent",points:`8, ${l(a)*.5} 8, ${l(a)-3} ${l(i)*.3+7}, ${l(a)-3}`},null,8,Hv),j("polyline",{fill:"#fff",points:`
              8, ${l(a)*.55} 8, ${l(a)*.7}
              2, ${l(a)*.7-7} 2, ${l(a)*.55+7}
            `},null,8,$v),j("polyline",{stroke:"#fff","stroke-width":"3",fill:"transparent",points:`${l(i)*.35}, ${l(a)-3} ${l(i)-3}, ${l(a)-3} ${l(i)-3}, ${l(a)*.35}`},null,8,Yv),j("polyline",{fill:"#fff",points:`
              ${l(i)*.92}, ${l(a)-3} ${l(i)-3}, ${l(a)-3} ${l(i)-3}, ${l(a)*.8}
              ${l(i)-9}, ${l(a)*.8+7} ${l(i)-9}, ${l(a)-9} ${l(i)*.92+7}, ${l(a)-9}
            `},null,8,Jv)])]),j("polygon",{fill:l(s),points:`
        15, 9 ${l(i)*.1+1}, 9 ${l(i)*.1+4}, 6 ${l(i)*.52+2}, 6
        ${l(i)*.52+6}, 10 ${l(i)*.58-7}, 10 ${l(i)*.58-2}, 6
        ${l(i)*.9+2}, 6 ${l(i)*.9+6}, 10 ${l(i)-10}, 10 ${l(i)-10}, ${l(a)*.1-6}
        ${l(i)-6}, ${l(a)*.1-1} ${l(i)-6}, ${l(a)*.8+1} ${l(i)-10}, ${l(a)*.8+6}
        ${l(i)-10}, ${l(a)-10} ${l(i)*.92+7}, ${l(a)-10}  ${l(i)*.92+2}, ${l(a)-6}
        11, ${l(a)-6} 11, ${l(a)*.15-2} 15, ${l(a)*.15-7}
      `},null,8,Zv),j("rect",{x:"0",y:"0",width:l(i),height:l(a),fill:`url(#${t})`,mask:`url(#${n})`},null,8,qv)],8,Pv))]))}});const Qv=u2(Xv,[["__scopeId","data-v-1e74db59"]]),Kv=Object.freeze(Object.defineProperty({__proto__:null,default:Qv},Symbol.toStringTag,{value:"Module"})),gr=o=>(W4("data-v-966367ac"),o=o(),H4(),o),e_={class:"go-border-box"},t_=["width","height"],n_=["d"],i_=gr(()=>j("stop",{offset:"0%","stop-color":"#fff","stop-opacity":"1"},null,-1)),o_=gr(()=>j("stop",{offset:"100%","stop-color":"#fff","stop-opacity":"0"},null,-1)),a_=[i_,o_],r_=["fill"],s_=["dur","path"],l_=["fill","points"],u_=["stroke","xlink:href"],c_=["stroke","xlink:href","mask"],d_=["from","to","dur"],f_=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){const e=o,t=`border-box-08-path-${U4()}`,n=`border-box-08-gradient-${U4()}`,i=`border-box-08-mask-${U4()}`,{w:a,h:r}=A1(e.chartConfig.attr),{colors:s,dur:u,backgroundColor:c,reverse:f}=A1(e.chartConfig.option),p=X1(()=>(a.value+r.value-5)*2),h=X1(()=>f.value?`M 2.5, 2.5 L 2.5, ${r.value-2.5} L ${a.value-2.5}, ${r.value-2.5} L ${a.value-2.5}, 2.5 L 2.5, 2.5`:`M2.5, 2.5 L${a.value-2.5}, 2.5 L${a.value-2.5}, ${r.value-2.5} L2.5, ${r.value-2.5} L2.5, 2.5`);return(m,y)=>(R(),e1("div",e_,[(R(),e1("svg",{width:l(a),height:l(r)},[j("defs",null,[j("path",{id:t,d:h.value,fill:"transparent"},null,8,n_),j("radialGradient",{id:n,cx:"50%",cy:"50%",r:"50%"},a_),j("mask",{id:i},[j("circle",{cx:"0",cy:"0",r:"150",fill:`url(#${n})`},[j("animateMotion",{dur:`${l(u)}s`,path:h.value,rotate:"auto",repeatCount:"indefinite"},null,8,s_)],8,r_)])]),j("polygon",{fill:l(c),points:`5, 5 ${l(a)-5}, 5 ${l(a)-5} ${l(r)-5} 5, ${l(r)-5}`},null,8,l_),j("use",{stroke:l(s)[0],"stroke-width":"1","xlink:href":`#${t}`},null,8,u_),j("use",{stroke:l(s)[1],"stroke-width":"3","xlink:href":`#${t}`,mask:`url(#${i})`},[j("animate",{attributeName:"stroke-dasharray",from:`0, ${p.value}`,to:`${p.value}, 0`,dur:`${l(u)}s`,repeatCount:"indefinite"},null,8,d_)],8,c_)],8,t_))]))}});const p_=u2(f_,[["__scopeId","data-v-966367ac"]]),h_=Object.freeze(Object.defineProperty({__proto__:null,default:p_},Symbol.toStringTag,{value:"Module"})),m_={class:"go-border-box"},g_=["width","height"],v_=["fill","points"],__=["fill"],y_=["fill","cx"],b_=["fill","cx","cy"],x_=["fill","cy"],S_=["stroke","points"],M_=["stroke","points"],A_=["stroke","points"],w_=["stroke","points"],C_=["stroke"],T_=["stroke"],E_=["stroke","points"],D_=["stroke","points"],I_=["stroke","points"],R_=["stroke","points"],O_=["stroke","points"],P_=["stroke","points"],L_=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){const e=o,{w:t,h:n}=A1(e.chartConfig.attr),{colors:i,backgroundColor:a}=A1(e.chartConfig.option);return(r,s)=>(R(),e1("div",m_,[(R(),e1("svg",{width:l(t),height:l(n)},[j("polygon",{fill:l(a),points:` 9, 7 ${l(t)-9}, 7 ${l(t)-9}, ${l(n)-7} 9, ${l(n)-7}`},null,8,v_),j("circle",{fill:l(i)[1],cx:"5",cy:"5",r:"2"},null,8,__),j("circle",{fill:l(i)[1],cx:l(t)-5,cy:"5",r:"2"},null,8,y_),j("circle",{fill:l(i)[1],cx:l(t)-5,cy:l(n)-5,r:"2"},null,8,b_),j("circle",{fill:l(i)[1],cx:"5",cy:l(n)-5,r:"2"},null,8,x_),j("polyline",{stroke:l(i)[0],points:`10, 4 ${l(t)-10}, 4`},null,8,S_),j("polyline",{stroke:l(i)[0],points:`10, ${l(n)-4} ${l(t)-10}, ${l(n)-4}`},null,8,M_),j("polyline",{stroke:l(i)[0],points:`5, 70 5, ${l(n)-70}`},null,8,A_),j("polyline",{stroke:l(i)[0],points:`${l(t)-5}, 70 ${l(t)-5}, ${l(n)-70}`},null,8,w_),j("polyline",{stroke:l(i)[0],points:"3, 10, 3, 50"},null,8,C_),j("polyline",{stroke:l(i)[0],points:"7, 30 7, 80"},null,8,T_),j("polyline",{stroke:l(i)[0],points:`${l(t)-3}, 10 ${l(t)-3}, 50`},null,8,E_),j("polyline",{stroke:l(i)[0],points:`${l(t)-7}, 30 ${l(t)-7}, 80`},null,8,D_),j("polyline",{stroke:l(i)[0],points:`3, ${l(n)-10} 3, ${l(n)-50}`},null,8,I_),j("polyline",{stroke:l(i)[0],points:`7, ${l(n)-30} 7, ${l(n)-80}`},null,8,R_),j("polyline",{stroke:l(i)[0],points:`${l(t)-3}, ${l(n)-10} ${l(t)-3}, ${l(n)-50}`},null,8,O_),j("polyline",{stroke:l(i)[0],points:`${l(t)-7}, ${l(n)-30} ${l(t)-7}, ${l(n)-80}`},null,8,P_)],8,g_))]))}});const z_=u2(L_,[["__scopeId","data-v-a43eb02e"]]),k_=Object.freeze(Object.defineProperty({__proto__:null,default:z_},Symbol.toStringTag,{value:"Module"})),N_={class:"go-border-box"},U_=["width","height"],F_=["fill","points"],B_=["stroke","points"],G_=["stroke","points"],V_=["stroke","points"],j_=["stroke"],W_=["stroke"],H_=["stroke"],$_=["stroke"],Y_=["stroke"],J_=["stroke","points"],Z_=["stroke","points"],q_=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){const e=o,{w:t,h:n}=A1(e.chartConfig.attr),{colors:i,backgroundColor:a}=A1(e.chartConfig.option);return(r,s)=>(R(),e1("div",N_,[(R(),e1("svg",{width:l(t),height:l(n)},[j("polygon",{fill:l(a),points:`
        ${l(t)-15}, 22 170, 22 150, 7 40, 7 28, 21 32, 24
        16, 42 16, ${l(n)-32} 41, ${l(n)-7} ${l(t)-15}, ${l(n)-7}
      `},null,8,F_),j("polyline",{class:"go-border-line-1",stroke:l(i)[0],points:`145, ${l(n)-5} 40, ${l(n)-5} 10, ${l(n)-35}
          10, 40 40, 5 150, 5 170, 20 ${l(t)-15}, 20`},null,8,B_),j("polyline",{stroke:l(i)[1],class:"go-border-line-2",points:`245, ${l(n)-1} 36, ${l(n)-1} 14, ${l(n)-23}
          14, ${l(n)-100}`},null,8,G_),j("polyline",{class:"go-border-line-3",stroke:l(i)[0],points:`7, ${l(n)-40} 7, ${l(n)-75}`},null,8,V_),j("polyline",{class:"go-border-line-4",stroke:l(i)[0],points:"28, 24 13, 41 13, 64"},null,8,j_),j("polyline",{class:"go-border-line-5",stroke:l(i)[0],points:"5, 45 5, 140"},null,8,W_),j("polyline",{class:"go-border-line-6",stroke:l(i)[1],points:"14, 75 14, 180"},null,8,H_),j("polyline",{class:"go-border-line-7",stroke:l(i)[1],points:"55, 11 147, 11 167, 26 250, 26"},null,8,$_),j("polyline",{class:"go-border-line-8",stroke:l(i)[1],points:"158, 5 173, 16"},null,8,Y_),j("polyline",{class:"go-border-line-9",stroke:l(i)[0],points:`200, 17 ${l(t)-10}, 17`},null,8,J_),j("polyline",{class:"go-border-line-10",stroke:l(i)[1],points:`385, 17 ${l(t)-10}, 17`},null,8,Z_)],8,U_))]))}});const X_=u2(q_,[["__scopeId","data-v-03a8a229"]]),Q_=Object.freeze(Object.defineProperty({__proto__:null,default:X_},Symbol.toStringTag,{value:"Module"})),K_={class:"go-border-box"},ey=["width","height"],ty=["fill","points"],ny=["stroke","points"],iy=["stroke","points"],oy=["stroke","points"],ay=["stroke","points"],ry=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){const e=o,{w:t,h:n}=A1(e.chartConfig.attr),{colors:i,backgroundColor:a}=A1(e.chartConfig.option);return(r,s)=>(R(),e1("div",K_,[(R(),e1("svg",{class:"bv-border-svg-container",width:l(t),height:l(n)},[j("polygon",{fill:l(a),points:`
        23, 23 ${l(t)-24}, 23 ${l(t)-24}, ${l(n)-24} 23, ${l(n)-24}
      `},null,8,ty),j("polyline",{class:"go-border-line-1",stroke:l(i)[0],points:`4, 4 ${l(t)-22} ,4 ${l(t)-22}, ${l(n)-22} 4, ${l(n)-22} 4, 4`},null,8,ny),j("polyline",{class:"go-border-line-3",stroke:l(i)[1],points:`10, 10 ${l(t)-16}, 10 ${l(t)-16}, ${l(n)-16} 10, ${l(n)-16} 10, 10`},null,8,iy),j("polyline",{class:"go-border-line-3",stroke:l(i)[1],points:`16, 16 ${l(t)-10}, 16 ${l(t)-10}, ${l(n)-10} 16, ${l(n)-10} 16, 16`},null,8,oy),j("polyline",{class:"go-border-line-3",stroke:l(i)[1],points:`22, 22 ${l(t)-4}, 22 ${l(t)-4}, ${l(n)-4} 22, ${l(n)-4} 22, 22`},null,8,ay)],8,ey))]))}});const sy=u2(ry,[["__scopeId","data-v-454ad1b3"]]),ly=Object.freeze(Object.defineProperty({__proto__:null,default:sy},Symbol.toStringTag,{value:"Module"})),uy={class:"go-border-box"},cy=["width","height"],dy=["fill","points"],fy=["stroke","points"],py=["stroke","points"],hy=["stroke","points"],my=["stroke","points"],gy=["stroke","points"],vy=["stroke","points"],_y=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){const e=o,{w:t,h:n}=A1(e.chartConfig.attr),{colors:i,backgroundColor:a}=A1(e.chartConfig.option);return(r,s)=>(R(),e1("div",uy,[(R(),e1("svg",{width:l(t),height:l(n)},[j("polygon",{fill:l(a),points:`
        10, 22 ${l(t)-22}, 22 ${l(t)-22}, ${l(n)-86} ${l(t)-84}, ${l(n)-24} 10, ${l(n)-24}
      `},null,8,dy),j("polyline",{class:"bv-bb5-line-1",stroke:l(i)[0],points:`8, 5 ${l(t)-5}, 5 ${l(t)-5}, ${l(n)-100}
          ${l(t)-100}, ${l(n)-5} 8, ${l(n)-5} 8, 5`},null,8,fy),j("polyline",{class:"bv-bb5-line-2",stroke:l(i)[1],points:`3, 5 ${l(t)-20}, 5 ${l(t)-20}, ${l(n)-60}
          ${l(t)-74}, ${l(n)-5} 3, ${l(n)-5} 3, 5`},null,8,py),j("polyline",{class:"bv-bb5-line-3",stroke:l(i)[1],points:`50, 13 ${l(t)-35}, 13`},null,8,hy),j("polyline",{class:"bv-bb5-line-4",stroke:l(i)[1],points:`15, 20 ${l(t)-35}, 20`},null,8,my),j("polyline",{class:"bv-bb5-line-5",stroke:l(i)[1],points:`15, ${l(n)-20} ${l(t)-110}, ${l(n)-20}`},null,8,gy),j("polyline",{class:"bv-bb5-line-6",stroke:l(i)[1],points:`15, ${l(n)-13} ${l(t)-110}, ${l(n)-13}`},null,8,vy)],8,cy))]))}});const yy=u2(_y,[["__scopeId","data-v-80c70916"]]),by=Object.freeze(Object.defineProperty({__proto__:null,default:yy},Symbol.toStringTag,{value:"Module"})),xy={class:"go-border-box"},Sy=["width","height"],My=["fill","points"],Ay=["stroke","points"],wy=["stroke","points"],Cy=["fill"],Ty=["fill","cx"],Ey=["fill","cx","cy"],Dy=["fill","cy"],Iy=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){const e=o,{w:t,h:n}=A1(e.chartConfig.attr),{colors:i,backgroundColor:a}=A1(e.chartConfig.option);return(r,s)=>(R(),e1("div",xy,[(R(),e1("svg",{width:l(t),height:l(n)},[j("polygon",{fill:l(a),points:`
        7, 7 ${l(t)-7}, 7 ${l(t)-7}, ${l(n)-7} 7, ${l(n)-7}
      `},null,8,My),j("polyline",{stroke:l(i)[0],points:`2, 2 ${l(t)-2} ,2 ${l(t)-2}, ${l(n)-2} 2, ${l(n)-2} 2, 2`},null,8,Ay),j("polyline",{stroke:l(i)[1],points:`6, 6 ${l(t)-6}, 6 ${l(t)-6}, ${l(n)-6} 6, ${l(n)-6} 6, 6`},null,8,wy),j("circle",{fill:l(i)[0],cx:"11",cy:"11",r:"1"},null,8,Cy),j("circle",{fill:l(i)[0],cx:l(t)-11,cy:"11",r:"1"},null,8,Ty),j("circle",{fill:l(i)[0],cx:l(t)-11,cy:l(n)-11,r:"1"},null,8,Ey),j("circle",{fill:l(i)[0],cx:"11",cy:l(n)-11,r:"1"},null,8,Dy)],8,Sy))]))}});const Ry=u2(Iy,[["__scopeId","data-v-e5e18ea8"]]),Oy=Object.freeze(Object.defineProperty({__proto__:null,default:Ry},Symbol.toStringTag,{value:"Module"})),Py=["width","height"],Ly=["x","y","width","height","fill"],zy=["to","dur"],ky=["x","y","width","height","fill"],Ny=["to","dur"],Uy=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){const e=o,{w:t,h:n}=A1(e.chartConfig.attr),{colors:i,dur:a,endWidth:r,lineHeight:s}=A1(e.chartConfig.option),u=X1(()=>0),c=X1(()=>n.value/2);return(f,p)=>(R(),e1("svg",{width:l(t),height:l(n)},[j("rect",{x:u.value,y:c.value,width:l(t),height:l(s),fill:l(i)[0]},[j("animate",{attributeName:"width",from:"0",to:l(t),dur:`${l(a)}s`,calcMode:"spline",keyTimes:"0;1",keySplines:".42,0,.58,1",repeatCount:"indefinite"},null,8,zy)],8,Ly),j("rect",{x:u.value,y:c.value,width:l(r),height:l(s),fill:l(i)[1]},[j("animate",{attributeName:"x",from:"0",to:l(t),dur:`${l(a)}s`,calcMode:"spline",keyTimes:"0;1",keySplines:"0.42,0,0.58,1",repeatCount:"indefinite"},null,8,Ny)],8,ky)],8,Py))}}),Fy=Object.freeze(Object.defineProperty({__proto__:null,default:Uy},Symbol.toStringTag,{value:"Module"})),By=["width"],Gy=["stroke","points"],Vy=["stroke","points"],jy=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){const e=o,{w:t,h:n}=A1(e.chartConfig.attr),{colors:i,dur:a,lineHeight:r}=A1(e.chartConfig.option);return(s,u)=>(R(),e1("div",{class:"go-decorates-2",style:f2(`width:${l(t)}px; height: ${l(r)}px animation-duration:${l(a)}s`)},[(R(),e1("svg",{width:l(t),height:3},[j("polyline",{stroke:l(i)[0],points:`0, 2.5 ${l(t)}, 2.5`},null,8,Gy),j("polyline",{stroke:l(i)[1],"stroke-width":"3","stroke-dasharray":"20, 80","stroke-dashoffset":"-30",points:`0, 2.5 ${l(t)}, 2.5`},null,8,Vy)],8,By))],4))}});const Wy=u2(jy,[["__scopeId","data-v-bf54f1e3"]]),Hy=Object.freeze(Object.defineProperty({__proto__:null,default:Wy},Symbol.toStringTag,{value:"Module"})),$y={width:20,height:20},Yy=["stroke"],Jy=["stroke"],Zy={width:20,height:20},qy=["stroke"],Xy=["stroke"],Qy=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){const e=o,{w:t,h:n}=A1(e.chartConfig.attr),{colors:i,dataset:a,textSize:r,textColor:s}=A1(e.chartConfig.option);return(u,c)=>(R(),e1("div",{class:"go-border-03",style:f2(`width: ${l(t)}px; height: ${l(n)}px`)},[(R(),e1("svg",$y,[j("polyline",{"stroke-width":"4",fill:"transparent",stroke:l(i)[0],points:"10, 0 19, 10 10, 20"},null,8,Yy),j("polyline",{"stroke-width":"2",fill:"transparent",stroke:l(i)[1],points:"2, 0 11, 10 2, 20"},null,8,Jy)])),j("span",{style:f2(`color: ${l(s)};font-size: ${l(r)}px`)},D2(l(a)),5),(R(),e1("svg",Zy,[j("polyline",{"stroke-width":"4",fill:"transparent",stroke:l(i)[0],points:"11, 0 2, 10 11, 20"},null,8,qy),j("polyline",{"stroke-width":"2",fill:"transparent",stroke:l(i)[1],points:"19, 0 10, 10 19, 20"},null,8,Xy)]))],4))}});const Ky=u2(Qy,[["__scopeId","data-v-a4359992"]]),eb=Object.freeze(Object.defineProperty({__proto__:null,default:Ky},Symbol.toStringTag,{value:"Module"})),tb={class:"go-border-04"},nb=["width","height"],ib=["stroke","points"],ob=["stroke","points"],ab=["stroke","points"],rb=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){const e=o,{w:t,h:n}=A1(e.chartConfig.attr),{colors:i,reverse:a}=A1(e.chartConfig.option),r=s=>a.value?t.value-s:s;return(s,u)=>(R(),e1("div",tb,[(R(),e1("svg",{width:l(t),height:l(n)},[j("polyline",{stroke:l(i)[0],"stroke-width":"2",fill:"transparent",points:`${r(0)}, 0 ${r(30)}, ${l(n)/2}`},null,8,ib),j("polyline",{stroke:l(i)[0],"stroke-width":"2",fill:"transparent",points:`${r(20)}, 0 ${r(50)}, ${l(n)/2} ${r(l(t))}, ${l(n)/2}`},null,8,ob),j("polyline",{stroke:l(i)[1],fill:"transparent","stroke-width":"3",points:`${r(0)}, ${l(n)-3}, ${r(200)}, ${l(n)-3}`},null,8,ab)],8,nb))]))}});const sb=u2(rb,[["__scopeId","data-v-8a13a421"]]),lb=Object.freeze(Object.defineProperty({__proto__:null,default:sb},Symbol.toStringTag,{value:"Module"})),ub={class:"go-border-05"},cb=["width","height"],db=["stroke","points"],fb=["stroke","points","stroke-dasharray"],pb=["values","dur","begin"],hb=["values","begin"],mb=["stroke","points","stroke-dasharray"],gb=["values","dur","begin"],vb=["values","begin"],_b=["stroke","points","stroke-dasharray"],yb=["values","dur","begin"],bb=["values","begin"],xb=["cy","fill"],Sb=["values","begin"],Mb=["cx","cy","fill"],Ab=["values","begin"],wb=["values","begin"],Cb=["cx","cy","fill"],Tb=["values","begin"],Eb=["values","begin"],Db=["cx","cy","fill"],Ib=["values","begin"],Rb=["values","begin"],Ob=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){const e=o,t=U4(),{w:n,h:i}=A1(e.chartConfig.attr),{colors:a,dur:r}=A1(e.chartConfig.option),s=`d10ani1${t}`,u=`d10ani2${t}`,c=`d10ani3${t}`,f=`d10ani4${t}`,p=`d10ani5${t}`,h=`d10ani6${t}`,m=`d10ani7${t}`;return(y,_)=>(R(),e1("div",ub,[(R(),e1("svg",{width:l(n),height:l(i)},[j("polyline",{stroke:l(a)[1],"stroke-width":"2",points:`0, ${l(i)/2} ${l(n)}, ${l(i)/2}`},null,8,db),j("polyline",{stroke:l(a)[0],"stroke-width":"2",points:`5, ${l(i)/2} ${l(n)*.2-3}, ${l(i)/2}`,"stroke-dasharray":`0, ${l(n)*.2}`,fill:"freeze"},[j("animate",{id:u,attributeName:"stroke-dasharray",values:`0, ${l(n)*.2};${l(n)*.2}, 0;`,dur:l(r),begin:`${s}.end`,fill:"freeze"},null,8,pb),j("animate",{attributeName:"stroke-dasharray",values:`${l(n)*.2}, 0;0, ${l(n)*.2}`,dur:"0.01s",begin:`${m}.end`,fill:"freeze"},null,8,hb)],8,fb),j("polyline",{stroke:l(a)[0],"stroke-width":"2",points:`${l(n)*.2+3}, ${l(i)/2} ${l(n)*.8-3}, ${l(i)/2}`,"stroke-dasharray":`0, ${l(n)*.6}`},[j("animate",{id:f,attributeName:"stroke-dasharray",values:`0, ${l(n)*.6};${l(n)*.6}, 0`,dur:l(r),begin:`${c}.end + 1s`,fill:"freeze"},null,8,gb),j("animate",{attributeName:"stroke-dasharray",values:`${l(n)*.6}, 0;0, ${l(n)*.6}`,dur:"0.01s",begin:`${m}.end`,fill:"freeze"},null,8,vb)],8,mb),j("polyline",{stroke:l(a)[0],"stroke-width":"2",points:`${l(n)*.8+3}, ${l(i)/2} ${l(n)-5}, ${l(i)/2}`,"stroke-dasharray":`0, ${l(n)*.2}`},[j("animate",{id:h,attributeName:"stroke-dasharray",values:`0, ${l(n)*.2};${l(n)*.2}, 0`,dur:l(r),begin:`${p}.end + 1s`,fill:"freeze"},null,8,yb),j("animate",{attributeName:"stroke-dasharray",values:`${l(n)*.2}, 0;0, ${l(n)*.3}`,dur:"0.01s",begin:`${m}.end`,fill:"freeze"},null,8,bb)],8,_b),j("circle",{cx:"2",cy:l(i)/2,r:"2",fill:l(a)[1]},[j("animate",{id:s,attributeName:"fill",values:`${l(a)[1]};${l(a)[0]}`,begin:`0s;${m}.end`,dur:"0.3s",fill:"freeze"},null,8,Sb)],8,xb),j("circle",{cx:l(n)*.2,cy:l(i)/2,r:"2",fill:l(a)[1]},[j("animate",{id:c,attributeName:"fill",values:`${l(a)[1]};${l(a)[0]}`,begin:`${u}.end`,dur:"0.3s",fill:"freeze"},null,8,Ab),j("animate",{attributeName:"fill",values:`${l(a)[1]};${l(a)[1]}`,dur:"0.01s",begin:`${m}.end`,fill:"freeze"},null,8,wb)],8,Mb),j("circle",{cx:l(n)*.8,cy:l(i)/2,r:"2",fill:l(a)[1]},[j("animate",{id:p,attributeName:"fill",values:`${l(a)[1]};${l(a)[0]}`,begin:`${f}.end`,dur:"0.3s",fill:"freeze"},null,8,Tb),j("animate",{attributeName:"fill",values:`${l(a)[1]};${l(a)[1]}`,dur:"0.01s",begin:`${m}.end`,fill:"freeze"},null,8,Eb)],8,Cb),j("circle",{cx:l(n)-2,cy:l(i)/2,r:"2",fill:l(a)[1]},[j("animate",{id:m,attributeName:"fill",values:`${l(a)[1]};${l(a)[0]}`,begin:`${h}.end`,dur:"0.3s",fill:"freeze"},null,8,Ib),j("animate",{attributeName:"fill",values:`${l(a)[1]};${l(a)[1]}`,dur:"0.01s",begin:`${m}.end`,fill:"freeze"},null,8,Rb)],8,Db)],8,cb))]))}});const Pb=u2(Ob,[["__scopeId","data-v-33f0e0ac"]]),Lb=Object.freeze(Object.defineProperty({__proto__:null,default:Pb},Symbol.toStringTag,{value:"Module"})),zb=o=>(W4("data-v-db506cc9"),o=o(),H4(),o),kb={class:"go-border-06"},Nb=["width","height"],Ub=zb(()=>j("polygon",{class:"stroke fill",points:"15.5 6.5 20.5 0.5 50.5 0.5 55.5 6.5 15.5 6.5"},null,-1)),Fb=["points"],Bb=["points"],Gb=["points"],Vb=["points"],jb=["points"],Wb=["points"],Hb={class:"text"},$b=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){a4(u=>({"30f2c0b4":l(i)[0],"30f2c076":l(i)[1],32016584:l(s),f68b5eba:l(r)+"px"}));const e=o;U4();const{w:t,h:n}=A1(e.chartConfig.attr),{colors:i,dataset:a,textSize:r,textColor:s}=A1(e.chartConfig.option);return(u,c)=>(R(),e1("div",kb,[(R(),e1("svg",{xmlns:"http://www.w3.org/2000/svg",width:l(t),height:l(n)},[Ub,j("polygon",{class:"stroke fill",points:`15.5 ${l(n)-6.5} 20.5 ${l(n)-.5} 50.5 ${l(n)-.5} 55.5 ${l(n)-6.5} 15.5 ${l(n)-6.5}`},null,8,Fb),j("polygon",{class:"stroke fill",points:`${l(t)-15.5} 6.5 ${l(t)-20.5} 0.5 ${l(t)-50.5} 0.5 ${l(t)-55.5} 6.5 ${l(t)-15.5} 6.5`},null,8,Bb),j("polygon",{class:"stroke fill",points:`${l(t)-15.5} ${l(n)-6.5} ${l(t)-20.5} ${l(n)-.5} ${l(t)-50.5} ${l(n)-.5} ${l(t)-55.5} ${l(n)-6.5} ${l(t)-15.5} ${l(n)-6.5}`},null,8,Gb),j("polygon",{class:"stroke fill",points:`15.5 6.5 0.5 ${l(n)/2} 15.5 ${l(n)-6.5} ${l(t)-15.5} ${l(n)-6.5} ${l(t)-.5} ${l(n)/2} ${l(t)-15.5} 6.5 15.5 6.5`},null,8,Vb),j("polyline",{class:"stroke fill-none",points:`20.5 14.5 8.5 ${l(n)/2} 20.5 ${l(n)-14.5}`},null,8,jb),j("polyline",{class:"stroke fill-none",points:`${l(t)-20.5} 14.5 ${l(t)-8.5} ${l(n)/2} ${l(t)-20.5} ${l(n)-14.5}`},null,8,Wb)],8,Nb)),j("span",Hb,D2(l(a)),1)]))}});const Yb=u2($b,[["__scopeId","data-v-db506cc9"]]),Jb=Object.freeze(Object.defineProperty({__proto__:null,default:Yb},Symbol.toStringTag,{value:"Module"})),Zb=o=>(W4("data-v-b4d0ec68"),o=o(),H4(),o),qb={xmlns:"http://www.w3.org/2000/svg",viewBox:"0 0 200 200"},Xb=Zl('<filter id="innerShadow" x="-20%" y="-20%" width="140%" height="140%" data-v-b4d0ec68><feGaussianBlur in="SourceGraphic" stdDeviation="3" result="blur" data-v-b4d0ec68></feGaussianBlur><feOffset in="blur" dx="2.5" dy="2.5" data-v-b4d0ec68></feOffset></filter><g data-v-b4d0ec68><circle id="shadow" style="fill:rgba(0, 0, 0, 0.1);" cx="100" cy="100" r="87" filter="url(#innerShadow)" data-v-b4d0ec68></circle><circle id="circle" class="clock-border" cx="100" cy="100" r="80" data-v-b4d0ec68></circle></g>',2),Qb={x1:"100",y1:"100",x2:"100",y2:"55",style:{"stroke-width":"3px"},class:"clock-line"},Kb=["from","to"],ex={x1:"100",y1:"100",x2:"100",y2:"40",style:{"stroke-width":"4px"},class:"clock-line"},tx=["from","to"],nx={x1:"100",y1:"100",x2:"100",y2:"30",style:{"stroke-width":"2px"},class:"clock-line"},ix=["from","to"],ox=Zb(()=>j("circle",{id:"center",style:{fill:"#128a86",stroke:"#c1efed","stroke-width":"2px"},cx:"100",cy:"100",r:"3"},null,-1)),ax=["transform"],rx=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){a4(f=>({"47fe3763":l(a),46891283:l(t)+"px","7239e62c":l(i),"67e7692f":l(n)}));const e=o;let{border:t,color:n,bgColor:i,borderColor:a}=A1(e.chartConfig.option);const r=new Date,s=360*r.getHours()/12+r.getMinutes()/2,u=360*r.getMinutes()/60,c=360*r.getSeconds()/60;return(f,p)=>(R(),e1("svg",qb,[Xb,j("g",null,[j("line",Qb,[j("animateTransform",{attributeName:"transform",attributeType:"XML",type:"rotate",dur:"43200s",repeatCount:"indefinite",from:`${s} 100 100`,to:`${s+360} 100 100`},null,8,Kb)]),j("line",ex,[j("animateTransform",{attributeName:"transform",attributeType:"XML",type:"rotate",dur:"3600s",repeatCount:"indefinite",from:`${u} 100 100`,to:`${u+360} 100 100`},null,8,tx)]),j("line",nx,[j("animateTransform",{attributeName:"transform",attributeType:"XML",type:"rotate",dur:"60s",repeatCount:"indefinite",from:`${c} 100 100`,to:`${c+360} 100 100`},null,8,ix)])]),ox,(R(),e1(S1,null,Y1(12,h=>j("line",{x1:"100",y1:"30",x2:"100",y2:"40",class:"clock-line",transform:`rotate(${(h+1)*360/12} 100 100)`,key:`line_${h+1}`},null,8,ax)),64))]))}});const sx=u2(rx,[["__scopeId","data-v-b4d0ec68"]]),lx=Object.freeze(Object.defineProperty({__proto__:null,default:sx},Symbol.toStringTag,{value:"Module"})),ux=["data-front"],cx=["data-back"],dx=s1({__name:"index",props:{flipType:{type:String,default:()=>"down"},count:{type:[Number,String],default:0},duration:{type:Number,default:600},width:{type:Number,default:60},height:{type:Number,default:100},radius:{type:Number,default:10},frontColor:{type:String,default:"#ffffff"},backColor:{type:String,default:"#000000"},borderWidth:{type:Number,default:2}},setup(o){a4(s=>({"06b40856":e.frontColor,"39840b7e":e.backColor,ddd97676:`${e.radius}px`,43622609:`${e.width}px`,"4281d24c":`${e.height}px`,"45eb2214":`${e.height*2}px`,"6026284e":`${e.borderWidth*2}px`,"76a98bc6":`${e.duration/1e3}s`}));const e=o,t=Z1(!1),n=Z1(e.count||0),i=Z1(e.count||0);let a=0;const r=(s,u)=>P2(this,null,function*(){if(t.value){t.value=!1,clearTimeout(a),yield J0(),yield r(s,u);return}i.value=u,n.value=s,t.value=!0,a=setTimeout(()=>{t.value=!1,n.value=u},e.duration)});return N1(()=>e.count,(s,u)=>{r(u,s)},{immediate:!0}),(s,u)=>(R(),e1("div",{class:F5(["go-flipper",[o.flipType,{go:t.value}]])},[j("div",{class:"digital front","data-front":n.value},null,8,ux),j("div",{class:"digital back","data-back":i.value},null,8,cx)],2))}});const f5=u2(dx,[["__scopeId","data-v-ce6b5020"]]),fx={key:0},px={key:1},hx={key:1},mx={key:2},gx={key:3},vx={key:4},_x={key:5},yx=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){a4(q=>({a0c42b24:`${l(t)}px`,a26aec42:`${l(n)}px`,"225bc0d6":`${l(p)}px`,bd636264:`${l(h)}px`,"48414a19":l(f)}));const e=o,{w:t,h:n}=A1(e.chartConfig.attr),{dataset:i,useEndDate:a,endDate:r,style:s,showDay:u,flipperBgColor:c,flipperTextColor:f,flipperWidth:p,flipperHeight:h,flipperRadius:m,flipperGap:y,flipperType:_,flipperSpeed:v}=A1(e.chartConfig.option),b=Z1(),x=Z1(!1),A=Z1(i.value*1e3),S=Z1([]),w=Z1([]),M=Z1([]),D=Z1([]),T=q=>{const $=Math.max(q.toString().length,2);return q.toString().padStart($,"0").split("")},E=(q,$,z)=>{const F=Math.floor(q/24);S.value=T(F),w.value=T(u.value?q%24:q),M.value=T($),D.value=T(z)},U=({hours:q,minutes:$,seconds:z})=>{E(q,$,z)},V=()=>{var q,$;try{x.value=!1,A.value=a.value?r.value-new Date().getTime():i.value*1e3,(q=b.value)!=null&&q.reset&&(($=b.value)==null||$.reset()),x.value=!0}catch(z){console.log(z)}};return N1(()=>e.chartConfig.option.dataset,()=>{V()},{immediate:!0}),N1(()=>e.chartConfig.option.endDate,()=>{V()},{immediate:!0}),N1(()=>e.chartConfig.option.useEndDate,()=>{V()},{immediate:!0}),v0(()=>{V()}),(q,$)=>{const z=L("n-countdown"),F=L("n-space");return R(),e1("div",null,[ql(d(z,{ref_key:"countdownRef",ref:b,duration:A.value,render:U,active:x.value},null,8,["duration","active"]),[[Xl,!1]]),d(F,{class:"go-decorates-more-countdown",size:l(y),align:"center",justify:"center"},{default:g(()=>[l(u)?(R(),e1(S1,{key:0},[(R(!0),e1(S1,null,Y1(S.value,(G,J)=>(R(),p1(l(f5),{count:G,width:l(p),height:l(h),"front-color":l(f),"back-color":l(c),radius:l(m),"flip-type":l(_),duration:l(v),key:J,class:"go-d-block"},null,8,["count","width","height","front-color","back-color","radius","flip-type","duration"]))),128)),l(s)==="时分秒"?(R(),e1("div",fx,"天")):(R(),e1("div",px,":"))],64)):p2("",!0),(R(!0),e1(S1,null,Y1(w.value,(G,J)=>(R(),p1(l(f5),{count:G,width:l(p),height:l(h),"front-color":l(f),"back-color":l(c),radius:l(m),"flip-type":l(_),duration:l(v),key:J,class:"go-d-block"},null,8,["count","width","height","front-color","back-color","radius","flip-type","duration"]))),128)),l(s)==="时分秒"?(R(),e1("div",hx,"时")):(R(),e1("div",mx,":")),(R(!0),e1(S1,null,Y1(M.value,(G,J)=>(R(),p1(l(f5),{count:G,width:l(p),height:l(h),"front-color":l(f),"back-color":l(c),radius:l(m),"flip-type":l(_),duration:l(v),key:J,class:"go-d-block"},null,8,["count","width","height","front-color","back-color","radius","flip-type","duration"]))),128)),l(s)==="时分秒"?(R(),e1("div",gx,"分")):(R(),e1("div",vx,":")),(R(!0),e1(S1,null,Y1(D.value,(G,J)=>(R(),p1(l(f5),{count:G,width:l(p),height:l(h),"front-color":l(f),"back-color":l(c),radius:l(m),"flip-type":l(_),duration:l(v),key:J,class:"go-d-block"},null,8,["count","width","height","front-color","back-color","radius","flip-type","duration"]))),128)),l(s)==="时分秒"?(R(),e1("div",_x,"秒")):p2("",!0)]),_:1},8,["size"])])}}});const bx=u2(yx,[["__scopeId","data-v-c49213a2"]]),xx=Object.freeze(Object.defineProperty({__proto__:null,default:bx},Symbol.toStringTag,{value:"Module"})),Sx=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){a4(b=>({"0aa6998a":`${l(t)}px`,"09d338fb":`${l(n)}px`}));const e=o,{w:t,h:n}=A1(e.chartConfig.attr),{flipperLength:i,flipperBgColor:a,flipperTextColor:r,flipperWidth:s,flipperHeight:u,flipperRadius:c,flipperGap:f,flipperType:p,flipperSpeed:h,flipperBorderWidth:m}=A1(e.chartConfig.option),y=Z1([]),_=b=>b.toString().padStart(i.value,"0").split("").slice(i.value*-1),v=b=>{y.value=_(b)};return N1(()=>e.chartConfig.option,b=>{try{v(b.dataset)}catch(x){console.log(x)}},{immediate:!0,deep:!0}),d2(e.chartConfig,K1,b=>{v(b)}),(b,x)=>{const A=L("n-space");return R(),p1(A,{class:"go-decorates-flipper-number",size:l(f),align:"center",justify:"center"},{default:g(()=>[(R(!0),e1(S1,null,Y1(y.value,(S,w)=>(R(),p1(l(f5),{count:S,width:l(s),height:l(u),"front-color":l(r),"back-color":l(a),radius:l(c),"flip-type":l(p),duration:l(h),"border-width":l(m),key:w,class:"go-d-block"},null,8,["count","width","height","front-color","back-color","radius","flip-type","duration","border-width"]))),128))]),_:1},8,["size"])}}});const Mx=u2(Sx,[["__scopeId","data-v-a957e66d"]]),Ax=Object.freeze(Object.defineProperty({__proto__:null,default:Mx},Symbol.toStringTag,{value:"Module"})),vr=o=>(W4("data-v-f1e3bbdb"),o=o(),H4(),o),wx=vr(()=>j("path",{d:"M665.6 1017.6c-19.2 0-38.4-19.2-38.4-38.4s19.2-38.4 38.4-38.4h268.8l6.4-268.8c0-19.2 19.2-38.4 38.4-38.4s38.4 19.2 38.4 38.4v294.4c0 32-25.6 51.2-51.2 51.2h-300.8zM51.2 396.8c-19.2 0-38.4-19.2-38.4-38.4V64C12.8 32 38.4 12.8 64 12.8h294.4c19.2 0 38.4 19.2 38.4 38.4s-19.2 38.4-38.4 38.4H89.6v268.8c0 19.2-19.2 38.4-38.4 38.4zM64 1017.6c-32 0-51.2-25.6-51.2-51.2v-294.4c0-19.2 19.2-38.4 38.4-38.4s38.4 19.2 38.4 38.4v217.6l198.4-198.4c6.4-6.4 19.2-12.8 25.6-12.8s19.2 6.4 25.6 12.8c6.4 6.4 12.8 19.2 12.8 25.6 0 12.8-6.4 19.2-12.8 25.6l-198.4 198.4h217.6c19.2 0 38.4 19.2 38.4 38.4s-19.2 38.4-38.4 38.4H64z m915.2-620.8c-19.2 0-38.4-19.2-38.4-38.4V140.8l-198.4 198.4c-6.4 6.4-19.2 12.8-25.6 12.8-12.8 0-19.2-6.4-25.6-12.8-12.8-12.8-12.8-38.4 0-51.2l198.4-198.4h-217.6c-19.2 0-38.4-19.2-38.4-38.4s19.2-38.4 38.4-38.4h294.4c32 0 51.2 25.6 51.2 51.2v294.4c0 19.2-19.2 38.4-38.4 38.4z",class:"fullScreen-border"},null,-1)),Cx=[wx],Tx=vr(()=>j("path",{d:"M379.336 697.237L153.362 921.55c-14.11 14.007-36.905 13.922-50.912-0.188-14.007-14.11-13.922-36.905 0.188-50.912l227.6-225.927H138.645c-18.99 0-34.385-15.446-34.385-34.5 0-19.053 15.395-34.5 34.385-34.5H413.72c18.99 0 34.384 15.447 34.384 34.5v276c0 9.15-3.622 17.926-10.07 24.396a34.326 34.326 0 0 1-24.314 10.104 34.326 34.326 0 0 1-24.314-10.104 34.559 34.559 0 0 1-10.071-24.396V697.237z m263.395-366.88l227.813-227.813c14.059-14.059 36.853-14.059 50.912 0 14.059 14.059 14.059 36.853 0 50.912l-225.18 225.18h187.147c18.99 0 34.385 15.445 34.385 34.5 0 19.053-15.395 34.5-34.385 34.5H608.346c-18.99 0-34.384-15.447-34.384-34.5v-276c0-9.15 3.622-17.926 10.07-24.396a34.326 34.326 0 0 1 24.314-10.105c9.12 0 17.865 3.635 24.314 10.105a34.559 34.559 0 0 1 10.07 24.395v193.223zM99.385 410a34.326 34.326 0 0 1-24.314-10.105A34.559 34.559 0 0 1 65 375.5v-276C65 80.446 80.395 65 99.385 65h275.077c18.99 0 34.384 15.446 34.384 34.5 0 19.054-15.394 34.5-34.384 34.5H133.769v241.5c0 9.15-3.622 17.925-10.07 24.395A34.326 34.326 0 0 1 99.384 410z m825.23 552H649.538c-18.99 0-34.384-15.446-34.384-34.5 0-19.054 15.394-34.5 34.384-34.5h240.693V651.5c0-19.054 15.394-34.5 34.384-34.5 18.99 0 34.385 15.446 34.385 34.5v276c0 19.054-15.395 34.5-34.385 34.5z",class:"fullScreen-border"},null,-1)),Ex=[Tx],Dx=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){a4(f=>({"5591d40a":l(i),"541caf2a":l(t)+"px",ead5d5de:l(n)}));const e=o;let{border:t,bgColor:n,borderColor:i}=A1(e.chartConfig.option);const a=Z1(!1),r=()=>{a.value=!!(document.fullscreenElement||document.webkitFullscreenElement||document.mozFullScreenElement||document.msFullscreenElement)};r();const s=f=>{f.requestFullscreen?f.requestFullscreen():document.mozRequestFullScreen?document.mozRequestFullScreen():document.webkitRequestFullscreen?document.webkitRequestFullscreen():document.msRequestFullscreen&&document.msRequestFullscreen()},u=()=>{document.fullscreenElement&&document.exitFullscreen?document.exitFullscreen():document.mozFullScreenElement&&document.mozCancelFullScreen?document.mozCancelFullScreen():document.webkitFullscreenElement&&document.webkitExitFullscreen?document.webkitExitFullscreen():document.msFullscreenElement&&document.msExitFullscreen&&document.msExitFullscreen()},c=()=>{a.value?u():s(document.documentElement),a.value=!a.value,setTimeout(()=>{r()},1e3)};return v0(()=>{document.addEventListener("fullscreenchange",r),document.addEventListener("webkitfullscreenchange",r),document.addEventListener("mozfullscreenchange",r),document.addEventListener("MSFullscreenChange",r)}),o0(()=>{document.removeEventListener("fullscreenchange",r),document.removeEventListener("webkitfullscreenchange",r),document.removeEventListener("mozfullscreenchange",r),document.removeEventListener("MSFullscreenChange",r)}),(f,p)=>a.value?(R(),e1("svg",{key:1,onClick:c,viewBox:"0 0 1024 1024"},Ex)):(R(),e1("svg",{key:0,onClick:c,viewBox:"0 0 1024 1024"},Cx))}});const Ix=u2(Dx,[["__scopeId","data-v-f1e3bbdb"]]),Rx=Object.freeze(Object.defineProperty({__proto__:null,default:Ix},Symbol.toStringTag,{value:"Module"})),Ox=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){const e=o,t=V3({from:0,dataset:0});A1(e.chartConfig.attr);let{dur:n,showSeparator:i,prefixText:a,prefixColor:r,suffixText:s,suffixColor:u,precision:c,numberSize:f,numberColor:p}=A1(e.chartConfig.option);const h=m=>{t.from=t.dataset,t.dataset=m};return N1(()=>e.chartConfig.option.from,()=>{t.from=e.chartConfig.option.from},{immediate:!0}),N1(()=>e.chartConfig.option.dataset,()=>{t.dataset=e.chartConfig.option.dataset},{immediate:!0,deep:!1}),d2(e.chartConfig,K1,h),(m,y)=>{const _=L("n-number-animation"),v=L("n-statistic");return R(),p1(v,{"tabular-nums":"",class:"go-decorates-number"},{prefix:g(()=>[j("span",{style:f2(`color:${l(r)};font-size:${l(f)}px`)},D2(l(a)),5)]),suffix:g(()=>[j("span",{style:f2(`color:${l(u)};font-size:${l(f)}px`)},D2(l(s)),5)]),default:g(()=>[j("span",{style:f2(`color:${l(p)};font-size:${l(f)}px`)},[d(_,{from:t.from,to:t.dataset,duration:l(n)*1e3,"show-separator":l(i),precision:l(c)},null,8,["from","to","duration","show-separator","precision"])],4)]),_:1})}}});const Px=u2(Ox,[["__scopeId","data-v-58b96eba"]]),Lx=Object.freeze(Object.defineProperty({__proto__:null,default:Px},Symbol.toStringTag,{value:"Module"})),zx={class:"go-decorates-line"},kx=["width","height"],Nx=["y1","x2","y2","stroke","stroke-width"],Ux=["y1","x2","y2","stroke","stroke-width"],Fx=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){const e=o,{w:t,h:n}=A1(e.chartConfig.attr),{o_color:i,i_color:a,line_class:r}=A1(e.chartConfig.option);return(s,u)=>(R(),e1("div",zx,[(R(),e1("svg",{width:l(t),height:l(n)},[j("line",{x1:0,y1:l(n)/2,x2:l(t),y2:l(n)/2,stroke:l(i),"stroke-width":l(n)},null,8,Nx),j("line",{x1:0,y1:l(n)/2,x2:l(t),y2:l(n)/2,stroke:l(a),"stroke-width":l(n)/2,class:F5(l(r))},null,10,Ux)],8,kx))]))}});const Bx=u2(Fx,[["__scopeId","data-v-08b081bc"]]),Gx=Object.freeze(Object.defineProperty({__proto__:null,default:Bx},Symbol.toStringTag,{value:"Module"})),Vx={class:"go-decorates-line"},jx=["width","height"],Wx=["x1","x2","y2","stroke","stroke-width"],Hx=["x1","x2","y2","stroke","stroke-width"],$x=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){const e=o,{w:t,h:n}=A1(e.chartConfig.attr),{o_color:i,i_color:a,line_class:r}=A1(e.chartConfig.option);return(s,u)=>(R(),e1("div",Vx,[(R(),e1("svg",{width:l(t),height:l(n)},[j("line",{x1:l(t)/2,y1:0,x2:l(t)/2,y2:l(n),stroke:l(i),"stroke-width":l(t)},null,8,Wx),j("line",{x1:l(t)/2,y1:0,x2:l(t)/2,y2:l(n),stroke:l(a),"stroke-width":l(t)/2,class:F5(l(r))},null,10,Hx)],8,jx))]))}});const Yx=u2($x,[["__scopeId","data-v-8d16a9c4"]]),Jx=Object.freeze(Object.defineProperty({__proto__:null,default:Yx},Symbol.toStringTag,{value:"Module"})),Zx=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){const e=o;let t=Z1("2021-2-3"),n=Z1("08:00:00"),i=Z1("2021-2-3 08:00:00"),a=Z1("none"),r=null;const{w:s,h:u}=A1(e.chartConfig.attr);let{timeColor:c,timeSize:f,timeLineHeight:p,timeTextIndent:h,fontWeight:m,showShadow:y,hShadow:_,vShadow:v,blurShadow:b,colorShadow:x}=A1(e.chartConfig.option);return N1(e.chartConfig.option,()=>{try{e.chartConfig.option.showShadow?a.value=`${e.chartConfig.option.hShadow}px ${e.chartConfig.option.vShadow}px ${e.chartConfig.option.blurShadow}px ${e.chartConfig.option.colorShadow}`:a.value="none"}catch(A){console.log(A)}},{immediate:!0}),v0(()=>{r=setInterval(()=>{var A=new Date,S=A.getFullYear(),w=A.getMonth()+1<10?"0"+(A.getMonth()+1):A.getMonth()+1,M=A.getDate()<10?"0"+A.getDate():A.getDate(),D=A.getHours(),T=A.getMinutes(),E=A.getSeconds();let U="";D<10&&(U+="0"),U+=D+":",T<10&&(U+="0"),U+=T+":",E<10&&(U+="0"),U+=E,t.value=`${S}-${w}-${M}`,n.value=U,i.value=t.value+" "+n.value},500)}),o0(()=>{clearInterval(r)}),d2(e.chartConfig,K1),(A,S)=>(R(),e1("div",{class:"go-decorates-number",style:f2(`width:${l(s)}px;height:${l(u)}px;`)},[j("div",{style:f2(`color:${l(c)};font-size:${l(f)}px;line-height:${l(p)}px;
      letter-spacing:${l(h)}px;font-weight:${l(m)};
      text-shadow: ${l(a)}`)},D2(l(i)),5)],4))}});const qx=u2(Zx,[["__scopeId","data-v-c7a246c7"]]),Xx=Object.freeze(Object.defineProperty({__proto__:null,default:qx},Symbol.toStringTag,{value:"Module"}));/**
 * @license
 * Copyright 2010-2022 Three.js Authors
 * SPDX-License-Identifier: MIT
 */const dn="145",t9={LEFT:0,MIDDLE:1,RIGHT:2,ROTATE:0,DOLLY:1,PAN:2},n9={ROTATE:0,PAN:1,DOLLY_PAN:2,DOLLY_ROTATE:3},Qx=0,ci=1,Kx=2,_r=1,eS=2,p5=3,B9=0,n4=1,_4=2,u0=0,k0=1,di=2,fi=3,pi=4,tS=5,A9=100,nS=101,iS=102,hi=103,mi=104,oS=200,aS=201,rS=202,sS=203,yr=204,br=205,lS=206,uS=207,cS=208,dS=209,fS=210,pS=0,hS=1,mS=2,Ae=3,gS=4,vS=5,_S=6,yS=7,xr=0,bS=1,xS=2,F4=0,SS=1,MS=2,AS=3,wS=4,CS=5,Sr=300,G9=301,V9=302,we=303,Ce=304,l6=306,Te=1e3,d4=1001,Ee=1002,M3=1003,gi=1004,vi=1005,Z3=1006,TS=1007,u6=1008,j0=1009,ES=1010,DS=1011,Mr=1012,IS=1013,R0=1014,O0=1015,C5=1016,RS=1017,OS=1018,z9=1020,PS=1021,LS=1022,y4=1023,zS=1024,kS=1025,N0=1026,j9=1027,NS=1028,US=1029,FS=1030,BS=1031,GS=1033,D6=33776,I6=33777,R6=33778,O6=33779,_i=35840,yi=35841,bi=35842,xi=35843,VS=36196,Si=37492,Mi=37496,Ai=37808,wi=37809,Ci=37810,Ti=37811,Ei=37812,Di=37813,Ii=37814,Ri=37815,Oi=37816,Pi=37817,Li=37818,zi=37819,ki=37820,Ni=37821,Ui=36492,W0=3e3,F2=3001,jS=3200,WS=3201,HS=0,$S=1,O4="srgb",P0="srgb-linear",P6=7680,YS=519,De=35044,Fi="300 es",Ie=1035;class Z0{addEventListener(e,t){this._listeners===void 0&&(this._listeners={});const n=this._listeners;n[e]===void 0&&(n[e]=[]),n[e].indexOf(t)===-1&&n[e].push(t)}hasEventListener(e,t){if(this._listeners===void 0)return!1;const n=this._listeners;return n[e]!==void 0&&n[e].indexOf(t)!==-1}removeEventListener(e,t){if(this._listeners===void 0)return;const i=this._listeners[e];if(i!==void 0){const a=i.indexOf(t);a!==-1&&i.splice(a,1)}}dispatchEvent(e){if(this._listeners===void 0)return;const n=this._listeners[e.type];if(n!==void 0){e.target=this;const i=n.slice(0);for(let a=0,r=i.length;a<r;a++)i[a].call(this,e);e.target=null}}}const p3=["00","01","02","03","04","05","06","07","08","09","0a","0b","0c","0d","0e","0f","10","11","12","13","14","15","16","17","18","19","1a","1b","1c","1d","1e","1f","20","21","22","23","24","25","26","27","28","29","2a","2b","2c","2d","2e","2f","30","31","32","33","34","35","36","37","38","39","3a","3b","3c","3d","3e","3f","40","41","42","43","44","45","46","47","48","49","4a","4b","4c","4d","4e","4f","50","51","52","53","54","55","56","57","58","59","5a","5b","5c","5d","5e","5f","60","61","62","63","64","65","66","67","68","69","6a","6b","6c","6d","6e","6f","70","71","72","73","74","75","76","77","78","79","7a","7b","7c","7d","7e","7f","80","81","82","83","84","85","86","87","88","89","8a","8b","8c","8d","8e","8f","90","91","92","93","94","95","96","97","98","99","9a","9b","9c","9d","9e","9f","a0","a1","a2","a3","a4","a5","a6","a7","a8","a9","aa","ab","ac","ad","ae","af","b0","b1","b2","b3","b4","b5","b6","b7","b8","b9","ba","bb","bc","bd","be","bf","c0","c1","c2","c3","c4","c5","c6","c7","c8","c9","ca","cb","cc","cd","ce","cf","d0","d1","d2","d3","d4","d5","d6","d7","d8","d9","da","db","dc","dd","de","df","e0","e1","e2","e3","e4","e5","e6","e7","e8","e9","ea","eb","ec","ed","ee","ef","f0","f1","f2","f3","f4","f5","f6","f7","f8","f9","fa","fb","fc","fd","fe","ff"],L6=Math.PI/180,Bi=180/Math.PI;function c0(){const o=Math.random()*4294967295|0,e=Math.random()*4294967295|0,t=Math.random()*4294967295|0,n=Math.random()*4294967295|0;return(p3[o&255]+p3[o>>8&255]+p3[o>>16&255]+p3[o>>24&255]+"-"+p3[e&255]+p3[e>>8&255]+"-"+p3[e>>16&15|64]+p3[e>>24&255]+"-"+p3[t&63|128]+p3[t>>8&255]+"-"+p3[t>>16&255]+p3[t>>24&255]+p3[n&255]+p3[n>>8&255]+p3[n>>16&255]+p3[n>>24&255]).toLowerCase()}function m3(o,e,t){return Math.max(e,Math.min(t,o))}function JS(o,e){return(o%e+e)%e}function z6(o,e,t){return(1-t)*o+t*e}function Gi(o){return(o&o-1)===0&&o!==0}function Re(o){return Math.pow(2,Math.floor(Math.log(o)/Math.LN2))}function a0(o,e){switch(e.constructor){case Float32Array:return o;case Uint16Array:return o/65535;case Uint8Array:return o/255;case Int16Array:return Math.max(o/32767,-1);case Int8Array:return Math.max(o/127,-1);default:throw new Error("Invalid component type.")}}function T2(o,e){switch(e.constructor){case Float32Array:return o;case Uint16Array:return Math.round(o*65535);case Uint8Array:return Math.round(o*255);case Int16Array:return Math.round(o*32767);case Int8Array:return Math.round(o*127);default:throw new Error("Invalid component type.")}}class G1{constructor(e=0,t=0){G1.prototype.isVector2=!0,this.x=e,this.y=t}get width(){return this.x}set width(e){this.x=e}get height(){return this.y}set height(e){this.y=e}set(e,t){return this.x=e,this.y=t,this}setScalar(e){return this.x=e,this.y=e,this}setX(e){return this.x=e,this}setY(e){return this.y=e,this}setComponent(e,t){switch(e){case 0:this.x=t;break;case 1:this.y=t;break;default:throw new Error("index is out of range: "+e)}return this}getComponent(e){switch(e){case 0:return this.x;case 1:return this.y;default:throw new Error("index is out of range: "+e)}}clone(){return new this.constructor(this.x,this.y)}copy(e){return this.x=e.x,this.y=e.y,this}add(e){return this.x+=e.x,this.y+=e.y,this}addScalar(e){return this.x+=e,this.y+=e,this}addVectors(e,t){return this.x=e.x+t.x,this.y=e.y+t.y,this}addScaledVector(e,t){return this.x+=e.x*t,this.y+=e.y*t,this}sub(e){return this.x-=e.x,this.y-=e.y,this}subScalar(e){return this.x-=e,this.y-=e,this}subVectors(e,t){return this.x=e.x-t.x,this.y=e.y-t.y,this}multiply(e){return this.x*=e.x,this.y*=e.y,this}multiplyScalar(e){return this.x*=e,this.y*=e,this}divide(e){return this.x/=e.x,this.y/=e.y,this}divideScalar(e){return this.multiplyScalar(1/e)}applyMatrix3(e){const t=this.x,n=this.y,i=e.elements;return this.x=i[0]*t+i[3]*n+i[6],this.y=i[1]*t+i[4]*n+i[7],this}min(e){return this.x=Math.min(this.x,e.x),this.y=Math.min(this.y,e.y),this}max(e){return this.x=Math.max(this.x,e.x),this.y=Math.max(this.y,e.y),this}clamp(e,t){return this.x=Math.max(e.x,Math.min(t.x,this.x)),this.y=Math.max(e.y,Math.min(t.y,this.y)),this}clampScalar(e,t){return this.x=Math.max(e,Math.min(t,this.x)),this.y=Math.max(e,Math.min(t,this.y)),this}clampLength(e,t){const n=this.length();return this.divideScalar(n||1).multiplyScalar(Math.max(e,Math.min(t,n)))}floor(){return this.x=Math.floor(this.x),this.y=Math.floor(this.y),this}ceil(){return this.x=Math.ceil(this.x),this.y=Math.ceil(this.y),this}round(){return this.x=Math.round(this.x),this.y=Math.round(this.y),this}roundToZero(){return this.x=this.x<0?Math.ceil(this.x):Math.floor(this.x),this.y=this.y<0?Math.ceil(this.y):Math.floor(this.y),this}negate(){return this.x=-this.x,this.y=-this.y,this}dot(e){return this.x*e.x+this.y*e.y}cross(e){return this.x*e.y-this.y*e.x}lengthSq(){return this.x*this.x+this.y*this.y}length(){return Math.sqrt(this.x*this.x+this.y*this.y)}manhattanLength(){return Math.abs(this.x)+Math.abs(this.y)}normalize(){return this.divideScalar(this.length()||1)}angle(){return Math.atan2(-this.y,-this.x)+Math.PI}distanceTo(e){return Math.sqrt(this.distanceToSquared(e))}distanceToSquared(e){const t=this.x-e.x,n=this.y-e.y;return t*t+n*n}manhattanDistanceTo(e){return Math.abs(this.x-e.x)+Math.abs(this.y-e.y)}setLength(e){return this.normalize().multiplyScalar(e)}lerp(e,t){return this.x+=(e.x-this.x)*t,this.y+=(e.y-this.y)*t,this}lerpVectors(e,t,n){return this.x=e.x+(t.x-e.x)*n,this.y=e.y+(t.y-e.y)*n,this}equals(e){return e.x===this.x&&e.y===this.y}fromArray(e,t=0){return this.x=e[t],this.y=e[t+1],this}toArray(e=[],t=0){return e[t]=this.x,e[t+1]=this.y,e}fromBufferAttribute(e,t){return this.x=e.getX(t),this.y=e.getY(t),this}rotateAround(e,t){const n=Math.cos(t),i=Math.sin(t),a=this.x-e.x,r=this.y-e.y;return this.x=a*n-r*i+e.x,this.y=a*i+r*n+e.y,this}random(){return this.x=Math.random(),this.y=Math.random(),this}*[Symbol.iterator](){yield this.x,yield this.y}}class X3{constructor(){X3.prototype.isMatrix3=!0,this.elements=[1,0,0,0,1,0,0,0,1]}set(e,t,n,i,a,r,s,u,c){const f=this.elements;return f[0]=e,f[1]=i,f[2]=s,f[3]=t,f[4]=a,f[5]=u,f[6]=n,f[7]=r,f[8]=c,this}identity(){return this.set(1,0,0,0,1,0,0,0,1),this}copy(e){const t=this.elements,n=e.elements;return t[0]=n[0],t[1]=n[1],t[2]=n[2],t[3]=n[3],t[4]=n[4],t[5]=n[5],t[6]=n[6],t[7]=n[7],t[8]=n[8],this}extractBasis(e,t,n){return e.setFromMatrix3Column(this,0),t.setFromMatrix3Column(this,1),n.setFromMatrix3Column(this,2),this}setFromMatrix4(e){const t=e.elements;return this.set(t[0],t[4],t[8],t[1],t[5],t[9],t[2],t[6],t[10]),this}multiply(e){return this.multiplyMatrices(this,e)}premultiply(e){return this.multiplyMatrices(e,this)}multiplyMatrices(e,t){const n=e.elements,i=t.elements,a=this.elements,r=n[0],s=n[3],u=n[6],c=n[1],f=n[4],p=n[7],h=n[2],m=n[5],y=n[8],_=i[0],v=i[3],b=i[6],x=i[1],A=i[4],S=i[7],w=i[2],M=i[5],D=i[8];return a[0]=r*_+s*x+u*w,a[3]=r*v+s*A+u*M,a[6]=r*b+s*S+u*D,a[1]=c*_+f*x+p*w,a[4]=c*v+f*A+p*M,a[7]=c*b+f*S+p*D,a[2]=h*_+m*x+y*w,a[5]=h*v+m*A+y*M,a[8]=h*b+m*S+y*D,this}multiplyScalar(e){const t=this.elements;return t[0]*=e,t[3]*=e,t[6]*=e,t[1]*=e,t[4]*=e,t[7]*=e,t[2]*=e,t[5]*=e,t[8]*=e,this}determinant(){const e=this.elements,t=e[0],n=e[1],i=e[2],a=e[3],r=e[4],s=e[5],u=e[6],c=e[7],f=e[8];return t*r*f-t*s*c-n*a*f+n*s*u+i*a*c-i*r*u}invert(){const e=this.elements,t=e[0],n=e[1],i=e[2],a=e[3],r=e[4],s=e[5],u=e[6],c=e[7],f=e[8],p=f*r-s*c,h=s*u-f*a,m=c*a-r*u,y=t*p+n*h+i*m;if(y===0)return this.set(0,0,0,0,0,0,0,0,0);const _=1/y;return e[0]=p*_,e[1]=(i*c-f*n)*_,e[2]=(s*n-i*r)*_,e[3]=h*_,e[4]=(f*t-i*u)*_,e[5]=(i*a-s*t)*_,e[6]=m*_,e[7]=(n*u-c*t)*_,e[8]=(r*t-n*a)*_,this}transpose(){let e;const t=this.elements;return e=t[1],t[1]=t[3],t[3]=e,e=t[2],t[2]=t[6],t[6]=e,e=t[5],t[5]=t[7],t[7]=e,this}getNormalMatrix(e){return this.setFromMatrix4(e).invert().transpose()}transposeIntoArray(e){const t=this.elements;return e[0]=t[0],e[1]=t[3],e[2]=t[6],e[3]=t[1],e[4]=t[4],e[5]=t[7],e[6]=t[2],e[7]=t[5],e[8]=t[8],this}setUvTransform(e,t,n,i,a,r,s){const u=Math.cos(a),c=Math.sin(a);return this.set(n*u,n*c,-n*(u*r+c*s)+r+e,-i*c,i*u,-i*(-c*r+u*s)+s+t,0,0,1),this}scale(e,t){const n=this.elements;return n[0]*=e,n[3]*=e,n[6]*=e,n[1]*=t,n[4]*=t,n[7]*=t,this}rotate(e){const t=Math.cos(e),n=Math.sin(e),i=this.elements,a=i[0],r=i[3],s=i[6],u=i[1],c=i[4],f=i[7];return i[0]=t*a+n*u,i[3]=t*r+n*c,i[6]=t*s+n*f,i[1]=-n*a+t*u,i[4]=-n*r+t*c,i[7]=-n*s+t*f,this}translate(e,t){const n=this.elements;return n[0]+=e*n[2],n[3]+=e*n[5],n[6]+=e*n[8],n[1]+=t*n[2],n[4]+=t*n[5],n[7]+=t*n[8],this}equals(e){const t=this.elements,n=e.elements;for(let i=0;i<9;i++)if(t[i]!==n[i])return!1;return!0}fromArray(e,t=0){for(let n=0;n<9;n++)this.elements[n]=e[n+t];return this}toArray(e=[],t=0){const n=this.elements;return e[t]=n[0],e[t+1]=n[1],e[t+2]=n[2],e[t+3]=n[3],e[t+4]=n[4],e[t+5]=n[5],e[t+6]=n[6],e[t+7]=n[7],e[t+8]=n[8],e}clone(){return new this.constructor().fromArray(this.elements)}}function Ar(o){for(let e=o.length-1;e>=0;--e)if(o[e]>=65535)return!0;return!1}function T5(o){return document.createElementNS("http://www.w3.org/1999/xhtml",o)}function U0(o){return o<.04045?o*.0773993808:Math.pow(o*.9478672986+.0521327014,2.4)}function P8(o){return o<.0031308?o*12.92:1.055*Math.pow(o,.41666)-.055}const k6={[O4]:{[P0]:U0},[P0]:{[O4]:P8}},s4={legacyMode:!0,get workingColorSpace(){return P0},set workingColorSpace(o){console.warn("THREE.ColorManagement: .workingColorSpace is readonly.")},convert:function(o,e,t){if(this.legacyMode||e===t||!e||!t)return o;if(k6[e]&&k6[e][t]!==void 0){const n=k6[e][t];return o.r=n(o.r),o.g=n(o.g),o.b=n(o.b),o}throw new Error("Unsupported color space conversion.")},fromWorkingColorSpace:function(o,e){return this.convert(o,this.workingColorSpace,e)},toWorkingColorSpace:function(o,e){return this.convert(o,e,this.workingColorSpace)}},wr={aliceblue:15792383,antiquewhite:16444375,aqua:65535,aquamarine:8388564,azure:15794175,beige:16119260,bisque:16770244,black:0,blanchedalmond:16772045,blue:255,blueviolet:9055202,brown:10824234,burlywood:14596231,cadetblue:6266528,chartreuse:8388352,chocolate:13789470,coral:16744272,cornflowerblue:6591981,cornsilk:16775388,crimson:14423100,cyan:65535,darkblue:139,darkcyan:35723,darkgoldenrod:12092939,darkgray:11119017,darkgreen:25600,darkgrey:11119017,darkkhaki:12433259,darkmagenta:9109643,darkolivegreen:5597999,darkorange:16747520,darkorchid:10040012,darkred:9109504,darksalmon:15308410,darkseagreen:9419919,darkslateblue:4734347,darkslategray:3100495,darkslategrey:3100495,darkturquoise:52945,darkviolet:9699539,deeppink:16716947,deepskyblue:49151,dimgray:6908265,dimgrey:6908265,dodgerblue:2003199,firebrick:11674146,floralwhite:16775920,forestgreen:2263842,fuchsia:16711935,gainsboro:14474460,ghostwhite:16316671,gold:16766720,goldenrod:14329120,gray:8421504,green:32768,greenyellow:11403055,grey:8421504,honeydew:15794160,hotpink:16738740,indianred:13458524,indigo:4915330,ivory:16777200,khaki:15787660,lavender:15132410,lavenderblush:16773365,lawngreen:8190976,lemonchiffon:16775885,lightblue:11393254,lightcoral:15761536,lightcyan:14745599,lightgoldenrodyellow:16448210,lightgray:13882323,lightgreen:9498256,lightgrey:13882323,lightpink:16758465,lightsalmon:16752762,lightseagreen:2142890,lightskyblue:8900346,lightslategray:7833753,lightslategrey:7833753,lightsteelblue:11584734,lightyellow:16777184,lime:65280,limegreen:3329330,linen:16445670,magenta:16711935,maroon:8388608,mediumaquamarine:6737322,mediumblue:205,mediumorchid:12211667,mediumpurple:9662683,mediumseagreen:3978097,mediumslateblue:8087790,mediumspringgreen:64154,mediumturquoise:4772300,mediumvioletred:13047173,midnightblue:1644912,mintcream:16121850,mistyrose:16770273,moccasin:16770229,navajowhite:16768685,navy:128,oldlace:16643558,olive:8421376,olivedrab:7048739,orange:16753920,orangered:16729344,orchid:14315734,palegoldenrod:15657130,palegreen:10025880,paleturquoise:11529966,palevioletred:14381203,papayawhip:16773077,peachpuff:16767673,peru:13468991,pink:16761035,plum:14524637,powderblue:11591910,purple:8388736,rebeccapurple:6697881,red:16711680,rosybrown:12357519,royalblue:4286945,saddlebrown:9127187,salmon:16416882,sandybrown:16032864,seagreen:3050327,seashell:16774638,sienna:10506797,silver:12632256,skyblue:8900331,slateblue:6970061,slategray:7372944,slategrey:7372944,snow:16775930,springgreen:65407,steelblue:4620980,tan:13808780,teal:32896,thistle:14204888,tomato:16737095,turquoise:4251856,violet:15631086,wheat:16113331,white:16777215,whitesmoke:16119285,yellow:16776960,yellowgreen:10145074},K2={r:0,g:0,b:0},l4={h:0,s:0,l:0},n8={h:0,s:0,l:0};function N6(o,e,t){return t<0&&(t+=1),t>1&&(t-=1),t<1/6?o+(e-o)*6*t:t<1/2?e:t<2/3?o+(e-o)*6*(2/3-t):o}function i8(o,e){return e.r=o.r,e.g=o.g,e.b=o.b,e}class y2{constructor(e,t,n){return this.isColor=!0,this.r=1,this.g=1,this.b=1,t===void 0&&n===void 0?this.set(e):this.setRGB(e,t,n)}set(e){return e&&e.isColor?this.copy(e):typeof e=="number"?this.setHex(e):typeof e=="string"&&this.setStyle(e),this}setScalar(e){return this.r=e,this.g=e,this.b=e,this}setHex(e,t=O4){return e=Math.floor(e),this.r=(e>>16&255)/255,this.g=(e>>8&255)/255,this.b=(e&255)/255,s4.toWorkingColorSpace(this,t),this}setRGB(e,t,n,i=P0){return this.r=e,this.g=t,this.b=n,s4.toWorkingColorSpace(this,i),this}setHSL(e,t,n,i=P0){if(e=JS(e,1),t=m3(t,0,1),n=m3(n,0,1),t===0)this.r=this.g=this.b=n;else{const a=n<=.5?n*(1+t):n+t-n*t,r=2*n-a;this.r=N6(r,a,e+1/3),this.g=N6(r,a,e),this.b=N6(r,a,e-1/3)}return s4.toWorkingColorSpace(this,i),this}setStyle(e,t=O4){function n(a){a!==void 0&&parseFloat(a)<1&&console.warn("THREE.Color: Alpha component of "+e+" will be ignored.")}let i;if(i=/^((?:rgb|hsl)a?)\(([^\)]*)\)/.exec(e)){let a;const r=i[1],s=i[2];switch(r){case"rgb":case"rgba":if(a=/^\s*(\d+)\s*,\s*(\d+)\s*,\s*(\d+)\s*(?:,\s*(\d*\.?\d+)\s*)?$/.exec(s))return this.r=Math.min(255,parseInt(a[1],10))/255,this.g=Math.min(255,parseInt(a[2],10))/255,this.b=Math.min(255,parseInt(a[3],10))/255,s4.toWorkingColorSpace(this,t),n(a[4]),this;if(a=/^\s*(\d+)\%\s*,\s*(\d+)\%\s*,\s*(\d+)\%\s*(?:,\s*(\d*\.?\d+)\s*)?$/.exec(s))return this.r=Math.min(100,parseInt(a[1],10))/100,this.g=Math.min(100,parseInt(a[2],10))/100,this.b=Math.min(100,parseInt(a[3],10))/100,s4.toWorkingColorSpace(this,t),n(a[4]),this;break;case"hsl":case"hsla":if(a=/^\s*(\d*\.?\d+)\s*,\s*(\d*\.?\d+)\%\s*,\s*(\d*\.?\d+)\%\s*(?:,\s*(\d*\.?\d+)\s*)?$/.exec(s)){const u=parseFloat(a[1])/360,c=parseFloat(a[2])/100,f=parseFloat(a[3])/100;return n(a[4]),this.setHSL(u,c,f,t)}break}}else if(i=/^\#([A-Fa-f\d]+)$/.exec(e)){const a=i[1],r=a.length;if(r===3)return this.r=parseInt(a.charAt(0)+a.charAt(0),16)/255,this.g=parseInt(a.charAt(1)+a.charAt(1),16)/255,this.b=parseInt(a.charAt(2)+a.charAt(2),16)/255,s4.toWorkingColorSpace(this,t),this;if(r===6)return this.r=parseInt(a.charAt(0)+a.charAt(1),16)/255,this.g=parseInt(a.charAt(2)+a.charAt(3),16)/255,this.b=parseInt(a.charAt(4)+a.charAt(5),16)/255,s4.toWorkingColorSpace(this,t),this}return e&&e.length>0?this.setColorName(e,t):this}setColorName(e,t=O4){const n=wr[e.toLowerCase()];return n!==void 0?this.setHex(n,t):console.warn("THREE.Color: Unknown color "+e),this}clone(){return new this.constructor(this.r,this.g,this.b)}copy(e){return this.r=e.r,this.g=e.g,this.b=e.b,this}copySRGBToLinear(e){return this.r=U0(e.r),this.g=U0(e.g),this.b=U0(e.b),this}copyLinearToSRGB(e){return this.r=P8(e.r),this.g=P8(e.g),this.b=P8(e.b),this}convertSRGBToLinear(){return this.copySRGBToLinear(this),this}convertLinearToSRGB(){return this.copyLinearToSRGB(this),this}getHex(e=O4){return s4.fromWorkingColorSpace(i8(this,K2),e),m3(K2.r*255,0,255)<<16^m3(K2.g*255,0,255)<<8^m3(K2.b*255,0,255)<<0}getHexString(e=O4){return("000000"+this.getHex(e).toString(16)).slice(-6)}getHSL(e,t=P0){s4.fromWorkingColorSpace(i8(this,K2),t);const n=K2.r,i=K2.g,a=K2.b,r=Math.max(n,i,a),s=Math.min(n,i,a);let u,c;const f=(s+r)/2;if(s===r)u=0,c=0;else{const p=r-s;switch(c=f<=.5?p/(r+s):p/(2-r-s),r){case n:u=(i-a)/p+(i<a?6:0);break;case i:u=(a-n)/p+2;break;case a:u=(n-i)/p+4;break}u/=6}return e.h=u,e.s=c,e.l=f,e}getRGB(e,t=P0){return s4.fromWorkingColorSpace(i8(this,K2),t),e.r=K2.r,e.g=K2.g,e.b=K2.b,e}getStyle(e=O4){return s4.fromWorkingColorSpace(i8(this,K2),e),e!==O4?`color(${e} ${K2.r} ${K2.g} ${K2.b})`:`rgb(${K2.r*255|0},${K2.g*255|0},${K2.b*255|0})`}offsetHSL(e,t,n){return this.getHSL(l4),l4.h+=e,l4.s+=t,l4.l+=n,this.setHSL(l4.h,l4.s,l4.l),this}add(e){return this.r+=e.r,this.g+=e.g,this.b+=e.b,this}addColors(e,t){return this.r=e.r+t.r,this.g=e.g+t.g,this.b=e.b+t.b,this}addScalar(e){return this.r+=e,this.g+=e,this.b+=e,this}sub(e){return this.r=Math.max(0,this.r-e.r),this.g=Math.max(0,this.g-e.g),this.b=Math.max(0,this.b-e.b),this}multiply(e){return this.r*=e.r,this.g*=e.g,this.b*=e.b,this}multiplyScalar(e){return this.r*=e,this.g*=e,this.b*=e,this}lerp(e,t){return this.r+=(e.r-this.r)*t,this.g+=(e.g-this.g)*t,this.b+=(e.b-this.b)*t,this}lerpColors(e,t,n){return this.r=e.r+(t.r-e.r)*n,this.g=e.g+(t.g-e.g)*n,this.b=e.b+(t.b-e.b)*n,this}lerpHSL(e,t){this.getHSL(l4),e.getHSL(n8);const n=z6(l4.h,n8.h,t),i=z6(l4.s,n8.s,t),a=z6(l4.l,n8.l,t);return this.setHSL(n,i,a),this}equals(e){return e.r===this.r&&e.g===this.g&&e.b===this.b}fromArray(e,t=0){return this.r=e[t],this.g=e[t+1],this.b=e[t+2],this}toArray(e=[],t=0){return e[t]=this.r,e[t+1]=this.g,e[t+2]=this.b,e}fromBufferAttribute(e,t){return this.r=e.getX(t),this.g=e.getY(t),this.b=e.getZ(t),this}toJSON(){return this.getHex()}*[Symbol.iterator](){yield this.r,yield this.g,yield this.b}}y2.NAMES=wr;let i9;class Cr{static getDataURL(e){if(/^data:/i.test(e.src)||typeof HTMLCanvasElement=="undefined")return e.src;let t;if(e instanceof HTMLCanvasElement)t=e;else{i9===void 0&&(i9=T5("canvas")),i9.width=e.width,i9.height=e.height;const n=i9.getContext("2d");e instanceof ImageData?n.putImageData(e,0,0):n.drawImage(e,0,0,e.width,e.height),t=i9}return t.width>2048||t.height>2048?(console.warn("THREE.ImageUtils.getDataURL: Image converted to jpg for performance reasons",e),t.toDataURL("image/jpeg",.6)):t.toDataURL("image/png")}static sRGBToLinear(e){if(typeof HTMLImageElement!="undefined"&&e instanceof HTMLImageElement||typeof HTMLCanvasElement!="undefined"&&e instanceof HTMLCanvasElement||typeof ImageBitmap!="undefined"&&e instanceof ImageBitmap){const t=T5("canvas");t.width=e.width,t.height=e.height;const n=t.getContext("2d");n.drawImage(e,0,0,e.width,e.height);const i=n.getImageData(0,0,e.width,e.height),a=i.data;for(let r=0;r<a.length;r++)a[r]=U0(a[r]/255)*255;return n.putImageData(i,0,0),t}else if(e.data){const t=e.data.slice(0);for(let n=0;n<t.length;n++)t instanceof Uint8Array||t instanceof Uint8ClampedArray?t[n]=Math.floor(U0(t[n]/255)*255):t[n]=U0(t[n]);return{data:t,width:e.width,height:e.height}}else return console.warn("THREE.ImageUtils.sRGBToLinear(): Unsupported image type. No color space conversion applied."),e}}class Tr{constructor(e=null){this.isSource=!0,this.uuid=c0(),this.data=e,this.version=0}set needsUpdate(e){e===!0&&this.version++}toJSON(e){const t=e===void 0||typeof e=="string";if(!t&&e.images[this.uuid]!==void 0)return e.images[this.uuid];const n={uuid:this.uuid,url:""},i=this.data;if(i!==null){let a;if(Array.isArray(i)){a=[];for(let r=0,s=i.length;r<s;r++)i[r].isDataTexture?a.push(U6(i[r].image)):a.push(U6(i[r]))}else a=U6(i);n.url=a}return t||(e.images[this.uuid]=n),n}}function U6(o){return typeof HTMLImageElement!="undefined"&&o instanceof HTMLImageElement||typeof HTMLCanvasElement!="undefined"&&o instanceof HTMLCanvasElement||typeof ImageBitmap!="undefined"&&o instanceof ImageBitmap?Cr.getDataURL(o):o.data?{data:Array.from(o.data),width:o.width,height:o.height,type:o.data.constructor.name}:(console.warn("THREE.Texture: Unable to serialize Texture."),{})}let ZS=0;class i4 extends Z0{constructor(e=i4.DEFAULT_IMAGE,t=i4.DEFAULT_MAPPING,n=d4,i=d4,a=Z3,r=u6,s=y4,u=j0,c=1,f=W0){super(),this.isTexture=!0,Object.defineProperty(this,"id",{value:ZS++}),this.uuid=c0(),this.name="",this.source=new Tr(e),this.mipmaps=[],this.mapping=t,this.wrapS=n,this.wrapT=i,this.magFilter=a,this.minFilter=r,this.anisotropy=c,this.format=s,this.internalFormat=null,this.type=u,this.offset=new G1(0,0),this.repeat=new G1(1,1),this.center=new G1(0,0),this.rotation=0,this.matrixAutoUpdate=!0,this.matrix=new X3,this.generateMipmaps=!0,this.premultiplyAlpha=!1,this.flipY=!0,this.unpackAlignment=4,this.encoding=f,this.userData={},this.version=0,this.onUpdate=null,this.isRenderTargetTexture=!1,this.needsPMREMUpdate=!1}get image(){return this.source.data}set image(e){this.source.data=e}updateMatrix(){this.matrix.setUvTransform(this.offset.x,this.offset.y,this.repeat.x,this.repeat.y,this.rotation,this.center.x,this.center.y)}clone(){return new this.constructor().copy(this)}copy(e){return this.name=e.name,this.source=e.source,this.mipmaps=e.mipmaps.slice(0),this.mapping=e.mapping,this.wrapS=e.wrapS,this.wrapT=e.wrapT,this.magFilter=e.magFilter,this.minFilter=e.minFilter,this.anisotropy=e.anisotropy,this.format=e.format,this.internalFormat=e.internalFormat,this.type=e.type,this.offset.copy(e.offset),this.repeat.copy(e.repeat),this.center.copy(e.center),this.rotation=e.rotation,this.matrixAutoUpdate=e.matrixAutoUpdate,this.matrix.copy(e.matrix),this.generateMipmaps=e.generateMipmaps,this.premultiplyAlpha=e.premultiplyAlpha,this.flipY=e.flipY,this.unpackAlignment=e.unpackAlignment,this.encoding=e.encoding,this.userData=JSON.parse(JSON.stringify(e.userData)),this.needsUpdate=!0,this}toJSON(e){const t=e===void 0||typeof e=="string";if(!t&&e.textures[this.uuid]!==void 0)return e.textures[this.uuid];const n={metadata:{version:4.5,type:"Texture",generator:"Texture.toJSON"},uuid:this.uuid,name:this.name,image:this.source.toJSON(e).uuid,mapping:this.mapping,repeat:[this.repeat.x,this.repeat.y],offset:[this.offset.x,this.offset.y],center:[this.center.x,this.center.y],rotation:this.rotation,wrap:[this.wrapS,this.wrapT],format:this.format,type:this.type,encoding:this.encoding,minFilter:this.minFilter,magFilter:this.magFilter,anisotropy:this.anisotropy,flipY:this.flipY,premultiplyAlpha:this.premultiplyAlpha,unpackAlignment:this.unpackAlignment};return JSON.stringify(this.userData)!=="{}"&&(n.userData=this.userData),t||(e.textures[this.uuid]=n),n}dispose(){this.dispatchEvent({type:"dispose"})}transformUv(e){if(this.mapping!==Sr)return e;if(e.applyMatrix3(this.matrix),e.x<0||e.x>1)switch(this.wrapS){case Te:e.x=e.x-Math.floor(e.x);break;case d4:e.x=e.x<0?0:1;break;case Ee:Math.abs(Math.floor(e.x)%2)===1?e.x=Math.ceil(e.x)-e.x:e.x=e.x-Math.floor(e.x);break}if(e.y<0||e.y>1)switch(this.wrapT){case Te:e.y=e.y-Math.floor(e.y);break;case d4:e.y=e.y<0?0:1;break;case Ee:Math.abs(Math.floor(e.y)%2)===1?e.y=Math.ceil(e.y)-e.y:e.y=e.y-Math.floor(e.y);break}return this.flipY&&(e.y=1-e.y),e}set needsUpdate(e){e===!0&&(this.version++,this.source.needsUpdate=!0)}}i4.DEFAULT_IMAGE=null;i4.DEFAULT_MAPPING=Sr;class c3{constructor(e=0,t=0,n=0,i=1){c3.prototype.isVector4=!0,this.x=e,this.y=t,this.z=n,this.w=i}get width(){return this.z}set width(e){this.z=e}get height(){return this.w}set height(e){this.w=e}set(e,t,n,i){return this.x=e,this.y=t,this.z=n,this.w=i,this}setScalar(e){return this.x=e,this.y=e,this.z=e,this.w=e,this}setX(e){return this.x=e,this}setY(e){return this.y=e,this}setZ(e){return this.z=e,this}setW(e){return this.w=e,this}setComponent(e,t){switch(e){case 0:this.x=t;break;case 1:this.y=t;break;case 2:this.z=t;break;case 3:this.w=t;break;default:throw new Error("index is out of range: "+e)}return this}getComponent(e){switch(e){case 0:return this.x;case 1:return this.y;case 2:return this.z;case 3:return this.w;default:throw new Error("index is out of range: "+e)}}clone(){return new this.constructor(this.x,this.y,this.z,this.w)}copy(e){return this.x=e.x,this.y=e.y,this.z=e.z,this.w=e.w!==void 0?e.w:1,this}add(e){return this.x+=e.x,this.y+=e.y,this.z+=e.z,this.w+=e.w,this}addScalar(e){return this.x+=e,this.y+=e,this.z+=e,this.w+=e,this}addVectors(e,t){return this.x=e.x+t.x,this.y=e.y+t.y,this.z=e.z+t.z,this.w=e.w+t.w,this}addScaledVector(e,t){return this.x+=e.x*t,this.y+=e.y*t,this.z+=e.z*t,this.w+=e.w*t,this}sub(e){return this.x-=e.x,this.y-=e.y,this.z-=e.z,this.w-=e.w,this}subScalar(e){return this.x-=e,this.y-=e,this.z-=e,this.w-=e,this}subVectors(e,t){return this.x=e.x-t.x,this.y=e.y-t.y,this.z=e.z-t.z,this.w=e.w-t.w,this}multiply(e){return this.x*=e.x,this.y*=e.y,this.z*=e.z,this.w*=e.w,this}multiplyScalar(e){return this.x*=e,this.y*=e,this.z*=e,this.w*=e,this}applyMatrix4(e){const t=this.x,n=this.y,i=this.z,a=this.w,r=e.elements;return this.x=r[0]*t+r[4]*n+r[8]*i+r[12]*a,this.y=r[1]*t+r[5]*n+r[9]*i+r[13]*a,this.z=r[2]*t+r[6]*n+r[10]*i+r[14]*a,this.w=r[3]*t+r[7]*n+r[11]*i+r[15]*a,this}divideScalar(e){return this.multiplyScalar(1/e)}setAxisAngleFromQuaternion(e){this.w=2*Math.acos(e.w);const t=Math.sqrt(1-e.w*e.w);return t<1e-4?(this.x=1,this.y=0,this.z=0):(this.x=e.x/t,this.y=e.y/t,this.z=e.z/t),this}setAxisAngleFromRotationMatrix(e){let t,n,i,a;const u=e.elements,c=u[0],f=u[4],p=u[8],h=u[1],m=u[5],y=u[9],_=u[2],v=u[6],b=u[10];if(Math.abs(f-h)<.01&&Math.abs(p-_)<.01&&Math.abs(y-v)<.01){if(Math.abs(f+h)<.1&&Math.abs(p+_)<.1&&Math.abs(y+v)<.1&&Math.abs(c+m+b-3)<.1)return this.set(1,0,0,0),this;t=Math.PI;const A=(c+1)/2,S=(m+1)/2,w=(b+1)/2,M=(f+h)/4,D=(p+_)/4,T=(y+v)/4;return A>S&&A>w?A<.01?(n=0,i=.707106781,a=.707106781):(n=Math.sqrt(A),i=M/n,a=D/n):S>w?S<.01?(n=.707106781,i=0,a=.707106781):(i=Math.sqrt(S),n=M/i,a=T/i):w<.01?(n=.707106781,i=.707106781,a=0):(a=Math.sqrt(w),n=D/a,i=T/a),this.set(n,i,a,t),this}let x=Math.sqrt((v-y)*(v-y)+(p-_)*(p-_)+(h-f)*(h-f));return Math.abs(x)<.001&&(x=1),this.x=(v-y)/x,this.y=(p-_)/x,this.z=(h-f)/x,this.w=Math.acos((c+m+b-1)/2),this}min(e){return this.x=Math.min(this.x,e.x),this.y=Math.min(this.y,e.y),this.z=Math.min(this.z,e.z),this.w=Math.min(this.w,e.w),this}max(e){return this.x=Math.max(this.x,e.x),this.y=Math.max(this.y,e.y),this.z=Math.max(this.z,e.z),this.w=Math.max(this.w,e.w),this}clamp(e,t){return this.x=Math.max(e.x,Math.min(t.x,this.x)),this.y=Math.max(e.y,Math.min(t.y,this.y)),this.z=Math.max(e.z,Math.min(t.z,this.z)),this.w=Math.max(e.w,Math.min(t.w,this.w)),this}clampScalar(e,t){return this.x=Math.max(e,Math.min(t,this.x)),this.y=Math.max(e,Math.min(t,this.y)),this.z=Math.max(e,Math.min(t,this.z)),this.w=Math.max(e,Math.min(t,this.w)),this}clampLength(e,t){const n=this.length();return this.divideScalar(n||1).multiplyScalar(Math.max(e,Math.min(t,n)))}floor(){return this.x=Math.floor(this.x),this.y=Math.floor(this.y),this.z=Math.floor(this.z),this.w=Math.floor(this.w),this}ceil(){return this.x=Math.ceil(this.x),this.y=Math.ceil(this.y),this.z=Math.ceil(this.z),this.w=Math.ceil(this.w),this}round(){return this.x=Math.round(this.x),this.y=Math.round(this.y),this.z=Math.round(this.z),this.w=Math.round(this.w),this}roundToZero(){return this.x=this.x<0?Math.ceil(this.x):Math.floor(this.x),this.y=this.y<0?Math.ceil(this.y):Math.floor(this.y),this.z=this.z<0?Math.ceil(this.z):Math.floor(this.z),this.w=this.w<0?Math.ceil(this.w):Math.floor(this.w),this}negate(){return this.x=-this.x,this.y=-this.y,this.z=-this.z,this.w=-this.w,this}dot(e){return this.x*e.x+this.y*e.y+this.z*e.z+this.w*e.w}lengthSq(){return this.x*this.x+this.y*this.y+this.z*this.z+this.w*this.w}length(){return Math.sqrt(this.x*this.x+this.y*this.y+this.z*this.z+this.w*this.w)}manhattanLength(){return Math.abs(this.x)+Math.abs(this.y)+Math.abs(this.z)+Math.abs(this.w)}normalize(){return this.divideScalar(this.length()||1)}setLength(e){return this.normalize().multiplyScalar(e)}lerp(e,t){return this.x+=(e.x-this.x)*t,this.y+=(e.y-this.y)*t,this.z+=(e.z-this.z)*t,this.w+=(e.w-this.w)*t,this}lerpVectors(e,t,n){return this.x=e.x+(t.x-e.x)*n,this.y=e.y+(t.y-e.y)*n,this.z=e.z+(t.z-e.z)*n,this.w=e.w+(t.w-e.w)*n,this}equals(e){return e.x===this.x&&e.y===this.y&&e.z===this.z&&e.w===this.w}fromArray(e,t=0){return this.x=e[t],this.y=e[t+1],this.z=e[t+2],this.w=e[t+3],this}toArray(e=[],t=0){return e[t]=this.x,e[t+1]=this.y,e[t+2]=this.z,e[t+3]=this.w,e}fromBufferAttribute(e,t){return this.x=e.getX(t),this.y=e.getY(t),this.z=e.getZ(t),this.w=e.getW(t),this}random(){return this.x=Math.random(),this.y=Math.random(),this.z=Math.random(),this.w=Math.random(),this}*[Symbol.iterator](){yield this.x,yield this.y,yield this.z,yield this.w}}class H0 extends Z0{constructor(e,t,n={}){super(),this.isWebGLRenderTarget=!0,this.width=e,this.height=t,this.depth=1,this.scissor=new c3(0,0,e,t),this.scissorTest=!1,this.viewport=new c3(0,0,e,t);const i={width:e,height:t,depth:1};this.texture=new i4(i,n.mapping,n.wrapS,n.wrapT,n.magFilter,n.minFilter,n.format,n.type,n.anisotropy,n.encoding),this.texture.isRenderTargetTexture=!0,this.texture.flipY=!1,this.texture.generateMipmaps=n.generateMipmaps!==void 0?n.generateMipmaps:!1,this.texture.internalFormat=n.internalFormat!==void 0?n.internalFormat:null,this.texture.minFilter=n.minFilter!==void 0?n.minFilter:Z3,this.depthBuffer=n.depthBuffer!==void 0?n.depthBuffer:!0,this.stencilBuffer=n.stencilBuffer!==void 0?n.stencilBuffer:!1,this.depthTexture=n.depthTexture!==void 0?n.depthTexture:null,this.samples=n.samples!==void 0?n.samples:0}setSize(e,t,n=1){(this.width!==e||this.height!==t||this.depth!==n)&&(this.width=e,this.height=t,this.depth=n,this.texture.image.width=e,this.texture.image.height=t,this.texture.image.depth=n,this.dispose()),this.viewport.set(0,0,e,t),this.scissor.set(0,0,e,t)}clone(){return new this.constructor().copy(this)}copy(e){this.width=e.width,this.height=e.height,this.depth=e.depth,this.viewport.copy(e.viewport),this.texture=e.texture.clone(),this.texture.isRenderTargetTexture=!0;const t=Object.assign({},e.texture.image);return this.texture.source=new Tr(t),this.depthBuffer=e.depthBuffer,this.stencilBuffer=e.stencilBuffer,e.depthTexture!==null&&(this.depthTexture=e.depthTexture.clone()),this.samples=e.samples,this}dispose(){this.dispatchEvent({type:"dispose"})}}class Er extends i4{constructor(e=null,t=1,n=1,i=1){super(null),this.isDataArrayTexture=!0,this.image={data:e,width:t,height:n,depth:i},this.magFilter=M3,this.minFilter=M3,this.wrapR=d4,this.generateMipmaps=!1,this.flipY=!1,this.unpackAlignment=1}}class qS extends i4{constructor(e=null,t=1,n=1,i=1){super(null),this.isData3DTexture=!0,this.image={data:e,width:t,height:n,depth:i},this.magFilter=M3,this.minFilter=M3,this.wrapR=d4,this.generateMipmaps=!1,this.flipY=!1,this.unpackAlignment=1}}class B4{constructor(e=0,t=0,n=0,i=1){this.isQuaternion=!0,this._x=e,this._y=t,this._z=n,this._w=i}static slerpFlat(e,t,n,i,a,r,s){let u=n[i+0],c=n[i+1],f=n[i+2],p=n[i+3];const h=a[r+0],m=a[r+1],y=a[r+2],_=a[r+3];if(s===0){e[t+0]=u,e[t+1]=c,e[t+2]=f,e[t+3]=p;return}if(s===1){e[t+0]=h,e[t+1]=m,e[t+2]=y,e[t+3]=_;return}if(p!==_||u!==h||c!==m||f!==y){let v=1-s;const b=u*h+c*m+f*y+p*_,x=b>=0?1:-1,A=1-b*b;if(A>Number.EPSILON){const w=Math.sqrt(A),M=Math.atan2(w,b*x);v=Math.sin(v*M)/w,s=Math.sin(s*M)/w}const S=s*x;if(u=u*v+h*S,c=c*v+m*S,f=f*v+y*S,p=p*v+_*S,v===1-s){const w=1/Math.sqrt(u*u+c*c+f*f+p*p);u*=w,c*=w,f*=w,p*=w}}e[t]=u,e[t+1]=c,e[t+2]=f,e[t+3]=p}static multiplyQuaternionsFlat(e,t,n,i,a,r){const s=n[i],u=n[i+1],c=n[i+2],f=n[i+3],p=a[r],h=a[r+1],m=a[r+2],y=a[r+3];return e[t]=s*y+f*p+u*m-c*h,e[t+1]=u*y+f*h+c*p-s*m,e[t+2]=c*y+f*m+s*h-u*p,e[t+3]=f*y-s*p-u*h-c*m,e}get x(){return this._x}set x(e){this._x=e,this._onChangeCallback()}get y(){return this._y}set y(e){this._y=e,this._onChangeCallback()}get z(){return this._z}set z(e){this._z=e,this._onChangeCallback()}get w(){return this._w}set w(e){this._w=e,this._onChangeCallback()}set(e,t,n,i){return this._x=e,this._y=t,this._z=n,this._w=i,this._onChangeCallback(),this}clone(){return new this.constructor(this._x,this._y,this._z,this._w)}copy(e){return this._x=e.x,this._y=e.y,this._z=e.z,this._w=e.w,this._onChangeCallback(),this}setFromEuler(e,t){const n=e._x,i=e._y,a=e._z,r=e._order,s=Math.cos,u=Math.sin,c=s(n/2),f=s(i/2),p=s(a/2),h=u(n/2),m=u(i/2),y=u(a/2);switch(r){case"XYZ":this._x=h*f*p+c*m*y,this._y=c*m*p-h*f*y,this._z=c*f*y+h*m*p,this._w=c*f*p-h*m*y;break;case"YXZ":this._x=h*f*p+c*m*y,this._y=c*m*p-h*f*y,this._z=c*f*y-h*m*p,this._w=c*f*p+h*m*y;break;case"ZXY":this._x=h*f*p-c*m*y,this._y=c*m*p+h*f*y,this._z=c*f*y+h*m*p,this._w=c*f*p-h*m*y;break;case"ZYX":this._x=h*f*p-c*m*y,this._y=c*m*p+h*f*y,this._z=c*f*y-h*m*p,this._w=c*f*p+h*m*y;break;case"YZX":this._x=h*f*p+c*m*y,this._y=c*m*p+h*f*y,this._z=c*f*y-h*m*p,this._w=c*f*p-h*m*y;break;case"XZY":this._x=h*f*p-c*m*y,this._y=c*m*p-h*f*y,this._z=c*f*y+h*m*p,this._w=c*f*p+h*m*y;break;default:console.warn("THREE.Quaternion: .setFromEuler() encountered an unknown order: "+r)}return t!==!1&&this._onChangeCallback(),this}setFromAxisAngle(e,t){const n=t/2,i=Math.sin(n);return this._x=e.x*i,this._y=e.y*i,this._z=e.z*i,this._w=Math.cos(n),this._onChangeCallback(),this}setFromRotationMatrix(e){const t=e.elements,n=t[0],i=t[4],a=t[8],r=t[1],s=t[5],u=t[9],c=t[2],f=t[6],p=t[10],h=n+s+p;if(h>0){const m=.5/Math.sqrt(h+1);this._w=.25/m,this._x=(f-u)*m,this._y=(a-c)*m,this._z=(r-i)*m}else if(n>s&&n>p){const m=2*Math.sqrt(1+n-s-p);this._w=(f-u)/m,this._x=.25*m,this._y=(i+r)/m,this._z=(a+c)/m}else if(s>p){const m=2*Math.sqrt(1+s-n-p);this._w=(a-c)/m,this._x=(i+r)/m,this._y=.25*m,this._z=(u+f)/m}else{const m=2*Math.sqrt(1+p-n-s);this._w=(r-i)/m,this._x=(a+c)/m,this._y=(u+f)/m,this._z=.25*m}return this._onChangeCallback(),this}setFromUnitVectors(e,t){let n=e.dot(t)+1;return n<Number.EPSILON?(n=0,Math.abs(e.x)>Math.abs(e.z)?(this._x=-e.y,this._y=e.x,this._z=0,this._w=n):(this._x=0,this._y=-e.z,this._z=e.y,this._w=n)):(this._x=e.y*t.z-e.z*t.y,this._y=e.z*t.x-e.x*t.z,this._z=e.x*t.y-e.y*t.x,this._w=n),this.normalize()}angleTo(e){return 2*Math.acos(Math.abs(m3(this.dot(e),-1,1)))}rotateTowards(e,t){const n=this.angleTo(e);if(n===0)return this;const i=Math.min(1,t/n);return this.slerp(e,i),this}identity(){return this.set(0,0,0,1)}invert(){return this.conjugate()}conjugate(){return this._x*=-1,this._y*=-1,this._z*=-1,this._onChangeCallback(),this}dot(e){return this._x*e._x+this._y*e._y+this._z*e._z+this._w*e._w}lengthSq(){return this._x*this._x+this._y*this._y+this._z*this._z+this._w*this._w}length(){return Math.sqrt(this._x*this._x+this._y*this._y+this._z*this._z+this._w*this._w)}normalize(){let e=this.length();return e===0?(this._x=0,this._y=0,this._z=0,this._w=1):(e=1/e,this._x=this._x*e,this._y=this._y*e,this._z=this._z*e,this._w=this._w*e),this._onChangeCallback(),this}multiply(e){return this.multiplyQuaternions(this,e)}premultiply(e){return this.multiplyQuaternions(e,this)}multiplyQuaternions(e,t){const n=e._x,i=e._y,a=e._z,r=e._w,s=t._x,u=t._y,c=t._z,f=t._w;return this._x=n*f+r*s+i*c-a*u,this._y=i*f+r*u+a*s-n*c,this._z=a*f+r*c+n*u-i*s,this._w=r*f-n*s-i*u-a*c,this._onChangeCallback(),this}slerp(e,t){if(t===0)return this;if(t===1)return this.copy(e);const n=this._x,i=this._y,a=this._z,r=this._w;let s=r*e._w+n*e._x+i*e._y+a*e._z;if(s<0?(this._w=-e._w,this._x=-e._x,this._y=-e._y,this._z=-e._z,s=-s):this.copy(e),s>=1)return this._w=r,this._x=n,this._y=i,this._z=a,this;const u=1-s*s;if(u<=Number.EPSILON){const m=1-t;return this._w=m*r+t*this._w,this._x=m*n+t*this._x,this._y=m*i+t*this._y,this._z=m*a+t*this._z,this.normalize(),this._onChangeCallback(),this}const c=Math.sqrt(u),f=Math.atan2(c,s),p=Math.sin((1-t)*f)/c,h=Math.sin(t*f)/c;return this._w=r*p+this._w*h,this._x=n*p+this._x*h,this._y=i*p+this._y*h,this._z=a*p+this._z*h,this._onChangeCallback(),this}slerpQuaternions(e,t,n){return this.copy(e).slerp(t,n)}random(){const e=Math.random(),t=Math.sqrt(1-e),n=Math.sqrt(e),i=2*Math.PI*Math.random(),a=2*Math.PI*Math.random();return this.set(t*Math.cos(i),n*Math.sin(a),n*Math.cos(a),t*Math.sin(i))}equals(e){return e._x===this._x&&e._y===this._y&&e._z===this._z&&e._w===this._w}fromArray(e,t=0){return this._x=e[t],this._y=e[t+1],this._z=e[t+2],this._w=e[t+3],this._onChangeCallback(),this}toArray(e=[],t=0){return e[t]=this._x,e[t+1]=this._y,e[t+2]=this._z,e[t+3]=this._w,e}fromBufferAttribute(e,t){return this._x=e.getX(t),this._y=e.getY(t),this._z=e.getZ(t),this._w=e.getW(t),this}_onChange(e){return this._onChangeCallback=e,this}_onChangeCallback(){}*[Symbol.iterator](){yield this._x,yield this._y,yield this._z,yield this._w}}class Y{constructor(e=0,t=0,n=0){Y.prototype.isVector3=!0,this.x=e,this.y=t,this.z=n}set(e,t,n){return n===void 0&&(n=this.z),this.x=e,this.y=t,this.z=n,this}setScalar(e){return this.x=e,this.y=e,this.z=e,this}setX(e){return this.x=e,this}setY(e){return this.y=e,this}setZ(e){return this.z=e,this}setComponent(e,t){switch(e){case 0:this.x=t;break;case 1:this.y=t;break;case 2:this.z=t;break;default:throw new Error("index is out of range: "+e)}return this}getComponent(e){switch(e){case 0:return this.x;case 1:return this.y;case 2:return this.z;default:throw new Error("index is out of range: "+e)}}clone(){return new this.constructor(this.x,this.y,this.z)}copy(e){return this.x=e.x,this.y=e.y,this.z=e.z,this}add(e){return this.x+=e.x,this.y+=e.y,this.z+=e.z,this}addScalar(e){return this.x+=e,this.y+=e,this.z+=e,this}addVectors(e,t){return this.x=e.x+t.x,this.y=e.y+t.y,this.z=e.z+t.z,this}addScaledVector(e,t){return this.x+=e.x*t,this.y+=e.y*t,this.z+=e.z*t,this}sub(e){return this.x-=e.x,this.y-=e.y,this.z-=e.z,this}subScalar(e){return this.x-=e,this.y-=e,this.z-=e,this}subVectors(e,t){return this.x=e.x-t.x,this.y=e.y-t.y,this.z=e.z-t.z,this}multiply(e){return this.x*=e.x,this.y*=e.y,this.z*=e.z,this}multiplyScalar(e){return this.x*=e,this.y*=e,this.z*=e,this}multiplyVectors(e,t){return this.x=e.x*t.x,this.y=e.y*t.y,this.z=e.z*t.z,this}applyEuler(e){return this.applyQuaternion(Vi.setFromEuler(e))}applyAxisAngle(e,t){return this.applyQuaternion(Vi.setFromAxisAngle(e,t))}applyMatrix3(e){const t=this.x,n=this.y,i=this.z,a=e.elements;return this.x=a[0]*t+a[3]*n+a[6]*i,this.y=a[1]*t+a[4]*n+a[7]*i,this.z=a[2]*t+a[5]*n+a[8]*i,this}applyNormalMatrix(e){return this.applyMatrix3(e).normalize()}applyMatrix4(e){const t=this.x,n=this.y,i=this.z,a=e.elements,r=1/(a[3]*t+a[7]*n+a[11]*i+a[15]);return this.x=(a[0]*t+a[4]*n+a[8]*i+a[12])*r,this.y=(a[1]*t+a[5]*n+a[9]*i+a[13])*r,this.z=(a[2]*t+a[6]*n+a[10]*i+a[14])*r,this}applyQuaternion(e){const t=this.x,n=this.y,i=this.z,a=e.x,r=e.y,s=e.z,u=e.w,c=u*t+r*i-s*n,f=u*n+s*t-a*i,p=u*i+a*n-r*t,h=-a*t-r*n-s*i;return this.x=c*u+h*-a+f*-s-p*-r,this.y=f*u+h*-r+p*-a-c*-s,this.z=p*u+h*-s+c*-r-f*-a,this}project(e){return this.applyMatrix4(e.matrixWorldInverse).applyMatrix4(e.projectionMatrix)}unproject(e){return this.applyMatrix4(e.projectionMatrixInverse).applyMatrix4(e.matrixWorld)}transformDirection(e){const t=this.x,n=this.y,i=this.z,a=e.elements;return this.x=a[0]*t+a[4]*n+a[8]*i,this.y=a[1]*t+a[5]*n+a[9]*i,this.z=a[2]*t+a[6]*n+a[10]*i,this.normalize()}divide(e){return this.x/=e.x,this.y/=e.y,this.z/=e.z,this}divideScalar(e){return this.multiplyScalar(1/e)}min(e){return this.x=Math.min(this.x,e.x),this.y=Math.min(this.y,e.y),this.z=Math.min(this.z,e.z),this}max(e){return this.x=Math.max(this.x,e.x),this.y=Math.max(this.y,e.y),this.z=Math.max(this.z,e.z),this}clamp(e,t){return this.x=Math.max(e.x,Math.min(t.x,this.x)),this.y=Math.max(e.y,Math.min(t.y,this.y)),this.z=Math.max(e.z,Math.min(t.z,this.z)),this}clampScalar(e,t){return this.x=Math.max(e,Math.min(t,this.x)),this.y=Math.max(e,Math.min(t,this.y)),this.z=Math.max(e,Math.min(t,this.z)),this}clampLength(e,t){const n=this.length();return this.divideScalar(n||1).multiplyScalar(Math.max(e,Math.min(t,n)))}floor(){return this.x=Math.floor(this.x),this.y=Math.floor(this.y),this.z=Math.floor(this.z),this}ceil(){return this.x=Math.ceil(this.x),this.y=Math.ceil(this.y),this.z=Math.ceil(this.z),this}round(){return this.x=Math.round(this.x),this.y=Math.round(this.y),this.z=Math.round(this.z),this}roundToZero(){return this.x=this.x<0?Math.ceil(this.x):Math.floor(this.x),this.y=this.y<0?Math.ceil(this.y):Math.floor(this.y),this.z=this.z<0?Math.ceil(this.z):Math.floor(this.z),this}negate(){return this.x=-this.x,this.y=-this.y,this.z=-this.z,this}dot(e){return this.x*e.x+this.y*e.y+this.z*e.z}lengthSq(){return this.x*this.x+this.y*this.y+this.z*this.z}length(){return Math.sqrt(this.x*this.x+this.y*this.y+this.z*this.z)}manhattanLength(){return Math.abs(this.x)+Math.abs(this.y)+Math.abs(this.z)}normalize(){return this.divideScalar(this.length()||1)}setLength(e){return this.normalize().multiplyScalar(e)}lerp(e,t){return this.x+=(e.x-this.x)*t,this.y+=(e.y-this.y)*t,this.z+=(e.z-this.z)*t,this}lerpVectors(e,t,n){return this.x=e.x+(t.x-e.x)*n,this.y=e.y+(t.y-e.y)*n,this.z=e.z+(t.z-e.z)*n,this}cross(e){return this.crossVectors(this,e)}crossVectors(e,t){const n=e.x,i=e.y,a=e.z,r=t.x,s=t.y,u=t.z;return this.x=i*u-a*s,this.y=a*r-n*u,this.z=n*s-i*r,this}projectOnVector(e){const t=e.lengthSq();if(t===0)return this.set(0,0,0);const n=e.dot(this)/t;return this.copy(e).multiplyScalar(n)}projectOnPlane(e){return F6.copy(this).projectOnVector(e),this.sub(F6)}reflect(e){return this.sub(F6.copy(e).multiplyScalar(2*this.dot(e)))}angleTo(e){const t=Math.sqrt(this.lengthSq()*e.lengthSq());if(t===0)return Math.PI/2;const n=this.dot(e)/t;return Math.acos(m3(n,-1,1))}distanceTo(e){return Math.sqrt(this.distanceToSquared(e))}distanceToSquared(e){const t=this.x-e.x,n=this.y-e.y,i=this.z-e.z;return t*t+n*n+i*i}manhattanDistanceTo(e){return Math.abs(this.x-e.x)+Math.abs(this.y-e.y)+Math.abs(this.z-e.z)}setFromSpherical(e){return this.setFromSphericalCoords(e.radius,e.phi,e.theta)}setFromSphericalCoords(e,t,n){const i=Math.sin(t)*e;return this.x=i*Math.sin(n),this.y=Math.cos(t)*e,this.z=i*Math.cos(n),this}setFromCylindrical(e){return this.setFromCylindricalCoords(e.radius,e.theta,e.y)}setFromCylindricalCoords(e,t,n){return this.x=e*Math.sin(t),this.y=n,this.z=e*Math.cos(t),this}setFromMatrixPosition(e){const t=e.elements;return this.x=t[12],this.y=t[13],this.z=t[14],this}setFromMatrixScale(e){const t=this.setFromMatrixColumn(e,0).length(),n=this.setFromMatrixColumn(e,1).length(),i=this.setFromMatrixColumn(e,2).length();return this.x=t,this.y=n,this.z=i,this}setFromMatrixColumn(e,t){return this.fromArray(e.elements,t*4)}setFromMatrix3Column(e,t){return this.fromArray(e.elements,t*3)}setFromEuler(e){return this.x=e._x,this.y=e._y,this.z=e._z,this}equals(e){return e.x===this.x&&e.y===this.y&&e.z===this.z}fromArray(e,t=0){return this.x=e[t],this.y=e[t+1],this.z=e[t+2],this}toArray(e=[],t=0){return e[t]=this.x,e[t+1]=this.y,e[t+2]=this.z,e}fromBufferAttribute(e,t){return this.x=e.getX(t),this.y=e.getY(t),this.z=e.getZ(t),this}random(){return this.x=Math.random(),this.y=Math.random(),this.z=Math.random(),this}randomDirection(){const e=(Math.random()-.5)*2,t=Math.random()*Math.PI*2,n=Math.sqrt(1-Jn(e,2));return this.x=n*Math.cos(t),this.y=n*Math.sin(t),this.z=e,this}*[Symbol.iterator](){yield this.x,yield this.y,yield this.z}}const F6=new Y,Vi=new B4;class G5{constructor(e=new Y(1/0,1/0,1/0),t=new Y(-1/0,-1/0,-1/0)){this.isBox3=!0,this.min=e,this.max=t}set(e,t){return this.min.copy(e),this.max.copy(t),this}setFromArray(e){let t=1/0,n=1/0,i=1/0,a=-1/0,r=-1/0,s=-1/0;for(let u=0,c=e.length;u<c;u+=3){const f=e[u],p=e[u+1],h=e[u+2];f<t&&(t=f),p<n&&(n=p),h<i&&(i=h),f>a&&(a=f),p>r&&(r=p),h>s&&(s=h)}return this.min.set(t,n,i),this.max.set(a,r,s),this}setFromBufferAttribute(e){let t=1/0,n=1/0,i=1/0,a=-1/0,r=-1/0,s=-1/0;for(let u=0,c=e.count;u<c;u++){const f=e.getX(u),p=e.getY(u),h=e.getZ(u);f<t&&(t=f),p<n&&(n=p),h<i&&(i=h),f>a&&(a=f),p>r&&(r=p),h>s&&(s=h)}return this.min.set(t,n,i),this.max.set(a,r,s),this}setFromPoints(e){this.makeEmpty();for(let t=0,n=e.length;t<n;t++)this.expandByPoint(e[t]);return this}setFromCenterAndSize(e,t){const n=S0.copy(t).multiplyScalar(.5);return this.min.copy(e).sub(n),this.max.copy(e).add(n),this}setFromObject(e,t=!1){return this.makeEmpty(),this.expandByObject(e,t)}clone(){return new this.constructor().copy(this)}copy(e){return this.min.copy(e.min),this.max.copy(e.max),this}makeEmpty(){return this.min.x=this.min.y=this.min.z=1/0,this.max.x=this.max.y=this.max.z=-1/0,this}isEmpty(){return this.max.x<this.min.x||this.max.y<this.min.y||this.max.z<this.min.z}getCenter(e){return this.isEmpty()?e.set(0,0,0):e.addVectors(this.min,this.max).multiplyScalar(.5)}getSize(e){return this.isEmpty()?e.set(0,0,0):e.subVectors(this.max,this.min)}expandByPoint(e){return this.min.min(e),this.max.max(e),this}expandByVector(e){return this.min.sub(e),this.max.add(e),this}expandByScalar(e){return this.min.addScalar(-e),this.max.addScalar(e),this}expandByObject(e,t=!1){e.updateWorldMatrix(!1,!1);const n=e.geometry;if(n!==void 0)if(t&&n.attributes!=null&&n.attributes.position!==void 0){const a=n.attributes.position;for(let r=0,s=a.count;r<s;r++)S0.fromBufferAttribute(a,r).applyMatrix4(e.matrixWorld),this.expandByPoint(S0)}else n.boundingBox===null&&n.computeBoundingBox(),B6.copy(n.boundingBox),B6.applyMatrix4(e.matrixWorld),this.union(B6);const i=e.children;for(let a=0,r=i.length;a<r;a++)this.expandByObject(i[a],t);return this}containsPoint(e){return!(e.x<this.min.x||e.x>this.max.x||e.y<this.min.y||e.y>this.max.y||e.z<this.min.z||e.z>this.max.z)}containsBox(e){return this.min.x<=e.min.x&&e.max.x<=this.max.x&&this.min.y<=e.min.y&&e.max.y<=this.max.y&&this.min.z<=e.min.z&&e.max.z<=this.max.z}getParameter(e,t){return t.set((e.x-this.min.x)/(this.max.x-this.min.x),(e.y-this.min.y)/(this.max.y-this.min.y),(e.z-this.min.z)/(this.max.z-this.min.z))}intersectsBox(e){return!(e.max.x<this.min.x||e.min.x>this.max.x||e.max.y<this.min.y||e.min.y>this.max.y||e.max.z<this.min.z||e.min.z>this.max.z)}intersectsSphere(e){return this.clampPoint(e.center,S0),S0.distanceToSquared(e.center)<=e.radius*e.radius}intersectsPlane(e){let t,n;return e.normal.x>0?(t=e.normal.x*this.min.x,n=e.normal.x*this.max.x):(t=e.normal.x*this.max.x,n=e.normal.x*this.min.x),e.normal.y>0?(t+=e.normal.y*this.min.y,n+=e.normal.y*this.max.y):(t+=e.normal.y*this.max.y,n+=e.normal.y*this.min.y),e.normal.z>0?(t+=e.normal.z*this.min.z,n+=e.normal.z*this.max.z):(t+=e.normal.z*this.max.z,n+=e.normal.z*this.min.z),t<=-e.constant&&n>=-e.constant}intersectsTriangle(e){if(this.isEmpty())return!1;this.getCenter(i5),o8.subVectors(this.max,i5),o9.subVectors(e.a,i5),a9.subVectors(e.b,i5),r9.subVectors(e.c,i5),Z4.subVectors(a9,o9),q4.subVectors(r9,a9),M0.subVectors(o9,r9);let t=[0,-Z4.z,Z4.y,0,-q4.z,q4.y,0,-M0.z,M0.y,Z4.z,0,-Z4.x,q4.z,0,-q4.x,M0.z,0,-M0.x,-Z4.y,Z4.x,0,-q4.y,q4.x,0,-M0.y,M0.x,0];return!G6(t,o9,a9,r9,o8)||(t=[1,0,0,0,1,0,0,0,1],!G6(t,o9,a9,r9,o8))?!1:(a8.crossVectors(Z4,q4),t=[a8.x,a8.y,a8.z],G6(t,o9,a9,r9,o8))}clampPoint(e,t){return t.copy(e).clamp(this.min,this.max)}distanceToPoint(e){return S0.copy(e).clamp(this.min,this.max).sub(e).length()}getBoundingSphere(e){return this.getCenter(e.center),e.radius=this.getSize(S0).length()*.5,e}intersect(e){return this.min.max(e.min),this.max.min(e.max),this.isEmpty()&&this.makeEmpty(),this}union(e){return this.min.min(e.min),this.max.max(e.max),this}applyMatrix4(e){return this.isEmpty()?this:(T4[0].set(this.min.x,this.min.y,this.min.z).applyMatrix4(e),T4[1].set(this.min.x,this.min.y,this.max.z).applyMatrix4(e),T4[2].set(this.min.x,this.max.y,this.min.z).applyMatrix4(e),T4[3].set(this.min.x,this.max.y,this.max.z).applyMatrix4(e),T4[4].set(this.max.x,this.min.y,this.min.z).applyMatrix4(e),T4[5].set(this.max.x,this.min.y,this.max.z).applyMatrix4(e),T4[6].set(this.max.x,this.max.y,this.min.z).applyMatrix4(e),T4[7].set(this.max.x,this.max.y,this.max.z).applyMatrix4(e),this.setFromPoints(T4),this)}translate(e){return this.min.add(e),this.max.add(e),this}equals(e){return e.min.equals(this.min)&&e.max.equals(this.max)}}const T4=[new Y,new Y,new Y,new Y,new Y,new Y,new Y,new Y],S0=new Y,B6=new G5,o9=new Y,a9=new Y,r9=new Y,Z4=new Y,q4=new Y,M0=new Y,i5=new Y,o8=new Y,a8=new Y,A0=new Y;function G6(o,e,t,n,i){for(let a=0,r=o.length-3;a<=r;a+=3){A0.fromArray(o,a);const s=i.x*Math.abs(A0.x)+i.y*Math.abs(A0.y)+i.z*Math.abs(A0.z),u=e.dot(A0),c=t.dot(A0),f=n.dot(A0);if(Math.max(-Math.max(u,c,f),Math.min(u,c,f))>s)return!1}return!0}const XS=new G5,ji=new Y,r8=new Y,V6=new Y;class V5{constructor(e=new Y,t=-1){this.center=e,this.radius=t}set(e,t){return this.center.copy(e),this.radius=t,this}setFromPoints(e,t){const n=this.center;t!==void 0?n.copy(t):XS.setFromPoints(e).getCenter(n);let i=0;for(let a=0,r=e.length;a<r;a++)i=Math.max(i,n.distanceToSquared(e[a]));return this.radius=Math.sqrt(i),this}copy(e){return this.center.copy(e.center),this.radius=e.radius,this}isEmpty(){return this.radius<0}makeEmpty(){return this.center.set(0,0,0),this.radius=-1,this}containsPoint(e){return e.distanceToSquared(this.center)<=this.radius*this.radius}distanceToPoint(e){return e.distanceTo(this.center)-this.radius}intersectsSphere(e){const t=this.radius+e.radius;return e.center.distanceToSquared(this.center)<=t*t}intersectsBox(e){return e.intersectsSphere(this)}intersectsPlane(e){return Math.abs(e.distanceToPoint(this.center))<=this.radius}clampPoint(e,t){const n=this.center.distanceToSquared(e);return t.copy(e),n>this.radius*this.radius&&(t.sub(this.center).normalize(),t.multiplyScalar(this.radius).add(this.center)),t}getBoundingBox(e){return this.isEmpty()?(e.makeEmpty(),e):(e.set(this.center,this.center),e.expandByScalar(this.radius),e)}applyMatrix4(e){return this.center.applyMatrix4(e),this.radius=this.radius*e.getMaxScaleOnAxis(),this}translate(e){return this.center.add(e),this}expandByPoint(e){if(this.isEmpty())return this.center.copy(e),this.radius=0,this;V6.subVectors(e,this.center);const t=V6.lengthSq();if(t>this.radius*this.radius){const n=Math.sqrt(t),i=(n-this.radius)*.5;this.center.add(V6.multiplyScalar(i/n)),this.radius+=i}return this}union(e){return e.isEmpty()?this:this.isEmpty()?(this.copy(e),this):(this.center.equals(e.center)===!0?r8.set(0,0,1).multiplyScalar(e.radius):r8.subVectors(e.center,this.center).normalize().multiplyScalar(e.radius),this.expandByPoint(ji.copy(e.center).add(r8)),this.expandByPoint(ji.copy(e.center).sub(r8)),this)}equals(e){return e.center.equals(this.center)&&e.radius===this.radius}clone(){return new this.constructor().copy(this)}}const E4=new Y,j6=new Y,s8=new Y,X4=new Y,W6=new Y,l8=new Y,H6=new Y;class fn{constructor(e=new Y,t=new Y(0,0,-1)){this.origin=e,this.direction=t}set(e,t){return this.origin.copy(e),this.direction.copy(t),this}copy(e){return this.origin.copy(e.origin),this.direction.copy(e.direction),this}at(e,t){return t.copy(this.direction).multiplyScalar(e).add(this.origin)}lookAt(e){return this.direction.copy(e).sub(this.origin).normalize(),this}recast(e){return this.origin.copy(this.at(e,E4)),this}closestPointToPoint(e,t){t.subVectors(e,this.origin);const n=t.dot(this.direction);return n<0?t.copy(this.origin):t.copy(this.direction).multiplyScalar(n).add(this.origin)}distanceToPoint(e){return Math.sqrt(this.distanceSqToPoint(e))}distanceSqToPoint(e){const t=E4.subVectors(e,this.origin).dot(this.direction);return t<0?this.origin.distanceToSquared(e):(E4.copy(this.direction).multiplyScalar(t).add(this.origin),E4.distanceToSquared(e))}distanceSqToSegment(e,t,n,i){j6.copy(e).add(t).multiplyScalar(.5),s8.copy(t).sub(e).normalize(),X4.copy(this.origin).sub(j6);const a=e.distanceTo(t)*.5,r=-this.direction.dot(s8),s=X4.dot(this.direction),u=-X4.dot(s8),c=X4.lengthSq(),f=Math.abs(1-r*r);let p,h,m,y;if(f>0)if(p=r*u-s,h=r*s-u,y=a*f,p>=0)if(h>=-y)if(h<=y){const _=1/f;p*=_,h*=_,m=p*(p+r*h+2*s)+h*(r*p+h+2*u)+c}else h=a,p=Math.max(0,-(r*h+s)),m=-p*p+h*(h+2*u)+c;else h=-a,p=Math.max(0,-(r*h+s)),m=-p*p+h*(h+2*u)+c;else h<=-y?(p=Math.max(0,-(-r*a+s)),h=p>0?-a:Math.min(Math.max(-a,-u),a),m=-p*p+h*(h+2*u)+c):h<=y?(p=0,h=Math.min(Math.max(-a,-u),a),m=h*(h+2*u)+c):(p=Math.max(0,-(r*a+s)),h=p>0?a:Math.min(Math.max(-a,-u),a),m=-p*p+h*(h+2*u)+c);else h=r>0?-a:a,p=Math.max(0,-(r*h+s)),m=-p*p+h*(h+2*u)+c;return n&&n.copy(this.direction).multiplyScalar(p).add(this.origin),i&&i.copy(s8).multiplyScalar(h).add(j6),m}intersectSphere(e,t){E4.subVectors(e.center,this.origin);const n=E4.dot(this.direction),i=E4.dot(E4)-n*n,a=e.radius*e.radius;if(i>a)return null;const r=Math.sqrt(a-i),s=n-r,u=n+r;return s<0&&u<0?null:s<0?this.at(u,t):this.at(s,t)}intersectsSphere(e){return this.distanceSqToPoint(e.center)<=e.radius*e.radius}distanceToPlane(e){const t=e.normal.dot(this.direction);if(t===0)return e.distanceToPoint(this.origin)===0?0:null;const n=-(this.origin.dot(e.normal)+e.constant)/t;return n>=0?n:null}intersectPlane(e,t){const n=this.distanceToPlane(e);return n===null?null:this.at(n,t)}intersectsPlane(e){const t=e.distanceToPoint(this.origin);return t===0||e.normal.dot(this.direction)*t<0}intersectBox(e,t){let n,i,a,r,s,u;const c=1/this.direction.x,f=1/this.direction.y,p=1/this.direction.z,h=this.origin;return c>=0?(n=(e.min.x-h.x)*c,i=(e.max.x-h.x)*c):(n=(e.max.x-h.x)*c,i=(e.min.x-h.x)*c),f>=0?(a=(e.min.y-h.y)*f,r=(e.max.y-h.y)*f):(a=(e.max.y-h.y)*f,r=(e.min.y-h.y)*f),n>r||a>i||((a>n||n!==n)&&(n=a),(r<i||i!==i)&&(i=r),p>=0?(s=(e.min.z-h.z)*p,u=(e.max.z-h.z)*p):(s=(e.max.z-h.z)*p,u=(e.min.z-h.z)*p),n>u||s>i)||((s>n||n!==n)&&(n=s),(u<i||i!==i)&&(i=u),i<0)?null:this.at(n>=0?n:i,t)}intersectsBox(e){return this.intersectBox(e,E4)!==null}intersectTriangle(e,t,n,i,a){W6.subVectors(t,e),l8.subVectors(n,e),H6.crossVectors(W6,l8);let r=this.direction.dot(H6),s;if(r>0){if(i)return null;s=1}else if(r<0)s=-1,r=-r;else return null;X4.subVectors(this.origin,e);const u=s*this.direction.dot(l8.crossVectors(X4,l8));if(u<0)return null;const c=s*this.direction.dot(W6.cross(X4));if(c<0||u+c>r)return null;const f=-s*X4.dot(H6);return f<0?null:this.at(f/r,a)}applyMatrix4(e){return this.origin.applyMatrix4(e),this.direction.transformDirection(e),this}equals(e){return e.origin.equals(this.origin)&&e.direction.equals(this.direction)}clone(){return new this.constructor().copy(this)}}class W2{constructor(){W2.prototype.isMatrix4=!0,this.elements=[1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1]}set(e,t,n,i,a,r,s,u,c,f,p,h,m,y,_,v){const b=this.elements;return b[0]=e,b[4]=t,b[8]=n,b[12]=i,b[1]=a,b[5]=r,b[9]=s,b[13]=u,b[2]=c,b[6]=f,b[10]=p,b[14]=h,b[3]=m,b[7]=y,b[11]=_,b[15]=v,this}identity(){return this.set(1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1),this}clone(){return new W2().fromArray(this.elements)}copy(e){const t=this.elements,n=e.elements;return t[0]=n[0],t[1]=n[1],t[2]=n[2],t[3]=n[3],t[4]=n[4],t[5]=n[5],t[6]=n[6],t[7]=n[7],t[8]=n[8],t[9]=n[9],t[10]=n[10],t[11]=n[11],t[12]=n[12],t[13]=n[13],t[14]=n[14],t[15]=n[15],this}copyPosition(e){const t=this.elements,n=e.elements;return t[12]=n[12],t[13]=n[13],t[14]=n[14],this}setFromMatrix3(e){const t=e.elements;return this.set(t[0],t[3],t[6],0,t[1],t[4],t[7],0,t[2],t[5],t[8],0,0,0,0,1),this}extractBasis(e,t,n){return e.setFromMatrixColumn(this,0),t.setFromMatrixColumn(this,1),n.setFromMatrixColumn(this,2),this}makeBasis(e,t,n){return this.set(e.x,t.x,n.x,0,e.y,t.y,n.y,0,e.z,t.z,n.z,0,0,0,0,1),this}extractRotation(e){const t=this.elements,n=e.elements,i=1/s9.setFromMatrixColumn(e,0).length(),a=1/s9.setFromMatrixColumn(e,1).length(),r=1/s9.setFromMatrixColumn(e,2).length();return t[0]=n[0]*i,t[1]=n[1]*i,t[2]=n[2]*i,t[3]=0,t[4]=n[4]*a,t[5]=n[5]*a,t[6]=n[6]*a,t[7]=0,t[8]=n[8]*r,t[9]=n[9]*r,t[10]=n[10]*r,t[11]=0,t[12]=0,t[13]=0,t[14]=0,t[15]=1,this}makeRotationFromEuler(e){const t=this.elements,n=e.x,i=e.y,a=e.z,r=Math.cos(n),s=Math.sin(n),u=Math.cos(i),c=Math.sin(i),f=Math.cos(a),p=Math.sin(a);if(e.order==="XYZ"){const h=r*f,m=r*p,y=s*f,_=s*p;t[0]=u*f,t[4]=-u*p,t[8]=c,t[1]=m+y*c,t[5]=h-_*c,t[9]=-s*u,t[2]=_-h*c,t[6]=y+m*c,t[10]=r*u}else if(e.order==="YXZ"){const h=u*f,m=u*p,y=c*f,_=c*p;t[0]=h+_*s,t[4]=y*s-m,t[8]=r*c,t[1]=r*p,t[5]=r*f,t[9]=-s,t[2]=m*s-y,t[6]=_+h*s,t[10]=r*u}else if(e.order==="ZXY"){const h=u*f,m=u*p,y=c*f,_=c*p;t[0]=h-_*s,t[4]=-r*p,t[8]=y+m*s,t[1]=m+y*s,t[5]=r*f,t[9]=_-h*s,t[2]=-r*c,t[6]=s,t[10]=r*u}else if(e.order==="ZYX"){const h=r*f,m=r*p,y=s*f,_=s*p;t[0]=u*f,t[4]=y*c-m,t[8]=h*c+_,t[1]=u*p,t[5]=_*c+h,t[9]=m*c-y,t[2]=-c,t[6]=s*u,t[10]=r*u}else if(e.order==="YZX"){const h=r*u,m=r*c,y=s*u,_=s*c;t[0]=u*f,t[4]=_-h*p,t[8]=y*p+m,t[1]=p,t[5]=r*f,t[9]=-s*f,t[2]=-c*f,t[6]=m*p+y,t[10]=h-_*p}else if(e.order==="XZY"){const h=r*u,m=r*c,y=s*u,_=s*c;t[0]=u*f,t[4]=-p,t[8]=c*f,t[1]=h*p+_,t[5]=r*f,t[9]=m*p-y,t[2]=y*p-m,t[6]=s*f,t[10]=_*p+h}return t[3]=0,t[7]=0,t[11]=0,t[12]=0,t[13]=0,t[14]=0,t[15]=1,this}makeRotationFromQuaternion(e){return this.compose(QS,e,KS)}lookAt(e,t,n){const i=this.elements;return L3.subVectors(e,t),L3.lengthSq()===0&&(L3.z=1),L3.normalize(),Q4.crossVectors(n,L3),Q4.lengthSq()===0&&(Math.abs(n.z)===1?L3.x+=1e-4:L3.z+=1e-4,L3.normalize(),Q4.crossVectors(n,L3)),Q4.normalize(),u8.crossVectors(L3,Q4),i[0]=Q4.x,i[4]=u8.x,i[8]=L3.x,i[1]=Q4.y,i[5]=u8.y,i[9]=L3.y,i[2]=Q4.z,i[6]=u8.z,i[10]=L3.z,this}multiply(e){return this.multiplyMatrices(this,e)}premultiply(e){return this.multiplyMatrices(e,this)}multiplyMatrices(e,t){const n=e.elements,i=t.elements,a=this.elements,r=n[0],s=n[4],u=n[8],c=n[12],f=n[1],p=n[5],h=n[9],m=n[13],y=n[2],_=n[6],v=n[10],b=n[14],x=n[3],A=n[7],S=n[11],w=n[15],M=i[0],D=i[4],T=i[8],E=i[12],U=i[1],V=i[5],q=i[9],$=i[13],z=i[2],F=i[6],G=i[10],J=i[14],O=i[3],N=i[7],B=i[11],t1=i[15];return a[0]=r*M+s*U+u*z+c*O,a[4]=r*D+s*V+u*F+c*N,a[8]=r*T+s*q+u*G+c*B,a[12]=r*E+s*$+u*J+c*t1,a[1]=f*M+p*U+h*z+m*O,a[5]=f*D+p*V+h*F+m*N,a[9]=f*T+p*q+h*G+m*B,a[13]=f*E+p*$+h*J+m*t1,a[2]=y*M+_*U+v*z+b*O,a[6]=y*D+_*V+v*F+b*N,a[10]=y*T+_*q+v*G+b*B,a[14]=y*E+_*$+v*J+b*t1,a[3]=x*M+A*U+S*z+w*O,a[7]=x*D+A*V+S*F+w*N,a[11]=x*T+A*q+S*G+w*B,a[15]=x*E+A*$+S*J+w*t1,this}multiplyScalar(e){const t=this.elements;return t[0]*=e,t[4]*=e,t[8]*=e,t[12]*=e,t[1]*=e,t[5]*=e,t[9]*=e,t[13]*=e,t[2]*=e,t[6]*=e,t[10]*=e,t[14]*=e,t[3]*=e,t[7]*=e,t[11]*=e,t[15]*=e,this}determinant(){const e=this.elements,t=e[0],n=e[4],i=e[8],a=e[12],r=e[1],s=e[5],u=e[9],c=e[13],f=e[2],p=e[6],h=e[10],m=e[14],y=e[3],_=e[7],v=e[11],b=e[15];return y*(+a*u*p-i*c*p-a*s*h+n*c*h+i*s*m-n*u*m)+_*(+t*u*m-t*c*h+a*r*h-i*r*m+i*c*f-a*u*f)+v*(+t*c*p-t*s*m-a*r*p+n*r*m+a*s*f-n*c*f)+b*(-i*s*f-t*u*p+t*s*h+i*r*p-n*r*h+n*u*f)}transpose(){const e=this.elements;let t;return t=e[1],e[1]=e[4],e[4]=t,t=e[2],e[2]=e[8],e[8]=t,t=e[6],e[6]=e[9],e[9]=t,t=e[3],e[3]=e[12],e[12]=t,t=e[7],e[7]=e[13],e[13]=t,t=e[11],e[11]=e[14],e[14]=t,this}setPosition(e,t,n){const i=this.elements;return e.isVector3?(i[12]=e.x,i[13]=e.y,i[14]=e.z):(i[12]=e,i[13]=t,i[14]=n),this}invert(){const e=this.elements,t=e[0],n=e[1],i=e[2],a=e[3],r=e[4],s=e[5],u=e[6],c=e[7],f=e[8],p=e[9],h=e[10],m=e[11],y=e[12],_=e[13],v=e[14],b=e[15],x=p*v*c-_*h*c+_*u*m-s*v*m-p*u*b+s*h*b,A=y*h*c-f*v*c-y*u*m+r*v*m+f*u*b-r*h*b,S=f*_*c-y*p*c+y*s*m-r*_*m-f*s*b+r*p*b,w=y*p*u-f*_*u-y*s*h+r*_*h+f*s*v-r*p*v,M=t*x+n*A+i*S+a*w;if(M===0)return this.set(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);const D=1/M;return e[0]=x*D,e[1]=(_*h*a-p*v*a-_*i*m+n*v*m+p*i*b-n*h*b)*D,e[2]=(s*v*a-_*u*a+_*i*c-n*v*c-s*i*b+n*u*b)*D,e[3]=(p*u*a-s*h*a-p*i*c+n*h*c+s*i*m-n*u*m)*D,e[4]=A*D,e[5]=(f*v*a-y*h*a+y*i*m-t*v*m-f*i*b+t*h*b)*D,e[6]=(y*u*a-r*v*a-y*i*c+t*v*c+r*i*b-t*u*b)*D,e[7]=(r*h*a-f*u*a+f*i*c-t*h*c-r*i*m+t*u*m)*D,e[8]=S*D,e[9]=(y*p*a-f*_*a-y*n*m+t*_*m+f*n*b-t*p*b)*D,e[10]=(r*_*a-y*s*a+y*n*c-t*_*c-r*n*b+t*s*b)*D,e[11]=(f*s*a-r*p*a-f*n*c+t*p*c+r*n*m-t*s*m)*D,e[12]=w*D,e[13]=(f*_*i-y*p*i+y*n*h-t*_*h-f*n*v+t*p*v)*D,e[14]=(y*s*i-r*_*i-y*n*u+t*_*u+r*n*v-t*s*v)*D,e[15]=(r*p*i-f*s*i+f*n*u-t*p*u-r*n*h+t*s*h)*D,this}scale(e){const t=this.elements,n=e.x,i=e.y,a=e.z;return t[0]*=n,t[4]*=i,t[8]*=a,t[1]*=n,t[5]*=i,t[9]*=a,t[2]*=n,t[6]*=i,t[10]*=a,t[3]*=n,t[7]*=i,t[11]*=a,this}getMaxScaleOnAxis(){const e=this.elements,t=e[0]*e[0]+e[1]*e[1]+e[2]*e[2],n=e[4]*e[4]+e[5]*e[5]+e[6]*e[6],i=e[8]*e[8]+e[9]*e[9]+e[10]*e[10];return Math.sqrt(Math.max(t,n,i))}makeTranslation(e,t,n){return this.set(1,0,0,e,0,1,0,t,0,0,1,n,0,0,0,1),this}makeRotationX(e){const t=Math.cos(e),n=Math.sin(e);return this.set(1,0,0,0,0,t,-n,0,0,n,t,0,0,0,0,1),this}makeRotationY(e){const t=Math.cos(e),n=Math.sin(e);return this.set(t,0,n,0,0,1,0,0,-n,0,t,0,0,0,0,1),this}makeRotationZ(e){const t=Math.cos(e),n=Math.sin(e);return this.set(t,-n,0,0,n,t,0,0,0,0,1,0,0,0,0,1),this}makeRotationAxis(e,t){const n=Math.cos(t),i=Math.sin(t),a=1-n,r=e.x,s=e.y,u=e.z,c=a*r,f=a*s;return this.set(c*r+n,c*s-i*u,c*u+i*s,0,c*s+i*u,f*s+n,f*u-i*r,0,c*u-i*s,f*u+i*r,a*u*u+n,0,0,0,0,1),this}makeScale(e,t,n){return this.set(e,0,0,0,0,t,0,0,0,0,n,0,0,0,0,1),this}makeShear(e,t,n,i,a,r){return this.set(1,n,a,0,e,1,r,0,t,i,1,0,0,0,0,1),this}compose(e,t,n){const i=this.elements,a=t._x,r=t._y,s=t._z,u=t._w,c=a+a,f=r+r,p=s+s,h=a*c,m=a*f,y=a*p,_=r*f,v=r*p,b=s*p,x=u*c,A=u*f,S=u*p,w=n.x,M=n.y,D=n.z;return i[0]=(1-(_+b))*w,i[1]=(m+S)*w,i[2]=(y-A)*w,i[3]=0,i[4]=(m-S)*M,i[5]=(1-(h+b))*M,i[6]=(v+x)*M,i[7]=0,i[8]=(y+A)*D,i[9]=(v-x)*D,i[10]=(1-(h+_))*D,i[11]=0,i[12]=e.x,i[13]=e.y,i[14]=e.z,i[15]=1,this}decompose(e,t,n){const i=this.elements;let a=s9.set(i[0],i[1],i[2]).length();const r=s9.set(i[4],i[5],i[6]).length(),s=s9.set(i[8],i[9],i[10]).length();this.determinant()<0&&(a=-a),e.x=i[12],e.y=i[13],e.z=i[14],u4.copy(this);const c=1/a,f=1/r,p=1/s;return u4.elements[0]*=c,u4.elements[1]*=c,u4.elements[2]*=c,u4.elements[4]*=f,u4.elements[5]*=f,u4.elements[6]*=f,u4.elements[8]*=p,u4.elements[9]*=p,u4.elements[10]*=p,t.setFromRotationMatrix(u4),n.x=a,n.y=r,n.z=s,this}makePerspective(e,t,n,i,a,r){const s=this.elements,u=2*a/(t-e),c=2*a/(n-i),f=(t+e)/(t-e),p=(n+i)/(n-i),h=-(r+a)/(r-a),m=-2*r*a/(r-a);return s[0]=u,s[4]=0,s[8]=f,s[12]=0,s[1]=0,s[5]=c,s[9]=p,s[13]=0,s[2]=0,s[6]=0,s[10]=h,s[14]=m,s[3]=0,s[7]=0,s[11]=-1,s[15]=0,this}makeOrthographic(e,t,n,i,a,r){const s=this.elements,u=1/(t-e),c=1/(n-i),f=1/(r-a),p=(t+e)*u,h=(n+i)*c,m=(r+a)*f;return s[0]=2*u,s[4]=0,s[8]=0,s[12]=-p,s[1]=0,s[5]=2*c,s[9]=0,s[13]=-h,s[2]=0,s[6]=0,s[10]=-2*f,s[14]=-m,s[3]=0,s[7]=0,s[11]=0,s[15]=1,this}equals(e){const t=this.elements,n=e.elements;for(let i=0;i<16;i++)if(t[i]!==n[i])return!1;return!0}fromArray(e,t=0){for(let n=0;n<16;n++)this.elements[n]=e[n+t];return this}toArray(e=[],t=0){const n=this.elements;return e[t]=n[0],e[t+1]=n[1],e[t+2]=n[2],e[t+3]=n[3],e[t+4]=n[4],e[t+5]=n[5],e[t+6]=n[6],e[t+7]=n[7],e[t+8]=n[8],e[t+9]=n[9],e[t+10]=n[10],e[t+11]=n[11],e[t+12]=n[12],e[t+13]=n[13],e[t+14]=n[14],e[t+15]=n[15],e}}const s9=new Y,u4=new W2,QS=new Y(0,0,0),KS=new Y(1,1,1),Q4=new Y,u8=new Y,L3=new Y,Wi=new W2,Hi=new B4;class j5{constructor(e=0,t=0,n=0,i=j5.DefaultOrder){this.isEuler=!0,this._x=e,this._y=t,this._z=n,this._order=i}get x(){return this._x}set x(e){this._x=e,this._onChangeCallback()}get y(){return this._y}set y(e){this._y=e,this._onChangeCallback()}get z(){return this._z}set z(e){this._z=e,this._onChangeCallback()}get order(){return this._order}set order(e){this._order=e,this._onChangeCallback()}set(e,t,n,i=this._order){return this._x=e,this._y=t,this._z=n,this._order=i,this._onChangeCallback(),this}clone(){return new this.constructor(this._x,this._y,this._z,this._order)}copy(e){return this._x=e._x,this._y=e._y,this._z=e._z,this._order=e._order,this._onChangeCallback(),this}setFromRotationMatrix(e,t=this._order,n=!0){const i=e.elements,a=i[0],r=i[4],s=i[8],u=i[1],c=i[5],f=i[9],p=i[2],h=i[6],m=i[10];switch(t){case"XYZ":this._y=Math.asin(m3(s,-1,1)),Math.abs(s)<.9999999?(this._x=Math.atan2(-f,m),this._z=Math.atan2(-r,a)):(this._x=Math.atan2(h,c),this._z=0);break;case"YXZ":this._x=Math.asin(-m3(f,-1,1)),Math.abs(f)<.9999999?(this._y=Math.atan2(s,m),this._z=Math.atan2(u,c)):(this._y=Math.atan2(-p,a),this._z=0);break;case"ZXY":this._x=Math.asin(m3(h,-1,1)),Math.abs(h)<.9999999?(this._y=Math.atan2(-p,m),this._z=Math.atan2(-r,c)):(this._y=0,this._z=Math.atan2(u,a));break;case"ZYX":this._y=Math.asin(-m3(p,-1,1)),Math.abs(p)<.9999999?(this._x=Math.atan2(h,m),this._z=Math.atan2(u,a)):(this._x=0,this._z=Math.atan2(-r,c));break;case"YZX":this._z=Math.asin(m3(u,-1,1)),Math.abs(u)<.9999999?(this._x=Math.atan2(-f,c),this._y=Math.atan2(-p,a)):(this._x=0,this._y=Math.atan2(s,m));break;case"XZY":this._z=Math.asin(-m3(r,-1,1)),Math.abs(r)<.9999999?(this._x=Math.atan2(h,c),this._y=Math.atan2(s,a)):(this._x=Math.atan2(-f,m),this._y=0);break;default:console.warn("THREE.Euler: .setFromRotationMatrix() encountered an unknown order: "+t)}return this._order=t,n===!0&&this._onChangeCallback(),this}setFromQuaternion(e,t,n){return Wi.makeRotationFromQuaternion(e),this.setFromRotationMatrix(Wi,t,n)}setFromVector3(e,t=this._order){return this.set(e.x,e.y,e.z,t)}reorder(e){return Hi.setFromEuler(this),this.setFromQuaternion(Hi,e)}equals(e){return e._x===this._x&&e._y===this._y&&e._z===this._z&&e._order===this._order}fromArray(e){return this._x=e[0],this._y=e[1],this._z=e[2],e[3]!==void 0&&(this._order=e[3]),this._onChangeCallback(),this}toArray(e=[],t=0){return e[t]=this._x,e[t+1]=this._y,e[t+2]=this._z,e[t+3]=this._order,e}_onChange(e){return this._onChangeCallback=e,this}_onChangeCallback(){}*[Symbol.iterator](){yield this._x,yield this._y,yield this._z,yield this._order}toVector3(){console.error("THREE.Euler: .toVector3() has been removed. Use Vector3.setFromEuler() instead")}}j5.DefaultOrder="XYZ";j5.RotationOrders=["XYZ","YZX","ZXY","XZY","YXZ","ZYX"];class Dr{constructor(){this.mask=1}set(e){this.mask=(1<<e|0)>>>0}enable(e){this.mask|=1<<e|0}enableAll(){this.mask=-1}toggle(e){this.mask^=1<<e|0}disable(e){this.mask&=~(1<<e|0)}disableAll(){this.mask=0}test(e){return(this.mask&e.mask)!==0}isEnabled(e){return(this.mask&(1<<e|0))!==0}}let eM=0;const $i=new Y,l9=new B4,D4=new W2,c8=new Y,o5=new Y,tM=new Y,nM=new B4,Yi=new Y(1,0,0),Ji=new Y(0,1,0),Zi=new Y(0,0,1),iM={type:"added"},qi={type:"removed"};class v3 extends Z0{constructor(){super(),this.isObject3D=!0,Object.defineProperty(this,"id",{value:eM++}),this.uuid=c0(),this.name="",this.type="Object3D",this.parent=null,this.children=[],this.up=v3.DefaultUp.clone();const e=new Y,t=new j5,n=new B4,i=new Y(1,1,1);function a(){n.setFromEuler(t,!1)}function r(){t.setFromQuaternion(n,void 0,!1)}t._onChange(a),n._onChange(r),Object.defineProperties(this,{position:{configurable:!0,enumerable:!0,value:e},rotation:{configurable:!0,enumerable:!0,value:t},quaternion:{configurable:!0,enumerable:!0,value:n},scale:{configurable:!0,enumerable:!0,value:i},modelViewMatrix:{value:new W2},normalMatrix:{value:new X3}}),this.matrix=new W2,this.matrixWorld=new W2,this.matrixAutoUpdate=v3.DefaultMatrixAutoUpdate,this.matrixWorldNeedsUpdate=!1,this.matrixWorldAutoUpdate=v3.DefaultMatrixWorldAutoUpdate,this.layers=new Dr,this.visible=!0,this.castShadow=!1,this.receiveShadow=!1,this.frustumCulled=!0,this.renderOrder=0,this.animations=[],this.userData={}}onBeforeRender(){}onAfterRender(){}applyMatrix4(e){this.matrixAutoUpdate&&this.updateMatrix(),this.matrix.premultiply(e),this.matrix.decompose(this.position,this.quaternion,this.scale)}applyQuaternion(e){return this.quaternion.premultiply(e),this}setRotationFromAxisAngle(e,t){this.quaternion.setFromAxisAngle(e,t)}setRotationFromEuler(e){this.quaternion.setFromEuler(e,!0)}setRotationFromMatrix(e){this.quaternion.setFromRotationMatrix(e)}setRotationFromQuaternion(e){this.quaternion.copy(e)}rotateOnAxis(e,t){return l9.setFromAxisAngle(e,t),this.quaternion.multiply(l9),this}rotateOnWorldAxis(e,t){return l9.setFromAxisAngle(e,t),this.quaternion.premultiply(l9),this}rotateX(e){return this.rotateOnAxis(Yi,e)}rotateY(e){return this.rotateOnAxis(Ji,e)}rotateZ(e){return this.rotateOnAxis(Zi,e)}translateOnAxis(e,t){return $i.copy(e).applyQuaternion(this.quaternion),this.position.add($i.multiplyScalar(t)),this}translateX(e){return this.translateOnAxis(Yi,e)}translateY(e){return this.translateOnAxis(Ji,e)}translateZ(e){return this.translateOnAxis(Zi,e)}localToWorld(e){return e.applyMatrix4(this.matrixWorld)}worldToLocal(e){return e.applyMatrix4(D4.copy(this.matrixWorld).invert())}lookAt(e,t,n){e.isVector3?c8.copy(e):c8.set(e,t,n);const i=this.parent;this.updateWorldMatrix(!0,!1),o5.setFromMatrixPosition(this.matrixWorld),this.isCamera||this.isLight?D4.lookAt(o5,c8,this.up):D4.lookAt(c8,o5,this.up),this.quaternion.setFromRotationMatrix(D4),i&&(D4.extractRotation(i.matrixWorld),l9.setFromRotationMatrix(D4),this.quaternion.premultiply(l9.invert()))}add(e){if(arguments.length>1){for(let t=0;t<arguments.length;t++)this.add(arguments[t]);return this}return e===this?(console.error("THREE.Object3D.add: object can't be added as a child of itself.",e),this):(e&&e.isObject3D?(e.parent!==null&&e.parent.remove(e),e.parent=this,this.children.push(e),e.dispatchEvent(iM)):console.error("THREE.Object3D.add: object not an instance of THREE.Object3D.",e),this)}remove(e){if(arguments.length>1){for(let n=0;n<arguments.length;n++)this.remove(arguments[n]);return this}const t=this.children.indexOf(e);return t!==-1&&(e.parent=null,this.children.splice(t,1),e.dispatchEvent(qi)),this}removeFromParent(){const e=this.parent;return e!==null&&e.remove(this),this}clear(){for(let e=0;e<this.children.length;e++){const t=this.children[e];t.parent=null,t.dispatchEvent(qi)}return this.children.length=0,this}attach(e){return this.updateWorldMatrix(!0,!1),D4.copy(this.matrixWorld).invert(),e.parent!==null&&(e.parent.updateWorldMatrix(!0,!1),D4.multiply(e.parent.matrixWorld)),e.applyMatrix4(D4),this.add(e),e.updateWorldMatrix(!1,!0),this}getObjectById(e){return this.getObjectByProperty("id",e)}getObjectByName(e){return this.getObjectByProperty("name",e)}getObjectByProperty(e,t){if(this[e]===t)return this;for(let n=0,i=this.children.length;n<i;n++){const r=this.children[n].getObjectByProperty(e,t);if(r!==void 0)return r}}getWorldPosition(e){return this.updateWorldMatrix(!0,!1),e.setFromMatrixPosition(this.matrixWorld)}getWorldQuaternion(e){return this.updateWorldMatrix(!0,!1),this.matrixWorld.decompose(o5,e,tM),e}getWorldScale(e){return this.updateWorldMatrix(!0,!1),this.matrixWorld.decompose(o5,nM,e),e}getWorldDirection(e){this.updateWorldMatrix(!0,!1);const t=this.matrixWorld.elements;return e.set(t[8],t[9],t[10]).normalize()}raycast(){}traverse(e){e(this);const t=this.children;for(let n=0,i=t.length;n<i;n++)t[n].traverse(e)}traverseVisible(e){if(this.visible===!1)return;e(this);const t=this.children;for(let n=0,i=t.length;n<i;n++)t[n].traverseVisible(e)}traverseAncestors(e){const t=this.parent;t!==null&&(e(t),t.traverseAncestors(e))}updateMatrix(){this.matrix.compose(this.position,this.quaternion,this.scale),this.matrixWorldNeedsUpdate=!0}updateMatrixWorld(e){this.matrixAutoUpdate&&this.updateMatrix(),(this.matrixWorldNeedsUpdate||e)&&(this.parent===null?this.matrixWorld.copy(this.matrix):this.matrixWorld.multiplyMatrices(this.parent.matrixWorld,this.matrix),this.matrixWorldNeedsUpdate=!1,e=!0);const t=this.children;for(let n=0,i=t.length;n<i;n++){const a=t[n];(a.matrixWorldAutoUpdate===!0||e===!0)&&a.updateMatrixWorld(e)}}updateWorldMatrix(e,t){const n=this.parent;if(e===!0&&n!==null&&n.matrixWorldAutoUpdate===!0&&n.updateWorldMatrix(!0,!1),this.matrixAutoUpdate&&this.updateMatrix(),this.parent===null?this.matrixWorld.copy(this.matrix):this.matrixWorld.multiplyMatrices(this.parent.matrixWorld,this.matrix),t===!0){const i=this.children;for(let a=0,r=i.length;a<r;a++){const s=i[a];s.matrixWorldAutoUpdate===!0&&s.updateWorldMatrix(!1,!0)}}}toJSON(e){const t=e===void 0||typeof e=="string",n={};t&&(e={geometries:{},materials:{},textures:{},images:{},shapes:{},skeletons:{},animations:{},nodes:{}},n.metadata={version:4.5,type:"Object",generator:"Object3D.toJSON"});const i={};i.uuid=this.uuid,i.type=this.type,this.name!==""&&(i.name=this.name),this.castShadow===!0&&(i.castShadow=!0),this.receiveShadow===!0&&(i.receiveShadow=!0),this.visible===!1&&(i.visible=!1),this.frustumCulled===!1&&(i.frustumCulled=!1),this.renderOrder!==0&&(i.renderOrder=this.renderOrder),JSON.stringify(this.userData)!=="{}"&&(i.userData=this.userData),i.layers=this.layers.mask,i.matrix=this.matrix.toArray(),this.matrixAutoUpdate===!1&&(i.matrixAutoUpdate=!1),this.isInstancedMesh&&(i.type="InstancedMesh",i.count=this.count,i.instanceMatrix=this.instanceMatrix.toJSON(),this.instanceColor!==null&&(i.instanceColor=this.instanceColor.toJSON()));function a(s,u){return s[u.uuid]===void 0&&(s[u.uuid]=u.toJSON(e)),u.uuid}if(this.isScene)this.background&&(this.background.isColor?i.background=this.background.toJSON():this.background.isTexture&&(i.background=this.background.toJSON(e).uuid)),this.environment&&this.environment.isTexture&&this.environment.isRenderTargetTexture!==!0&&(i.environment=this.environment.toJSON(e).uuid);else if(this.isMesh||this.isLine||this.isPoints){i.geometry=a(e.geometries,this.geometry);const s=this.geometry.parameters;if(s!==void 0&&s.shapes!==void 0){const u=s.shapes;if(Array.isArray(u))for(let c=0,f=u.length;c<f;c++){const p=u[c];a(e.shapes,p)}else a(e.shapes,u)}}if(this.isSkinnedMesh&&(i.bindMode=this.bindMode,i.bindMatrix=this.bindMatrix.toArray(),this.skeleton!==void 0&&(a(e.skeletons,this.skeleton),i.skeleton=this.skeleton.uuid)),this.material!==void 0)if(Array.isArray(this.material)){const s=[];for(let u=0,c=this.material.length;u<c;u++)s.push(a(e.materials,this.material[u]));i.material=s}else i.material=a(e.materials,this.material);if(this.children.length>0){i.children=[];for(let s=0;s<this.children.length;s++)i.children.push(this.children[s].toJSON(e).object)}if(this.animations.length>0){i.animations=[];for(let s=0;s<this.animations.length;s++){const u=this.animations[s];i.animations.push(a(e.animations,u))}}if(t){const s=r(e.geometries),u=r(e.materials),c=r(e.textures),f=r(e.images),p=r(e.shapes),h=r(e.skeletons),m=r(e.animations),y=r(e.nodes);s.length>0&&(n.geometries=s),u.length>0&&(n.materials=u),c.length>0&&(n.textures=c),f.length>0&&(n.images=f),p.length>0&&(n.shapes=p),h.length>0&&(n.skeletons=h),m.length>0&&(n.animations=m),y.length>0&&(n.nodes=y)}return n.object=i,n;function r(s){const u=[];for(const c in s){const f=s[c];delete f.metadata,u.push(f)}return u}}clone(e){return new this.constructor().copy(this,e)}copy(e,t=!0){if(this.name=e.name,this.up.copy(e.up),this.position.copy(e.position),this.rotation.order=e.rotation.order,this.quaternion.copy(e.quaternion),this.scale.copy(e.scale),this.matrix.copy(e.matrix),this.matrixWorld.copy(e.matrixWorld),this.matrixAutoUpdate=e.matrixAutoUpdate,this.matrixWorldNeedsUpdate=e.matrixWorldNeedsUpdate,this.matrixWorldAutoUpdate=e.matrixWorldAutoUpdate,this.layers.mask=e.layers.mask,this.visible=e.visible,this.castShadow=e.castShadow,this.receiveShadow=e.receiveShadow,this.frustumCulled=e.frustumCulled,this.renderOrder=e.renderOrder,this.userData=JSON.parse(JSON.stringify(e.userData)),t===!0)for(let n=0;n<e.children.length;n++){const i=e.children[n];this.add(i.clone())}return this}}v3.DefaultUp=new Y(0,1,0);v3.DefaultMatrixAutoUpdate=!0;v3.DefaultMatrixWorldAutoUpdate=!0;const c4=new Y,I4=new Y,$6=new Y,R4=new Y,u9=new Y,c9=new Y,Xi=new Y,Y6=new Y,J6=new Y,Z6=new Y;class g4{constructor(e=new Y,t=new Y,n=new Y){this.a=e,this.b=t,this.c=n}static getNormal(e,t,n,i){i.subVectors(n,t),c4.subVectors(e,t),i.cross(c4);const a=i.lengthSq();return a>0?i.multiplyScalar(1/Math.sqrt(a)):i.set(0,0,0)}static getBarycoord(e,t,n,i,a){c4.subVectors(i,t),I4.subVectors(n,t),$6.subVectors(e,t);const r=c4.dot(c4),s=c4.dot(I4),u=c4.dot($6),c=I4.dot(I4),f=I4.dot($6),p=r*c-s*s;if(p===0)return a.set(-2,-1,-1);const h=1/p,m=(c*u-s*f)*h,y=(r*f-s*u)*h;return a.set(1-m-y,y,m)}static containsPoint(e,t,n,i){return this.getBarycoord(e,t,n,i,R4),R4.x>=0&&R4.y>=0&&R4.x+R4.y<=1}static getUV(e,t,n,i,a,r,s,u){return this.getBarycoord(e,t,n,i,R4),u.set(0,0),u.addScaledVector(a,R4.x),u.addScaledVector(r,R4.y),u.addScaledVector(s,R4.z),u}static isFrontFacing(e,t,n,i){return c4.subVectors(n,t),I4.subVectors(e,t),c4.cross(I4).dot(i)<0}set(e,t,n){return this.a.copy(e),this.b.copy(t),this.c.copy(n),this}setFromPointsAndIndices(e,t,n,i){return this.a.copy(e[t]),this.b.copy(e[n]),this.c.copy(e[i]),this}setFromAttributeAndIndices(e,t,n,i){return this.a.fromBufferAttribute(e,t),this.b.fromBufferAttribute(e,n),this.c.fromBufferAttribute(e,i),this}clone(){return new this.constructor().copy(this)}copy(e){return this.a.copy(e.a),this.b.copy(e.b),this.c.copy(e.c),this}getArea(){return c4.subVectors(this.c,this.b),I4.subVectors(this.a,this.b),c4.cross(I4).length()*.5}getMidpoint(e){return e.addVectors(this.a,this.b).add(this.c).multiplyScalar(1/3)}getNormal(e){return g4.getNormal(this.a,this.b,this.c,e)}getPlane(e){return e.setFromCoplanarPoints(this.a,this.b,this.c)}getBarycoord(e,t){return g4.getBarycoord(e,this.a,this.b,this.c,t)}getUV(e,t,n,i,a){return g4.getUV(e,this.a,this.b,this.c,t,n,i,a)}containsPoint(e){return g4.containsPoint(e,this.a,this.b,this.c)}isFrontFacing(e){return g4.isFrontFacing(this.a,this.b,this.c,e)}intersectsBox(e){return e.intersectsTriangle(this)}closestPointToPoint(e,t){const n=this.a,i=this.b,a=this.c;let r,s;u9.subVectors(i,n),c9.subVectors(a,n),Y6.subVectors(e,n);const u=u9.dot(Y6),c=c9.dot(Y6);if(u<=0&&c<=0)return t.copy(n);J6.subVectors(e,i);const f=u9.dot(J6),p=c9.dot(J6);if(f>=0&&p<=f)return t.copy(i);const h=u*p-f*c;if(h<=0&&u>=0&&f<=0)return r=u/(u-f),t.copy(n).addScaledVector(u9,r);Z6.subVectors(e,a);const m=u9.dot(Z6),y=c9.dot(Z6);if(y>=0&&m<=y)return t.copy(a);const _=m*c-u*y;if(_<=0&&c>=0&&y<=0)return s=c/(c-y),t.copy(n).addScaledVector(c9,s);const v=f*y-m*p;if(v<=0&&p-f>=0&&m-y>=0)return Xi.subVectors(a,i),s=(p-f)/(p-f+(m-y)),t.copy(i).addScaledVector(Xi,s);const b=1/(v+_+h);return r=_*b,s=h*b,t.copy(n).addScaledVector(u9,r).addScaledVector(c9,s)}equals(e){return e.a.equals(this.a)&&e.b.equals(this.b)&&e.c.equals(this.c)}}let oM=0;class q0 extends Z0{constructor(){super(),this.isMaterial=!0,Object.defineProperty(this,"id",{value:oM++}),this.uuid=c0(),this.name="",this.type="Material",this.blending=k0,this.side=B9,this.vertexColors=!1,this.opacity=1,this.transparent=!1,this.blendSrc=yr,this.blendDst=br,this.blendEquation=A9,this.blendSrcAlpha=null,this.blendDstAlpha=null,this.blendEquationAlpha=null,this.depthFunc=Ae,this.depthTest=!0,this.depthWrite=!0,this.stencilWriteMask=255,this.stencilFunc=YS,this.stencilRef=0,this.stencilFuncMask=255,this.stencilFail=P6,this.stencilZFail=P6,this.stencilZPass=P6,this.stencilWrite=!1,this.clippingPlanes=null,this.clipIntersection=!1,this.clipShadows=!1,this.shadowSide=null,this.colorWrite=!0,this.precision=null,this.polygonOffset=!1,this.polygonOffsetFactor=0,this.polygonOffsetUnits=0,this.dithering=!1,this.alphaToCoverage=!1,this.premultipliedAlpha=!1,this.visible=!0,this.toneMapped=!0,this.userData={},this.version=0,this._alphaTest=0}get alphaTest(){return this._alphaTest}set alphaTest(e){this._alphaTest>0!=e>0&&this.version++,this._alphaTest=e}onBuild(){}onBeforeRender(){}onBeforeCompile(){}customProgramCacheKey(){return this.onBeforeCompile.toString()}setValues(e){if(e!==void 0)for(const t in e){const n=e[t];if(n===void 0){console.warn("THREE.Material: '"+t+"' parameter is undefined.");continue}const i=this[t];if(i===void 0){console.warn("THREE."+this.type+": '"+t+"' is not a property of this material.");continue}i&&i.isColor?i.set(n):i&&i.isVector3&&n&&n.isVector3?i.copy(n):this[t]=n}}toJSON(e){const t=e===void 0||typeof e=="string";t&&(e={textures:{},images:{}});const n={metadata:{version:4.5,type:"Material",generator:"Material.toJSON"}};n.uuid=this.uuid,n.type=this.type,this.name!==""&&(n.name=this.name),this.color&&this.color.isColor&&(n.color=this.color.getHex()),this.roughness!==void 0&&(n.roughness=this.roughness),this.metalness!==void 0&&(n.metalness=this.metalness),this.sheen!==void 0&&(n.sheen=this.sheen),this.sheenColor&&this.sheenColor.isColor&&(n.sheenColor=this.sheenColor.getHex()),this.sheenRoughness!==void 0&&(n.sheenRoughness=this.sheenRoughness),this.emissive&&this.emissive.isColor&&(n.emissive=this.emissive.getHex()),this.emissiveIntensity&&this.emissiveIntensity!==1&&(n.emissiveIntensity=this.emissiveIntensity),this.specular&&this.specular.isColor&&(n.specular=this.specular.getHex()),this.specularIntensity!==void 0&&(n.specularIntensity=this.specularIntensity),this.specularColor&&this.specularColor.isColor&&(n.specularColor=this.specularColor.getHex()),this.shininess!==void 0&&(n.shininess=this.shininess),this.clearcoat!==void 0&&(n.clearcoat=this.clearcoat),this.clearcoatRoughness!==void 0&&(n.clearcoatRoughness=this.clearcoatRoughness),this.clearcoatMap&&this.clearcoatMap.isTexture&&(n.clearcoatMap=this.clearcoatMap.toJSON(e).uuid),this.clearcoatRoughnessMap&&this.clearcoatRoughnessMap.isTexture&&(n.clearcoatRoughnessMap=this.clearcoatRoughnessMap.toJSON(e).uuid),this.clearcoatNormalMap&&this.clearcoatNormalMap.isTexture&&(n.clearcoatNormalMap=this.clearcoatNormalMap.toJSON(e).uuid,n.clearcoatNormalScale=this.clearcoatNormalScale.toArray()),this.iridescence!==void 0&&(n.iridescence=this.iridescence),this.iridescenceIOR!==void 0&&(n.iridescenceIOR=this.iridescenceIOR),this.iridescenceThicknessRange!==void 0&&(n.iridescenceThicknessRange=this.iridescenceThicknessRange),this.iridescenceMap&&this.iridescenceMap.isTexture&&(n.iridescenceMap=this.iridescenceMap.toJSON(e).uuid),this.iridescenceThicknessMap&&this.iridescenceThicknessMap.isTexture&&(n.iridescenceThicknessMap=this.iridescenceThicknessMap.toJSON(e).uuid),this.map&&this.map.isTexture&&(n.map=this.map.toJSON(e).uuid),this.matcap&&this.matcap.isTexture&&(n.matcap=this.matcap.toJSON(e).uuid),this.alphaMap&&this.alphaMap.isTexture&&(n.alphaMap=this.alphaMap.toJSON(e).uuid),this.lightMap&&this.lightMap.isTexture&&(n.lightMap=this.lightMap.toJSON(e).uuid,n.lightMapIntensity=this.lightMapIntensity),this.aoMap&&this.aoMap.isTexture&&(n.aoMap=this.aoMap.toJSON(e).uuid,n.aoMapIntensity=this.aoMapIntensity),this.bumpMap&&this.bumpMap.isTexture&&(n.bumpMap=this.bumpMap.toJSON(e).uuid,n.bumpScale=this.bumpScale),this.normalMap&&this.normalMap.isTexture&&(n.normalMap=this.normalMap.toJSON(e).uuid,n.normalMapType=this.normalMapType,n.normalScale=this.normalScale.toArray()),this.displacementMap&&this.displacementMap.isTexture&&(n.displacementMap=this.displacementMap.toJSON(e).uuid,n.displacementScale=this.displacementScale,n.displacementBias=this.displacementBias),this.roughnessMap&&this.roughnessMap.isTexture&&(n.roughnessMap=this.roughnessMap.toJSON(e).uuid),this.metalnessMap&&this.metalnessMap.isTexture&&(n.metalnessMap=this.metalnessMap.toJSON(e).uuid),this.emissiveMap&&this.emissiveMap.isTexture&&(n.emissiveMap=this.emissiveMap.toJSON(e).uuid),this.specularMap&&this.specularMap.isTexture&&(n.specularMap=this.specularMap.toJSON(e).uuid),this.specularIntensityMap&&this.specularIntensityMap.isTexture&&(n.specularIntensityMap=this.specularIntensityMap.toJSON(e).uuid),this.specularColorMap&&this.specularColorMap.isTexture&&(n.specularColorMap=this.specularColorMap.toJSON(e).uuid),this.envMap&&this.envMap.isTexture&&(n.envMap=this.envMap.toJSON(e).uuid,this.combine!==void 0&&(n.combine=this.combine)),this.envMapIntensity!==void 0&&(n.envMapIntensity=this.envMapIntensity),this.reflectivity!==void 0&&(n.reflectivity=this.reflectivity),this.refractionRatio!==void 0&&(n.refractionRatio=this.refractionRatio),this.gradientMap&&this.gradientMap.isTexture&&(n.gradientMap=this.gradientMap.toJSON(e).uuid),this.transmission!==void 0&&(n.transmission=this.transmission),this.transmissionMap&&this.transmissionMap.isTexture&&(n.transmissionMap=this.transmissionMap.toJSON(e).uuid),this.thickness!==void 0&&(n.thickness=this.thickness),this.thicknessMap&&this.thicknessMap.isTexture&&(n.thicknessMap=this.thicknessMap.toJSON(e).uuid),this.attenuationDistance!==void 0&&(n.attenuationDistance=this.attenuationDistance),this.attenuationColor!==void 0&&(n.attenuationColor=this.attenuationColor.getHex()),this.size!==void 0&&(n.size=this.size),this.shadowSide!==null&&(n.shadowSide=this.shadowSide),this.sizeAttenuation!==void 0&&(n.sizeAttenuation=this.sizeAttenuation),this.blending!==k0&&(n.blending=this.blending),this.side!==B9&&(n.side=this.side),this.vertexColors&&(n.vertexColors=!0),this.opacity<1&&(n.opacity=this.opacity),this.transparent===!0&&(n.transparent=this.transparent),n.depthFunc=this.depthFunc,n.depthTest=this.depthTest,n.depthWrite=this.depthWrite,n.colorWrite=this.colorWrite,n.stencilWrite=this.stencilWrite,n.stencilWriteMask=this.stencilWriteMask,n.stencilFunc=this.stencilFunc,n.stencilRef=this.stencilRef,n.stencilFuncMask=this.stencilFuncMask,n.stencilFail=this.stencilFail,n.stencilZFail=this.stencilZFail,n.stencilZPass=this.stencilZPass,this.rotation!==void 0&&this.rotation!==0&&(n.rotation=this.rotation),this.polygonOffset===!0&&(n.polygonOffset=!0),this.polygonOffsetFactor!==0&&(n.polygonOffsetFactor=this.polygonOffsetFactor),this.polygonOffsetUnits!==0&&(n.polygonOffsetUnits=this.polygonOffsetUnits),this.linewidth!==void 0&&this.linewidth!==1&&(n.linewidth=this.linewidth),this.dashSize!==void 0&&(n.dashSize=this.dashSize),this.gapSize!==void 0&&(n.gapSize=this.gapSize),this.scale!==void 0&&(n.scale=this.scale),this.dithering===!0&&(n.dithering=!0),this.alphaTest>0&&(n.alphaTest=this.alphaTest),this.alphaToCoverage===!0&&(n.alphaToCoverage=this.alphaToCoverage),this.premultipliedAlpha===!0&&(n.premultipliedAlpha=this.premultipliedAlpha),this.wireframe===!0&&(n.wireframe=this.wireframe),this.wireframeLinewidth>1&&(n.wireframeLinewidth=this.wireframeLinewidth),this.wireframeLinecap!=="round"&&(n.wireframeLinecap=this.wireframeLinecap),this.wireframeLinejoin!=="round"&&(n.wireframeLinejoin=this.wireframeLinejoin),this.flatShading===!0&&(n.flatShading=this.flatShading),this.visible===!1&&(n.visible=!1),this.toneMapped===!1&&(n.toneMapped=!1),this.fog===!1&&(n.fog=!1),JSON.stringify(this.userData)!=="{}"&&(n.userData=this.userData);function i(a){const r=[];for(const s in a){const u=a[s];delete u.metadata,r.push(u)}return r}if(t){const a=i(e.textures),r=i(e.images);a.length>0&&(n.textures=a),r.length>0&&(n.images=r)}return n}clone(){return new this.constructor().copy(this)}copy(e){this.name=e.name,this.blending=e.blending,this.side=e.side,this.vertexColors=e.vertexColors,this.opacity=e.opacity,this.transparent=e.transparent,this.blendSrc=e.blendSrc,this.blendDst=e.blendDst,this.blendEquation=e.blendEquation,this.blendSrcAlpha=e.blendSrcAlpha,this.blendDstAlpha=e.blendDstAlpha,this.blendEquationAlpha=e.blendEquationAlpha,this.depthFunc=e.depthFunc,this.depthTest=e.depthTest,this.depthWrite=e.depthWrite,this.stencilWriteMask=e.stencilWriteMask,this.stencilFunc=e.stencilFunc,this.stencilRef=e.stencilRef,this.stencilFuncMask=e.stencilFuncMask,this.stencilFail=e.stencilFail,this.stencilZFail=e.stencilZFail,this.stencilZPass=e.stencilZPass,this.stencilWrite=e.stencilWrite;const t=e.clippingPlanes;let n=null;if(t!==null){const i=t.length;n=new Array(i);for(let a=0;a!==i;++a)n[a]=t[a].clone()}return this.clippingPlanes=n,this.clipIntersection=e.clipIntersection,this.clipShadows=e.clipShadows,this.shadowSide=e.shadowSide,this.colorWrite=e.colorWrite,this.precision=e.precision,this.polygonOffset=e.polygonOffset,this.polygonOffsetFactor=e.polygonOffsetFactor,this.polygonOffsetUnits=e.polygonOffsetUnits,this.dithering=e.dithering,this.alphaTest=e.alphaTest,this.alphaToCoverage=e.alphaToCoverage,this.premultipliedAlpha=e.premultipliedAlpha,this.visible=e.visible,this.toneMapped=e.toneMapped,this.userData=JSON.parse(JSON.stringify(e.userData)),this}dispose(){this.dispatchEvent({type:"dispose"})}set needsUpdate(e){e===!0&&this.version++}}class k4 extends q0{constructor(e){super(),this.isMeshBasicMaterial=!0,this.type="MeshBasicMaterial",this.color=new y2(16777215),this.map=null,this.lightMap=null,this.lightMapIntensity=1,this.aoMap=null,this.aoMapIntensity=1,this.specularMap=null,this.alphaMap=null,this.envMap=null,this.combine=xr,this.reflectivity=1,this.refractionRatio=.98,this.wireframe=!1,this.wireframeLinewidth=1,this.wireframeLinecap="round",this.wireframeLinejoin="round",this.fog=!0,this.setValues(e)}copy(e){return super.copy(e),this.color.copy(e.color),this.map=e.map,this.lightMap=e.lightMap,this.lightMapIntensity=e.lightMapIntensity,this.aoMap=e.aoMap,this.aoMapIntensity=e.aoMapIntensity,this.specularMap=e.specularMap,this.alphaMap=e.alphaMap,this.envMap=e.envMap,this.combine=e.combine,this.reflectivity=e.reflectivity,this.refractionRatio=e.refractionRatio,this.wireframe=e.wireframe,this.wireframeLinewidth=e.wireframeLinewidth,this.wireframeLinecap=e.wireframeLinecap,this.wireframeLinejoin=e.wireframeLinejoin,this.fog=e.fog,this}}const Z2=new Y,d8=new G1;class F3{constructor(e,t,n){if(Array.isArray(e))throw new TypeError("THREE.BufferAttribute: array should be a Typed Array.");this.isBufferAttribute=!0,this.name="",this.array=e,this.itemSize=t,this.count=e!==void 0?e.length/t:0,this.normalized=n===!0,this.usage=De,this.updateRange={offset:0,count:-1},this.version=0}onUploadCallback(){}set needsUpdate(e){e===!0&&this.version++}setUsage(e){return this.usage=e,this}copy(e){return this.name=e.name,this.array=new e.array.constructor(e.array),this.itemSize=e.itemSize,this.count=e.count,this.normalized=e.normalized,this.usage=e.usage,this}copyAt(e,t,n){e*=this.itemSize,n*=t.itemSize;for(let i=0,a=this.itemSize;i<a;i++)this.array[e+i]=t.array[n+i];return this}copyArray(e){return this.array.set(e),this}applyMatrix3(e){if(this.itemSize===2)for(let t=0,n=this.count;t<n;t++)d8.fromBufferAttribute(this,t),d8.applyMatrix3(e),this.setXY(t,d8.x,d8.y);else if(this.itemSize===3)for(let t=0,n=this.count;t<n;t++)Z2.fromBufferAttribute(this,t),Z2.applyMatrix3(e),this.setXYZ(t,Z2.x,Z2.y,Z2.z);return this}applyMatrix4(e){for(let t=0,n=this.count;t<n;t++)Z2.fromBufferAttribute(this,t),Z2.applyMatrix4(e),this.setXYZ(t,Z2.x,Z2.y,Z2.z);return this}applyNormalMatrix(e){for(let t=0,n=this.count;t<n;t++)Z2.fromBufferAttribute(this,t),Z2.applyNormalMatrix(e),this.setXYZ(t,Z2.x,Z2.y,Z2.z);return this}transformDirection(e){for(let t=0,n=this.count;t<n;t++)Z2.fromBufferAttribute(this,t),Z2.transformDirection(e),this.setXYZ(t,Z2.x,Z2.y,Z2.z);return this}set(e,t=0){return this.array.set(e,t),this}getX(e){let t=this.array[e*this.itemSize];return this.normalized&&(t=a0(t,this.array)),t}setX(e,t){return this.normalized&&(t=T2(t,this.array)),this.array[e*this.itemSize]=t,this}getY(e){let t=this.array[e*this.itemSize+1];return this.normalized&&(t=a0(t,this.array)),t}setY(e,t){return this.normalized&&(t=T2(t,this.array)),this.array[e*this.itemSize+1]=t,this}getZ(e){let t=this.array[e*this.itemSize+2];return this.normalized&&(t=a0(t,this.array)),t}setZ(e,t){return this.normalized&&(t=T2(t,this.array)),this.array[e*this.itemSize+2]=t,this}getW(e){let t=this.array[e*this.itemSize+3];return this.normalized&&(t=a0(t,this.array)),t}setW(e,t){return this.normalized&&(t=T2(t,this.array)),this.array[e*this.itemSize+3]=t,this}setXY(e,t,n){return e*=this.itemSize,this.normalized&&(t=T2(t,this.array),n=T2(n,this.array)),this.array[e+0]=t,this.array[e+1]=n,this}setXYZ(e,t,n,i){return e*=this.itemSize,this.normalized&&(t=T2(t,this.array),n=T2(n,this.array),i=T2(i,this.array)),this.array[e+0]=t,this.array[e+1]=n,this.array[e+2]=i,this}setXYZW(e,t,n,i,a){return e*=this.itemSize,this.normalized&&(t=T2(t,this.array),n=T2(n,this.array),i=T2(i,this.array),a=T2(a,this.array)),this.array[e+0]=t,this.array[e+1]=n,this.array[e+2]=i,this.array[e+3]=a,this}onUpload(e){return this.onUploadCallback=e,this}clone(){return new this.constructor(this.array,this.itemSize).copy(this)}toJSON(){const e={itemSize:this.itemSize,type:this.array.constructor.name,array:Array.from(this.array),normalized:this.normalized};return this.name!==""&&(e.name=this.name),this.usage!==De&&(e.usage=this.usage),(this.updateRange.offset!==0||this.updateRange.count!==-1)&&(e.updateRange=this.updateRange),e}copyColorsArray(){console.error("THREE.BufferAttribute: copyColorsArray() was removed in r144.")}copyVector2sArray(){console.error("THREE.BufferAttribute: copyVector2sArray() was removed in r144.")}copyVector3sArray(){console.error("THREE.BufferAttribute: copyVector3sArray() was removed in r144.")}copyVector4sArray(){console.error("THREE.BufferAttribute: copyVector4sArray() was removed in r144.")}}class Ir extends F3{constructor(e,t,n){super(new Uint16Array(e),t,n)}}class Rr extends F3{constructor(e,t,n){super(new Uint32Array(e),t,n)}}class w3 extends F3{constructor(e,t,n){super(new Float32Array(e),t,n)}}let aM=0;const $3=new W2,q6=new v3,d9=new Y,z3=new G5,a5=new G5,a3=new Y;class O3 extends Z0{constructor(){super(),this.isBufferGeometry=!0,Object.defineProperty(this,"id",{value:aM++}),this.uuid=c0(),this.name="",this.type="BufferGeometry",this.index=null,this.attributes={},this.morphAttributes={},this.morphTargetsRelative=!1,this.groups=[],this.boundingBox=null,this.boundingSphere=null,this.drawRange={start:0,count:1/0},this.userData={}}getIndex(){return this.index}setIndex(e){return Array.isArray(e)?this.index=new(Ar(e)?Rr:Ir)(e,1):this.index=e,this}getAttribute(e){return this.attributes[e]}setAttribute(e,t){return this.attributes[e]=t,this}deleteAttribute(e){return delete this.attributes[e],this}hasAttribute(e){return this.attributes[e]!==void 0}addGroup(e,t,n=0){this.groups.push({start:e,count:t,materialIndex:n})}clearGroups(){this.groups=[]}setDrawRange(e,t){this.drawRange.start=e,this.drawRange.count=t}applyMatrix4(e){const t=this.attributes.position;t!==void 0&&(t.applyMatrix4(e),t.needsUpdate=!0);const n=this.attributes.normal;if(n!==void 0){const a=new X3().getNormalMatrix(e);n.applyNormalMatrix(a),n.needsUpdate=!0}const i=this.attributes.tangent;return i!==void 0&&(i.transformDirection(e),i.needsUpdate=!0),this.boundingBox!==null&&this.computeBoundingBox(),this.boundingSphere!==null&&this.computeBoundingSphere(),this}applyQuaternion(e){return $3.makeRotationFromQuaternion(e),this.applyMatrix4($3),this}rotateX(e){return $3.makeRotationX(e),this.applyMatrix4($3),this}rotateY(e){return $3.makeRotationY(e),this.applyMatrix4($3),this}rotateZ(e){return $3.makeRotationZ(e),this.applyMatrix4($3),this}translate(e,t,n){return $3.makeTranslation(e,t,n),this.applyMatrix4($3),this}scale(e,t,n){return $3.makeScale(e,t,n),this.applyMatrix4($3),this}lookAt(e){return q6.lookAt(e),q6.updateMatrix(),this.applyMatrix4(q6.matrix),this}center(){return this.computeBoundingBox(),this.boundingBox.getCenter(d9).negate(),this.translate(d9.x,d9.y,d9.z),this}setFromPoints(e){const t=[];for(let n=0,i=e.length;n<i;n++){const a=e[n];t.push(a.x,a.y,a.z||0)}return this.setAttribute("position",new w3(t,3)),this}computeBoundingBox(){this.boundingBox===null&&(this.boundingBox=new G5);const e=this.attributes.position,t=this.morphAttributes.position;if(e&&e.isGLBufferAttribute){console.error('THREE.BufferGeometry.computeBoundingBox(): GLBufferAttribute requires a manual bounding box. Alternatively set "mesh.frustumCulled" to "false".',this),this.boundingBox.set(new Y(-1/0,-1/0,-1/0),new Y(1/0,1/0,1/0));return}if(e!==void 0){if(this.boundingBox.setFromBufferAttribute(e),t)for(let n=0,i=t.length;n<i;n++){const a=t[n];z3.setFromBufferAttribute(a),this.morphTargetsRelative?(a3.addVectors(this.boundingBox.min,z3.min),this.boundingBox.expandByPoint(a3),a3.addVectors(this.boundingBox.max,z3.max),this.boundingBox.expandByPoint(a3)):(this.boundingBox.expandByPoint(z3.min),this.boundingBox.expandByPoint(z3.max))}}else this.boundingBox.makeEmpty();(isNaN(this.boundingBox.min.x)||isNaN(this.boundingBox.min.y)||isNaN(this.boundingBox.min.z))&&console.error('THREE.BufferGeometry.computeBoundingBox(): Computed min/max have NaN values. The "position" attribute is likely to have NaN values.',this)}computeBoundingSphere(){this.boundingSphere===null&&(this.boundingSphere=new V5);const e=this.attributes.position,t=this.morphAttributes.position;if(e&&e.isGLBufferAttribute){console.error('THREE.BufferGeometry.computeBoundingSphere(): GLBufferAttribute requires a manual bounding sphere. Alternatively set "mesh.frustumCulled" to "false".',this),this.boundingSphere.set(new Y,1/0);return}if(e){const n=this.boundingSphere.center;if(z3.setFromBufferAttribute(e),t)for(let a=0,r=t.length;a<r;a++){const s=t[a];a5.setFromBufferAttribute(s),this.morphTargetsRelative?(a3.addVectors(z3.min,a5.min),z3.expandByPoint(a3),a3.addVectors(z3.max,a5.max),z3.expandByPoint(a3)):(z3.expandByPoint(a5.min),z3.expandByPoint(a5.max))}z3.getCenter(n);let i=0;for(let a=0,r=e.count;a<r;a++)a3.fromBufferAttribute(e,a),i=Math.max(i,n.distanceToSquared(a3));if(t)for(let a=0,r=t.length;a<r;a++){const s=t[a],u=this.morphTargetsRelative;for(let c=0,f=s.count;c<f;c++)a3.fromBufferAttribute(s,c),u&&(d9.fromBufferAttribute(e,c),a3.add(d9)),i=Math.max(i,n.distanceToSquared(a3))}this.boundingSphere.radius=Math.sqrt(i),isNaN(this.boundingSphere.radius)&&console.error('THREE.BufferGeometry.computeBoundingSphere(): Computed radius is NaN. The "position" attribute is likely to have NaN values.',this)}}computeTangents(){const e=this.index,t=this.attributes;if(e===null||t.position===void 0||t.normal===void 0||t.uv===void 0){console.error("THREE.BufferGeometry: .computeTangents() failed. Missing required attributes (index, position, normal or uv)");return}const n=e.array,i=t.position.array,a=t.normal.array,r=t.uv.array,s=i.length/3;this.hasAttribute("tangent")===!1&&this.setAttribute("tangent",new F3(new Float32Array(4*s),4));const u=this.getAttribute("tangent").array,c=[],f=[];for(let U=0;U<s;U++)c[U]=new Y,f[U]=new Y;const p=new Y,h=new Y,m=new Y,y=new G1,_=new G1,v=new G1,b=new Y,x=new Y;function A(U,V,q){p.fromArray(i,U*3),h.fromArray(i,V*3),m.fromArray(i,q*3),y.fromArray(r,U*2),_.fromArray(r,V*2),v.fromArray(r,q*2),h.sub(p),m.sub(p),_.sub(y),v.sub(y);const $=1/(_.x*v.y-v.x*_.y);isFinite($)&&(b.copy(h).multiplyScalar(v.y).addScaledVector(m,-_.y).multiplyScalar($),x.copy(m).multiplyScalar(_.x).addScaledVector(h,-v.x).multiplyScalar($),c[U].add(b),c[V].add(b),c[q].add(b),f[U].add(x),f[V].add(x),f[q].add(x))}let S=this.groups;S.length===0&&(S=[{start:0,count:n.length}]);for(let U=0,V=S.length;U<V;++U){const q=S[U],$=q.start,z=q.count;for(let F=$,G=$+z;F<G;F+=3)A(n[F+0],n[F+1],n[F+2])}const w=new Y,M=new Y,D=new Y,T=new Y;function E(U){D.fromArray(a,U*3),T.copy(D);const V=c[U];w.copy(V),w.sub(D.multiplyScalar(D.dot(V))).normalize(),M.crossVectors(T,V);const $=M.dot(f[U])<0?-1:1;u[U*4]=w.x,u[U*4+1]=w.y,u[U*4+2]=w.z,u[U*4+3]=$}for(let U=0,V=S.length;U<V;++U){const q=S[U],$=q.start,z=q.count;for(let F=$,G=$+z;F<G;F+=3)E(n[F+0]),E(n[F+1]),E(n[F+2])}}computeVertexNormals(){const e=this.index,t=this.getAttribute("position");if(t!==void 0){let n=this.getAttribute("normal");if(n===void 0)n=new F3(new Float32Array(t.count*3),3),this.setAttribute("normal",n);else for(let h=0,m=n.count;h<m;h++)n.setXYZ(h,0,0,0);const i=new Y,a=new Y,r=new Y,s=new Y,u=new Y,c=new Y,f=new Y,p=new Y;if(e)for(let h=0,m=e.count;h<m;h+=3){const y=e.getX(h+0),_=e.getX(h+1),v=e.getX(h+2);i.fromBufferAttribute(t,y),a.fromBufferAttribute(t,_),r.fromBufferAttribute(t,v),f.subVectors(r,a),p.subVectors(i,a),f.cross(p),s.fromBufferAttribute(n,y),u.fromBufferAttribute(n,_),c.fromBufferAttribute(n,v),s.add(f),u.add(f),c.add(f),n.setXYZ(y,s.x,s.y,s.z),n.setXYZ(_,u.x,u.y,u.z),n.setXYZ(v,c.x,c.y,c.z)}else for(let h=0,m=t.count;h<m;h+=3)i.fromBufferAttribute(t,h+0),a.fromBufferAttribute(t,h+1),r.fromBufferAttribute(t,h+2),f.subVectors(r,a),p.subVectors(i,a),f.cross(p),n.setXYZ(h+0,f.x,f.y,f.z),n.setXYZ(h+1,f.x,f.y,f.z),n.setXYZ(h+2,f.x,f.y,f.z);this.normalizeNormals(),n.needsUpdate=!0}}merge(){return console.error("THREE.BufferGeometry.merge() has been removed. Use THREE.BufferGeometryUtils.mergeBufferGeometries() instead."),this}normalizeNormals(){const e=this.attributes.normal;for(let t=0,n=e.count;t<n;t++)a3.fromBufferAttribute(e,t),a3.normalize(),e.setXYZ(t,a3.x,a3.y,a3.z)}toNonIndexed(){function e(s,u){const c=s.array,f=s.itemSize,p=s.normalized,h=new c.constructor(u.length*f);let m=0,y=0;for(let _=0,v=u.length;_<v;_++){s.isInterleavedBufferAttribute?m=u[_]*s.data.stride+s.offset:m=u[_]*f;for(let b=0;b<f;b++)h[y++]=c[m++]}return new F3(h,f,p)}if(this.index===null)return console.warn("THREE.BufferGeometry.toNonIndexed(): BufferGeometry is already non-indexed."),this;const t=new O3,n=this.index.array,i=this.attributes;for(const s in i){const u=i[s],c=e(u,n);t.setAttribute(s,c)}const a=this.morphAttributes;for(const s in a){const u=[],c=a[s];for(let f=0,p=c.length;f<p;f++){const h=c[f],m=e(h,n);u.push(m)}t.morphAttributes[s]=u}t.morphTargetsRelative=this.morphTargetsRelative;const r=this.groups;for(let s=0,u=r.length;s<u;s++){const c=r[s];t.addGroup(c.start,c.count,c.materialIndex)}return t}toJSON(){const e={metadata:{version:4.5,type:"BufferGeometry",generator:"BufferGeometry.toJSON"}};if(e.uuid=this.uuid,e.type=this.type,this.name!==""&&(e.name=this.name),Object.keys(this.userData).length>0&&(e.userData=this.userData),this.parameters!==void 0){const u=this.parameters;for(const c in u)u[c]!==void 0&&(e[c]=u[c]);return e}e.data={attributes:{}};const t=this.index;t!==null&&(e.data.index={type:t.array.constructor.name,array:Array.prototype.slice.call(t.array)});const n=this.attributes;for(const u in n){const c=n[u];e.data.attributes[u]=c.toJSON(e.data)}const i={};let a=!1;for(const u in this.morphAttributes){const c=this.morphAttributes[u],f=[];for(let p=0,h=c.length;p<h;p++){const m=c[p];f.push(m.toJSON(e.data))}f.length>0&&(i[u]=f,a=!0)}a&&(e.data.morphAttributes=i,e.data.morphTargetsRelative=this.morphTargetsRelative);const r=this.groups;r.length>0&&(e.data.groups=JSON.parse(JSON.stringify(r)));const s=this.boundingSphere;return s!==null&&(e.data.boundingSphere={center:s.center.toArray(),radius:s.radius}),e}clone(){return new this.constructor().copy(this)}copy(e){this.index=null,this.attributes={},this.morphAttributes={},this.groups=[],this.boundingBox=null,this.boundingSphere=null;const t={};this.name=e.name;const n=e.index;n!==null&&this.setIndex(n.clone(t));const i=e.attributes;for(const c in i){const f=i[c];this.setAttribute(c,f.clone(t))}const a=e.morphAttributes;for(const c in a){const f=[],p=a[c];for(let h=0,m=p.length;h<m;h++)f.push(p[h].clone(t));this.morphAttributes[c]=f}this.morphTargetsRelative=e.morphTargetsRelative;const r=e.groups;for(let c=0,f=r.length;c<f;c++){const p=r[c];this.addGroup(p.start,p.count,p.materialIndex)}const s=e.boundingBox;s!==null&&(this.boundingBox=s.clone());const u=e.boundingSphere;return u!==null&&(this.boundingSphere=u.clone()),this.drawRange.start=e.drawRange.start,this.drawRange.count=e.drawRange.count,this.userData=e.userData,e.parameters!==void 0&&(this.parameters=Object.assign({},e.parameters)),this}dispose(){this.dispatchEvent({type:"dispose"})}}const Qi=new W2,f9=new fn,X6=new V5,K4=new Y,e0=new Y,t0=new Y,Q6=new Y,K6=new Y,e7=new Y,f8=new Y,p8=new Y,h8=new Y,m8=new G1,g8=new G1,v8=new G1,t7=new Y,_8=new Y;class u3 extends v3{constructor(e=new O3,t=new k4){super(),this.isMesh=!0,this.type="Mesh",this.geometry=e,this.material=t,this.updateMorphTargets()}copy(e,t){return super.copy(e,t),e.morphTargetInfluences!==void 0&&(this.morphTargetInfluences=e.morphTargetInfluences.slice()),e.morphTargetDictionary!==void 0&&(this.morphTargetDictionary=Object.assign({},e.morphTargetDictionary)),this.material=e.material,this.geometry=e.geometry,this}updateMorphTargets(){const t=this.geometry.morphAttributes,n=Object.keys(t);if(n.length>0){const i=t[n[0]];if(i!==void 0){this.morphTargetInfluences=[],this.morphTargetDictionary={};for(let a=0,r=i.length;a<r;a++){const s=i[a].name||String(a);this.morphTargetInfluences.push(0),this.morphTargetDictionary[s]=a}}}}raycast(e,t){const n=this.geometry,i=this.material,a=this.matrixWorld;if(i===void 0||(n.boundingSphere===null&&n.computeBoundingSphere(),X6.copy(n.boundingSphere),X6.applyMatrix4(a),e.ray.intersectsSphere(X6)===!1)||(Qi.copy(a).invert(),f9.copy(e.ray).applyMatrix4(Qi),n.boundingBox!==null&&f9.intersectsBox(n.boundingBox)===!1))return;let r;const s=n.index,u=n.attributes.position,c=n.morphAttributes.position,f=n.morphTargetsRelative,p=n.attributes.uv,h=n.attributes.uv2,m=n.groups,y=n.drawRange;if(s!==null)if(Array.isArray(i))for(let _=0,v=m.length;_<v;_++){const b=m[_],x=i[b.materialIndex],A=Math.max(b.start,y.start),S=Math.min(s.count,Math.min(b.start+b.count,y.start+y.count));for(let w=A,M=S;w<M;w+=3){const D=s.getX(w),T=s.getX(w+1),E=s.getX(w+2);r=y8(this,x,e,f9,u,c,f,p,h,D,T,E),r&&(r.faceIndex=Math.floor(w/3),r.face.materialIndex=b.materialIndex,t.push(r))}}else{const _=Math.max(0,y.start),v=Math.min(s.count,y.start+y.count);for(let b=_,x=v;b<x;b+=3){const A=s.getX(b),S=s.getX(b+1),w=s.getX(b+2);r=y8(this,i,e,f9,u,c,f,p,h,A,S,w),r&&(r.faceIndex=Math.floor(b/3),t.push(r))}}else if(u!==void 0)if(Array.isArray(i))for(let _=0,v=m.length;_<v;_++){const b=m[_],x=i[b.materialIndex],A=Math.max(b.start,y.start),S=Math.min(u.count,Math.min(b.start+b.count,y.start+y.count));for(let w=A,M=S;w<M;w+=3){const D=w,T=w+1,E=w+2;r=y8(this,x,e,f9,u,c,f,p,h,D,T,E),r&&(r.faceIndex=Math.floor(w/3),r.face.materialIndex=b.materialIndex,t.push(r))}}else{const _=Math.max(0,y.start),v=Math.min(u.count,y.start+y.count);for(let b=_,x=v;b<x;b+=3){const A=b,S=b+1,w=b+2;r=y8(this,i,e,f9,u,c,f,p,h,A,S,w),r&&(r.faceIndex=Math.floor(b/3),t.push(r))}}}}function rM(o,e,t,n,i,a,r,s){let u;if(e.side===n4?u=n.intersectTriangle(r,a,i,!0,s):u=n.intersectTriangle(i,a,r,e.side!==_4,s),u===null)return null;_8.copy(s),_8.applyMatrix4(o.matrixWorld);const c=t.ray.origin.distanceTo(_8);return c<t.near||c>t.far?null:{distance:c,point:_8.clone(),object:o}}function y8(o,e,t,n,i,a,r,s,u,c,f,p){K4.fromBufferAttribute(i,c),e0.fromBufferAttribute(i,f),t0.fromBufferAttribute(i,p);const h=o.morphTargetInfluences;if(a&&h){f8.set(0,0,0),p8.set(0,0,0),h8.set(0,0,0);for(let y=0,_=a.length;y<_;y++){const v=h[y],b=a[y];v!==0&&(Q6.fromBufferAttribute(b,c),K6.fromBufferAttribute(b,f),e7.fromBufferAttribute(b,p),r?(f8.addScaledVector(Q6,v),p8.addScaledVector(K6,v),h8.addScaledVector(e7,v)):(f8.addScaledVector(Q6.sub(K4),v),p8.addScaledVector(K6.sub(e0),v),h8.addScaledVector(e7.sub(t0),v)))}K4.add(f8),e0.add(p8),t0.add(h8)}o.isSkinnedMesh&&(o.boneTransform(c,K4),o.boneTransform(f,e0),o.boneTransform(p,t0));const m=rM(o,e,t,n,K4,e0,t0,t7);if(m){s&&(m8.fromBufferAttribute(s,c),g8.fromBufferAttribute(s,f),v8.fromBufferAttribute(s,p),m.uv=g4.getUV(t7,K4,e0,t0,m8,g8,v8,new G1)),u&&(m8.fromBufferAttribute(u,c),g8.fromBufferAttribute(u,f),v8.fromBufferAttribute(u,p),m.uv2=g4.getUV(t7,K4,e0,t0,m8,g8,v8,new G1));const y={a:c,b:f,c:p,normal:new Y,materialIndex:0};g4.getNormal(K4,e0,t0,y.normal),m.face=y}return m}class W5 extends O3{constructor(e=1,t=1,n=1,i=1,a=1,r=1){super(),this.type="BoxGeometry",this.parameters={width:e,height:t,depth:n,widthSegments:i,heightSegments:a,depthSegments:r};const s=this;i=Math.floor(i),a=Math.floor(a),r=Math.floor(r);const u=[],c=[],f=[],p=[];let h=0,m=0;y("z","y","x",-1,-1,n,t,e,r,a,0),y("z","y","x",1,-1,n,t,-e,r,a,1),y("x","z","y",1,1,e,n,t,i,r,2),y("x","z","y",1,-1,e,n,-t,i,r,3),y("x","y","z",1,-1,e,t,n,i,a,4),y("x","y","z",-1,-1,e,t,-n,i,a,5),this.setIndex(u),this.setAttribute("position",new w3(c,3)),this.setAttribute("normal",new w3(f,3)),this.setAttribute("uv",new w3(p,2));function y(_,v,b,x,A,S,w,M,D,T,E){const U=S/D,V=w/T,q=S/2,$=w/2,z=M/2,F=D+1,G=T+1;let J=0,O=0;const N=new Y;for(let B=0;B<G;B++){const t1=B*V-$;for(let n1=0;n1<F;n1++){const u1=n1*U-q;N[_]=u1*x,N[v]=t1*A,N[b]=z,c.push(N.x,N.y,N.z),N[_]=0,N[v]=0,N[b]=M>0?1:-1,f.push(N.x,N.y,N.z),p.push(n1/D),p.push(1-B/T),J+=1}}for(let B=0;B<T;B++)for(let t1=0;t1<D;t1++){const n1=h+t1+F*B,u1=h+t1+F*(B+1),a1=h+(t1+1)+F*(B+1),Q=h+(t1+1)+F*B;u.push(n1,u1,Q),u.push(u1,a1,Q),O+=6}s.addGroup(m,O,E),m+=O,h+=J}}static fromJSON(e){return new W5(e.width,e.height,e.depth,e.widthSegments,e.heightSegments,e.depthSegments)}}function W9(o){const e={};for(const t in o){e[t]={};for(const n in o[t]){const i=o[t][n];i&&(i.isColor||i.isMatrix3||i.isMatrix4||i.isVector2||i.isVector3||i.isVector4||i.isTexture||i.isQuaternion)?e[t][n]=i.clone():Array.isArray(i)?e[t][n]=i.slice():e[t][n]=i}}return e}function h3(o){const e={};for(let t=0;t<o.length;t++){const n=W9(o[t]);for(const i in n)e[i]=n[i]}return e}function sM(o){const e=[];for(let t=0;t<o.length;t++)e.push(o[t].clone());return e}const lM={clone:W9,merge:h3};var uM=`void main() {
	gl_Position = projectionMatrix * modelViewMatrix * vec4( position, 1.0 );
}`,cM=`void main() {
	gl_FragColor = vec4( 1.0, 0.0, 0.0, 1.0 );
}`;class G4 extends q0{constructor(e){super(),this.isShaderMaterial=!0,this.type="ShaderMaterial",this.defines={},this.uniforms={},this.uniformsGroups=[],this.vertexShader=uM,this.fragmentShader=cM,this.linewidth=1,this.wireframe=!1,this.wireframeLinewidth=1,this.fog=!1,this.lights=!1,this.clipping=!1,this.extensions={derivatives:!1,fragDepth:!1,drawBuffers:!1,shaderTextureLOD:!1},this.defaultAttributeValues={color:[1,1,1],uv:[0,0],uv2:[0,0]},this.index0AttributeName=void 0,this.uniformsNeedUpdate=!1,this.glslVersion=null,e!==void 0&&this.setValues(e)}copy(e){return super.copy(e),this.fragmentShader=e.fragmentShader,this.vertexShader=e.vertexShader,this.uniforms=W9(e.uniforms),this.uniformsGroups=sM(e.uniformsGroups),this.defines=Object.assign({},e.defines),this.wireframe=e.wireframe,this.wireframeLinewidth=e.wireframeLinewidth,this.fog=e.fog,this.lights=e.lights,this.clipping=e.clipping,this.extensions=Object.assign({},e.extensions),this.glslVersion=e.glslVersion,this}toJSON(e){const t=super.toJSON(e);t.glslVersion=this.glslVersion,t.uniforms={};for(const i in this.uniforms){const r=this.uniforms[i].value;r&&r.isTexture?t.uniforms[i]={type:"t",value:r.toJSON(e).uuid}:r&&r.isColor?t.uniforms[i]={type:"c",value:r.getHex()}:r&&r.isVector2?t.uniforms[i]={type:"v2",value:r.toArray()}:r&&r.isVector3?t.uniforms[i]={type:"v3",value:r.toArray()}:r&&r.isVector4?t.uniforms[i]={type:"v4",value:r.toArray()}:r&&r.isMatrix3?t.uniforms[i]={type:"m3",value:r.toArray()}:r&&r.isMatrix4?t.uniforms[i]={type:"m4",value:r.toArray()}:t.uniforms[i]={value:r}}Object.keys(this.defines).length>0&&(t.defines=this.defines),t.vertexShader=this.vertexShader,t.fragmentShader=this.fragmentShader;const n={};for(const i in this.extensions)this.extensions[i]===!0&&(n[i]=!0);return Object.keys(n).length>0&&(t.extensions=n),t}}class Or extends v3{constructor(){super(),this.isCamera=!0,this.type="Camera",this.matrixWorldInverse=new W2,this.projectionMatrix=new W2,this.projectionMatrixInverse=new W2}copy(e,t){return super.copy(e,t),this.matrixWorldInverse.copy(e.matrixWorldInverse),this.projectionMatrix.copy(e.projectionMatrix),this.projectionMatrixInverse.copy(e.projectionMatrixInverse),this}getWorldDirection(e){this.updateWorldMatrix(!0,!1);const t=this.matrixWorld.elements;return e.set(-t[8],-t[9],-t[10]).normalize()}updateMatrixWorld(e){super.updateMatrixWorld(e),this.matrixWorldInverse.copy(this.matrixWorld).invert()}updateWorldMatrix(e,t){super.updateWorldMatrix(e,t),this.matrixWorldInverse.copy(this.matrixWorld).invert()}clone(){return new this.constructor().copy(this)}}class q3 extends Or{constructor(e=50,t=1,n=.1,i=2e3){super(),this.isPerspectiveCamera=!0,this.type="PerspectiveCamera",this.fov=e,this.zoom=1,this.near=n,this.far=i,this.focus=10,this.aspect=t,this.view=null,this.filmGauge=35,this.filmOffset=0,this.updateProjectionMatrix()}copy(e,t){return super.copy(e,t),this.fov=e.fov,this.zoom=e.zoom,this.near=e.near,this.far=e.far,this.focus=e.focus,this.aspect=e.aspect,this.view=e.view===null?null:Object.assign({},e.view),this.filmGauge=e.filmGauge,this.filmOffset=e.filmOffset,this}setFocalLength(e){const t=.5*this.getFilmHeight()/e;this.fov=Bi*2*Math.atan(t),this.updateProjectionMatrix()}getFocalLength(){const e=Math.tan(L6*.5*this.fov);return .5*this.getFilmHeight()/e}getEffectiveFOV(){return Bi*2*Math.atan(Math.tan(L6*.5*this.fov)/this.zoom)}getFilmWidth(){return this.filmGauge*Math.min(this.aspect,1)}getFilmHeight(){return this.filmGauge/Math.max(this.aspect,1)}setViewOffset(e,t,n,i,a,r){this.aspect=e/t,this.view===null&&(this.view={enabled:!0,fullWidth:1,fullHeight:1,offsetX:0,offsetY:0,width:1,height:1}),this.view.enabled=!0,this.view.fullWidth=e,this.view.fullHeight=t,this.view.offsetX=n,this.view.offsetY=i,this.view.width=a,this.view.height=r,this.updateProjectionMatrix()}clearViewOffset(){this.view!==null&&(this.view.enabled=!1),this.updateProjectionMatrix()}updateProjectionMatrix(){const e=this.near;let t=e*Math.tan(L6*.5*this.fov)/this.zoom,n=2*t,i=this.aspect*n,a=-.5*i;const r=this.view;if(this.view!==null&&this.view.enabled){const u=r.fullWidth,c=r.fullHeight;a+=r.offsetX*i/u,t-=r.offsetY*n/c,i*=r.width/u,n*=r.height/c}const s=this.filmOffset;s!==0&&(a+=e*s/this.getFilmWidth()),this.projectionMatrix.makePerspective(a,a+i,t,t-n,e,this.far),this.projectionMatrixInverse.copy(this.projectionMatrix).invert()}toJSON(e){const t=super.toJSON(e);return t.object.fov=this.fov,t.object.zoom=this.zoom,t.object.near=this.near,t.object.far=this.far,t.object.focus=this.focus,t.object.aspect=this.aspect,this.view!==null&&(t.object.view=Object.assign({},this.view)),t.object.filmGauge=this.filmGauge,t.object.filmOffset=this.filmOffset,t}}const p9=90,h9=1;class dM extends v3{constructor(e,t,n){super(),this.type="CubeCamera",this.renderTarget=n;const i=new q3(p9,h9,e,t);i.layers=this.layers,i.up.set(0,-1,0),i.lookAt(new Y(1,0,0)),this.add(i);const a=new q3(p9,h9,e,t);a.layers=this.layers,a.up.set(0,-1,0),a.lookAt(new Y(-1,0,0)),this.add(a);const r=new q3(p9,h9,e,t);r.layers=this.layers,r.up.set(0,0,1),r.lookAt(new Y(0,1,0)),this.add(r);const s=new q3(p9,h9,e,t);s.layers=this.layers,s.up.set(0,0,-1),s.lookAt(new Y(0,-1,0)),this.add(s);const u=new q3(p9,h9,e,t);u.layers=this.layers,u.up.set(0,-1,0),u.lookAt(new Y(0,0,1)),this.add(u);const c=new q3(p9,h9,e,t);c.layers=this.layers,c.up.set(0,-1,0),c.lookAt(new Y(0,0,-1)),this.add(c)}update(e,t){this.parent===null&&this.updateMatrixWorld();const n=this.renderTarget,[i,a,r,s,u,c]=this.children,f=e.getRenderTarget(),p=e.toneMapping,h=e.xr.enabled;e.toneMapping=F4,e.xr.enabled=!1;const m=n.texture.generateMipmaps;n.texture.generateMipmaps=!1,e.setRenderTarget(n,0),e.render(t,i),e.setRenderTarget(n,1),e.render(t,a),e.setRenderTarget(n,2),e.render(t,r),e.setRenderTarget(n,3),e.render(t,s),e.setRenderTarget(n,4),e.render(t,u),n.texture.generateMipmaps=m,e.setRenderTarget(n,5),e.render(t,c),e.setRenderTarget(f),e.toneMapping=p,e.xr.enabled=h,n.texture.needsPMREMUpdate=!0}}class Pr extends i4{constructor(e,t,n,i,a,r,s,u,c,f){e=e!==void 0?e:[],t=t!==void 0?t:G9,super(e,t,n,i,a,r,s,u,c,f),this.isCubeTexture=!0,this.flipY=!1}get images(){return this.image}set images(e){this.image=e}}class fM extends H0{constructor(e,t={}){super(e,e,t),this.isWebGLCubeRenderTarget=!0;const n={width:e,height:e,depth:1},i=[n,n,n,n,n,n];this.texture=new Pr(i,t.mapping,t.wrapS,t.wrapT,t.magFilter,t.minFilter,t.format,t.type,t.anisotropy,t.encoding),this.texture.isRenderTargetTexture=!0,this.texture.generateMipmaps=t.generateMipmaps!==void 0?t.generateMipmaps:!1,this.texture.minFilter=t.minFilter!==void 0?t.minFilter:Z3}fromEquirectangularTexture(e,t){this.texture.type=t.type,this.texture.encoding=t.encoding,this.texture.generateMipmaps=t.generateMipmaps,this.texture.minFilter=t.minFilter,this.texture.magFilter=t.magFilter;const n={uniforms:{tEquirect:{value:null}},vertexShader:`

				varying vec3 vWorldDirection;

				vec3 transformDirection( in vec3 dir, in mat4 matrix ) {

					return normalize( ( matrix * vec4( dir, 0.0 ) ).xyz );

				}

				void main() {

					vWorldDirection = transformDirection( position, modelMatrix );

					#include <begin_vertex>
					#include <project_vertex>

				}
			`,fragmentShader:`

				uniform sampler2D tEquirect;

				varying vec3 vWorldDirection;

				#include <common>

				void main() {

					vec3 direction = normalize( vWorldDirection );

					vec2 sampleUV = equirectUv( direction );

					gl_FragColor = texture2D( tEquirect, sampleUV );

				}
			`},i=new W5(5,5,5),a=new G4({name:"CubemapFromEquirect",uniforms:W9(n.uniforms),vertexShader:n.vertexShader,fragmentShader:n.fragmentShader,side:n4,blending:u0});a.uniforms.tEquirect.value=t;const r=new u3(i,a),s=t.minFilter;return t.minFilter===u6&&(t.minFilter=Z3),new dM(1,10,this).update(e,r),t.minFilter=s,r.geometry.dispose(),r.material.dispose(),this}clear(e,t,n,i){const a=e.getRenderTarget();for(let r=0;r<6;r++)e.setRenderTarget(this,r),e.clear(t,n,i);e.setRenderTarget(a)}}const n7=new Y,pM=new Y,hM=new X3;class T0{constructor(e=new Y(1,0,0),t=0){this.isPlane=!0,this.normal=e,this.constant=t}set(e,t){return this.normal.copy(e),this.constant=t,this}setComponents(e,t,n,i){return this.normal.set(e,t,n),this.constant=i,this}setFromNormalAndCoplanarPoint(e,t){return this.normal.copy(e),this.constant=-t.dot(this.normal),this}setFromCoplanarPoints(e,t,n){const i=n7.subVectors(n,t).cross(pM.subVectors(e,t)).normalize();return this.setFromNormalAndCoplanarPoint(i,e),this}copy(e){return this.normal.copy(e.normal),this.constant=e.constant,this}normalize(){const e=1/this.normal.length();return this.normal.multiplyScalar(e),this.constant*=e,this}negate(){return this.constant*=-1,this.normal.negate(),this}distanceToPoint(e){return this.normal.dot(e)+this.constant}distanceToSphere(e){return this.distanceToPoint(e.center)-e.radius}projectPoint(e,t){return t.copy(this.normal).multiplyScalar(-this.distanceToPoint(e)).add(e)}intersectLine(e,t){const n=e.delta(n7),i=this.normal.dot(n);if(i===0)return this.distanceToPoint(e.start)===0?t.copy(e.start):null;const a=-(e.start.dot(this.normal)+this.constant)/i;return a<0||a>1?null:t.copy(n).multiplyScalar(a).add(e.start)}intersectsLine(e){const t=this.distanceToPoint(e.start),n=this.distanceToPoint(e.end);return t<0&&n>0||n<0&&t>0}intersectsBox(e){return e.intersectsPlane(this)}intersectsSphere(e){return e.intersectsPlane(this)}coplanarPoint(e){return e.copy(this.normal).multiplyScalar(-this.constant)}applyMatrix4(e,t){const n=t||hM.getNormalMatrix(e),i=this.coplanarPoint(n7).applyMatrix4(e),a=this.normal.applyMatrix3(n).normalize();return this.constant=-i.dot(a),this}translate(e){return this.constant-=e.dot(this.normal),this}equals(e){return e.normal.equals(this.normal)&&e.constant===this.constant}clone(){return new this.constructor().copy(this)}}const m9=new V5,b8=new Y;class Lr{constructor(e=new T0,t=new T0,n=new T0,i=new T0,a=new T0,r=new T0){this.planes=[e,t,n,i,a,r]}set(e,t,n,i,a,r){const s=this.planes;return s[0].copy(e),s[1].copy(t),s[2].copy(n),s[3].copy(i),s[4].copy(a),s[5].copy(r),this}copy(e){const t=this.planes;for(let n=0;n<6;n++)t[n].copy(e.planes[n]);return this}setFromProjectionMatrix(e){const t=this.planes,n=e.elements,i=n[0],a=n[1],r=n[2],s=n[3],u=n[4],c=n[5],f=n[6],p=n[7],h=n[8],m=n[9],y=n[10],_=n[11],v=n[12],b=n[13],x=n[14],A=n[15];return t[0].setComponents(s-i,p-u,_-h,A-v).normalize(),t[1].setComponents(s+i,p+u,_+h,A+v).normalize(),t[2].setComponents(s+a,p+c,_+m,A+b).normalize(),t[3].setComponents(s-a,p-c,_-m,A-b).normalize(),t[4].setComponents(s-r,p-f,_-y,A-x).normalize(),t[5].setComponents(s+r,p+f,_+y,A+x).normalize(),this}intersectsObject(e){const t=e.geometry;return t.boundingSphere===null&&t.computeBoundingSphere(),m9.copy(t.boundingSphere).applyMatrix4(e.matrixWorld),this.intersectsSphere(m9)}intersectsSprite(e){return m9.center.set(0,0,0),m9.radius=.7071067811865476,m9.applyMatrix4(e.matrixWorld),this.intersectsSphere(m9)}intersectsSphere(e){const t=this.planes,n=e.center,i=-e.radius;for(let a=0;a<6;a++)if(t[a].distanceToPoint(n)<i)return!1;return!0}intersectsBox(e){const t=this.planes;for(let n=0;n<6;n++){const i=t[n];if(b8.x=i.normal.x>0?e.max.x:e.min.x,b8.y=i.normal.y>0?e.max.y:e.min.y,b8.z=i.normal.z>0?e.max.z:e.min.z,i.distanceToPoint(b8)<0)return!1}return!0}containsPoint(e){const t=this.planes;for(let n=0;n<6;n++)if(t[n].distanceToPoint(e)<0)return!1;return!0}clone(){return new this.constructor().copy(this)}}function zr(){let o=null,e=!1,t=null,n=null;function i(a,r){t(a,r),n=o.requestAnimationFrame(i)}return{start:function(){e!==!0&&t!==null&&(n=o.requestAnimationFrame(i),e=!0)},stop:function(){o.cancelAnimationFrame(n),e=!1},setAnimationLoop:function(a){t=a},setContext:function(a){o=a}}}function mM(o,e){const t=e.isWebGL2,n=new WeakMap;function i(c,f){const p=c.array,h=c.usage,m=o.createBuffer();o.bindBuffer(f,m),o.bufferData(f,p,h),c.onUploadCallback();let y;if(p instanceof Float32Array)y=5126;else if(p instanceof Uint16Array)if(c.isFloat16BufferAttribute)if(t)y=5131;else throw new Error("THREE.WebGLAttributes: Usage of Float16BufferAttribute requires WebGL2.");else y=5123;else if(p instanceof Int16Array)y=5122;else if(p instanceof Uint32Array)y=5125;else if(p instanceof Int32Array)y=5124;else if(p instanceof Int8Array)y=5120;else if(p instanceof Uint8Array)y=5121;else if(p instanceof Uint8ClampedArray)y=5121;else throw new Error("THREE.WebGLAttributes: Unsupported buffer data format: "+p);return{buffer:m,type:y,bytesPerElement:p.BYTES_PER_ELEMENT,version:c.version}}function a(c,f,p){const h=f.array,m=f.updateRange;o.bindBuffer(p,c),m.count===-1?o.bufferSubData(p,0,h):(t?o.bufferSubData(p,m.offset*h.BYTES_PER_ELEMENT,h,m.offset,m.count):o.bufferSubData(p,m.offset*h.BYTES_PER_ELEMENT,h.subarray(m.offset,m.offset+m.count)),m.count=-1)}function r(c){return c.isInterleavedBufferAttribute&&(c=c.data),n.get(c)}function s(c){c.isInterleavedBufferAttribute&&(c=c.data);const f=n.get(c);f&&(o.deleteBuffer(f.buffer),n.delete(c))}function u(c,f){if(c.isGLBufferAttribute){const h=n.get(c);(!h||h.version<c.version)&&n.set(c,{buffer:c.buffer,type:c.type,bytesPerElement:c.elementSize,version:c.version});return}c.isInterleavedBufferAttribute&&(c=c.data);const p=n.get(c);p===void 0?n.set(c,i(c,f)):p.version<c.version&&(a(p.buffer,c,f),p.version=c.version)}return{get:r,remove:s,update:u}}class X9 extends O3{constructor(e=1,t=1,n=1,i=1){super(),this.type="PlaneGeometry",this.parameters={width:e,height:t,widthSegments:n,heightSegments:i};const a=e/2,r=t/2,s=Math.floor(n),u=Math.floor(i),c=s+1,f=u+1,p=e/s,h=t/u,m=[],y=[],_=[],v=[];for(let b=0;b<f;b++){const x=b*h-r;for(let A=0;A<c;A++){const S=A*p-a;y.push(S,-x,0),_.push(0,0,1),v.push(A/s),v.push(1-b/u)}}for(let b=0;b<u;b++)for(let x=0;x<s;x++){const A=x+c*b,S=x+c*(b+1),w=x+1+c*(b+1),M=x+1+c*b;m.push(A,S,M),m.push(S,w,M)}this.setIndex(m),this.setAttribute("position",new w3(y,3)),this.setAttribute("normal",new w3(_,3)),this.setAttribute("uv",new w3(v,2))}static fromJSON(e){return new X9(e.width,e.height,e.widthSegments,e.heightSegments)}}var gM=`#ifdef USE_ALPHAMAP
	diffuseColor.a *= texture2D( alphaMap, vUv ).g;
#endif`,vM=`#ifdef USE_ALPHAMAP
	uniform sampler2D alphaMap;
#endif`,_M=`#ifdef USE_ALPHATEST
	if ( diffuseColor.a < alphaTest ) discard;
#endif`,yM=`#ifdef USE_ALPHATEST
	uniform float alphaTest;
#endif`,bM=`#ifdef USE_AOMAP
	float ambientOcclusion = ( texture2D( aoMap, vUv2 ).r - 1.0 ) * aoMapIntensity + 1.0;
	reflectedLight.indirectDiffuse *= ambientOcclusion;
	#if defined( USE_ENVMAP ) && defined( STANDARD )
		float dotNV = saturate( dot( geometry.normal, geometry.viewDir ) );
		reflectedLight.indirectSpecular *= computeSpecularOcclusion( dotNV, ambientOcclusion, material.roughness );
	#endif
#endif`,xM=`#ifdef USE_AOMAP
	uniform sampler2D aoMap;
	uniform float aoMapIntensity;
#endif`,SM="vec3 transformed = vec3( position );",MM=`vec3 objectNormal = vec3( normal );
#ifdef USE_TANGENT
	vec3 objectTangent = vec3( tangent.xyz );
#endif`,AM=`vec3 BRDF_Lambert( const in vec3 diffuseColor ) {
	return RECIPROCAL_PI * diffuseColor;
}
vec3 F_Schlick( const in vec3 f0, const in float f90, const in float dotVH ) {
	float fresnel = exp2( ( - 5.55473 * dotVH - 6.98316 ) * dotVH );
	return f0 * ( 1.0 - fresnel ) + ( f90 * fresnel );
}
float F_Schlick( const in float f0, const in float f90, const in float dotVH ) {
	float fresnel = exp2( ( - 5.55473 * dotVH - 6.98316 ) * dotVH );
	return f0 * ( 1.0 - fresnel ) + ( f90 * fresnel );
}
vec3 Schlick_to_F0( const in vec3 f, const in float f90, const in float dotVH ) {
    float x = clamp( 1.0 - dotVH, 0.0, 1.0 );
    float x2 = x * x;
    float x5 = clamp( x * x2 * x2, 0.0, 0.9999 );
    return ( f - vec3( f90 ) * x5 ) / ( 1.0 - x5 );
}
float V_GGX_SmithCorrelated( const in float alpha, const in float dotNL, const in float dotNV ) {
	float a2 = pow2( alpha );
	float gv = dotNL * sqrt( a2 + ( 1.0 - a2 ) * pow2( dotNV ) );
	float gl = dotNV * sqrt( a2 + ( 1.0 - a2 ) * pow2( dotNL ) );
	return 0.5 / max( gv + gl, EPSILON );
}
float D_GGX( const in float alpha, const in float dotNH ) {
	float a2 = pow2( alpha );
	float denom = pow2( dotNH ) * ( a2 - 1.0 ) + 1.0;
	return RECIPROCAL_PI * a2 / pow2( denom );
}
vec3 BRDF_GGX( const in vec3 lightDir, const in vec3 viewDir, const in vec3 normal, const in vec3 f0, const in float f90, const in float roughness ) {
	float alpha = pow2( roughness );
	vec3 halfDir = normalize( lightDir + viewDir );
	float dotNL = saturate( dot( normal, lightDir ) );
	float dotNV = saturate( dot( normal, viewDir ) );
	float dotNH = saturate( dot( normal, halfDir ) );
	float dotVH = saturate( dot( viewDir, halfDir ) );
	vec3 F = F_Schlick( f0, f90, dotVH );
	float V = V_GGX_SmithCorrelated( alpha, dotNL, dotNV );
	float D = D_GGX( alpha, dotNH );
	return F * ( V * D );
}
#ifdef USE_IRIDESCENCE
	vec3 BRDF_GGX_Iridescence( const in vec3 lightDir, const in vec3 viewDir, const in vec3 normal, const in vec3 f0, const in float f90, const in float iridescence, const in vec3 iridescenceFresnel, const in float roughness ) {
		float alpha = pow2( roughness );
		vec3 halfDir = normalize( lightDir + viewDir );
		float dotNL = saturate( dot( normal, lightDir ) );
		float dotNV = saturate( dot( normal, viewDir ) );
		float dotNH = saturate( dot( normal, halfDir ) );
		float dotVH = saturate( dot( viewDir, halfDir ) );
		vec3 F = mix( F_Schlick( f0, f90, dotVH ), iridescenceFresnel, iridescence );
		float V = V_GGX_SmithCorrelated( alpha, dotNL, dotNV );
		float D = D_GGX( alpha, dotNH );
		return F * ( V * D );
	}
#endif
vec2 LTC_Uv( const in vec3 N, const in vec3 V, const in float roughness ) {
	const float LUT_SIZE = 64.0;
	const float LUT_SCALE = ( LUT_SIZE - 1.0 ) / LUT_SIZE;
	const float LUT_BIAS = 0.5 / LUT_SIZE;
	float dotNV = saturate( dot( N, V ) );
	vec2 uv = vec2( roughness, sqrt( 1.0 - dotNV ) );
	uv = uv * LUT_SCALE + LUT_BIAS;
	return uv;
}
float LTC_ClippedSphereFormFactor( const in vec3 f ) {
	float l = length( f );
	return max( ( l * l + f.z ) / ( l + 1.0 ), 0.0 );
}
vec3 LTC_EdgeVectorFormFactor( const in vec3 v1, const in vec3 v2 ) {
	float x = dot( v1, v2 );
	float y = abs( x );
	float a = 0.8543985 + ( 0.4965155 + 0.0145206 * y ) * y;
	float b = 3.4175940 + ( 4.1616724 + y ) * y;
	float v = a / b;
	float theta_sintheta = ( x > 0.0 ) ? v : 0.5 * inversesqrt( max( 1.0 - x * x, 1e-7 ) ) - v;
	return cross( v1, v2 ) * theta_sintheta;
}
vec3 LTC_Evaluate( const in vec3 N, const in vec3 V, const in vec3 P, const in mat3 mInv, const in vec3 rectCoords[ 4 ] ) {
	vec3 v1 = rectCoords[ 1 ] - rectCoords[ 0 ];
	vec3 v2 = rectCoords[ 3 ] - rectCoords[ 0 ];
	vec3 lightNormal = cross( v1, v2 );
	if( dot( lightNormal, P - rectCoords[ 0 ] ) < 0.0 ) return vec3( 0.0 );
	vec3 T1, T2;
	T1 = normalize( V - N * dot( V, N ) );
	T2 = - cross( N, T1 );
	mat3 mat = mInv * transposeMat3( mat3( T1, T2, N ) );
	vec3 coords[ 4 ];
	coords[ 0 ] = mat * ( rectCoords[ 0 ] - P );
	coords[ 1 ] = mat * ( rectCoords[ 1 ] - P );
	coords[ 2 ] = mat * ( rectCoords[ 2 ] - P );
	coords[ 3 ] = mat * ( rectCoords[ 3 ] - P );
	coords[ 0 ] = normalize( coords[ 0 ] );
	coords[ 1 ] = normalize( coords[ 1 ] );
	coords[ 2 ] = normalize( coords[ 2 ] );
	coords[ 3 ] = normalize( coords[ 3 ] );
	vec3 vectorFormFactor = vec3( 0.0 );
	vectorFormFactor += LTC_EdgeVectorFormFactor( coords[ 0 ], coords[ 1 ] );
	vectorFormFactor += LTC_EdgeVectorFormFactor( coords[ 1 ], coords[ 2 ] );
	vectorFormFactor += LTC_EdgeVectorFormFactor( coords[ 2 ], coords[ 3 ] );
	vectorFormFactor += LTC_EdgeVectorFormFactor( coords[ 3 ], coords[ 0 ] );
	float result = LTC_ClippedSphereFormFactor( vectorFormFactor );
	return vec3( result );
}
float G_BlinnPhong_Implicit( ) {
	return 0.25;
}
float D_BlinnPhong( const in float shininess, const in float dotNH ) {
	return RECIPROCAL_PI * ( shininess * 0.5 + 1.0 ) * pow( dotNH, shininess );
}
vec3 BRDF_BlinnPhong( const in vec3 lightDir, const in vec3 viewDir, const in vec3 normal, const in vec3 specularColor, const in float shininess ) {
	vec3 halfDir = normalize( lightDir + viewDir );
	float dotNH = saturate( dot( normal, halfDir ) );
	float dotVH = saturate( dot( viewDir, halfDir ) );
	vec3 F = F_Schlick( specularColor, 1.0, dotVH );
	float G = G_BlinnPhong_Implicit( );
	float D = D_BlinnPhong( shininess, dotNH );
	return F * ( G * D );
}
#if defined( USE_SHEEN )
float D_Charlie( float roughness, float dotNH ) {
	float alpha = pow2( roughness );
	float invAlpha = 1.0 / alpha;
	float cos2h = dotNH * dotNH;
	float sin2h = max( 1.0 - cos2h, 0.0078125 );
	return ( 2.0 + invAlpha ) * pow( sin2h, invAlpha * 0.5 ) / ( 2.0 * PI );
}
float V_Neubelt( float dotNV, float dotNL ) {
	return saturate( 1.0 / ( 4.0 * ( dotNL + dotNV - dotNL * dotNV ) ) );
}
vec3 BRDF_Sheen( const in vec3 lightDir, const in vec3 viewDir, const in vec3 normal, vec3 sheenColor, const in float sheenRoughness ) {
	vec3 halfDir = normalize( lightDir + viewDir );
	float dotNL = saturate( dot( normal, lightDir ) );
	float dotNV = saturate( dot( normal, viewDir ) );
	float dotNH = saturate( dot( normal, halfDir ) );
	float D = D_Charlie( sheenRoughness, dotNH );
	float V = V_Neubelt( dotNV, dotNL );
	return sheenColor * ( D * V );
}
#endif`,wM=`#ifdef USE_IRIDESCENCE
	const mat3 XYZ_TO_REC709 = mat3(
		 3.2404542, -0.9692660,  0.0556434,
		-1.5371385,  1.8760108, -0.2040259,
		-0.4985314,  0.0415560,  1.0572252
	);
	vec3 Fresnel0ToIor( vec3 fresnel0 ) {
		vec3 sqrtF0 = sqrt( fresnel0 );
		return ( vec3( 1.0 ) + sqrtF0 ) / ( vec3( 1.0 ) - sqrtF0 );
	}
	vec3 IorToFresnel0( vec3 transmittedIor, float incidentIor ) {
		return pow2( ( transmittedIor - vec3( incidentIor ) ) / ( transmittedIor + vec3( incidentIor ) ) );
	}
	float IorToFresnel0( float transmittedIor, float incidentIor ) {
		return pow2( ( transmittedIor - incidentIor ) / ( transmittedIor + incidentIor ));
	}
	vec3 evalSensitivity( float OPD, vec3 shift ) {
		float phase = 2.0 * PI * OPD * 1.0e-9;
		vec3 val = vec3( 5.4856e-13, 4.4201e-13, 5.2481e-13 );
		vec3 pos = vec3( 1.6810e+06, 1.7953e+06, 2.2084e+06 );
		vec3 var = vec3( 4.3278e+09, 9.3046e+09, 6.6121e+09 );
		vec3 xyz = val * sqrt( 2.0 * PI * var ) * cos( pos * phase + shift ) * exp( - pow2( phase ) * var );
		xyz.x += 9.7470e-14 * sqrt( 2.0 * PI * 4.5282e+09 ) * cos( 2.2399e+06 * phase + shift[ 0 ] ) * exp( - 4.5282e+09 * pow2( phase ) );
		xyz /= 1.0685e-7;
		vec3 rgb = XYZ_TO_REC709 * xyz;
		return rgb;
	}
	vec3 evalIridescence( float outsideIOR, float eta2, float cosTheta1, float thinFilmThickness, vec3 baseF0 ) {
		vec3 I;
		float iridescenceIOR = mix( outsideIOR, eta2, smoothstep( 0.0, 0.03, thinFilmThickness ) );
		float sinTheta2Sq = pow2( outsideIOR / iridescenceIOR ) * ( 1.0 - pow2( cosTheta1 ) );
		float cosTheta2Sq = 1.0 - sinTheta2Sq;
		if ( cosTheta2Sq < 0.0 ) {
			 return vec3( 1.0 );
		}
		float cosTheta2 = sqrt( cosTheta2Sq );
		float R0 = IorToFresnel0( iridescenceIOR, outsideIOR );
		float R12 = F_Schlick( R0, 1.0, cosTheta1 );
		float R21 = R12;
		float T121 = 1.0 - R12;
		float phi12 = 0.0;
		if ( iridescenceIOR < outsideIOR ) phi12 = PI;
		float phi21 = PI - phi12;
		vec3 baseIOR = Fresnel0ToIor( clamp( baseF0, 0.0, 0.9999 ) );		vec3 R1 = IorToFresnel0( baseIOR, iridescenceIOR );
		vec3 R23 = F_Schlick( R1, 1.0, cosTheta2 );
		vec3 phi23 = vec3( 0.0 );
		if ( baseIOR[ 0 ] < iridescenceIOR ) phi23[ 0 ] = PI;
		if ( baseIOR[ 1 ] < iridescenceIOR ) phi23[ 1 ] = PI;
		if ( baseIOR[ 2 ] < iridescenceIOR ) phi23[ 2 ] = PI;
		float OPD = 2.0 * iridescenceIOR * thinFilmThickness * cosTheta2;
		vec3 phi = vec3( phi21 ) + phi23;
		vec3 R123 = clamp( R12 * R23, 1e-5, 0.9999 );
		vec3 r123 = sqrt( R123 );
		vec3 Rs = pow2( T121 ) * R23 / ( vec3( 1.0 ) - R123 );
		vec3 C0 = R12 + Rs;
		I = C0;
		vec3 Cm = Rs - T121;
		for ( int m = 1; m <= 2; ++ m ) {
			Cm *= r123;
			vec3 Sm = 2.0 * evalSensitivity( float( m ) * OPD, float( m ) * phi );
			I += Cm * Sm;
		}
		return max( I, vec3( 0.0 ) );
	}
#endif`,CM=`#ifdef USE_BUMPMAP
	uniform sampler2D bumpMap;
	uniform float bumpScale;
	vec2 dHdxy_fwd() {
		vec2 dSTdx = dFdx( vUv );
		vec2 dSTdy = dFdy( vUv );
		float Hll = bumpScale * texture2D( bumpMap, vUv ).x;
		float dBx = bumpScale * texture2D( bumpMap, vUv + dSTdx ).x - Hll;
		float dBy = bumpScale * texture2D( bumpMap, vUv + dSTdy ).x - Hll;
		return vec2( dBx, dBy );
	}
	vec3 perturbNormalArb( vec3 surf_pos, vec3 surf_norm, vec2 dHdxy, float faceDirection ) {
		vec3 vSigmaX = dFdx( surf_pos.xyz );
		vec3 vSigmaY = dFdy( surf_pos.xyz );
		vec3 vN = surf_norm;
		vec3 R1 = cross( vSigmaY, vN );
		vec3 R2 = cross( vN, vSigmaX );
		float fDet = dot( vSigmaX, R1 ) * faceDirection;
		vec3 vGrad = sign( fDet ) * ( dHdxy.x * R1 + dHdxy.y * R2 );
		return normalize( abs( fDet ) * surf_norm - vGrad );
	}
#endif`,TM=`#if NUM_CLIPPING_PLANES > 0
	vec4 plane;
	#pragma unroll_loop_start
	for ( int i = 0; i < UNION_CLIPPING_PLANES; i ++ ) {
		plane = clippingPlanes[ i ];
		if ( dot( vClipPosition, plane.xyz ) > plane.w ) discard;
	}
	#pragma unroll_loop_end
	#if UNION_CLIPPING_PLANES < NUM_CLIPPING_PLANES
		bool clipped = true;
		#pragma unroll_loop_start
		for ( int i = UNION_CLIPPING_PLANES; i < NUM_CLIPPING_PLANES; i ++ ) {
			plane = clippingPlanes[ i ];
			clipped = ( dot( vClipPosition, plane.xyz ) > plane.w ) && clipped;
		}
		#pragma unroll_loop_end
		if ( clipped ) discard;
	#endif
#endif`,EM=`#if NUM_CLIPPING_PLANES > 0
	varying vec3 vClipPosition;
	uniform vec4 clippingPlanes[ NUM_CLIPPING_PLANES ];
#endif`,DM=`#if NUM_CLIPPING_PLANES > 0
	varying vec3 vClipPosition;
#endif`,IM=`#if NUM_CLIPPING_PLANES > 0
	vClipPosition = - mvPosition.xyz;
#endif`,RM=`#if defined( USE_COLOR_ALPHA )
	diffuseColor *= vColor;
#elif defined( USE_COLOR )
	diffuseColor.rgb *= vColor;
#endif`,OM=`#if defined( USE_COLOR_ALPHA )
	varying vec4 vColor;
#elif defined( USE_COLOR )
	varying vec3 vColor;
#endif`,PM=`#if defined( USE_COLOR_ALPHA )
	varying vec4 vColor;
#elif defined( USE_COLOR ) || defined( USE_INSTANCING_COLOR )
	varying vec3 vColor;
#endif`,LM=`#if defined( USE_COLOR_ALPHA )
	vColor = vec4( 1.0 );
#elif defined( USE_COLOR ) || defined( USE_INSTANCING_COLOR )
	vColor = vec3( 1.0 );
#endif
#ifdef USE_COLOR
	vColor *= color;
#endif
#ifdef USE_INSTANCING_COLOR
	vColor.xyz *= instanceColor.xyz;
#endif`,zM=`#define PI 3.141592653589793
#define PI2 6.283185307179586
#define PI_HALF 1.5707963267948966
#define RECIPROCAL_PI 0.3183098861837907
#define RECIPROCAL_PI2 0.15915494309189535
#define EPSILON 1e-6
#ifndef saturate
#define saturate( a ) clamp( a, 0.0, 1.0 )
#endif
#define whiteComplement( a ) ( 1.0 - saturate( a ) )
float pow2( const in float x ) { return x*x; }
vec3 pow2( const in vec3 x ) { return x*x; }
float pow3( const in float x ) { return x*x*x; }
float pow4( const in float x ) { float x2 = x*x; return x2*x2; }
float max3( const in vec3 v ) { return max( max( v.x, v.y ), v.z ); }
float average( const in vec3 v ) { return dot( v, vec3( 0.3333333 ) ); }
highp float rand( const in vec2 uv ) {
	const highp float a = 12.9898, b = 78.233, c = 43758.5453;
	highp float dt = dot( uv.xy, vec2( a,b ) ), sn = mod( dt, PI );
	return fract( sin( sn ) * c );
}
#ifdef HIGH_PRECISION
	float precisionSafeLength( vec3 v ) { return length( v ); }
#else
	float precisionSafeLength( vec3 v ) {
		float maxComponent = max3( abs( v ) );
		return length( v / maxComponent ) * maxComponent;
	}
#endif
struct IncidentLight {
	vec3 color;
	vec3 direction;
	bool visible;
};
struct ReflectedLight {
	vec3 directDiffuse;
	vec3 directSpecular;
	vec3 indirectDiffuse;
	vec3 indirectSpecular;
};
struct GeometricContext {
	vec3 position;
	vec3 normal;
	vec3 viewDir;
#ifdef USE_CLEARCOAT
	vec3 clearcoatNormal;
#endif
};
vec3 transformDirection( in vec3 dir, in mat4 matrix ) {
	return normalize( ( matrix * vec4( dir, 0.0 ) ).xyz );
}
vec3 inverseTransformDirection( in vec3 dir, in mat4 matrix ) {
	return normalize( ( vec4( dir, 0.0 ) * matrix ).xyz );
}
mat3 transposeMat3( const in mat3 m ) {
	mat3 tmp;
	tmp[ 0 ] = vec3( m[ 0 ].x, m[ 1 ].x, m[ 2 ].x );
	tmp[ 1 ] = vec3( m[ 0 ].y, m[ 1 ].y, m[ 2 ].y );
	tmp[ 2 ] = vec3( m[ 0 ].z, m[ 1 ].z, m[ 2 ].z );
	return tmp;
}
float luminance( const in vec3 rgb ) {
	const vec3 weights = vec3( 0.2126729, 0.7151522, 0.0721750 );
	return dot( weights, rgb );
}
bool isPerspectiveMatrix( mat4 m ) {
	return m[ 2 ][ 3 ] == - 1.0;
}
vec2 equirectUv( in vec3 dir ) {
	float u = atan( dir.z, dir.x ) * RECIPROCAL_PI2 + 0.5;
	float v = asin( clamp( dir.y, - 1.0, 1.0 ) ) * RECIPROCAL_PI + 0.5;
	return vec2( u, v );
}`,kM=`#ifdef ENVMAP_TYPE_CUBE_UV
	#define cubeUV_minMipLevel 4.0
	#define cubeUV_minTileSize 16.0
	float getFace( vec3 direction ) {
		vec3 absDirection = abs( direction );
		float face = - 1.0;
		if ( absDirection.x > absDirection.z ) {
			if ( absDirection.x > absDirection.y )
				face = direction.x > 0.0 ? 0.0 : 3.0;
			else
				face = direction.y > 0.0 ? 1.0 : 4.0;
		} else {
			if ( absDirection.z > absDirection.y )
				face = direction.z > 0.0 ? 2.0 : 5.0;
			else
				face = direction.y > 0.0 ? 1.0 : 4.0;
		}
		return face;
	}
	vec2 getUV( vec3 direction, float face ) {
		vec2 uv;
		if ( face == 0.0 ) {
			uv = vec2( direction.z, direction.y ) / abs( direction.x );
		} else if ( face == 1.0 ) {
			uv = vec2( - direction.x, - direction.z ) / abs( direction.y );
		} else if ( face == 2.0 ) {
			uv = vec2( - direction.x, direction.y ) / abs( direction.z );
		} else if ( face == 3.0 ) {
			uv = vec2( - direction.z, direction.y ) / abs( direction.x );
		} else if ( face == 4.0 ) {
			uv = vec2( - direction.x, direction.z ) / abs( direction.y );
		} else {
			uv = vec2( direction.x, direction.y ) / abs( direction.z );
		}
		return 0.5 * ( uv + 1.0 );
	}
	vec3 bilinearCubeUV( sampler2D envMap, vec3 direction, float mipInt ) {
		float face = getFace( direction );
		float filterInt = max( cubeUV_minMipLevel - mipInt, 0.0 );
		mipInt = max( mipInt, cubeUV_minMipLevel );
		float faceSize = exp2( mipInt );
		vec2 uv = getUV( direction, face ) * ( faceSize - 2.0 ) + 1.0;
		if ( face > 2.0 ) {
			uv.y += faceSize;
			face -= 3.0;
		}
		uv.x += face * faceSize;
		uv.x += filterInt * 3.0 * cubeUV_minTileSize;
		uv.y += 4.0 * ( exp2( CUBEUV_MAX_MIP ) - faceSize );
		uv.x *= CUBEUV_TEXEL_WIDTH;
		uv.y *= CUBEUV_TEXEL_HEIGHT;
		#ifdef texture2DGradEXT
			return texture2DGradEXT( envMap, uv, vec2( 0.0 ), vec2( 0.0 ) ).rgb;
		#else
			return texture2D( envMap, uv ).rgb;
		#endif
	}
	#define cubeUV_r0 1.0
	#define cubeUV_v0 0.339
	#define cubeUV_m0 - 2.0
	#define cubeUV_r1 0.8
	#define cubeUV_v1 0.276
	#define cubeUV_m1 - 1.0
	#define cubeUV_r4 0.4
	#define cubeUV_v4 0.046
	#define cubeUV_m4 2.0
	#define cubeUV_r5 0.305
	#define cubeUV_v5 0.016
	#define cubeUV_m5 3.0
	#define cubeUV_r6 0.21
	#define cubeUV_v6 0.0038
	#define cubeUV_m6 4.0
	float roughnessToMip( float roughness ) {
		float mip = 0.0;
		if ( roughness >= cubeUV_r1 ) {
			mip = ( cubeUV_r0 - roughness ) * ( cubeUV_m1 - cubeUV_m0 ) / ( cubeUV_r0 - cubeUV_r1 ) + cubeUV_m0;
		} else if ( roughness >= cubeUV_r4 ) {
			mip = ( cubeUV_r1 - roughness ) * ( cubeUV_m4 - cubeUV_m1 ) / ( cubeUV_r1 - cubeUV_r4 ) + cubeUV_m1;
		} else if ( roughness >= cubeUV_r5 ) {
			mip = ( cubeUV_r4 - roughness ) * ( cubeUV_m5 - cubeUV_m4 ) / ( cubeUV_r4 - cubeUV_r5 ) + cubeUV_m4;
		} else if ( roughness >= cubeUV_r6 ) {
			mip = ( cubeUV_r5 - roughness ) * ( cubeUV_m6 - cubeUV_m5 ) / ( cubeUV_r5 - cubeUV_r6 ) + cubeUV_m5;
		} else {
			mip = - 2.0 * log2( 1.16 * roughness );		}
		return mip;
	}
	vec4 textureCubeUV( sampler2D envMap, vec3 sampleDir, float roughness ) {
		float mip = clamp( roughnessToMip( roughness ), cubeUV_m0, CUBEUV_MAX_MIP );
		float mipF = fract( mip );
		float mipInt = floor( mip );
		vec3 color0 = bilinearCubeUV( envMap, sampleDir, mipInt );
		if ( mipF == 0.0 ) {
			return vec4( color0, 1.0 );
		} else {
			vec3 color1 = bilinearCubeUV( envMap, sampleDir, mipInt + 1.0 );
			return vec4( mix( color0, color1, mipF ), 1.0 );
		}
	}
#endif`,NM=`vec3 transformedNormal = objectNormal;
#ifdef USE_INSTANCING
	mat3 m = mat3( instanceMatrix );
	transformedNormal /= vec3( dot( m[ 0 ], m[ 0 ] ), dot( m[ 1 ], m[ 1 ] ), dot( m[ 2 ], m[ 2 ] ) );
	transformedNormal = m * transformedNormal;
#endif
transformedNormal = normalMatrix * transformedNormal;
#ifdef FLIP_SIDED
	transformedNormal = - transformedNormal;
#endif
#ifdef USE_TANGENT
	vec3 transformedTangent = ( modelViewMatrix * vec4( objectTangent, 0.0 ) ).xyz;
	#ifdef FLIP_SIDED
		transformedTangent = - transformedTangent;
	#endif
#endif`,UM=`#ifdef USE_DISPLACEMENTMAP
	uniform sampler2D displacementMap;
	uniform float displacementScale;
	uniform float displacementBias;
#endif`,FM=`#ifdef USE_DISPLACEMENTMAP
	transformed += normalize( objectNormal ) * ( texture2D( displacementMap, vUv ).x * displacementScale + displacementBias );
#endif`,BM=`#ifdef USE_EMISSIVEMAP
	vec4 emissiveColor = texture2D( emissiveMap, vUv );
	totalEmissiveRadiance *= emissiveColor.rgb;
#endif`,GM=`#ifdef USE_EMISSIVEMAP
	uniform sampler2D emissiveMap;
#endif`,VM="gl_FragColor = linearToOutputTexel( gl_FragColor );",jM=`vec4 LinearToLinear( in vec4 value ) {
	return value;
}
vec4 LinearTosRGB( in vec4 value ) {
	return vec4( mix( pow( value.rgb, vec3( 0.41666 ) ) * 1.055 - vec3( 0.055 ), value.rgb * 12.92, vec3( lessThanEqual( value.rgb, vec3( 0.0031308 ) ) ) ), value.a );
}`,WM=`#ifdef USE_ENVMAP
	#ifdef ENV_WORLDPOS
		vec3 cameraToFrag;
		if ( isOrthographic ) {
			cameraToFrag = normalize( vec3( - viewMatrix[ 0 ][ 2 ], - viewMatrix[ 1 ][ 2 ], - viewMatrix[ 2 ][ 2 ] ) );
		} else {
			cameraToFrag = normalize( vWorldPosition - cameraPosition );
		}
		vec3 worldNormal = inverseTransformDirection( normal, viewMatrix );
		#ifdef ENVMAP_MODE_REFLECTION
			vec3 reflectVec = reflect( cameraToFrag, worldNormal );
		#else
			vec3 reflectVec = refract( cameraToFrag, worldNormal, refractionRatio );
		#endif
	#else
		vec3 reflectVec = vReflect;
	#endif
	#ifdef ENVMAP_TYPE_CUBE
		vec4 envColor = textureCube( envMap, vec3( flipEnvMap * reflectVec.x, reflectVec.yz ) );
	#elif defined( ENVMAP_TYPE_CUBE_UV )
		vec4 envColor = textureCubeUV( envMap, reflectVec, 0.0 );
	#else
		vec4 envColor = vec4( 0.0 );
	#endif
	#ifdef ENVMAP_BLENDING_MULTIPLY
		outgoingLight = mix( outgoingLight, outgoingLight * envColor.xyz, specularStrength * reflectivity );
	#elif defined( ENVMAP_BLENDING_MIX )
		outgoingLight = mix( outgoingLight, envColor.xyz, specularStrength * reflectivity );
	#elif defined( ENVMAP_BLENDING_ADD )
		outgoingLight += envColor.xyz * specularStrength * reflectivity;
	#endif
#endif`,HM=`#ifdef USE_ENVMAP
	uniform float envMapIntensity;
	uniform float flipEnvMap;
	#ifdef ENVMAP_TYPE_CUBE
		uniform samplerCube envMap;
	#else
		uniform sampler2D envMap;
	#endif
	
#endif`,$M=`#ifdef USE_ENVMAP
	uniform float reflectivity;
	#if defined( USE_BUMPMAP ) || defined( USE_NORMALMAP ) || defined( PHONG ) || defined( LAMBERT )
		#define ENV_WORLDPOS
	#endif
	#ifdef ENV_WORLDPOS
		varying vec3 vWorldPosition;
		uniform float refractionRatio;
	#else
		varying vec3 vReflect;
	#endif
#endif`,YM=`#ifdef USE_ENVMAP
	#if defined( USE_BUMPMAP ) || defined( USE_NORMALMAP ) || defined( PHONG ) || defined( LAMBERT )
		#define ENV_WORLDPOS
	#endif
	#ifdef ENV_WORLDPOS
		
		varying vec3 vWorldPosition;
	#else
		varying vec3 vReflect;
		uniform float refractionRatio;
	#endif
#endif`,JM=`#ifdef USE_ENVMAP
	#ifdef ENV_WORLDPOS
		vWorldPosition = worldPosition.xyz;
	#else
		vec3 cameraToVertex;
		if ( isOrthographic ) {
			cameraToVertex = normalize( vec3( - viewMatrix[ 0 ][ 2 ], - viewMatrix[ 1 ][ 2 ], - viewMatrix[ 2 ][ 2 ] ) );
		} else {
			cameraToVertex = normalize( worldPosition.xyz - cameraPosition );
		}
		vec3 worldNormal = inverseTransformDirection( transformedNormal, viewMatrix );
		#ifdef ENVMAP_MODE_REFLECTION
			vReflect = reflect( cameraToVertex, worldNormal );
		#else
			vReflect = refract( cameraToVertex, worldNormal, refractionRatio );
		#endif
	#endif
#endif`,ZM=`#ifdef USE_FOG
	vFogDepth = - mvPosition.z;
#endif`,qM=`#ifdef USE_FOG
	varying float vFogDepth;
#endif`,XM=`#ifdef USE_FOG
	#ifdef FOG_EXP2
		float fogFactor = 1.0 - exp( - fogDensity * fogDensity * vFogDepth * vFogDepth );
	#else
		float fogFactor = smoothstep( fogNear, fogFar, vFogDepth );
	#endif
	gl_FragColor.rgb = mix( gl_FragColor.rgb, fogColor, fogFactor );
#endif`,QM=`#ifdef USE_FOG
	uniform vec3 fogColor;
	varying float vFogDepth;
	#ifdef FOG_EXP2
		uniform float fogDensity;
	#else
		uniform float fogNear;
		uniform float fogFar;
	#endif
#endif`,KM=`#ifdef USE_GRADIENTMAP
	uniform sampler2D gradientMap;
#endif
vec3 getGradientIrradiance( vec3 normal, vec3 lightDirection ) {
	float dotNL = dot( normal, lightDirection );
	vec2 coord = vec2( dotNL * 0.5 + 0.5, 0.0 );
	#ifdef USE_GRADIENTMAP
		return vec3( texture2D( gradientMap, coord ).r );
	#else
		vec2 fw = fwidth( coord ) * 0.5;
		return mix( vec3( 0.7 ), vec3( 1.0 ), smoothstep( 0.7 - fw.x, 0.7 + fw.x, coord.x ) );
	#endif
}`,eA=`#ifdef USE_LIGHTMAP
	vec4 lightMapTexel = texture2D( lightMap, vUv2 );
	vec3 lightMapIrradiance = lightMapTexel.rgb * lightMapIntensity;
	reflectedLight.indirectDiffuse += lightMapIrradiance;
#endif`,tA=`#ifdef USE_LIGHTMAP
	uniform sampler2D lightMap;
	uniform float lightMapIntensity;
#endif`,nA=`LambertMaterial material;
material.diffuseColor = diffuseColor.rgb;
material.specularStrength = specularStrength;`,iA=`varying vec3 vViewPosition;
struct LambertMaterial {
	vec3 diffuseColor;
	float specularStrength;
};
void RE_Direct_Lambert( const in IncidentLight directLight, const in GeometricContext geometry, const in LambertMaterial material, inout ReflectedLight reflectedLight ) {
	float dotNL = saturate( dot( geometry.normal, directLight.direction ) );
	vec3 irradiance = dotNL * directLight.color;
	reflectedLight.directDiffuse += irradiance * BRDF_Lambert( material.diffuseColor );
}
void RE_IndirectDiffuse_Lambert( const in vec3 irradiance, const in GeometricContext geometry, const in LambertMaterial material, inout ReflectedLight reflectedLight ) {
	reflectedLight.indirectDiffuse += irradiance * BRDF_Lambert( material.diffuseColor );
}
#define RE_Direct				RE_Direct_Lambert
#define RE_IndirectDiffuse		RE_IndirectDiffuse_Lambert
#define Material_LightProbeLOD( material )	(0)`,oA=`uniform bool receiveShadow;
uniform vec3 ambientLightColor;
uniform vec3 lightProbe[ 9 ];
vec3 shGetIrradianceAt( in vec3 normal, in vec3 shCoefficients[ 9 ] ) {
	float x = normal.x, y = normal.y, z = normal.z;
	vec3 result = shCoefficients[ 0 ] * 0.886227;
	result += shCoefficients[ 1 ] * 2.0 * 0.511664 * y;
	result += shCoefficients[ 2 ] * 2.0 * 0.511664 * z;
	result += shCoefficients[ 3 ] * 2.0 * 0.511664 * x;
	result += shCoefficients[ 4 ] * 2.0 * 0.429043 * x * y;
	result += shCoefficients[ 5 ] * 2.0 * 0.429043 * y * z;
	result += shCoefficients[ 6 ] * ( 0.743125 * z * z - 0.247708 );
	result += shCoefficients[ 7 ] * 2.0 * 0.429043 * x * z;
	result += shCoefficients[ 8 ] * 0.429043 * ( x * x - y * y );
	return result;
}
vec3 getLightProbeIrradiance( const in vec3 lightProbe[ 9 ], const in vec3 normal ) {
	vec3 worldNormal = inverseTransformDirection( normal, viewMatrix );
	vec3 irradiance = shGetIrradianceAt( worldNormal, lightProbe );
	return irradiance;
}
vec3 getAmbientLightIrradiance( const in vec3 ambientLightColor ) {
	vec3 irradiance = ambientLightColor;
	return irradiance;
}
float getDistanceAttenuation( const in float lightDistance, const in float cutoffDistance, const in float decayExponent ) {
	#if defined ( PHYSICALLY_CORRECT_LIGHTS )
		float distanceFalloff = 1.0 / max( pow( lightDistance, decayExponent ), 0.01 );
		if ( cutoffDistance > 0.0 ) {
			distanceFalloff *= pow2( saturate( 1.0 - pow4( lightDistance / cutoffDistance ) ) );
		}
		return distanceFalloff;
	#else
		if ( cutoffDistance > 0.0 && decayExponent > 0.0 ) {
			return pow( saturate( - lightDistance / cutoffDistance + 1.0 ), decayExponent );
		}
		return 1.0;
	#endif
}
float getSpotAttenuation( const in float coneCosine, const in float penumbraCosine, const in float angleCosine ) {
	return smoothstep( coneCosine, penumbraCosine, angleCosine );
}
#if NUM_DIR_LIGHTS > 0
	struct DirectionalLight {
		vec3 direction;
		vec3 color;
	};
	uniform DirectionalLight directionalLights[ NUM_DIR_LIGHTS ];
	void getDirectionalLightInfo( const in DirectionalLight directionalLight, const in GeometricContext geometry, out IncidentLight light ) {
		light.color = directionalLight.color;
		light.direction = directionalLight.direction;
		light.visible = true;
	}
#endif
#if NUM_POINT_LIGHTS > 0
	struct PointLight {
		vec3 position;
		vec3 color;
		float distance;
		float decay;
	};
	uniform PointLight pointLights[ NUM_POINT_LIGHTS ];
	void getPointLightInfo( const in PointLight pointLight, const in GeometricContext geometry, out IncidentLight light ) {
		vec3 lVector = pointLight.position - geometry.position;
		light.direction = normalize( lVector );
		float lightDistance = length( lVector );
		light.color = pointLight.color;
		light.color *= getDistanceAttenuation( lightDistance, pointLight.distance, pointLight.decay );
		light.visible = ( light.color != vec3( 0.0 ) );
	}
#endif
#if NUM_SPOT_LIGHTS > 0
	struct SpotLight {
		vec3 position;
		vec3 direction;
		vec3 color;
		float distance;
		float decay;
		float coneCos;
		float penumbraCos;
	};
	uniform SpotLight spotLights[ NUM_SPOT_LIGHTS ];
	void getSpotLightInfo( const in SpotLight spotLight, const in GeometricContext geometry, out IncidentLight light ) {
		vec3 lVector = spotLight.position - geometry.position;
		light.direction = normalize( lVector );
		float angleCos = dot( light.direction, spotLight.direction );
		float spotAttenuation = getSpotAttenuation( spotLight.coneCos, spotLight.penumbraCos, angleCos );
		if ( spotAttenuation > 0.0 ) {
			float lightDistance = length( lVector );
			light.color = spotLight.color * spotAttenuation;
			light.color *= getDistanceAttenuation( lightDistance, spotLight.distance, spotLight.decay );
			light.visible = ( light.color != vec3( 0.0 ) );
		} else {
			light.color = vec3( 0.0 );
			light.visible = false;
		}
	}
#endif
#if NUM_RECT_AREA_LIGHTS > 0
	struct RectAreaLight {
		vec3 color;
		vec3 position;
		vec3 halfWidth;
		vec3 halfHeight;
	};
	uniform sampler2D ltc_1;	uniform sampler2D ltc_2;
	uniform RectAreaLight rectAreaLights[ NUM_RECT_AREA_LIGHTS ];
#endif
#if NUM_HEMI_LIGHTS > 0
	struct HemisphereLight {
		vec3 direction;
		vec3 skyColor;
		vec3 groundColor;
	};
	uniform HemisphereLight hemisphereLights[ NUM_HEMI_LIGHTS ];
	vec3 getHemisphereLightIrradiance( const in HemisphereLight hemiLight, const in vec3 normal ) {
		float dotNL = dot( normal, hemiLight.direction );
		float hemiDiffuseWeight = 0.5 * dotNL + 0.5;
		vec3 irradiance = mix( hemiLight.groundColor, hemiLight.skyColor, hemiDiffuseWeight );
		return irradiance;
	}
#endif`,aA=`#if defined( USE_ENVMAP )
	vec3 getIBLIrradiance( const in vec3 normal ) {
		#if defined( ENVMAP_TYPE_CUBE_UV )
			vec3 worldNormal = inverseTransformDirection( normal, viewMatrix );
			vec4 envMapColor = textureCubeUV( envMap, worldNormal, 1.0 );
			return PI * envMapColor.rgb * envMapIntensity;
		#else
			return vec3( 0.0 );
		#endif
	}
	vec3 getIBLRadiance( const in vec3 viewDir, const in vec3 normal, const in float roughness ) {
		#if defined( ENVMAP_TYPE_CUBE_UV )
			vec3 reflectVec = reflect( - viewDir, normal );
			reflectVec = normalize( mix( reflectVec, normal, roughness * roughness) );
			reflectVec = inverseTransformDirection( reflectVec, viewMatrix );
			vec4 envMapColor = textureCubeUV( envMap, reflectVec, roughness );
			return envMapColor.rgb * envMapIntensity;
		#else
			return vec3( 0.0 );
		#endif
	}
#endif`,rA=`ToonMaterial material;
material.diffuseColor = diffuseColor.rgb;`,sA=`varying vec3 vViewPosition;
struct ToonMaterial {
	vec3 diffuseColor;
};
void RE_Direct_Toon( const in IncidentLight directLight, const in GeometricContext geometry, const in ToonMaterial material, inout ReflectedLight reflectedLight ) {
	vec3 irradiance = getGradientIrradiance( geometry.normal, directLight.direction ) * directLight.color;
	reflectedLight.directDiffuse += irradiance * BRDF_Lambert( material.diffuseColor );
}
void RE_IndirectDiffuse_Toon( const in vec3 irradiance, const in GeometricContext geometry, const in ToonMaterial material, inout ReflectedLight reflectedLight ) {
	reflectedLight.indirectDiffuse += irradiance * BRDF_Lambert( material.diffuseColor );
}
#define RE_Direct				RE_Direct_Toon
#define RE_IndirectDiffuse		RE_IndirectDiffuse_Toon
#define Material_LightProbeLOD( material )	(0)`,lA=`BlinnPhongMaterial material;
material.diffuseColor = diffuseColor.rgb;
material.specularColor = specular;
material.specularShininess = shininess;
material.specularStrength = specularStrength;`,uA=`varying vec3 vViewPosition;
struct BlinnPhongMaterial {
	vec3 diffuseColor;
	vec3 specularColor;
	float specularShininess;
	float specularStrength;
};
void RE_Direct_BlinnPhong( const in IncidentLight directLight, const in GeometricContext geometry, const in BlinnPhongMaterial material, inout ReflectedLight reflectedLight ) {
	float dotNL = saturate( dot( geometry.normal, directLight.direction ) );
	vec3 irradiance = dotNL * directLight.color;
	reflectedLight.directDiffuse += irradiance * BRDF_Lambert( material.diffuseColor );
	reflectedLight.directSpecular += irradiance * BRDF_BlinnPhong( directLight.direction, geometry.viewDir, geometry.normal, material.specularColor, material.specularShininess ) * material.specularStrength;
}
void RE_IndirectDiffuse_BlinnPhong( const in vec3 irradiance, const in GeometricContext geometry, const in BlinnPhongMaterial material, inout ReflectedLight reflectedLight ) {
	reflectedLight.indirectDiffuse += irradiance * BRDF_Lambert( material.diffuseColor );
}
#define RE_Direct				RE_Direct_BlinnPhong
#define RE_IndirectDiffuse		RE_IndirectDiffuse_BlinnPhong
#define Material_LightProbeLOD( material )	(0)`,cA=`PhysicalMaterial material;
material.diffuseColor = diffuseColor.rgb * ( 1.0 - metalnessFactor );
vec3 dxy = max( abs( dFdx( geometryNormal ) ), abs( dFdy( geometryNormal ) ) );
float geometryRoughness = max( max( dxy.x, dxy.y ), dxy.z );
material.roughness = max( roughnessFactor, 0.0525 );material.roughness += geometryRoughness;
material.roughness = min( material.roughness, 1.0 );
#ifdef IOR
	material.ior = ior;
	#ifdef SPECULAR
		float specularIntensityFactor = specularIntensity;
		vec3 specularColorFactor = specularColor;
		#ifdef USE_SPECULARINTENSITYMAP
			specularIntensityFactor *= texture2D( specularIntensityMap, vUv ).a;
		#endif
		#ifdef USE_SPECULARCOLORMAP
			specularColorFactor *= texture2D( specularColorMap, vUv ).rgb;
		#endif
		material.specularF90 = mix( specularIntensityFactor, 1.0, metalnessFactor );
	#else
		float specularIntensityFactor = 1.0;
		vec3 specularColorFactor = vec3( 1.0 );
		material.specularF90 = 1.0;
	#endif
	material.specularColor = mix( min( pow2( ( material.ior - 1.0 ) / ( material.ior + 1.0 ) ) * specularColorFactor, vec3( 1.0 ) ) * specularIntensityFactor, diffuseColor.rgb, metalnessFactor );
#else
	material.specularColor = mix( vec3( 0.04 ), diffuseColor.rgb, metalnessFactor );
	material.specularF90 = 1.0;
#endif
#ifdef USE_CLEARCOAT
	material.clearcoat = clearcoat;
	material.clearcoatRoughness = clearcoatRoughness;
	material.clearcoatF0 = vec3( 0.04 );
	material.clearcoatF90 = 1.0;
	#ifdef USE_CLEARCOATMAP
		material.clearcoat *= texture2D( clearcoatMap, vUv ).x;
	#endif
	#ifdef USE_CLEARCOAT_ROUGHNESSMAP
		material.clearcoatRoughness *= texture2D( clearcoatRoughnessMap, vUv ).y;
	#endif
	material.clearcoat = saturate( material.clearcoat );	material.clearcoatRoughness = max( material.clearcoatRoughness, 0.0525 );
	material.clearcoatRoughness += geometryRoughness;
	material.clearcoatRoughness = min( material.clearcoatRoughness, 1.0 );
#endif
#ifdef USE_IRIDESCENCE
	material.iridescence = iridescence;
	material.iridescenceIOR = iridescenceIOR;
	#ifdef USE_IRIDESCENCEMAP
		material.iridescence *= texture2D( iridescenceMap, vUv ).r;
	#endif
	#ifdef USE_IRIDESCENCE_THICKNESSMAP
		material.iridescenceThickness = (iridescenceThicknessMaximum - iridescenceThicknessMinimum) * texture2D( iridescenceThicknessMap, vUv ).g + iridescenceThicknessMinimum;
	#else
		material.iridescenceThickness = iridescenceThicknessMaximum;
	#endif
#endif
#ifdef USE_SHEEN
	material.sheenColor = sheenColor;
	#ifdef USE_SHEENCOLORMAP
		material.sheenColor *= texture2D( sheenColorMap, vUv ).rgb;
	#endif
	material.sheenRoughness = clamp( sheenRoughness, 0.07, 1.0 );
	#ifdef USE_SHEENROUGHNESSMAP
		material.sheenRoughness *= texture2D( sheenRoughnessMap, vUv ).a;
	#endif
#endif`,dA=`struct PhysicalMaterial {
	vec3 diffuseColor;
	float roughness;
	vec3 specularColor;
	float specularF90;
	#ifdef USE_CLEARCOAT
		float clearcoat;
		float clearcoatRoughness;
		vec3 clearcoatF0;
		float clearcoatF90;
	#endif
	#ifdef USE_IRIDESCENCE
		float iridescence;
		float iridescenceIOR;
		float iridescenceThickness;
		vec3 iridescenceFresnel;
		vec3 iridescenceF0;
	#endif
	#ifdef USE_SHEEN
		vec3 sheenColor;
		float sheenRoughness;
	#endif
	#ifdef IOR
		float ior;
	#endif
	#ifdef USE_TRANSMISSION
		float transmission;
		float transmissionAlpha;
		float thickness;
		float attenuationDistance;
		vec3 attenuationColor;
	#endif
};
vec3 clearcoatSpecular = vec3( 0.0 );
vec3 sheenSpecular = vec3( 0.0 );
float IBLSheenBRDF( const in vec3 normal, const in vec3 viewDir, const in float roughness ) {
	float dotNV = saturate( dot( normal, viewDir ) );
	float r2 = roughness * roughness;
	float a = roughness < 0.25 ? -339.2 * r2 + 161.4 * roughness - 25.9 : -8.48 * r2 + 14.3 * roughness - 9.95;
	float b = roughness < 0.25 ? 44.0 * r2 - 23.7 * roughness + 3.26 : 1.97 * r2 - 3.27 * roughness + 0.72;
	float DG = exp( a * dotNV + b ) + ( roughness < 0.25 ? 0.0 : 0.1 * ( roughness - 0.25 ) );
	return saturate( DG * RECIPROCAL_PI );
}
vec2 DFGApprox( const in vec3 normal, const in vec3 viewDir, const in float roughness ) {
	float dotNV = saturate( dot( normal, viewDir ) );
	const vec4 c0 = vec4( - 1, - 0.0275, - 0.572, 0.022 );
	const vec4 c1 = vec4( 1, 0.0425, 1.04, - 0.04 );
	vec4 r = roughness * c0 + c1;
	float a004 = min( r.x * r.x, exp2( - 9.28 * dotNV ) ) * r.x + r.y;
	vec2 fab = vec2( - 1.04, 1.04 ) * a004 + r.zw;
	return fab;
}
vec3 EnvironmentBRDF( const in vec3 normal, const in vec3 viewDir, const in vec3 specularColor, const in float specularF90, const in float roughness ) {
	vec2 fab = DFGApprox( normal, viewDir, roughness );
	return specularColor * fab.x + specularF90 * fab.y;
}
#ifdef USE_IRIDESCENCE
void computeMultiscatteringIridescence( const in vec3 normal, const in vec3 viewDir, const in vec3 specularColor, const in float specularF90, const in float iridescence, const in vec3 iridescenceF0, const in float roughness, inout vec3 singleScatter, inout vec3 multiScatter ) {
#else
void computeMultiscattering( const in vec3 normal, const in vec3 viewDir, const in vec3 specularColor, const in float specularF90, const in float roughness, inout vec3 singleScatter, inout vec3 multiScatter ) {
#endif
	vec2 fab = DFGApprox( normal, viewDir, roughness );
	#ifdef USE_IRIDESCENCE
		vec3 Fr = mix( specularColor, iridescenceF0, iridescence );
	#else
		vec3 Fr = specularColor;
	#endif
	vec3 FssEss = Fr * fab.x + specularF90 * fab.y;
	float Ess = fab.x + fab.y;
	float Ems = 1.0 - Ess;
	vec3 Favg = Fr + ( 1.0 - Fr ) * 0.047619;	vec3 Fms = FssEss * Favg / ( 1.0 - Ems * Favg );
	singleScatter += FssEss;
	multiScatter += Fms * Ems;
}
#if NUM_RECT_AREA_LIGHTS > 0
	void RE_Direct_RectArea_Physical( const in RectAreaLight rectAreaLight, const in GeometricContext geometry, const in PhysicalMaterial material, inout ReflectedLight reflectedLight ) {
		vec3 normal = geometry.normal;
		vec3 viewDir = geometry.viewDir;
		vec3 position = geometry.position;
		vec3 lightPos = rectAreaLight.position;
		vec3 halfWidth = rectAreaLight.halfWidth;
		vec3 halfHeight = rectAreaLight.halfHeight;
		vec3 lightColor = rectAreaLight.color;
		float roughness = material.roughness;
		vec3 rectCoords[ 4 ];
		rectCoords[ 0 ] = lightPos + halfWidth - halfHeight;		rectCoords[ 1 ] = lightPos - halfWidth - halfHeight;
		rectCoords[ 2 ] = lightPos - halfWidth + halfHeight;
		rectCoords[ 3 ] = lightPos + halfWidth + halfHeight;
		vec2 uv = LTC_Uv( normal, viewDir, roughness );
		vec4 t1 = texture2D( ltc_1, uv );
		vec4 t2 = texture2D( ltc_2, uv );
		mat3 mInv = mat3(
			vec3( t1.x, 0, t1.y ),
			vec3(    0, 1,    0 ),
			vec3( t1.z, 0, t1.w )
		);
		vec3 fresnel = ( material.specularColor * t2.x + ( vec3( 1.0 ) - material.specularColor ) * t2.y );
		reflectedLight.directSpecular += lightColor * fresnel * LTC_Evaluate( normal, viewDir, position, mInv, rectCoords );
		reflectedLight.directDiffuse += lightColor * material.diffuseColor * LTC_Evaluate( normal, viewDir, position, mat3( 1.0 ), rectCoords );
	}
#endif
void RE_Direct_Physical( const in IncidentLight directLight, const in GeometricContext geometry, const in PhysicalMaterial material, inout ReflectedLight reflectedLight ) {
	float dotNL = saturate( dot( geometry.normal, directLight.direction ) );
	vec3 irradiance = dotNL * directLight.color;
	#ifdef USE_CLEARCOAT
		float dotNLcc = saturate( dot( geometry.clearcoatNormal, directLight.direction ) );
		vec3 ccIrradiance = dotNLcc * directLight.color;
		clearcoatSpecular += ccIrradiance * BRDF_GGX( directLight.direction, geometry.viewDir, geometry.clearcoatNormal, material.clearcoatF0, material.clearcoatF90, material.clearcoatRoughness );
	#endif
	#ifdef USE_SHEEN
		sheenSpecular += irradiance * BRDF_Sheen( directLight.direction, geometry.viewDir, geometry.normal, material.sheenColor, material.sheenRoughness );
	#endif
	#ifdef USE_IRIDESCENCE
		reflectedLight.directSpecular += irradiance * BRDF_GGX_Iridescence( directLight.direction, geometry.viewDir, geometry.normal, material.specularColor, material.specularF90, material.iridescence, material.iridescenceFresnel, material.roughness );
	#else
		reflectedLight.directSpecular += irradiance * BRDF_GGX( directLight.direction, geometry.viewDir, geometry.normal, material.specularColor, material.specularF90, material.roughness );
	#endif
	reflectedLight.directDiffuse += irradiance * BRDF_Lambert( material.diffuseColor );
}
void RE_IndirectDiffuse_Physical( const in vec3 irradiance, const in GeometricContext geometry, const in PhysicalMaterial material, inout ReflectedLight reflectedLight ) {
	reflectedLight.indirectDiffuse += irradiance * BRDF_Lambert( material.diffuseColor );
}
void RE_IndirectSpecular_Physical( const in vec3 radiance, const in vec3 irradiance, const in vec3 clearcoatRadiance, const in GeometricContext geometry, const in PhysicalMaterial material, inout ReflectedLight reflectedLight) {
	#ifdef USE_CLEARCOAT
		clearcoatSpecular += clearcoatRadiance * EnvironmentBRDF( geometry.clearcoatNormal, geometry.viewDir, material.clearcoatF0, material.clearcoatF90, material.clearcoatRoughness );
	#endif
	#ifdef USE_SHEEN
		sheenSpecular += irradiance * material.sheenColor * IBLSheenBRDF( geometry.normal, geometry.viewDir, material.sheenRoughness );
	#endif
	vec3 singleScattering = vec3( 0.0 );
	vec3 multiScattering = vec3( 0.0 );
	vec3 cosineWeightedIrradiance = irradiance * RECIPROCAL_PI;
	#ifdef USE_IRIDESCENCE
		computeMultiscatteringIridescence( geometry.normal, geometry.viewDir, material.specularColor, material.specularF90, material.iridescence, material.iridescenceFresnel, material.roughness, singleScattering, multiScattering );
	#else
		computeMultiscattering( geometry.normal, geometry.viewDir, material.specularColor, material.specularF90, material.roughness, singleScattering, multiScattering );
	#endif
	vec3 totalScattering = singleScattering + multiScattering;
	vec3 diffuse = material.diffuseColor * ( 1.0 - max( max( totalScattering.r, totalScattering.g ), totalScattering.b ) );
	reflectedLight.indirectSpecular += radiance * singleScattering;
	reflectedLight.indirectSpecular += multiScattering * cosineWeightedIrradiance;
	reflectedLight.indirectDiffuse += diffuse * cosineWeightedIrradiance;
}
#define RE_Direct				RE_Direct_Physical
#define RE_Direct_RectArea		RE_Direct_RectArea_Physical
#define RE_IndirectDiffuse		RE_IndirectDiffuse_Physical
#define RE_IndirectSpecular		RE_IndirectSpecular_Physical
float computeSpecularOcclusion( const in float dotNV, const in float ambientOcclusion, const in float roughness ) {
	return saturate( pow( dotNV + ambientOcclusion, exp2( - 16.0 * roughness - 1.0 ) ) - 1.0 + ambientOcclusion );
}`,fA=`
GeometricContext geometry;
geometry.position = - vViewPosition;
geometry.normal = normal;
geometry.viewDir = ( isOrthographic ) ? vec3( 0, 0, 1 ) : normalize( vViewPosition );
#ifdef USE_CLEARCOAT
	geometry.clearcoatNormal = clearcoatNormal;
#endif
#ifdef USE_IRIDESCENCE
	float dotNVi = saturate( dot( normal, geometry.viewDir ) );
	if ( material.iridescenceThickness == 0.0 ) {
		material.iridescence = 0.0;
	} else {
		material.iridescence = saturate( material.iridescence );
	}
	if ( material.iridescence > 0.0 ) {
		material.iridescenceFresnel = evalIridescence( 1.0, material.iridescenceIOR, dotNVi, material.iridescenceThickness, material.specularColor );
		material.iridescenceF0 = Schlick_to_F0( material.iridescenceFresnel, 1.0, dotNVi );
	}
#endif
IncidentLight directLight;
#if ( NUM_POINT_LIGHTS > 0 ) && defined( RE_Direct )
	PointLight pointLight;
	#if defined( USE_SHADOWMAP ) && NUM_POINT_LIGHT_SHADOWS > 0
	PointLightShadow pointLightShadow;
	#endif
	#pragma unroll_loop_start
	for ( int i = 0; i < NUM_POINT_LIGHTS; i ++ ) {
		pointLight = pointLights[ i ];
		getPointLightInfo( pointLight, geometry, directLight );
		#if defined( USE_SHADOWMAP ) && ( UNROLLED_LOOP_INDEX < NUM_POINT_LIGHT_SHADOWS )
		pointLightShadow = pointLightShadows[ i ];
		directLight.color *= all( bvec2( directLight.visible, receiveShadow ) ) ? getPointShadow( pointShadowMap[ i ], pointLightShadow.shadowMapSize, pointLightShadow.shadowBias, pointLightShadow.shadowRadius, vPointShadowCoord[ i ], pointLightShadow.shadowCameraNear, pointLightShadow.shadowCameraFar ) : 1.0;
		#endif
		RE_Direct( directLight, geometry, material, reflectedLight );
	}
	#pragma unroll_loop_end
#endif
#if ( NUM_SPOT_LIGHTS > 0 ) && defined( RE_Direct )
	SpotLight spotLight;
	vec4 spotColor;
	vec3 spotLightCoord;
	bool inSpotLightMap;
	#if defined( USE_SHADOWMAP ) && NUM_SPOT_LIGHT_SHADOWS > 0
	SpotLightShadow spotLightShadow;
	#endif
	#pragma unroll_loop_start
	for ( int i = 0; i < NUM_SPOT_LIGHTS; i ++ ) {
		spotLight = spotLights[ i ];
		getSpotLightInfo( spotLight, geometry, directLight );
		#if ( UNROLLED_LOOP_INDEX < NUM_SPOT_LIGHT_SHADOWS_WITH_MAPS )
		#define SPOT_LIGHT_MAP_INDEX UNROLLED_LOOP_INDEX
		#elif ( UNROLLED_LOOP_INDEX < NUM_SPOT_LIGHT_SHADOWS )
		#define SPOT_LIGHT_MAP_INDEX NUM_SPOT_LIGHT_MAPS
		#else
		#define SPOT_LIGHT_MAP_INDEX ( UNROLLED_LOOP_INDEX - NUM_SPOT_LIGHT_SHADOWS + NUM_SPOT_LIGHT_SHADOWS_WITH_MAPS )
		#endif
		#if ( SPOT_LIGHT_MAP_INDEX < NUM_SPOT_LIGHT_MAPS )
			spotLightCoord = vSpotLightCoord[ i ].xyz / vSpotLightCoord[ i ].w;
			inSpotLightMap = all( lessThan( abs( spotLightCoord * 2. - 1. ), vec3( 1.0 ) ) );
			spotColor = texture2D( spotLightMap[ SPOT_LIGHT_MAP_INDEX ], spotLightCoord.xy );
			directLight.color = inSpotLightMap ? directLight.color * spotColor.rgb : directLight.color;
		#endif
		#undef SPOT_LIGHT_MAP_INDEX
		#if defined( USE_SHADOWMAP ) && ( UNROLLED_LOOP_INDEX < NUM_SPOT_LIGHT_SHADOWS )
		spotLightShadow = spotLightShadows[ i ];
		directLight.color *= all( bvec2( directLight.visible, receiveShadow ) ) ? getShadow( spotShadowMap[ i ], spotLightShadow.shadowMapSize, spotLightShadow.shadowBias, spotLightShadow.shadowRadius, vSpotLightCoord[ i ] ) : 1.0;
		#endif
		RE_Direct( directLight, geometry, material, reflectedLight );
	}
	#pragma unroll_loop_end
#endif
#if ( NUM_DIR_LIGHTS > 0 ) && defined( RE_Direct )
	DirectionalLight directionalLight;
	#if defined( USE_SHADOWMAP ) && NUM_DIR_LIGHT_SHADOWS > 0
	DirectionalLightShadow directionalLightShadow;
	#endif
	#pragma unroll_loop_start
	for ( int i = 0; i < NUM_DIR_LIGHTS; i ++ ) {
		directionalLight = directionalLights[ i ];
		getDirectionalLightInfo( directionalLight, geometry, directLight );
		#if defined( USE_SHADOWMAP ) && ( UNROLLED_LOOP_INDEX < NUM_DIR_LIGHT_SHADOWS )
		directionalLightShadow = directionalLightShadows[ i ];
		directLight.color *= all( bvec2( directLight.visible, receiveShadow ) ) ? getShadow( directionalShadowMap[ i ], directionalLightShadow.shadowMapSize, directionalLightShadow.shadowBias, directionalLightShadow.shadowRadius, vDirectionalShadowCoord[ i ] ) : 1.0;
		#endif
		RE_Direct( directLight, geometry, material, reflectedLight );
	}
	#pragma unroll_loop_end
#endif
#if ( NUM_RECT_AREA_LIGHTS > 0 ) && defined( RE_Direct_RectArea )
	RectAreaLight rectAreaLight;
	#pragma unroll_loop_start
	for ( int i = 0; i < NUM_RECT_AREA_LIGHTS; i ++ ) {
		rectAreaLight = rectAreaLights[ i ];
		RE_Direct_RectArea( rectAreaLight, geometry, material, reflectedLight );
	}
	#pragma unroll_loop_end
#endif
#if defined( RE_IndirectDiffuse )
	vec3 iblIrradiance = vec3( 0.0 );
	vec3 irradiance = getAmbientLightIrradiance( ambientLightColor );
	irradiance += getLightProbeIrradiance( lightProbe, geometry.normal );
	#if ( NUM_HEMI_LIGHTS > 0 )
		#pragma unroll_loop_start
		for ( int i = 0; i < NUM_HEMI_LIGHTS; i ++ ) {
			irradiance += getHemisphereLightIrradiance( hemisphereLights[ i ], geometry.normal );
		}
		#pragma unroll_loop_end
	#endif
#endif
#if defined( RE_IndirectSpecular )
	vec3 radiance = vec3( 0.0 );
	vec3 clearcoatRadiance = vec3( 0.0 );
#endif`,pA=`#if defined( RE_IndirectDiffuse )
	#ifdef USE_LIGHTMAP
		vec4 lightMapTexel = texture2D( lightMap, vUv2 );
		vec3 lightMapIrradiance = lightMapTexel.rgb * lightMapIntensity;
		irradiance += lightMapIrradiance;
	#endif
	#if defined( USE_ENVMAP ) && defined( STANDARD ) && defined( ENVMAP_TYPE_CUBE_UV )
		iblIrradiance += getIBLIrradiance( geometry.normal );
	#endif
#endif
#if defined( USE_ENVMAP ) && defined( RE_IndirectSpecular )
	radiance += getIBLRadiance( geometry.viewDir, geometry.normal, material.roughness );
	#ifdef USE_CLEARCOAT
		clearcoatRadiance += getIBLRadiance( geometry.viewDir, geometry.clearcoatNormal, material.clearcoatRoughness );
	#endif
#endif`,hA=`#if defined( RE_IndirectDiffuse )
	RE_IndirectDiffuse( irradiance, geometry, material, reflectedLight );
#endif
#if defined( RE_IndirectSpecular )
	RE_IndirectSpecular( radiance, iblIrradiance, clearcoatRadiance, geometry, material, reflectedLight );
#endif`,mA=`#if defined( USE_LOGDEPTHBUF ) && defined( USE_LOGDEPTHBUF_EXT )
	gl_FragDepthEXT = vIsPerspective == 0.0 ? gl_FragCoord.z : log2( vFragDepth ) * logDepthBufFC * 0.5;
#endif`,gA=`#if defined( USE_LOGDEPTHBUF ) && defined( USE_LOGDEPTHBUF_EXT )
	uniform float logDepthBufFC;
	varying float vFragDepth;
	varying float vIsPerspective;
#endif`,vA=`#ifdef USE_LOGDEPTHBUF
	#ifdef USE_LOGDEPTHBUF_EXT
		varying float vFragDepth;
		varying float vIsPerspective;
	#else
		uniform float logDepthBufFC;
	#endif
#endif`,_A=`#ifdef USE_LOGDEPTHBUF
	#ifdef USE_LOGDEPTHBUF_EXT
		vFragDepth = 1.0 + gl_Position.w;
		vIsPerspective = float( isPerspectiveMatrix( projectionMatrix ) );
	#else
		if ( isPerspectiveMatrix( projectionMatrix ) ) {
			gl_Position.z = log2( max( EPSILON, gl_Position.w + 1.0 ) ) * logDepthBufFC - 1.0;
			gl_Position.z *= gl_Position.w;
		}
	#endif
#endif`,yA=`#ifdef USE_MAP
	vec4 sampledDiffuseColor = texture2D( map, vUv );
	#ifdef DECODE_VIDEO_TEXTURE
		sampledDiffuseColor = vec4( mix( pow( sampledDiffuseColor.rgb * 0.9478672986 + vec3( 0.0521327014 ), vec3( 2.4 ) ), sampledDiffuseColor.rgb * 0.0773993808, vec3( lessThanEqual( sampledDiffuseColor.rgb, vec3( 0.04045 ) ) ) ), sampledDiffuseColor.w );
	#endif
	diffuseColor *= sampledDiffuseColor;
#endif`,bA=`#ifdef USE_MAP
	uniform sampler2D map;
#endif`,xA=`#if defined( USE_MAP ) || defined( USE_ALPHAMAP )
	vec2 uv = ( uvTransform * vec3( gl_PointCoord.x, 1.0 - gl_PointCoord.y, 1 ) ).xy;
#endif
#ifdef USE_MAP
	diffuseColor *= texture2D( map, uv );
#endif
#ifdef USE_ALPHAMAP
	diffuseColor.a *= texture2D( alphaMap, uv ).g;
#endif`,SA=`#if defined( USE_MAP ) || defined( USE_ALPHAMAP )
	uniform mat3 uvTransform;
#endif
#ifdef USE_MAP
	uniform sampler2D map;
#endif
#ifdef USE_ALPHAMAP
	uniform sampler2D alphaMap;
#endif`,MA=`float metalnessFactor = metalness;
#ifdef USE_METALNESSMAP
	vec4 texelMetalness = texture2D( metalnessMap, vUv );
	metalnessFactor *= texelMetalness.b;
#endif`,AA=`#ifdef USE_METALNESSMAP
	uniform sampler2D metalnessMap;
#endif`,wA=`#if defined( USE_MORPHCOLORS ) && defined( MORPHTARGETS_TEXTURE )
	vColor *= morphTargetBaseInfluence;
	for ( int i = 0; i < MORPHTARGETS_COUNT; i ++ ) {
		#if defined( USE_COLOR_ALPHA )
			if ( morphTargetInfluences[ i ] != 0.0 ) vColor += getMorph( gl_VertexID, i, 2 ) * morphTargetInfluences[ i ];
		#elif defined( USE_COLOR )
			if ( morphTargetInfluences[ i ] != 0.0 ) vColor += getMorph( gl_VertexID, i, 2 ).rgb * morphTargetInfluences[ i ];
		#endif
	}
#endif`,CA=`#ifdef USE_MORPHNORMALS
	objectNormal *= morphTargetBaseInfluence;
	#ifdef MORPHTARGETS_TEXTURE
		for ( int i = 0; i < MORPHTARGETS_COUNT; i ++ ) {
			if ( morphTargetInfluences[ i ] != 0.0 ) objectNormal += getMorph( gl_VertexID, i, 1 ).xyz * morphTargetInfluences[ i ];
		}
	#else
		objectNormal += morphNormal0 * morphTargetInfluences[ 0 ];
		objectNormal += morphNormal1 * morphTargetInfluences[ 1 ];
		objectNormal += morphNormal2 * morphTargetInfluences[ 2 ];
		objectNormal += morphNormal3 * morphTargetInfluences[ 3 ];
	#endif
#endif`,TA=`#ifdef USE_MORPHTARGETS
	uniform float morphTargetBaseInfluence;
	#ifdef MORPHTARGETS_TEXTURE
		uniform float morphTargetInfluences[ MORPHTARGETS_COUNT ];
		uniform sampler2DArray morphTargetsTexture;
		uniform ivec2 morphTargetsTextureSize;
		vec4 getMorph( const in int vertexIndex, const in int morphTargetIndex, const in int offset ) {
			int texelIndex = vertexIndex * MORPHTARGETS_TEXTURE_STRIDE + offset;
			int y = texelIndex / morphTargetsTextureSize.x;
			int x = texelIndex - y * morphTargetsTextureSize.x;
			ivec3 morphUV = ivec3( x, y, morphTargetIndex );
			return texelFetch( morphTargetsTexture, morphUV, 0 );
		}
	#else
		#ifndef USE_MORPHNORMALS
			uniform float morphTargetInfluences[ 8 ];
		#else
			uniform float morphTargetInfluences[ 4 ];
		#endif
	#endif
#endif`,EA=`#ifdef USE_MORPHTARGETS
	transformed *= morphTargetBaseInfluence;
	#ifdef MORPHTARGETS_TEXTURE
		for ( int i = 0; i < MORPHTARGETS_COUNT; i ++ ) {
			if ( morphTargetInfluences[ i ] != 0.0 ) transformed += getMorph( gl_VertexID, i, 0 ).xyz * morphTargetInfluences[ i ];
		}
	#else
		transformed += morphTarget0 * morphTargetInfluences[ 0 ];
		transformed += morphTarget1 * morphTargetInfluences[ 1 ];
		transformed += morphTarget2 * morphTargetInfluences[ 2 ];
		transformed += morphTarget3 * morphTargetInfluences[ 3 ];
		#ifndef USE_MORPHNORMALS
			transformed += morphTarget4 * morphTargetInfluences[ 4 ];
			transformed += morphTarget5 * morphTargetInfluences[ 5 ];
			transformed += morphTarget6 * morphTargetInfluences[ 6 ];
			transformed += morphTarget7 * morphTargetInfluences[ 7 ];
		#endif
	#endif
#endif`,DA=`float faceDirection = gl_FrontFacing ? 1.0 : - 1.0;
#ifdef FLAT_SHADED
	vec3 fdx = dFdx( vViewPosition );
	vec3 fdy = dFdy( vViewPosition );
	vec3 normal = normalize( cross( fdx, fdy ) );
#else
	vec3 normal = normalize( vNormal );
	#ifdef DOUBLE_SIDED
		normal = normal * faceDirection;
	#endif
	#ifdef USE_TANGENT
		vec3 tangent = normalize( vTangent );
		vec3 bitangent = normalize( vBitangent );
		#ifdef DOUBLE_SIDED
			tangent = tangent * faceDirection;
			bitangent = bitangent * faceDirection;
		#endif
		#if defined( TANGENTSPACE_NORMALMAP ) || defined( USE_CLEARCOAT_NORMALMAP )
			mat3 vTBN = mat3( tangent, bitangent, normal );
		#endif
	#endif
#endif
vec3 geometryNormal = normal;`,IA=`#ifdef OBJECTSPACE_NORMALMAP
	normal = texture2D( normalMap, vUv ).xyz * 2.0 - 1.0;
	#ifdef FLIP_SIDED
		normal = - normal;
	#endif
	#ifdef DOUBLE_SIDED
		normal = normal * faceDirection;
	#endif
	normal = normalize( normalMatrix * normal );
#elif defined( TANGENTSPACE_NORMALMAP )
	vec3 mapN = texture2D( normalMap, vUv ).xyz * 2.0 - 1.0;
	mapN.xy *= normalScale;
	#ifdef USE_TANGENT
		normal = normalize( vTBN * mapN );
	#else
		normal = perturbNormal2Arb( - vViewPosition, normal, mapN, faceDirection );
	#endif
#elif defined( USE_BUMPMAP )
	normal = perturbNormalArb( - vViewPosition, normal, dHdxy_fwd(), faceDirection );
#endif`,RA=`#ifndef FLAT_SHADED
	varying vec3 vNormal;
	#ifdef USE_TANGENT
		varying vec3 vTangent;
		varying vec3 vBitangent;
	#endif
#endif`,OA=`#ifndef FLAT_SHADED
	varying vec3 vNormal;
	#ifdef USE_TANGENT
		varying vec3 vTangent;
		varying vec3 vBitangent;
	#endif
#endif`,PA=`#ifndef FLAT_SHADED
	vNormal = normalize( transformedNormal );
	#ifdef USE_TANGENT
		vTangent = normalize( transformedTangent );
		vBitangent = normalize( cross( vNormal, vTangent ) * tangent.w );
	#endif
#endif`,LA=`#ifdef USE_NORMALMAP
	uniform sampler2D normalMap;
	uniform vec2 normalScale;
#endif
#ifdef OBJECTSPACE_NORMALMAP
	uniform mat3 normalMatrix;
#endif
#if ! defined ( USE_TANGENT ) && ( defined ( TANGENTSPACE_NORMALMAP ) || defined ( USE_CLEARCOAT_NORMALMAP ) )
	vec3 perturbNormal2Arb( vec3 eye_pos, vec3 surf_norm, vec3 mapN, float faceDirection ) {
		vec3 q0 = dFdx( eye_pos.xyz );
		vec3 q1 = dFdy( eye_pos.xyz );
		vec2 st0 = dFdx( vUv.st );
		vec2 st1 = dFdy( vUv.st );
		vec3 N = surf_norm;
		vec3 q1perp = cross( q1, N );
		vec3 q0perp = cross( N, q0 );
		vec3 T = q1perp * st0.x + q0perp * st1.x;
		vec3 B = q1perp * st0.y + q0perp * st1.y;
		float det = max( dot( T, T ), dot( B, B ) );
		float scale = ( det == 0.0 ) ? 0.0 : faceDirection * inversesqrt( det );
		return normalize( T * ( mapN.x * scale ) + B * ( mapN.y * scale ) + N * mapN.z );
	}
#endif`,zA=`#ifdef USE_CLEARCOAT
	vec3 clearcoatNormal = geometryNormal;
#endif`,kA=`#ifdef USE_CLEARCOAT_NORMALMAP
	vec3 clearcoatMapN = texture2D( clearcoatNormalMap, vUv ).xyz * 2.0 - 1.0;
	clearcoatMapN.xy *= clearcoatNormalScale;
	#ifdef USE_TANGENT
		clearcoatNormal = normalize( vTBN * clearcoatMapN );
	#else
		clearcoatNormal = perturbNormal2Arb( - vViewPosition, clearcoatNormal, clearcoatMapN, faceDirection );
	#endif
#endif`,NA=`#ifdef USE_CLEARCOATMAP
	uniform sampler2D clearcoatMap;
#endif
#ifdef USE_CLEARCOAT_ROUGHNESSMAP
	uniform sampler2D clearcoatRoughnessMap;
#endif
#ifdef USE_CLEARCOAT_NORMALMAP
	uniform sampler2D clearcoatNormalMap;
	uniform vec2 clearcoatNormalScale;
#endif`,UA=`#ifdef USE_IRIDESCENCEMAP
	uniform sampler2D iridescenceMap;
#endif
#ifdef USE_IRIDESCENCE_THICKNESSMAP
	uniform sampler2D iridescenceThicknessMap;
#endif`,FA=`#ifdef OPAQUE
diffuseColor.a = 1.0;
#endif
#ifdef USE_TRANSMISSION
diffuseColor.a *= material.transmissionAlpha + 0.1;
#endif
gl_FragColor = vec4( outgoingLight, diffuseColor.a );`,BA=`vec3 packNormalToRGB( const in vec3 normal ) {
	return normalize( normal ) * 0.5 + 0.5;
}
vec3 unpackRGBToNormal( const in vec3 rgb ) {
	return 2.0 * rgb.xyz - 1.0;
}
const float PackUpscale = 256. / 255.;const float UnpackDownscale = 255. / 256.;
const vec3 PackFactors = vec3( 256. * 256. * 256., 256. * 256., 256. );
const vec4 UnpackFactors = UnpackDownscale / vec4( PackFactors, 1. );
const float ShiftRight8 = 1. / 256.;
vec4 packDepthToRGBA( const in float v ) {
	vec4 r = vec4( fract( v * PackFactors ), v );
	r.yzw -= r.xyz * ShiftRight8;	return r * PackUpscale;
}
float unpackRGBAToDepth( const in vec4 v ) {
	return dot( v, UnpackFactors );
}
vec4 pack2HalfToRGBA( vec2 v ) {
	vec4 r = vec4( v.x, fract( v.x * 255.0 ), v.y, fract( v.y * 255.0 ) );
	return vec4( r.x - r.y / 255.0, r.y, r.z - r.w / 255.0, r.w );
}
vec2 unpackRGBATo2Half( vec4 v ) {
	return vec2( v.x + ( v.y / 255.0 ), v.z + ( v.w / 255.0 ) );
}
float viewZToOrthographicDepth( const in float viewZ, const in float near, const in float far ) {
	return ( viewZ + near ) / ( near - far );
}
float orthographicDepthToViewZ( const in float linearClipZ, const in float near, const in float far ) {
	return linearClipZ * ( near - far ) - near;
}
float viewZToPerspectiveDepth( const in float viewZ, const in float near, const in float far ) {
	return ( ( near + viewZ ) * far ) / ( ( far - near ) * viewZ );
}
float perspectiveDepthToViewZ( const in float invClipZ, const in float near, const in float far ) {
	return ( near * far ) / ( ( far - near ) * invClipZ - far );
}`,GA=`#ifdef PREMULTIPLIED_ALPHA
	gl_FragColor.rgb *= gl_FragColor.a;
#endif`,VA=`vec4 mvPosition = vec4( transformed, 1.0 );
#ifdef USE_INSTANCING
	mvPosition = instanceMatrix * mvPosition;
#endif
mvPosition = modelViewMatrix * mvPosition;
gl_Position = projectionMatrix * mvPosition;`,jA=`#ifdef DITHERING
	gl_FragColor.rgb = dithering( gl_FragColor.rgb );
#endif`,WA=`#ifdef DITHERING
	vec3 dithering( vec3 color ) {
		float grid_position = rand( gl_FragCoord.xy );
		vec3 dither_shift_RGB = vec3( 0.25 / 255.0, -0.25 / 255.0, 0.25 / 255.0 );
		dither_shift_RGB = mix( 2.0 * dither_shift_RGB, -2.0 * dither_shift_RGB, grid_position );
		return color + dither_shift_RGB;
	}
#endif`,HA=`float roughnessFactor = roughness;
#ifdef USE_ROUGHNESSMAP
	vec4 texelRoughness = texture2D( roughnessMap, vUv );
	roughnessFactor *= texelRoughness.g;
#endif`,$A=`#ifdef USE_ROUGHNESSMAP
	uniform sampler2D roughnessMap;
#endif`,YA=`#if NUM_SPOT_LIGHT_COORDS > 0
  varying vec4 vSpotLightCoord[ NUM_SPOT_LIGHT_COORDS ];
#endif
#if NUM_SPOT_LIGHT_MAPS > 0
  uniform sampler2D spotLightMap[ NUM_SPOT_LIGHT_MAPS ];
#endif
#ifdef USE_SHADOWMAP
	#if NUM_DIR_LIGHT_SHADOWS > 0
		uniform sampler2D directionalShadowMap[ NUM_DIR_LIGHT_SHADOWS ];
		varying vec4 vDirectionalShadowCoord[ NUM_DIR_LIGHT_SHADOWS ];
		struct DirectionalLightShadow {
			float shadowBias;
			float shadowNormalBias;
			float shadowRadius;
			vec2 shadowMapSize;
		};
		uniform DirectionalLightShadow directionalLightShadows[ NUM_DIR_LIGHT_SHADOWS ];
	#endif
	#if NUM_SPOT_LIGHT_SHADOWS > 0
		uniform sampler2D spotShadowMap[ NUM_SPOT_LIGHT_SHADOWS ];
		struct SpotLightShadow {
			float shadowBias;
			float shadowNormalBias;
			float shadowRadius;
			vec2 shadowMapSize;
		};
		uniform SpotLightShadow spotLightShadows[ NUM_SPOT_LIGHT_SHADOWS ];
	#endif
	#if NUM_POINT_LIGHT_SHADOWS > 0
		uniform sampler2D pointShadowMap[ NUM_POINT_LIGHT_SHADOWS ];
		varying vec4 vPointShadowCoord[ NUM_POINT_LIGHT_SHADOWS ];
		struct PointLightShadow {
			float shadowBias;
			float shadowNormalBias;
			float shadowRadius;
			vec2 shadowMapSize;
			float shadowCameraNear;
			float shadowCameraFar;
		};
		uniform PointLightShadow pointLightShadows[ NUM_POINT_LIGHT_SHADOWS ];
	#endif
	float texture2DCompare( sampler2D depths, vec2 uv, float compare ) {
		return step( compare, unpackRGBAToDepth( texture2D( depths, uv ) ) );
	}
	vec2 texture2DDistribution( sampler2D shadow, vec2 uv ) {
		return unpackRGBATo2Half( texture2D( shadow, uv ) );
	}
	float VSMShadow (sampler2D shadow, vec2 uv, float compare ){
		float occlusion = 1.0;
		vec2 distribution = texture2DDistribution( shadow, uv );
		float hard_shadow = step( compare , distribution.x );
		if (hard_shadow != 1.0 ) {
			float distance = compare - distribution.x ;
			float variance = max( 0.00000, distribution.y * distribution.y );
			float softness_probability = variance / (variance + distance * distance );			softness_probability = clamp( ( softness_probability - 0.3 ) / ( 0.95 - 0.3 ), 0.0, 1.0 );			occlusion = clamp( max( hard_shadow, softness_probability ), 0.0, 1.0 );
		}
		return occlusion;
	}
	float getShadow( sampler2D shadowMap, vec2 shadowMapSize, float shadowBias, float shadowRadius, vec4 shadowCoord ) {
		float shadow = 1.0;
		shadowCoord.xyz /= shadowCoord.w;
		shadowCoord.z += shadowBias;
		bvec4 inFrustumVec = bvec4 ( shadowCoord.x >= 0.0, shadowCoord.x <= 1.0, shadowCoord.y >= 0.0, shadowCoord.y <= 1.0 );
		bool inFrustum = all( inFrustumVec );
		bvec2 frustumTestVec = bvec2( inFrustum, shadowCoord.z <= 1.0 );
		bool frustumTest = all( frustumTestVec );
		if ( frustumTest ) {
		#if defined( SHADOWMAP_TYPE_PCF )
			vec2 texelSize = vec2( 1.0 ) / shadowMapSize;
			float dx0 = - texelSize.x * shadowRadius;
			float dy0 = - texelSize.y * shadowRadius;
			float dx1 = + texelSize.x * shadowRadius;
			float dy1 = + texelSize.y * shadowRadius;
			float dx2 = dx0 / 2.0;
			float dy2 = dy0 / 2.0;
			float dx3 = dx1 / 2.0;
			float dy3 = dy1 / 2.0;
			shadow = (
				texture2DCompare( shadowMap, shadowCoord.xy + vec2( dx0, dy0 ), shadowCoord.z ) +
				texture2DCompare( shadowMap, shadowCoord.xy + vec2( 0.0, dy0 ), shadowCoord.z ) +
				texture2DCompare( shadowMap, shadowCoord.xy + vec2( dx1, dy0 ), shadowCoord.z ) +
				texture2DCompare( shadowMap, shadowCoord.xy + vec2( dx2, dy2 ), shadowCoord.z ) +
				texture2DCompare( shadowMap, shadowCoord.xy + vec2( 0.0, dy2 ), shadowCoord.z ) +
				texture2DCompare( shadowMap, shadowCoord.xy + vec2( dx3, dy2 ), shadowCoord.z ) +
				texture2DCompare( shadowMap, shadowCoord.xy + vec2( dx0, 0.0 ), shadowCoord.z ) +
				texture2DCompare( shadowMap, shadowCoord.xy + vec2( dx2, 0.0 ), shadowCoord.z ) +
				texture2DCompare( shadowMap, shadowCoord.xy, shadowCoord.z ) +
				texture2DCompare( shadowMap, shadowCoord.xy + vec2( dx3, 0.0 ), shadowCoord.z ) +
				texture2DCompare( shadowMap, shadowCoord.xy + vec2( dx1, 0.0 ), shadowCoord.z ) +
				texture2DCompare( shadowMap, shadowCoord.xy + vec2( dx2, dy3 ), shadowCoord.z ) +
				texture2DCompare( shadowMap, shadowCoord.xy + vec2( 0.0, dy3 ), shadowCoord.z ) +
				texture2DCompare( shadowMap, shadowCoord.xy + vec2( dx3, dy3 ), shadowCoord.z ) +
				texture2DCompare( shadowMap, shadowCoord.xy + vec2( dx0, dy1 ), shadowCoord.z ) +
				texture2DCompare( shadowMap, shadowCoord.xy + vec2( 0.0, dy1 ), shadowCoord.z ) +
				texture2DCompare( shadowMap, shadowCoord.xy + vec2( dx1, dy1 ), shadowCoord.z )
			) * ( 1.0 / 17.0 );
		#elif defined( SHADOWMAP_TYPE_PCF_SOFT )
			vec2 texelSize = vec2( 1.0 ) / shadowMapSize;
			float dx = texelSize.x;
			float dy = texelSize.y;
			vec2 uv = shadowCoord.xy;
			vec2 f = fract( uv * shadowMapSize + 0.5 );
			uv -= f * texelSize;
			shadow = (
				texture2DCompare( shadowMap, uv, shadowCoord.z ) +
				texture2DCompare( shadowMap, uv + vec2( dx, 0.0 ), shadowCoord.z ) +
				texture2DCompare( shadowMap, uv + vec2( 0.0, dy ), shadowCoord.z ) +
				texture2DCompare( shadowMap, uv + texelSize, shadowCoord.z ) +
				mix( texture2DCompare( shadowMap, uv + vec2( -dx, 0.0 ), shadowCoord.z ),
					 texture2DCompare( shadowMap, uv + vec2( 2.0 * dx, 0.0 ), shadowCoord.z ),
					 f.x ) +
				mix( texture2DCompare( shadowMap, uv + vec2( -dx, dy ), shadowCoord.z ),
					 texture2DCompare( shadowMap, uv + vec2( 2.0 * dx, dy ), shadowCoord.z ),
					 f.x ) +
				mix( texture2DCompare( shadowMap, uv + vec2( 0.0, -dy ), shadowCoord.z ),
					 texture2DCompare( shadowMap, uv + vec2( 0.0, 2.0 * dy ), shadowCoord.z ),
					 f.y ) +
				mix( texture2DCompare( shadowMap, uv + vec2( dx, -dy ), shadowCoord.z ),
					 texture2DCompare( shadowMap, uv + vec2( dx, 2.0 * dy ), shadowCoord.z ),
					 f.y ) +
				mix( mix( texture2DCompare( shadowMap, uv + vec2( -dx, -dy ), shadowCoord.z ),
						  texture2DCompare( shadowMap, uv + vec2( 2.0 * dx, -dy ), shadowCoord.z ),
						  f.x ),
					 mix( texture2DCompare( shadowMap, uv + vec2( -dx, 2.0 * dy ), shadowCoord.z ),
						  texture2DCompare( shadowMap, uv + vec2( 2.0 * dx, 2.0 * dy ), shadowCoord.z ),
						  f.x ),
					 f.y )
			) * ( 1.0 / 9.0 );
		#elif defined( SHADOWMAP_TYPE_VSM )
			shadow = VSMShadow( shadowMap, shadowCoord.xy, shadowCoord.z );
		#else
			shadow = texture2DCompare( shadowMap, shadowCoord.xy, shadowCoord.z );
		#endif
		}
		return shadow;
	}
	vec2 cubeToUV( vec3 v, float texelSizeY ) {
		vec3 absV = abs( v );
		float scaleToCube = 1.0 / max( absV.x, max( absV.y, absV.z ) );
		absV *= scaleToCube;
		v *= scaleToCube * ( 1.0 - 2.0 * texelSizeY );
		vec2 planar = v.xy;
		float almostATexel = 1.5 * texelSizeY;
		float almostOne = 1.0 - almostATexel;
		if ( absV.z >= almostOne ) {
			if ( v.z > 0.0 )
				planar.x = 4.0 - v.x;
		} else if ( absV.x >= almostOne ) {
			float signX = sign( v.x );
			planar.x = v.z * signX + 2.0 * signX;
		} else if ( absV.y >= almostOne ) {
			float signY = sign( v.y );
			planar.x = v.x + 2.0 * signY + 2.0;
			planar.y = v.z * signY - 2.0;
		}
		return vec2( 0.125, 0.25 ) * planar + vec2( 0.375, 0.75 );
	}
	float getPointShadow( sampler2D shadowMap, vec2 shadowMapSize, float shadowBias, float shadowRadius, vec4 shadowCoord, float shadowCameraNear, float shadowCameraFar ) {
		vec2 texelSize = vec2( 1.0 ) / ( shadowMapSize * vec2( 4.0, 2.0 ) );
		vec3 lightToPosition = shadowCoord.xyz;
		float dp = ( length( lightToPosition ) - shadowCameraNear ) / ( shadowCameraFar - shadowCameraNear );		dp += shadowBias;
		vec3 bd3D = normalize( lightToPosition );
		#if defined( SHADOWMAP_TYPE_PCF ) || defined( SHADOWMAP_TYPE_PCF_SOFT ) || defined( SHADOWMAP_TYPE_VSM )
			vec2 offset = vec2( - 1, 1 ) * shadowRadius * texelSize.y;
			return (
				texture2DCompare( shadowMap, cubeToUV( bd3D + offset.xyy, texelSize.y ), dp ) +
				texture2DCompare( shadowMap, cubeToUV( bd3D + offset.yyy, texelSize.y ), dp ) +
				texture2DCompare( shadowMap, cubeToUV( bd3D + offset.xyx, texelSize.y ), dp ) +
				texture2DCompare( shadowMap, cubeToUV( bd3D + offset.yyx, texelSize.y ), dp ) +
				texture2DCompare( shadowMap, cubeToUV( bd3D, texelSize.y ), dp ) +
				texture2DCompare( shadowMap, cubeToUV( bd3D + offset.xxy, texelSize.y ), dp ) +
				texture2DCompare( shadowMap, cubeToUV( bd3D + offset.yxy, texelSize.y ), dp ) +
				texture2DCompare( shadowMap, cubeToUV( bd3D + offset.xxx, texelSize.y ), dp ) +
				texture2DCompare( shadowMap, cubeToUV( bd3D + offset.yxx, texelSize.y ), dp )
			) * ( 1.0 / 9.0 );
		#else
			return texture2DCompare( shadowMap, cubeToUV( bd3D, texelSize.y ), dp );
		#endif
	}
#endif`,JA=`#if NUM_SPOT_LIGHT_COORDS > 0
  uniform mat4 spotLightMatrix[ NUM_SPOT_LIGHT_COORDS ];
  varying vec4 vSpotLightCoord[ NUM_SPOT_LIGHT_COORDS ];
#endif
#ifdef USE_SHADOWMAP
	#if NUM_DIR_LIGHT_SHADOWS > 0
		uniform mat4 directionalShadowMatrix[ NUM_DIR_LIGHT_SHADOWS ];
		varying vec4 vDirectionalShadowCoord[ NUM_DIR_LIGHT_SHADOWS ];
		struct DirectionalLightShadow {
			float shadowBias;
			float shadowNormalBias;
			float shadowRadius;
			vec2 shadowMapSize;
		};
		uniform DirectionalLightShadow directionalLightShadows[ NUM_DIR_LIGHT_SHADOWS ];
	#endif
	#if NUM_SPOT_LIGHT_SHADOWS > 0
		struct SpotLightShadow {
			float shadowBias;
			float shadowNormalBias;
			float shadowRadius;
			vec2 shadowMapSize;
		};
		uniform SpotLightShadow spotLightShadows[ NUM_SPOT_LIGHT_SHADOWS ];
	#endif
	#if NUM_POINT_LIGHT_SHADOWS > 0
		uniform mat4 pointShadowMatrix[ NUM_POINT_LIGHT_SHADOWS ];
		varying vec4 vPointShadowCoord[ NUM_POINT_LIGHT_SHADOWS ];
		struct PointLightShadow {
			float shadowBias;
			float shadowNormalBias;
			float shadowRadius;
			vec2 shadowMapSize;
			float shadowCameraNear;
			float shadowCameraFar;
		};
		uniform PointLightShadow pointLightShadows[ NUM_POINT_LIGHT_SHADOWS ];
	#endif
#endif`,ZA=`#if defined( USE_SHADOWMAP ) || ( NUM_SPOT_LIGHT_COORDS > 0 )
	#if NUM_DIR_LIGHT_SHADOWS > 0 || NUM_SPOT_LIGHT_COORDS > 0 || NUM_POINT_LIGHT_SHADOWS > 0
		vec3 shadowWorldNormal = inverseTransformDirection( transformedNormal, viewMatrix );
		vec4 shadowWorldPosition;
	#endif
	#if NUM_DIR_LIGHT_SHADOWS > 0
	#pragma unroll_loop_start
	for ( int i = 0; i < NUM_DIR_LIGHT_SHADOWS; i ++ ) {
		shadowWorldPosition = worldPosition + vec4( shadowWorldNormal * directionalLightShadows[ i ].shadowNormalBias, 0 );
		vDirectionalShadowCoord[ i ] = directionalShadowMatrix[ i ] * shadowWorldPosition;
	}
	#pragma unroll_loop_end
	#endif
	#if NUM_SPOT_LIGHT_COORDS > 0
	#pragma unroll_loop_start
	for ( int i = 0; i < NUM_SPOT_LIGHT_COORDS; i ++ ) {
		shadowWorldPosition = worldPosition;
		#if ( defined( USE_SHADOWMAP ) && UNROLLED_LOOP_INDEX < NUM_SPOT_LIGHT_SHADOWS )
			shadowWorldPosition.xyz += shadowWorldNormal * spotLightShadows[ i ].shadowNormalBias;
		#endif
		vSpotLightCoord[ i ] = spotLightMatrix[ i ] * shadowWorldPosition;
	}
	#pragma unroll_loop_end
	#endif
	#if NUM_POINT_LIGHT_SHADOWS > 0
	#pragma unroll_loop_start
	for ( int i = 0; i < NUM_POINT_LIGHT_SHADOWS; i ++ ) {
		shadowWorldPosition = worldPosition + vec4( shadowWorldNormal * pointLightShadows[ i ].shadowNormalBias, 0 );
		vPointShadowCoord[ i ] = pointShadowMatrix[ i ] * shadowWorldPosition;
	}
	#pragma unroll_loop_end
	#endif
#endif`,qA=`float getShadowMask() {
	float shadow = 1.0;
	#ifdef USE_SHADOWMAP
	#if NUM_DIR_LIGHT_SHADOWS > 0
	DirectionalLightShadow directionalLight;
	#pragma unroll_loop_start
	for ( int i = 0; i < NUM_DIR_LIGHT_SHADOWS; i ++ ) {
		directionalLight = directionalLightShadows[ i ];
		shadow *= receiveShadow ? getShadow( directionalShadowMap[ i ], directionalLight.shadowMapSize, directionalLight.shadowBias, directionalLight.shadowRadius, vDirectionalShadowCoord[ i ] ) : 1.0;
	}
	#pragma unroll_loop_end
	#endif
	#if NUM_SPOT_LIGHT_SHADOWS > 0
	SpotLightShadow spotLight;
	#pragma unroll_loop_start
	for ( int i = 0; i < NUM_SPOT_LIGHT_SHADOWS; i ++ ) {
		spotLight = spotLightShadows[ i ];
		shadow *= receiveShadow ? getShadow( spotShadowMap[ i ], spotLight.shadowMapSize, spotLight.shadowBias, spotLight.shadowRadius, vSpotLightCoord[ i ] ) : 1.0;
	}
	#pragma unroll_loop_end
	#endif
	#if NUM_POINT_LIGHT_SHADOWS > 0
	PointLightShadow pointLight;
	#pragma unroll_loop_start
	for ( int i = 0; i < NUM_POINT_LIGHT_SHADOWS; i ++ ) {
		pointLight = pointLightShadows[ i ];
		shadow *= receiveShadow ? getPointShadow( pointShadowMap[ i ], pointLight.shadowMapSize, pointLight.shadowBias, pointLight.shadowRadius, vPointShadowCoord[ i ], pointLight.shadowCameraNear, pointLight.shadowCameraFar ) : 1.0;
	}
	#pragma unroll_loop_end
	#endif
	#endif
	return shadow;
}`,XA=`#ifdef USE_SKINNING
	mat4 boneMatX = getBoneMatrix( skinIndex.x );
	mat4 boneMatY = getBoneMatrix( skinIndex.y );
	mat4 boneMatZ = getBoneMatrix( skinIndex.z );
	mat4 boneMatW = getBoneMatrix( skinIndex.w );
#endif`,QA=`#ifdef USE_SKINNING
	uniform mat4 bindMatrix;
	uniform mat4 bindMatrixInverse;
	uniform highp sampler2D boneTexture;
	uniform int boneTextureSize;
	mat4 getBoneMatrix( const in float i ) {
		float j = i * 4.0;
		float x = mod( j, float( boneTextureSize ) );
		float y = floor( j / float( boneTextureSize ) );
		float dx = 1.0 / float( boneTextureSize );
		float dy = 1.0 / float( boneTextureSize );
		y = dy * ( y + 0.5 );
		vec4 v1 = texture2D( boneTexture, vec2( dx * ( x + 0.5 ), y ) );
		vec4 v2 = texture2D( boneTexture, vec2( dx * ( x + 1.5 ), y ) );
		vec4 v3 = texture2D( boneTexture, vec2( dx * ( x + 2.5 ), y ) );
		vec4 v4 = texture2D( boneTexture, vec2( dx * ( x + 3.5 ), y ) );
		mat4 bone = mat4( v1, v2, v3, v4 );
		return bone;
	}
#endif`,KA=`#ifdef USE_SKINNING
	vec4 skinVertex = bindMatrix * vec4( transformed, 1.0 );
	vec4 skinned = vec4( 0.0 );
	skinned += boneMatX * skinVertex * skinWeight.x;
	skinned += boneMatY * skinVertex * skinWeight.y;
	skinned += boneMatZ * skinVertex * skinWeight.z;
	skinned += boneMatW * skinVertex * skinWeight.w;
	transformed = ( bindMatrixInverse * skinned ).xyz;
#endif`,ew=`#ifdef USE_SKINNING
	mat4 skinMatrix = mat4( 0.0 );
	skinMatrix += skinWeight.x * boneMatX;
	skinMatrix += skinWeight.y * boneMatY;
	skinMatrix += skinWeight.z * boneMatZ;
	skinMatrix += skinWeight.w * boneMatW;
	skinMatrix = bindMatrixInverse * skinMatrix * bindMatrix;
	objectNormal = vec4( skinMatrix * vec4( objectNormal, 0.0 ) ).xyz;
	#ifdef USE_TANGENT
		objectTangent = vec4( skinMatrix * vec4( objectTangent, 0.0 ) ).xyz;
	#endif
#endif`,tw=`float specularStrength;
#ifdef USE_SPECULARMAP
	vec4 texelSpecular = texture2D( specularMap, vUv );
	specularStrength = texelSpecular.r;
#else
	specularStrength = 1.0;
#endif`,nw=`#ifdef USE_SPECULARMAP
	uniform sampler2D specularMap;
#endif`,iw=`#if defined( TONE_MAPPING )
	gl_FragColor.rgb = toneMapping( gl_FragColor.rgb );
#endif`,ow=`#ifndef saturate
#define saturate( a ) clamp( a, 0.0, 1.0 )
#endif
uniform float toneMappingExposure;
vec3 LinearToneMapping( vec3 color ) {
	return toneMappingExposure * color;
}
vec3 ReinhardToneMapping( vec3 color ) {
	color *= toneMappingExposure;
	return saturate( color / ( vec3( 1.0 ) + color ) );
}
vec3 OptimizedCineonToneMapping( vec3 color ) {
	color *= toneMappingExposure;
	color = max( vec3( 0.0 ), color - 0.004 );
	return pow( ( color * ( 6.2 * color + 0.5 ) ) / ( color * ( 6.2 * color + 1.7 ) + 0.06 ), vec3( 2.2 ) );
}
vec3 RRTAndODTFit( vec3 v ) {
	vec3 a = v * ( v + 0.0245786 ) - 0.000090537;
	vec3 b = v * ( 0.983729 * v + 0.4329510 ) + 0.238081;
	return a / b;
}
vec3 ACESFilmicToneMapping( vec3 color ) {
	const mat3 ACESInputMat = mat3(
		vec3( 0.59719, 0.07600, 0.02840 ),		vec3( 0.35458, 0.90834, 0.13383 ),
		vec3( 0.04823, 0.01566, 0.83777 )
	);
	const mat3 ACESOutputMat = mat3(
		vec3(  1.60475, -0.10208, -0.00327 ),		vec3( -0.53108,  1.10813, -0.07276 ),
		vec3( -0.07367, -0.00605,  1.07602 )
	);
	color *= toneMappingExposure / 0.6;
	color = ACESInputMat * color;
	color = RRTAndODTFit( color );
	color = ACESOutputMat * color;
	return saturate( color );
}
vec3 CustomToneMapping( vec3 color ) { return color; }`,aw=`#ifdef USE_TRANSMISSION
	material.transmission = transmission;
	material.transmissionAlpha = 1.0;
	material.thickness = thickness;
	material.attenuationDistance = attenuationDistance;
	material.attenuationColor = attenuationColor;
	#ifdef USE_TRANSMISSIONMAP
		material.transmission *= texture2D( transmissionMap, vUv ).r;
	#endif
	#ifdef USE_THICKNESSMAP
		material.thickness *= texture2D( thicknessMap, vUv ).g;
	#endif
	vec3 pos = vWorldPosition;
	vec3 v = normalize( cameraPosition - pos );
	vec3 n = inverseTransformDirection( normal, viewMatrix );
	vec4 transmission = getIBLVolumeRefraction(
		n, v, material.roughness, material.diffuseColor, material.specularColor, material.specularF90,
		pos, modelMatrix, viewMatrix, projectionMatrix, material.ior, material.thickness,
		material.attenuationColor, material.attenuationDistance );
	material.transmissionAlpha = mix( material.transmissionAlpha, transmission.a, material.transmission );
	totalDiffuse = mix( totalDiffuse, transmission.rgb, material.transmission );
#endif`,rw=`#ifdef USE_TRANSMISSION
	uniform float transmission;
	uniform float thickness;
	uniform float attenuationDistance;
	uniform vec3 attenuationColor;
	#ifdef USE_TRANSMISSIONMAP
		uniform sampler2D transmissionMap;
	#endif
	#ifdef USE_THICKNESSMAP
		uniform sampler2D thicknessMap;
	#endif
	uniform vec2 transmissionSamplerSize;
	uniform sampler2D transmissionSamplerMap;
	uniform mat4 modelMatrix;
	uniform mat4 projectionMatrix;
	varying vec3 vWorldPosition;
	vec3 getVolumeTransmissionRay( const in vec3 n, const in vec3 v, const in float thickness, const in float ior, const in mat4 modelMatrix ) {
		vec3 refractionVector = refract( - v, normalize( n ), 1.0 / ior );
		vec3 modelScale;
		modelScale.x = length( vec3( modelMatrix[ 0 ].xyz ) );
		modelScale.y = length( vec3( modelMatrix[ 1 ].xyz ) );
		modelScale.z = length( vec3( modelMatrix[ 2 ].xyz ) );
		return normalize( refractionVector ) * thickness * modelScale;
	}
	float applyIorToRoughness( const in float roughness, const in float ior ) {
		return roughness * clamp( ior * 2.0 - 2.0, 0.0, 1.0 );
	}
	vec4 getTransmissionSample( const in vec2 fragCoord, const in float roughness, const in float ior ) {
		float framebufferLod = log2( transmissionSamplerSize.x ) * applyIorToRoughness( roughness, ior );
		#ifdef texture2DLodEXT
			return texture2DLodEXT( transmissionSamplerMap, fragCoord.xy, framebufferLod );
		#else
			return texture2D( transmissionSamplerMap, fragCoord.xy, framebufferLod );
		#endif
	}
	vec3 applyVolumeAttenuation( const in vec3 radiance, const in float transmissionDistance, const in vec3 attenuationColor, const in float attenuationDistance ) {
		if ( isinf( attenuationDistance ) ) {
			return radiance;
		} else {
			vec3 attenuationCoefficient = -log( attenuationColor ) / attenuationDistance;
			vec3 transmittance = exp( - attenuationCoefficient * transmissionDistance );			return transmittance * radiance;
		}
	}
	vec4 getIBLVolumeRefraction( const in vec3 n, const in vec3 v, const in float roughness, const in vec3 diffuseColor,
		const in vec3 specularColor, const in float specularF90, const in vec3 position, const in mat4 modelMatrix,
		const in mat4 viewMatrix, const in mat4 projMatrix, const in float ior, const in float thickness,
		const in vec3 attenuationColor, const in float attenuationDistance ) {
		vec3 transmissionRay = getVolumeTransmissionRay( n, v, thickness, ior, modelMatrix );
		vec3 refractedRayExit = position + transmissionRay;
		vec4 ndcPos = projMatrix * viewMatrix * vec4( refractedRayExit, 1.0 );
		vec2 refractionCoords = ndcPos.xy / ndcPos.w;
		refractionCoords += 1.0;
		refractionCoords /= 2.0;
		vec4 transmittedLight = getTransmissionSample( refractionCoords, roughness, ior );
		vec3 attenuatedColor = applyVolumeAttenuation( transmittedLight.rgb, length( transmissionRay ), attenuationColor, attenuationDistance );
		vec3 F = EnvironmentBRDF( n, v, specularColor, specularF90, roughness );
		return vec4( ( 1.0 - F ) * attenuatedColor * diffuseColor, transmittedLight.a );
	}
#endif`,sw=`#if ( defined( USE_UV ) && ! defined( UVS_VERTEX_ONLY ) )
	varying vec2 vUv;
#endif`,lw=`#ifdef USE_UV
	#ifdef UVS_VERTEX_ONLY
		vec2 vUv;
	#else
		varying vec2 vUv;
	#endif
	uniform mat3 uvTransform;
#endif`,uw=`#ifdef USE_UV
	vUv = ( uvTransform * vec3( uv, 1 ) ).xy;
#endif`,cw=`#if defined( USE_LIGHTMAP ) || defined( USE_AOMAP )
	varying vec2 vUv2;
#endif`,dw=`#if defined( USE_LIGHTMAP ) || defined( USE_AOMAP )
	attribute vec2 uv2;
	varying vec2 vUv2;
	uniform mat3 uv2Transform;
#endif`,fw=`#if defined( USE_LIGHTMAP ) || defined( USE_AOMAP )
	vUv2 = ( uv2Transform * vec3( uv2, 1 ) ).xy;
#endif`,pw=`#if defined( USE_ENVMAP ) || defined( DISTANCE ) || defined ( USE_SHADOWMAP ) || defined ( USE_TRANSMISSION ) || NUM_SPOT_LIGHT_COORDS > 0
	vec4 worldPosition = vec4( transformed, 1.0 );
	#ifdef USE_INSTANCING
		worldPosition = instanceMatrix * worldPosition;
	#endif
	worldPosition = modelMatrix * worldPosition;
#endif`;const hw=`varying vec2 vUv;
uniform mat3 uvTransform;
void main() {
	vUv = ( uvTransform * vec3( uv, 1 ) ).xy;
	gl_Position = vec4( position.xy, 1.0, 1.0 );
}`,mw=`uniform sampler2D t2D;
varying vec2 vUv;
void main() {
	gl_FragColor = texture2D( t2D, vUv );
	#ifdef DECODE_VIDEO_TEXTURE
		gl_FragColor = vec4( mix( pow( gl_FragColor.rgb * 0.9478672986 + vec3( 0.0521327014 ), vec3( 2.4 ) ), gl_FragColor.rgb * 0.0773993808, vec3( lessThanEqual( gl_FragColor.rgb, vec3( 0.04045 ) ) ) ), gl_FragColor.w );
	#endif
	#include <tonemapping_fragment>
	#include <encodings_fragment>
}`,gw=`varying vec3 vWorldDirection;
#include <common>
void main() {
	vWorldDirection = transformDirection( position, modelMatrix );
	#include <begin_vertex>
	#include <project_vertex>
	gl_Position.z = gl_Position.w;
}`,vw=`#include <envmap_common_pars_fragment>
uniform float opacity;
varying vec3 vWorldDirection;
#include <cube_uv_reflection_fragment>
void main() {
	vec3 vReflect = vWorldDirection;
	#include <envmap_fragment>
	gl_FragColor = envColor;
	gl_FragColor.a *= opacity;
	#include <tonemapping_fragment>
	#include <encodings_fragment>
}`,_w=`#include <common>
#include <uv_pars_vertex>
#include <displacementmap_pars_vertex>
#include <morphtarget_pars_vertex>
#include <skinning_pars_vertex>
#include <logdepthbuf_pars_vertex>
#include <clipping_planes_pars_vertex>
varying vec2 vHighPrecisionZW;
void main() {
	#include <uv_vertex>
	#include <skinbase_vertex>
	#ifdef USE_DISPLACEMENTMAP
		#include <beginnormal_vertex>
		#include <morphnormal_vertex>
		#include <skinnormal_vertex>
	#endif
	#include <begin_vertex>
	#include <morphtarget_vertex>
	#include <skinning_vertex>
	#include <displacementmap_vertex>
	#include <project_vertex>
	#include <logdepthbuf_vertex>
	#include <clipping_planes_vertex>
	vHighPrecisionZW = gl_Position.zw;
}`,yw=`#if DEPTH_PACKING == 3200
	uniform float opacity;
#endif
#include <common>
#include <packing>
#include <uv_pars_fragment>
#include <map_pars_fragment>
#include <alphamap_pars_fragment>
#include <alphatest_pars_fragment>
#include <logdepthbuf_pars_fragment>
#include <clipping_planes_pars_fragment>
varying vec2 vHighPrecisionZW;
void main() {
	#include <clipping_planes_fragment>
	vec4 diffuseColor = vec4( 1.0 );
	#if DEPTH_PACKING == 3200
		diffuseColor.a = opacity;
	#endif
	#include <map_fragment>
	#include <alphamap_fragment>
	#include <alphatest_fragment>
	#include <logdepthbuf_fragment>
	float fragCoordZ = 0.5 * vHighPrecisionZW[0] / vHighPrecisionZW[1] + 0.5;
	#if DEPTH_PACKING == 3200
		gl_FragColor = vec4( vec3( 1.0 - fragCoordZ ), opacity );
	#elif DEPTH_PACKING == 3201
		gl_FragColor = packDepthToRGBA( fragCoordZ );
	#endif
}`,bw=`#define DISTANCE
varying vec3 vWorldPosition;
#include <common>
#include <uv_pars_vertex>
#include <displacementmap_pars_vertex>
#include <morphtarget_pars_vertex>
#include <skinning_pars_vertex>
#include <clipping_planes_pars_vertex>
void main() {
	#include <uv_vertex>
	#include <skinbase_vertex>
	#ifdef USE_DISPLACEMENTMAP
		#include <beginnormal_vertex>
		#include <morphnormal_vertex>
		#include <skinnormal_vertex>
	#endif
	#include <begin_vertex>
	#include <morphtarget_vertex>
	#include <skinning_vertex>
	#include <displacementmap_vertex>
	#include <project_vertex>
	#include <worldpos_vertex>
	#include <clipping_planes_vertex>
	vWorldPosition = worldPosition.xyz;
}`,xw=`#define DISTANCE
uniform vec3 referencePosition;
uniform float nearDistance;
uniform float farDistance;
varying vec3 vWorldPosition;
#include <common>
#include <packing>
#include <uv_pars_fragment>
#include <map_pars_fragment>
#include <alphamap_pars_fragment>
#include <alphatest_pars_fragment>
#include <clipping_planes_pars_fragment>
void main () {
	#include <clipping_planes_fragment>
	vec4 diffuseColor = vec4( 1.0 );
	#include <map_fragment>
	#include <alphamap_fragment>
	#include <alphatest_fragment>
	float dist = length( vWorldPosition - referencePosition );
	dist = ( dist - nearDistance ) / ( farDistance - nearDistance );
	dist = saturate( dist );
	gl_FragColor = packDepthToRGBA( dist );
}`,Sw=`varying vec3 vWorldDirection;
#include <common>
void main() {
	vWorldDirection = transformDirection( position, modelMatrix );
	#include <begin_vertex>
	#include <project_vertex>
}`,Mw=`uniform sampler2D tEquirect;
varying vec3 vWorldDirection;
#include <common>
void main() {
	vec3 direction = normalize( vWorldDirection );
	vec2 sampleUV = equirectUv( direction );
	gl_FragColor = texture2D( tEquirect, sampleUV );
	#include <tonemapping_fragment>
	#include <encodings_fragment>
}`,Aw=`uniform float scale;
attribute float lineDistance;
varying float vLineDistance;
#include <common>
#include <color_pars_vertex>
#include <fog_pars_vertex>
#include <morphtarget_pars_vertex>
#include <logdepthbuf_pars_vertex>
#include <clipping_planes_pars_vertex>
void main() {
	vLineDistance = scale * lineDistance;
	#include <color_vertex>
	#include <morphcolor_vertex>
	#include <begin_vertex>
	#include <morphtarget_vertex>
	#include <project_vertex>
	#include <logdepthbuf_vertex>
	#include <clipping_planes_vertex>
	#include <fog_vertex>
}`,ww=`uniform vec3 diffuse;
uniform float opacity;
uniform float dashSize;
uniform float totalSize;
varying float vLineDistance;
#include <common>
#include <color_pars_fragment>
#include <fog_pars_fragment>
#include <logdepthbuf_pars_fragment>
#include <clipping_planes_pars_fragment>
void main() {
	#include <clipping_planes_fragment>
	if ( mod( vLineDistance, totalSize ) > dashSize ) {
		discard;
	}
	vec3 outgoingLight = vec3( 0.0 );
	vec4 diffuseColor = vec4( diffuse, opacity );
	#include <logdepthbuf_fragment>
	#include <color_fragment>
	outgoingLight = diffuseColor.rgb;
	#include <output_fragment>
	#include <tonemapping_fragment>
	#include <encodings_fragment>
	#include <fog_fragment>
	#include <premultiplied_alpha_fragment>
}`,Cw=`#include <common>
#include <uv_pars_vertex>
#include <uv2_pars_vertex>
#include <envmap_pars_vertex>
#include <color_pars_vertex>
#include <fog_pars_vertex>
#include <morphtarget_pars_vertex>
#include <skinning_pars_vertex>
#include <logdepthbuf_pars_vertex>
#include <clipping_planes_pars_vertex>
void main() {
	#include <uv_vertex>
	#include <uv2_vertex>
	#include <color_vertex>
	#include <morphcolor_vertex>
	#if defined ( USE_ENVMAP ) || defined ( USE_SKINNING )
		#include <beginnormal_vertex>
		#include <morphnormal_vertex>
		#include <skinbase_vertex>
		#include <skinnormal_vertex>
		#include <defaultnormal_vertex>
	#endif
	#include <begin_vertex>
	#include <morphtarget_vertex>
	#include <skinning_vertex>
	#include <project_vertex>
	#include <logdepthbuf_vertex>
	#include <clipping_planes_vertex>
	#include <worldpos_vertex>
	#include <envmap_vertex>
	#include <fog_vertex>
}`,Tw=`uniform vec3 diffuse;
uniform float opacity;
#ifndef FLAT_SHADED
	varying vec3 vNormal;
#endif
#include <common>
#include <dithering_pars_fragment>
#include <color_pars_fragment>
#include <uv_pars_fragment>
#include <uv2_pars_fragment>
#include <map_pars_fragment>
#include <alphamap_pars_fragment>
#include <alphatest_pars_fragment>
#include <aomap_pars_fragment>
#include <lightmap_pars_fragment>
#include <envmap_common_pars_fragment>
#include <envmap_pars_fragment>
#include <fog_pars_fragment>
#include <specularmap_pars_fragment>
#include <logdepthbuf_pars_fragment>
#include <clipping_planes_pars_fragment>
void main() {
	#include <clipping_planes_fragment>
	vec4 diffuseColor = vec4( diffuse, opacity );
	#include <logdepthbuf_fragment>
	#include <map_fragment>
	#include <color_fragment>
	#include <alphamap_fragment>
	#include <alphatest_fragment>
	#include <specularmap_fragment>
	ReflectedLight reflectedLight = ReflectedLight( vec3( 0.0 ), vec3( 0.0 ), vec3( 0.0 ), vec3( 0.0 ) );
	#ifdef USE_LIGHTMAP
		vec4 lightMapTexel = texture2D( lightMap, vUv2 );
		reflectedLight.indirectDiffuse += lightMapTexel.rgb * lightMapIntensity * RECIPROCAL_PI;
	#else
		reflectedLight.indirectDiffuse += vec3( 1.0 );
	#endif
	#include <aomap_fragment>
	reflectedLight.indirectDiffuse *= diffuseColor.rgb;
	vec3 outgoingLight = reflectedLight.indirectDiffuse;
	#include <envmap_fragment>
	#include <output_fragment>
	#include <tonemapping_fragment>
	#include <encodings_fragment>
	#include <fog_fragment>
	#include <premultiplied_alpha_fragment>
	#include <dithering_fragment>
}`,Ew=`#define LAMBERT
varying vec3 vViewPosition;
#include <common>
#include <uv_pars_vertex>
#include <uv2_pars_vertex>
#include <displacementmap_pars_vertex>
#include <envmap_pars_vertex>
#include <color_pars_vertex>
#include <fog_pars_vertex>
#include <normal_pars_vertex>
#include <morphtarget_pars_vertex>
#include <skinning_pars_vertex>
#include <shadowmap_pars_vertex>
#include <logdepthbuf_pars_vertex>
#include <clipping_planes_pars_vertex>
void main() {
	#include <uv_vertex>
	#include <uv2_vertex>
	#include <color_vertex>
	#include <morphcolor_vertex>
	#include <beginnormal_vertex>
	#include <morphnormal_vertex>
	#include <skinbase_vertex>
	#include <skinnormal_vertex>
	#include <defaultnormal_vertex>
	#include <normal_vertex>
	#include <begin_vertex>
	#include <morphtarget_vertex>
	#include <skinning_vertex>
	#include <displacementmap_vertex>
	#include <project_vertex>
	#include <logdepthbuf_vertex>
	#include <clipping_planes_vertex>
	vViewPosition = - mvPosition.xyz;
	#include <worldpos_vertex>
	#include <envmap_vertex>
	#include <shadowmap_vertex>
	#include <fog_vertex>
}`,Dw=`#define LAMBERT
uniform vec3 diffuse;
uniform vec3 emissive;
uniform float opacity;
#include <common>
#include <packing>
#include <dithering_pars_fragment>
#include <color_pars_fragment>
#include <uv_pars_fragment>
#include <uv2_pars_fragment>
#include <map_pars_fragment>
#include <alphamap_pars_fragment>
#include <alphatest_pars_fragment>
#include <aomap_pars_fragment>
#include <lightmap_pars_fragment>
#include <emissivemap_pars_fragment>
#include <envmap_common_pars_fragment>
#include <envmap_pars_fragment>
#include <fog_pars_fragment>
#include <bsdfs>
#include <lights_pars_begin>
#include <normal_pars_fragment>
#include <lights_lambert_pars_fragment>
#include <shadowmap_pars_fragment>
#include <bumpmap_pars_fragment>
#include <normalmap_pars_fragment>
#include <specularmap_pars_fragment>
#include <logdepthbuf_pars_fragment>
#include <clipping_planes_pars_fragment>
void main() {
	#include <clipping_planes_fragment>
	vec4 diffuseColor = vec4( diffuse, opacity );
	ReflectedLight reflectedLight = ReflectedLight( vec3( 0.0 ), vec3( 0.0 ), vec3( 0.0 ), vec3( 0.0 ) );
	vec3 totalEmissiveRadiance = emissive;
	#include <logdepthbuf_fragment>
	#include <map_fragment>
	#include <color_fragment>
	#include <alphamap_fragment>
	#include <alphatest_fragment>
	#include <specularmap_fragment>
	#include <normal_fragment_begin>
	#include <normal_fragment_maps>
	#include <emissivemap_fragment>
	#include <lights_lambert_fragment>
	#include <lights_fragment_begin>
	#include <lights_fragment_maps>
	#include <lights_fragment_end>
	#include <aomap_fragment>
	vec3 outgoingLight = reflectedLight.directDiffuse + reflectedLight.indirectDiffuse + totalEmissiveRadiance;
	#include <envmap_fragment>
	#include <output_fragment>
	#include <tonemapping_fragment>
	#include <encodings_fragment>
	#include <fog_fragment>
	#include <premultiplied_alpha_fragment>
	#include <dithering_fragment>
}`,Iw=`#define MATCAP
varying vec3 vViewPosition;
#include <common>
#include <uv_pars_vertex>
#include <color_pars_vertex>
#include <displacementmap_pars_vertex>
#include <fog_pars_vertex>
#include <normal_pars_vertex>
#include <morphtarget_pars_vertex>
#include <skinning_pars_vertex>
#include <logdepthbuf_pars_vertex>
#include <clipping_planes_pars_vertex>
void main() {
	#include <uv_vertex>
	#include <color_vertex>
	#include <morphcolor_vertex>
	#include <beginnormal_vertex>
	#include <morphnormal_vertex>
	#include <skinbase_vertex>
	#include <skinnormal_vertex>
	#include <defaultnormal_vertex>
	#include <normal_vertex>
	#include <begin_vertex>
	#include <morphtarget_vertex>
	#include <skinning_vertex>
	#include <displacementmap_vertex>
	#include <project_vertex>
	#include <logdepthbuf_vertex>
	#include <clipping_planes_vertex>
	#include <fog_vertex>
	vViewPosition = - mvPosition.xyz;
}`,Rw=`#define MATCAP
uniform vec3 diffuse;
uniform float opacity;
uniform sampler2D matcap;
varying vec3 vViewPosition;
#include <common>
#include <dithering_pars_fragment>
#include <color_pars_fragment>
#include <uv_pars_fragment>
#include <map_pars_fragment>
#include <alphamap_pars_fragment>
#include <alphatest_pars_fragment>
#include <fog_pars_fragment>
#include <normal_pars_fragment>
#include <bumpmap_pars_fragment>
#include <normalmap_pars_fragment>
#include <logdepthbuf_pars_fragment>
#include <clipping_planes_pars_fragment>
void main() {
	#include <clipping_planes_fragment>
	vec4 diffuseColor = vec4( diffuse, opacity );
	#include <logdepthbuf_fragment>
	#include <map_fragment>
	#include <color_fragment>
	#include <alphamap_fragment>
	#include <alphatest_fragment>
	#include <normal_fragment_begin>
	#include <normal_fragment_maps>
	vec3 viewDir = normalize( vViewPosition );
	vec3 x = normalize( vec3( viewDir.z, 0.0, - viewDir.x ) );
	vec3 y = cross( viewDir, x );
	vec2 uv = vec2( dot( x, normal ), dot( y, normal ) ) * 0.495 + 0.5;
	#ifdef USE_MATCAP
		vec4 matcapColor = texture2D( matcap, uv );
	#else
		vec4 matcapColor = vec4( vec3( mix( 0.2, 0.8, uv.y ) ), 1.0 );
	#endif
	vec3 outgoingLight = diffuseColor.rgb * matcapColor.rgb;
	#include <output_fragment>
	#include <tonemapping_fragment>
	#include <encodings_fragment>
	#include <fog_fragment>
	#include <premultiplied_alpha_fragment>
	#include <dithering_fragment>
}`,Ow=`#define NORMAL
#if defined( FLAT_SHADED ) || defined( USE_BUMPMAP ) || defined( TANGENTSPACE_NORMALMAP )
	varying vec3 vViewPosition;
#endif
#include <common>
#include <uv_pars_vertex>
#include <displacementmap_pars_vertex>
#include <normal_pars_vertex>
#include <morphtarget_pars_vertex>
#include <skinning_pars_vertex>
#include <logdepthbuf_pars_vertex>
#include <clipping_planes_pars_vertex>
void main() {
	#include <uv_vertex>
	#include <beginnormal_vertex>
	#include <morphnormal_vertex>
	#include <skinbase_vertex>
	#include <skinnormal_vertex>
	#include <defaultnormal_vertex>
	#include <normal_vertex>
	#include <begin_vertex>
	#include <morphtarget_vertex>
	#include <skinning_vertex>
	#include <displacementmap_vertex>
	#include <project_vertex>
	#include <logdepthbuf_vertex>
	#include <clipping_planes_vertex>
#if defined( FLAT_SHADED ) || defined( USE_BUMPMAP ) || defined( TANGENTSPACE_NORMALMAP )
	vViewPosition = - mvPosition.xyz;
#endif
}`,Pw=`#define NORMAL
uniform float opacity;
#if defined( FLAT_SHADED ) || defined( USE_BUMPMAP ) || defined( TANGENTSPACE_NORMALMAP )
	varying vec3 vViewPosition;
#endif
#include <packing>
#include <uv_pars_fragment>
#include <normal_pars_fragment>
#include <bumpmap_pars_fragment>
#include <normalmap_pars_fragment>
#include <logdepthbuf_pars_fragment>
#include <clipping_planes_pars_fragment>
void main() {
	#include <clipping_planes_fragment>
	#include <logdepthbuf_fragment>
	#include <normal_fragment_begin>
	#include <normal_fragment_maps>
	gl_FragColor = vec4( packNormalToRGB( normal ), opacity );
	#ifdef OPAQUE
		gl_FragColor.a = 1.0;
	#endif
}`,Lw=`#define PHONG
varying vec3 vViewPosition;
#include <common>
#include <uv_pars_vertex>
#include <uv2_pars_vertex>
#include <displacementmap_pars_vertex>
#include <envmap_pars_vertex>
#include <color_pars_vertex>
#include <fog_pars_vertex>
#include <normal_pars_vertex>
#include <morphtarget_pars_vertex>
#include <skinning_pars_vertex>
#include <shadowmap_pars_vertex>
#include <logdepthbuf_pars_vertex>
#include <clipping_planes_pars_vertex>
void main() {
	#include <uv_vertex>
	#include <uv2_vertex>
	#include <color_vertex>
	#include <morphcolor_vertex>
	#include <beginnormal_vertex>
	#include <morphnormal_vertex>
	#include <skinbase_vertex>
	#include <skinnormal_vertex>
	#include <defaultnormal_vertex>
	#include <normal_vertex>
	#include <begin_vertex>
	#include <morphtarget_vertex>
	#include <skinning_vertex>
	#include <displacementmap_vertex>
	#include <project_vertex>
	#include <logdepthbuf_vertex>
	#include <clipping_planes_vertex>
	vViewPosition = - mvPosition.xyz;
	#include <worldpos_vertex>
	#include <envmap_vertex>
	#include <shadowmap_vertex>
	#include <fog_vertex>
}`,zw=`#define PHONG
uniform vec3 diffuse;
uniform vec3 emissive;
uniform vec3 specular;
uniform float shininess;
uniform float opacity;
#include <common>
#include <packing>
#include <dithering_pars_fragment>
#include <color_pars_fragment>
#include <uv_pars_fragment>
#include <uv2_pars_fragment>
#include <map_pars_fragment>
#include <alphamap_pars_fragment>
#include <alphatest_pars_fragment>
#include <aomap_pars_fragment>
#include <lightmap_pars_fragment>
#include <emissivemap_pars_fragment>
#include <envmap_common_pars_fragment>
#include <envmap_pars_fragment>
#include <fog_pars_fragment>
#include <bsdfs>
#include <lights_pars_begin>
#include <normal_pars_fragment>
#include <lights_phong_pars_fragment>
#include <shadowmap_pars_fragment>
#include <bumpmap_pars_fragment>
#include <normalmap_pars_fragment>
#include <specularmap_pars_fragment>
#include <logdepthbuf_pars_fragment>
#include <clipping_planes_pars_fragment>
void main() {
	#include <clipping_planes_fragment>
	vec4 diffuseColor = vec4( diffuse, opacity );
	ReflectedLight reflectedLight = ReflectedLight( vec3( 0.0 ), vec3( 0.0 ), vec3( 0.0 ), vec3( 0.0 ) );
	vec3 totalEmissiveRadiance = emissive;
	#include <logdepthbuf_fragment>
	#include <map_fragment>
	#include <color_fragment>
	#include <alphamap_fragment>
	#include <alphatest_fragment>
	#include <specularmap_fragment>
	#include <normal_fragment_begin>
	#include <normal_fragment_maps>
	#include <emissivemap_fragment>
	#include <lights_phong_fragment>
	#include <lights_fragment_begin>
	#include <lights_fragment_maps>
	#include <lights_fragment_end>
	#include <aomap_fragment>
	vec3 outgoingLight = reflectedLight.directDiffuse + reflectedLight.indirectDiffuse + reflectedLight.directSpecular + reflectedLight.indirectSpecular + totalEmissiveRadiance;
	#include <envmap_fragment>
	#include <output_fragment>
	#include <tonemapping_fragment>
	#include <encodings_fragment>
	#include <fog_fragment>
	#include <premultiplied_alpha_fragment>
	#include <dithering_fragment>
}`,kw=`#define STANDARD
varying vec3 vViewPosition;
#ifdef USE_TRANSMISSION
	varying vec3 vWorldPosition;
#endif
#include <common>
#include <uv_pars_vertex>
#include <uv2_pars_vertex>
#include <displacementmap_pars_vertex>
#include <color_pars_vertex>
#include <fog_pars_vertex>
#include <normal_pars_vertex>
#include <morphtarget_pars_vertex>
#include <skinning_pars_vertex>
#include <shadowmap_pars_vertex>
#include <logdepthbuf_pars_vertex>
#include <clipping_planes_pars_vertex>
void main() {
	#include <uv_vertex>
	#include <uv2_vertex>
	#include <color_vertex>
	#include <morphcolor_vertex>
	#include <beginnormal_vertex>
	#include <morphnormal_vertex>
	#include <skinbase_vertex>
	#include <skinnormal_vertex>
	#include <defaultnormal_vertex>
	#include <normal_vertex>
	#include <begin_vertex>
	#include <morphtarget_vertex>
	#include <skinning_vertex>
	#include <displacementmap_vertex>
	#include <project_vertex>
	#include <logdepthbuf_vertex>
	#include <clipping_planes_vertex>
	vViewPosition = - mvPosition.xyz;
	#include <worldpos_vertex>
	#include <shadowmap_vertex>
	#include <fog_vertex>
#ifdef USE_TRANSMISSION
	vWorldPosition = worldPosition.xyz;
#endif
}`,Nw=`#define STANDARD
#ifdef PHYSICAL
	#define IOR
	#define SPECULAR
#endif
uniform vec3 diffuse;
uniform vec3 emissive;
uniform float roughness;
uniform float metalness;
uniform float opacity;
#ifdef IOR
	uniform float ior;
#endif
#ifdef SPECULAR
	uniform float specularIntensity;
	uniform vec3 specularColor;
	#ifdef USE_SPECULARINTENSITYMAP
		uniform sampler2D specularIntensityMap;
	#endif
	#ifdef USE_SPECULARCOLORMAP
		uniform sampler2D specularColorMap;
	#endif
#endif
#ifdef USE_CLEARCOAT
	uniform float clearcoat;
	uniform float clearcoatRoughness;
#endif
#ifdef USE_IRIDESCENCE
	uniform float iridescence;
	uniform float iridescenceIOR;
	uniform float iridescenceThicknessMinimum;
	uniform float iridescenceThicknessMaximum;
#endif
#ifdef USE_SHEEN
	uniform vec3 sheenColor;
	uniform float sheenRoughness;
	#ifdef USE_SHEENCOLORMAP
		uniform sampler2D sheenColorMap;
	#endif
	#ifdef USE_SHEENROUGHNESSMAP
		uniform sampler2D sheenRoughnessMap;
	#endif
#endif
varying vec3 vViewPosition;
#include <common>
#include <packing>
#include <dithering_pars_fragment>
#include <color_pars_fragment>
#include <uv_pars_fragment>
#include <uv2_pars_fragment>
#include <map_pars_fragment>
#include <alphamap_pars_fragment>
#include <alphatest_pars_fragment>
#include <aomap_pars_fragment>
#include <lightmap_pars_fragment>
#include <emissivemap_pars_fragment>
#include <bsdfs>
#include <iridescence_fragment>
#include <cube_uv_reflection_fragment>
#include <envmap_common_pars_fragment>
#include <envmap_physical_pars_fragment>
#include <fog_pars_fragment>
#include <lights_pars_begin>
#include <normal_pars_fragment>
#include <lights_physical_pars_fragment>
#include <transmission_pars_fragment>
#include <shadowmap_pars_fragment>
#include <bumpmap_pars_fragment>
#include <normalmap_pars_fragment>
#include <clearcoat_pars_fragment>
#include <iridescence_pars_fragment>
#include <roughnessmap_pars_fragment>
#include <metalnessmap_pars_fragment>
#include <logdepthbuf_pars_fragment>
#include <clipping_planes_pars_fragment>
void main() {
	#include <clipping_planes_fragment>
	vec4 diffuseColor = vec4( diffuse, opacity );
	ReflectedLight reflectedLight = ReflectedLight( vec3( 0.0 ), vec3( 0.0 ), vec3( 0.0 ), vec3( 0.0 ) );
	vec3 totalEmissiveRadiance = emissive;
	#include <logdepthbuf_fragment>
	#include <map_fragment>
	#include <color_fragment>
	#include <alphamap_fragment>
	#include <alphatest_fragment>
	#include <roughnessmap_fragment>
	#include <metalnessmap_fragment>
	#include <normal_fragment_begin>
	#include <normal_fragment_maps>
	#include <clearcoat_normal_fragment_begin>
	#include <clearcoat_normal_fragment_maps>
	#include <emissivemap_fragment>
	#include <lights_physical_fragment>
	#include <lights_fragment_begin>
	#include <lights_fragment_maps>
	#include <lights_fragment_end>
	#include <aomap_fragment>
	vec3 totalDiffuse = reflectedLight.directDiffuse + reflectedLight.indirectDiffuse;
	vec3 totalSpecular = reflectedLight.directSpecular + reflectedLight.indirectSpecular;
	#include <transmission_fragment>
	vec3 outgoingLight = totalDiffuse + totalSpecular + totalEmissiveRadiance;
	#ifdef USE_SHEEN
		float sheenEnergyComp = 1.0 - 0.157 * max3( material.sheenColor );
		outgoingLight = outgoingLight * sheenEnergyComp + sheenSpecular;
	#endif
	#ifdef USE_CLEARCOAT
		float dotNVcc = saturate( dot( geometry.clearcoatNormal, geometry.viewDir ) );
		vec3 Fcc = F_Schlick( material.clearcoatF0, material.clearcoatF90, dotNVcc );
		outgoingLight = outgoingLight * ( 1.0 - material.clearcoat * Fcc ) + clearcoatSpecular * material.clearcoat;
	#endif
	#include <output_fragment>
	#include <tonemapping_fragment>
	#include <encodings_fragment>
	#include <fog_fragment>
	#include <premultiplied_alpha_fragment>
	#include <dithering_fragment>
}`,Uw=`#define TOON
varying vec3 vViewPosition;
#include <common>
#include <uv_pars_vertex>
#include <uv2_pars_vertex>
#include <displacementmap_pars_vertex>
#include <color_pars_vertex>
#include <fog_pars_vertex>
#include <normal_pars_vertex>
#include <morphtarget_pars_vertex>
#include <skinning_pars_vertex>
#include <shadowmap_pars_vertex>
#include <logdepthbuf_pars_vertex>
#include <clipping_planes_pars_vertex>
void main() {
	#include <uv_vertex>
	#include <uv2_vertex>
	#include <color_vertex>
	#include <morphcolor_vertex>
	#include <beginnormal_vertex>
	#include <morphnormal_vertex>
	#include <skinbase_vertex>
	#include <skinnormal_vertex>
	#include <defaultnormal_vertex>
	#include <normal_vertex>
	#include <begin_vertex>
	#include <morphtarget_vertex>
	#include <skinning_vertex>
	#include <displacementmap_vertex>
	#include <project_vertex>
	#include <logdepthbuf_vertex>
	#include <clipping_planes_vertex>
	vViewPosition = - mvPosition.xyz;
	#include <worldpos_vertex>
	#include <shadowmap_vertex>
	#include <fog_vertex>
}`,Fw=`#define TOON
uniform vec3 diffuse;
uniform vec3 emissive;
uniform float opacity;
#include <common>
#include <packing>
#include <dithering_pars_fragment>
#include <color_pars_fragment>
#include <uv_pars_fragment>
#include <uv2_pars_fragment>
#include <map_pars_fragment>
#include <alphamap_pars_fragment>
#include <alphatest_pars_fragment>
#include <aomap_pars_fragment>
#include <lightmap_pars_fragment>
#include <emissivemap_pars_fragment>
#include <gradientmap_pars_fragment>
#include <fog_pars_fragment>
#include <bsdfs>
#include <lights_pars_begin>
#include <normal_pars_fragment>
#include <lights_toon_pars_fragment>
#include <shadowmap_pars_fragment>
#include <bumpmap_pars_fragment>
#include <normalmap_pars_fragment>
#include <logdepthbuf_pars_fragment>
#include <clipping_planes_pars_fragment>
void main() {
	#include <clipping_planes_fragment>
	vec4 diffuseColor = vec4( diffuse, opacity );
	ReflectedLight reflectedLight = ReflectedLight( vec3( 0.0 ), vec3( 0.0 ), vec3( 0.0 ), vec3( 0.0 ) );
	vec3 totalEmissiveRadiance = emissive;
	#include <logdepthbuf_fragment>
	#include <map_fragment>
	#include <color_fragment>
	#include <alphamap_fragment>
	#include <alphatest_fragment>
	#include <normal_fragment_begin>
	#include <normal_fragment_maps>
	#include <emissivemap_fragment>
	#include <lights_toon_fragment>
	#include <lights_fragment_begin>
	#include <lights_fragment_maps>
	#include <lights_fragment_end>
	#include <aomap_fragment>
	vec3 outgoingLight = reflectedLight.directDiffuse + reflectedLight.indirectDiffuse + totalEmissiveRadiance;
	#include <output_fragment>
	#include <tonemapping_fragment>
	#include <encodings_fragment>
	#include <fog_fragment>
	#include <premultiplied_alpha_fragment>
	#include <dithering_fragment>
}`,Bw=`uniform float size;
uniform float scale;
#include <common>
#include <color_pars_vertex>
#include <fog_pars_vertex>
#include <morphtarget_pars_vertex>
#include <logdepthbuf_pars_vertex>
#include <clipping_planes_pars_vertex>
void main() {
	#include <color_vertex>
	#include <morphcolor_vertex>
	#include <begin_vertex>
	#include <morphtarget_vertex>
	#include <project_vertex>
	gl_PointSize = size;
	#ifdef USE_SIZEATTENUATION
		bool isPerspective = isPerspectiveMatrix( projectionMatrix );
		if ( isPerspective ) gl_PointSize *= ( scale / - mvPosition.z );
	#endif
	#include <logdepthbuf_vertex>
	#include <clipping_planes_vertex>
	#include <worldpos_vertex>
	#include <fog_vertex>
}`,Gw=`uniform vec3 diffuse;
uniform float opacity;
#include <common>
#include <color_pars_fragment>
#include <map_particle_pars_fragment>
#include <alphatest_pars_fragment>
#include <fog_pars_fragment>
#include <logdepthbuf_pars_fragment>
#include <clipping_planes_pars_fragment>
void main() {
	#include <clipping_planes_fragment>
	vec3 outgoingLight = vec3( 0.0 );
	vec4 diffuseColor = vec4( diffuse, opacity );
	#include <logdepthbuf_fragment>
	#include <map_particle_fragment>
	#include <color_fragment>
	#include <alphatest_fragment>
	outgoingLight = diffuseColor.rgb;
	#include <output_fragment>
	#include <tonemapping_fragment>
	#include <encodings_fragment>
	#include <fog_fragment>
	#include <premultiplied_alpha_fragment>
}`,Vw=`#include <common>
#include <fog_pars_vertex>
#include <morphtarget_pars_vertex>
#include <skinning_pars_vertex>
#include <shadowmap_pars_vertex>
void main() {
	#include <beginnormal_vertex>
	#include <morphnormal_vertex>
	#include <skinbase_vertex>
	#include <skinnormal_vertex>
	#include <defaultnormal_vertex>
	#include <begin_vertex>
	#include <morphtarget_vertex>
	#include <skinning_vertex>
	#include <project_vertex>
	#include <worldpos_vertex>
	#include <shadowmap_vertex>
	#include <fog_vertex>
}`,jw=`uniform vec3 color;
uniform float opacity;
#include <common>
#include <packing>
#include <fog_pars_fragment>
#include <bsdfs>
#include <lights_pars_begin>
#include <shadowmap_pars_fragment>
#include <shadowmask_pars_fragment>
void main() {
	gl_FragColor = vec4( color, opacity * ( 1.0 - getShadowMask() ) );
	#include <tonemapping_fragment>
	#include <encodings_fragment>
	#include <fog_fragment>
}`,Ww=`uniform float rotation;
uniform vec2 center;
#include <common>
#include <uv_pars_vertex>
#include <fog_pars_vertex>
#include <logdepthbuf_pars_vertex>
#include <clipping_planes_pars_vertex>
void main() {
	#include <uv_vertex>
	vec4 mvPosition = modelViewMatrix * vec4( 0.0, 0.0, 0.0, 1.0 );
	vec2 scale;
	scale.x = length( vec3( modelMatrix[ 0 ].x, modelMatrix[ 0 ].y, modelMatrix[ 0 ].z ) );
	scale.y = length( vec3( modelMatrix[ 1 ].x, modelMatrix[ 1 ].y, modelMatrix[ 1 ].z ) );
	#ifndef USE_SIZEATTENUATION
		bool isPerspective = isPerspectiveMatrix( projectionMatrix );
		if ( isPerspective ) scale *= - mvPosition.z;
	#endif
	vec2 alignedPosition = ( position.xy - ( center - vec2( 0.5 ) ) ) * scale;
	vec2 rotatedPosition;
	rotatedPosition.x = cos( rotation ) * alignedPosition.x - sin( rotation ) * alignedPosition.y;
	rotatedPosition.y = sin( rotation ) * alignedPosition.x + cos( rotation ) * alignedPosition.y;
	mvPosition.xy += rotatedPosition;
	gl_Position = projectionMatrix * mvPosition;
	#include <logdepthbuf_vertex>
	#include <clipping_planes_vertex>
	#include <fog_vertex>
}`,Hw=`uniform vec3 diffuse;
uniform float opacity;
#include <common>
#include <uv_pars_fragment>
#include <map_pars_fragment>
#include <alphamap_pars_fragment>
#include <alphatest_pars_fragment>
#include <fog_pars_fragment>
#include <logdepthbuf_pars_fragment>
#include <clipping_planes_pars_fragment>
void main() {
	#include <clipping_planes_fragment>
	vec3 outgoingLight = vec3( 0.0 );
	vec4 diffuseColor = vec4( diffuse, opacity );
	#include <logdepthbuf_fragment>
	#include <map_fragment>
	#include <alphamap_fragment>
	#include <alphatest_fragment>
	outgoingLight = diffuseColor.rgb;
	#include <output_fragment>
	#include <tonemapping_fragment>
	#include <encodings_fragment>
	#include <fog_fragment>
}`,c2={alphamap_fragment:gM,alphamap_pars_fragment:vM,alphatest_fragment:_M,alphatest_pars_fragment:yM,aomap_fragment:bM,aomap_pars_fragment:xM,begin_vertex:SM,beginnormal_vertex:MM,bsdfs:AM,iridescence_fragment:wM,bumpmap_pars_fragment:CM,clipping_planes_fragment:TM,clipping_planes_pars_fragment:EM,clipping_planes_pars_vertex:DM,clipping_planes_vertex:IM,color_fragment:RM,color_pars_fragment:OM,color_pars_vertex:PM,color_vertex:LM,common:zM,cube_uv_reflection_fragment:kM,defaultnormal_vertex:NM,displacementmap_pars_vertex:UM,displacementmap_vertex:FM,emissivemap_fragment:BM,emissivemap_pars_fragment:GM,encodings_fragment:VM,encodings_pars_fragment:jM,envmap_fragment:WM,envmap_common_pars_fragment:HM,envmap_pars_fragment:$M,envmap_pars_vertex:YM,envmap_physical_pars_fragment:aA,envmap_vertex:JM,fog_vertex:ZM,fog_pars_vertex:qM,fog_fragment:XM,fog_pars_fragment:QM,gradientmap_pars_fragment:KM,lightmap_fragment:eA,lightmap_pars_fragment:tA,lights_lambert_fragment:nA,lights_lambert_pars_fragment:iA,lights_pars_begin:oA,lights_toon_fragment:rA,lights_toon_pars_fragment:sA,lights_phong_fragment:lA,lights_phong_pars_fragment:uA,lights_physical_fragment:cA,lights_physical_pars_fragment:dA,lights_fragment_begin:fA,lights_fragment_maps:pA,lights_fragment_end:hA,logdepthbuf_fragment:mA,logdepthbuf_pars_fragment:gA,logdepthbuf_pars_vertex:vA,logdepthbuf_vertex:_A,map_fragment:yA,map_pars_fragment:bA,map_particle_fragment:xA,map_particle_pars_fragment:SA,metalnessmap_fragment:MA,metalnessmap_pars_fragment:AA,morphcolor_vertex:wA,morphnormal_vertex:CA,morphtarget_pars_vertex:TA,morphtarget_vertex:EA,normal_fragment_begin:DA,normal_fragment_maps:IA,normal_pars_fragment:RA,normal_pars_vertex:OA,normal_vertex:PA,normalmap_pars_fragment:LA,clearcoat_normal_fragment_begin:zA,clearcoat_normal_fragment_maps:kA,clearcoat_pars_fragment:NA,iridescence_pars_fragment:UA,output_fragment:FA,packing:BA,premultiplied_alpha_fragment:GA,project_vertex:VA,dithering_fragment:jA,dithering_pars_fragment:WA,roughnessmap_fragment:HA,roughnessmap_pars_fragment:$A,shadowmap_pars_fragment:YA,shadowmap_pars_vertex:JA,shadowmap_vertex:ZA,shadowmask_pars_fragment:qA,skinbase_vertex:XA,skinning_pars_vertex:QA,skinning_vertex:KA,skinnormal_vertex:ew,specularmap_fragment:tw,specularmap_pars_fragment:nw,tonemapping_fragment:iw,tonemapping_pars_fragment:ow,transmission_fragment:aw,transmission_pars_fragment:rw,uv_pars_fragment:sw,uv_pars_vertex:lw,uv_vertex:uw,uv2_pars_fragment:cw,uv2_pars_vertex:dw,uv2_vertex:fw,worldpos_vertex:pw,background_vert:hw,background_frag:mw,cube_vert:gw,cube_frag:vw,depth_vert:_w,depth_frag:yw,distanceRGBA_vert:bw,distanceRGBA_frag:xw,equirect_vert:Sw,equirect_frag:Mw,linedashed_vert:Aw,linedashed_frag:ww,meshbasic_vert:Cw,meshbasic_frag:Tw,meshlambert_vert:Ew,meshlambert_frag:Dw,meshmatcap_vert:Iw,meshmatcap_frag:Rw,meshnormal_vert:Ow,meshnormal_frag:Pw,meshphong_vert:Lw,meshphong_frag:zw,meshphysical_vert:kw,meshphysical_frag:Nw,meshtoon_vert:Uw,meshtoon_frag:Fw,points_vert:Bw,points_frag:Gw,shadow_vert:Vw,shadow_frag:jw,sprite_vert:Ww,sprite_frag:Hw},I1={common:{diffuse:{value:new y2(16777215)},opacity:{value:1},map:{value:null},uvTransform:{value:new X3},uv2Transform:{value:new X3},alphaMap:{value:null},alphaTest:{value:0}},specularmap:{specularMap:{value:null}},envmap:{envMap:{value:null},flipEnvMap:{value:-1},reflectivity:{value:1},ior:{value:1.5},refractionRatio:{value:.98}},aomap:{aoMap:{value:null},aoMapIntensity:{value:1}},lightmap:{lightMap:{value:null},lightMapIntensity:{value:1}},emissivemap:{emissiveMap:{value:null}},bumpmap:{bumpMap:{value:null},bumpScale:{value:1}},normalmap:{normalMap:{value:null},normalScale:{value:new G1(1,1)}},displacementmap:{displacementMap:{value:null},displacementScale:{value:1},displacementBias:{value:0}},roughnessmap:{roughnessMap:{value:null}},metalnessmap:{metalnessMap:{value:null}},gradientmap:{gradientMap:{value:null}},fog:{fogDensity:{value:25e-5},fogNear:{value:1},fogFar:{value:2e3},fogColor:{value:new y2(16777215)}},lights:{ambientLightColor:{value:[]},lightProbe:{value:[]},directionalLights:{value:[],properties:{direction:{},color:{}}},directionalLightShadows:{value:[],properties:{shadowBias:{},shadowNormalBias:{},shadowRadius:{},shadowMapSize:{}}},directionalShadowMap:{value:[]},directionalShadowMatrix:{value:[]},spotLights:{value:[],properties:{color:{},position:{},direction:{},distance:{},coneCos:{},penumbraCos:{},decay:{}}},spotLightShadows:{value:[],properties:{shadowBias:{},shadowNormalBias:{},shadowRadius:{},shadowMapSize:{}}},spotLightMap:{value:[]},spotShadowMap:{value:[]},spotLightMatrix:{value:[]},pointLights:{value:[],properties:{color:{},position:{},decay:{},distance:{}}},pointLightShadows:{value:[],properties:{shadowBias:{},shadowNormalBias:{},shadowRadius:{},shadowMapSize:{},shadowCameraNear:{},shadowCameraFar:{}}},pointShadowMap:{value:[]},pointShadowMatrix:{value:[]},hemisphereLights:{value:[],properties:{direction:{},skyColor:{},groundColor:{}}},rectAreaLights:{value:[],properties:{color:{},position:{},width:{},height:{}}},ltc_1:{value:null},ltc_2:{value:null}},points:{diffuse:{value:new y2(16777215)},opacity:{value:1},size:{value:1},scale:{value:1},map:{value:null},alphaMap:{value:null},alphaTest:{value:0},uvTransform:{value:new X3}},sprite:{diffuse:{value:new y2(16777215)},opacity:{value:1},center:{value:new G1(.5,.5)},rotation:{value:0},map:{value:null},alphaMap:{value:null},alphaTest:{value:0},uvTransform:{value:new X3}}},m4={basic:{uniforms:h3([I1.common,I1.specularmap,I1.envmap,I1.aomap,I1.lightmap,I1.fog]),vertexShader:c2.meshbasic_vert,fragmentShader:c2.meshbasic_frag},lambert:{uniforms:h3([I1.common,I1.specularmap,I1.envmap,I1.aomap,I1.lightmap,I1.emissivemap,I1.bumpmap,I1.normalmap,I1.displacementmap,I1.fog,I1.lights,{emissive:{value:new y2(0)}}]),vertexShader:c2.meshlambert_vert,fragmentShader:c2.meshlambert_frag},phong:{uniforms:h3([I1.common,I1.specularmap,I1.envmap,I1.aomap,I1.lightmap,I1.emissivemap,I1.bumpmap,I1.normalmap,I1.displacementmap,I1.fog,I1.lights,{emissive:{value:new y2(0)},specular:{value:new y2(1118481)},shininess:{value:30}}]),vertexShader:c2.meshphong_vert,fragmentShader:c2.meshphong_frag},standard:{uniforms:h3([I1.common,I1.envmap,I1.aomap,I1.lightmap,I1.emissivemap,I1.bumpmap,I1.normalmap,I1.displacementmap,I1.roughnessmap,I1.metalnessmap,I1.fog,I1.lights,{emissive:{value:new y2(0)},roughness:{value:1},metalness:{value:0},envMapIntensity:{value:1}}]),vertexShader:c2.meshphysical_vert,fragmentShader:c2.meshphysical_frag},toon:{uniforms:h3([I1.common,I1.aomap,I1.lightmap,I1.emissivemap,I1.bumpmap,I1.normalmap,I1.displacementmap,I1.gradientmap,I1.fog,I1.lights,{emissive:{value:new y2(0)}}]),vertexShader:c2.meshtoon_vert,fragmentShader:c2.meshtoon_frag},matcap:{uniforms:h3([I1.common,I1.bumpmap,I1.normalmap,I1.displacementmap,I1.fog,{matcap:{value:null}}]),vertexShader:c2.meshmatcap_vert,fragmentShader:c2.meshmatcap_frag},points:{uniforms:h3([I1.points,I1.fog]),vertexShader:c2.points_vert,fragmentShader:c2.points_frag},dashed:{uniforms:h3([I1.common,I1.fog,{scale:{value:1},dashSize:{value:1},totalSize:{value:2}}]),vertexShader:c2.linedashed_vert,fragmentShader:c2.linedashed_frag},depth:{uniforms:h3([I1.common,I1.displacementmap]),vertexShader:c2.depth_vert,fragmentShader:c2.depth_frag},normal:{uniforms:h3([I1.common,I1.bumpmap,I1.normalmap,I1.displacementmap,{opacity:{value:1}}]),vertexShader:c2.meshnormal_vert,fragmentShader:c2.meshnormal_frag},sprite:{uniforms:h3([I1.sprite,I1.fog]),vertexShader:c2.sprite_vert,fragmentShader:c2.sprite_frag},background:{uniforms:{uvTransform:{value:new X3},t2D:{value:null}},vertexShader:c2.background_vert,fragmentShader:c2.background_frag},cube:{uniforms:h3([I1.envmap,{opacity:{value:1}}]),vertexShader:c2.cube_vert,fragmentShader:c2.cube_frag},equirect:{uniforms:{tEquirect:{value:null}},vertexShader:c2.equirect_vert,fragmentShader:c2.equirect_frag},distanceRGBA:{uniforms:h3([I1.common,I1.displacementmap,{referencePosition:{value:new Y},nearDistance:{value:1},farDistance:{value:1e3}}]),vertexShader:c2.distanceRGBA_vert,fragmentShader:c2.distanceRGBA_frag},shadow:{uniforms:h3([I1.lights,I1.fog,{color:{value:new y2(0)},opacity:{value:1}}]),vertexShader:c2.shadow_vert,fragmentShader:c2.shadow_frag}};m4.physical={uniforms:h3([m4.standard.uniforms,{clearcoat:{value:0},clearcoatMap:{value:null},clearcoatRoughness:{value:0},clearcoatRoughnessMap:{value:null},clearcoatNormalScale:{value:new G1(1,1)},clearcoatNormalMap:{value:null},iridescence:{value:0},iridescenceMap:{value:null},iridescenceIOR:{value:1.3},iridescenceThicknessMinimum:{value:100},iridescenceThicknessMaximum:{value:400},iridescenceThicknessMap:{value:null},sheen:{value:0},sheenColor:{value:new y2(0)},sheenColorMap:{value:null},sheenRoughness:{value:1},sheenRoughnessMap:{value:null},transmission:{value:0},transmissionMap:{value:null},transmissionSamplerSize:{value:new G1},transmissionSamplerMap:{value:null},thickness:{value:0},thicknessMap:{value:null},attenuationDistance:{value:0},attenuationColor:{value:new y2(0)},specularIntensity:{value:1},specularIntensityMap:{value:null},specularColor:{value:new y2(1,1,1)},specularColorMap:{value:null}}]),vertexShader:c2.meshphysical_vert,fragmentShader:c2.meshphysical_frag};function $w(o,e,t,n,i,a){const r=new y2(0);let s=i===!0?0:1,u,c,f=null,p=0,h=null;function m(_,v){let b=!1,x=v.isScene===!0?v.background:null;x&&x.isTexture&&(x=e.get(x));const A=o.xr,S=A.getSession&&A.getSession();S&&S.environmentBlendMode==="additive"&&(x=null),x===null?y(r,s):x&&x.isColor&&(y(x,1),b=!0),(o.autoClear||b)&&o.clear(o.autoClearColor,o.autoClearDepth,o.autoClearStencil),x&&(x.isCubeTexture||x.mapping===l6)?(c===void 0&&(c=new u3(new W5(1,1,1),new G4({name:"BackgroundCubeMaterial",uniforms:W9(m4.cube.uniforms),vertexShader:m4.cube.vertexShader,fragmentShader:m4.cube.fragmentShader,side:n4,depthTest:!1,depthWrite:!1,fog:!1})),c.geometry.deleteAttribute("normal"),c.geometry.deleteAttribute("uv"),c.onBeforeRender=function(w,M,D){this.matrixWorld.copyPosition(D.matrixWorld)},Object.defineProperty(c.material,"envMap",{get:function(){return this.uniforms.envMap.value}}),n.update(c)),c.material.uniforms.envMap.value=x,c.material.uniforms.flipEnvMap.value=x.isCubeTexture&&x.isRenderTargetTexture===!1?-1:1,(f!==x||p!==x.version||h!==o.toneMapping)&&(c.material.needsUpdate=!0,f=x,p=x.version,h=o.toneMapping),c.layers.enableAll(),_.unshift(c,c.geometry,c.material,0,0,null)):x&&x.isTexture&&(u===void 0&&(u=new u3(new X9(2,2),new G4({name:"BackgroundMaterial",uniforms:W9(m4.background.uniforms),vertexShader:m4.background.vertexShader,fragmentShader:m4.background.fragmentShader,side:B9,depthTest:!1,depthWrite:!1,fog:!1})),u.geometry.deleteAttribute("normal"),Object.defineProperty(u.material,"map",{get:function(){return this.uniforms.t2D.value}}),n.update(u)),u.material.uniforms.t2D.value=x,x.matrixAutoUpdate===!0&&x.updateMatrix(),u.material.uniforms.uvTransform.value.copy(x.matrix),(f!==x||p!==x.version||h!==o.toneMapping)&&(u.material.needsUpdate=!0,f=x,p=x.version,h=o.toneMapping),u.layers.enableAll(),_.unshift(u,u.geometry,u.material,0,0,null))}function y(_,v){t.buffers.color.setClear(_.r,_.g,_.b,v,a)}return{getClearColor:function(){return r},setClearColor:function(_,v=1){r.set(_),s=v,y(r,s)},getClearAlpha:function(){return s},setClearAlpha:function(_){s=_,y(r,s)},render:m}}function Yw(o,e,t,n){const i=o.getParameter(34921),a=n.isWebGL2?null:e.get("OES_vertex_array_object"),r=n.isWebGL2||a!==null,s={},u=v(null);let c=u,f=!1;function p(z,F,G,J,O){let N=!1;if(r){const B=_(J,G,F);c!==B&&(c=B,m(c.object)),N=b(z,J,G,O),N&&x(z,J,G,O)}else{const B=F.wireframe===!0;(c.geometry!==J.id||c.program!==G.id||c.wireframe!==B)&&(c.geometry=J.id,c.program=G.id,c.wireframe=B,N=!0)}O!==null&&t.update(O,34963),(N||f)&&(f=!1,T(z,F,G,J),O!==null&&o.bindBuffer(34963,t.get(O).buffer))}function h(){return n.isWebGL2?o.createVertexArray():a.createVertexArrayOES()}function m(z){return n.isWebGL2?o.bindVertexArray(z):a.bindVertexArrayOES(z)}function y(z){return n.isWebGL2?o.deleteVertexArray(z):a.deleteVertexArrayOES(z)}function _(z,F,G){const J=G.wireframe===!0;let O=s[z.id];O===void 0&&(O={},s[z.id]=O);let N=O[F.id];N===void 0&&(N={},O[F.id]=N);let B=N[J];return B===void 0&&(B=v(h()),N[J]=B),B}function v(z){const F=[],G=[],J=[];for(let O=0;O<i;O++)F[O]=0,G[O]=0,J[O]=0;return{geometry:null,program:null,wireframe:!1,newAttributes:F,enabledAttributes:G,attributeDivisors:J,object:z,attributes:{},index:null}}function b(z,F,G,J){const O=c.attributes,N=F.attributes;let B=0;const t1=G.getAttributes();for(const n1 in t1)if(t1[n1].location>=0){const a1=O[n1];let Q=N[n1];if(Q===void 0&&(n1==="instanceMatrix"&&z.instanceMatrix&&(Q=z.instanceMatrix),n1==="instanceColor"&&z.instanceColor&&(Q=z.instanceColor)),a1===void 0||a1.attribute!==Q||Q&&a1.data!==Q.data)return!0;B++}return c.attributesNum!==B||c.index!==J}function x(z,F,G,J){const O={},N=F.attributes;let B=0;const t1=G.getAttributes();for(const n1 in t1)if(t1[n1].location>=0){let a1=N[n1];a1===void 0&&(n1==="instanceMatrix"&&z.instanceMatrix&&(a1=z.instanceMatrix),n1==="instanceColor"&&z.instanceColor&&(a1=z.instanceColor));const Q={};Q.attribute=a1,a1&&a1.data&&(Q.data=a1.data),O[n1]=Q,B++}c.attributes=O,c.attributesNum=B,c.index=J}function A(){const z=c.newAttributes;for(let F=0,G=z.length;F<G;F++)z[F]=0}function S(z){w(z,0)}function w(z,F){const G=c.newAttributes,J=c.enabledAttributes,O=c.attributeDivisors;G[z]=1,J[z]===0&&(o.enableVertexAttribArray(z),J[z]=1),O[z]!==F&&((n.isWebGL2?o:e.get("ANGLE_instanced_arrays"))[n.isWebGL2?"vertexAttribDivisor":"vertexAttribDivisorANGLE"](z,F),O[z]=F)}function M(){const z=c.newAttributes,F=c.enabledAttributes;for(let G=0,J=F.length;G<J;G++)F[G]!==z[G]&&(o.disableVertexAttribArray(G),F[G]=0)}function D(z,F,G,J,O,N){n.isWebGL2===!0&&(G===5124||G===5125)?o.vertexAttribIPointer(z,F,G,O,N):o.vertexAttribPointer(z,F,G,J,O,N)}function T(z,F,G,J){if(n.isWebGL2===!1&&(z.isInstancedMesh||J.isInstancedBufferGeometry)&&e.get("ANGLE_instanced_arrays")===null)return;A();const O=J.attributes,N=G.getAttributes(),B=F.defaultAttributeValues;for(const t1 in N){const n1=N[t1];if(n1.location>=0){let u1=O[t1];if(u1===void 0&&(t1==="instanceMatrix"&&z.instanceMatrix&&(u1=z.instanceMatrix),t1==="instanceColor"&&z.instanceColor&&(u1=z.instanceColor)),u1!==void 0){const a1=u1.normalized,Q=u1.itemSize,Z=t.get(u1);if(Z===void 0)continue;const d1=Z.buffer,m1=Z.type,g1=Z.bytesPerElement;if(u1.isInterleavedBufferAttribute){const c1=u1.data,L1=c1.stride,M1=u1.offset;if(c1.isInstancedInterleavedBuffer){for(let b1=0;b1<n1.locationSize;b1++)w(n1.location+b1,c1.meshPerAttribute);z.isInstancedMesh!==!0&&J._maxInstanceCount===void 0&&(J._maxInstanceCount=c1.meshPerAttribute*c1.count)}else for(let b1=0;b1<n1.locationSize;b1++)S(n1.location+b1);o.bindBuffer(34962,d1);for(let b1=0;b1<n1.locationSize;b1++)D(n1.location+b1,Q/n1.locationSize,m1,a1,L1*g1,(M1+Q/n1.locationSize*b1)*g1)}else{if(u1.isInstancedBufferAttribute){for(let c1=0;c1<n1.locationSize;c1++)w(n1.location+c1,u1.meshPerAttribute);z.isInstancedMesh!==!0&&J._maxInstanceCount===void 0&&(J._maxInstanceCount=u1.meshPerAttribute*u1.count)}else for(let c1=0;c1<n1.locationSize;c1++)S(n1.location+c1);o.bindBuffer(34962,d1);for(let c1=0;c1<n1.locationSize;c1++)D(n1.location+c1,Q/n1.locationSize,m1,a1,Q*g1,Q/n1.locationSize*c1*g1)}}else if(B!==void 0){const a1=B[t1];if(a1!==void 0)switch(a1.length){case 2:o.vertexAttrib2fv(n1.location,a1);break;case 3:o.vertexAttrib3fv(n1.location,a1);break;case 4:o.vertexAttrib4fv(n1.location,a1);break;default:o.vertexAttrib1fv(n1.location,a1)}}}}M()}function E(){q();for(const z in s){const F=s[z];for(const G in F){const J=F[G];for(const O in J)y(J[O].object),delete J[O];delete F[G]}delete s[z]}}function U(z){if(s[z.id]===void 0)return;const F=s[z.id];for(const G in F){const J=F[G];for(const O in J)y(J[O].object),delete J[O];delete F[G]}delete s[z.id]}function V(z){for(const F in s){const G=s[F];if(G[z.id]===void 0)continue;const J=G[z.id];for(const O in J)y(J[O].object),delete J[O];delete G[z.id]}}function q(){$(),f=!0,c!==u&&(c=u,m(c.object))}function $(){u.geometry=null,u.program=null,u.wireframe=!1}return{setup:p,reset:q,resetDefaultState:$,dispose:E,releaseStatesOfGeometry:U,releaseStatesOfProgram:V,initAttributes:A,enableAttribute:S,disableUnusedAttributes:M}}function Jw(o,e,t,n){const i=n.isWebGL2;let a;function r(c){a=c}function s(c,f){o.drawArrays(a,c,f),t.update(f,a,1)}function u(c,f,p){if(p===0)return;let h,m;if(i)h=o,m="drawArraysInstanced";else if(h=e.get("ANGLE_instanced_arrays"),m="drawArraysInstancedANGLE",h===null){console.error("THREE.WebGLBufferRenderer: using THREE.InstancedBufferGeometry but hardware does not support extension ANGLE_instanced_arrays.");return}h[m](a,c,f,p),t.update(f,a,p)}this.setMode=r,this.render=s,this.renderInstances=u}function Zw(o,e,t){let n;function i(){if(n!==void 0)return n;if(e.has("EXT_texture_filter_anisotropic")===!0){const D=e.get("EXT_texture_filter_anisotropic");n=o.getParameter(D.MAX_TEXTURE_MAX_ANISOTROPY_EXT)}else n=0;return n}function a(D){if(D==="highp"){if(o.getShaderPrecisionFormat(35633,36338).precision>0&&o.getShaderPrecisionFormat(35632,36338).precision>0)return"highp";D="mediump"}return D==="mediump"&&o.getShaderPrecisionFormat(35633,36337).precision>0&&o.getShaderPrecisionFormat(35632,36337).precision>0?"mediump":"lowp"}const r=typeof WebGL2RenderingContext!="undefined"&&o instanceof WebGL2RenderingContext||typeof WebGL2ComputeRenderingContext!="undefined"&&o instanceof WebGL2ComputeRenderingContext;let s=t.precision!==void 0?t.precision:"highp";const u=a(s);u!==s&&(console.warn("THREE.WebGLRenderer:",s,"not supported, using",u,"instead."),s=u);const c=r||e.has("WEBGL_draw_buffers"),f=t.logarithmicDepthBuffer===!0,p=o.getParameter(34930),h=o.getParameter(35660),m=o.getParameter(3379),y=o.getParameter(34076),_=o.getParameter(34921),v=o.getParameter(36347),b=o.getParameter(36348),x=o.getParameter(36349),A=h>0,S=r||e.has("OES_texture_float"),w=A&&S,M=r?o.getParameter(36183):0;return{isWebGL2:r,drawBuffers:c,getMaxAnisotropy:i,getMaxPrecision:a,precision:s,logarithmicDepthBuffer:f,maxTextures:p,maxVertexTextures:h,maxTextureSize:m,maxCubemapSize:y,maxAttributes:_,maxVertexUniforms:v,maxVaryings:b,maxFragmentUniforms:x,vertexTextures:A,floatFragmentTextures:S,floatVertexTextures:w,maxSamples:M}}function qw(o){const e=this;let t=null,n=0,i=!1,a=!1;const r=new T0,s=new X3,u={value:null,needsUpdate:!1};this.uniform=u,this.numPlanes=0,this.numIntersection=0,this.init=function(p,h,m){const y=p.length!==0||h||n!==0||i;return i=h,t=f(p,m,0),n=p.length,y},this.beginShadows=function(){a=!0,f(null)},this.endShadows=function(){a=!1,c()},this.setState=function(p,h,m){const y=p.clippingPlanes,_=p.clipIntersection,v=p.clipShadows,b=o.get(p);if(!i||y===null||y.length===0||a&&!v)a?f(null):c();else{const x=a?0:n,A=x*4;let S=b.clippingState||null;u.value=S,S=f(y,h,A,m);for(let w=0;w!==A;++w)S[w]=t[w];b.clippingState=S,this.numIntersection=_?this.numPlanes:0,this.numPlanes+=x}};function c(){u.value!==t&&(u.value=t,u.needsUpdate=n>0),e.numPlanes=n,e.numIntersection=0}function f(p,h,m,y){const _=p!==null?p.length:0;let v=null;if(_!==0){if(v=u.value,y!==!0||v===null){const b=m+_*4,x=h.matrixWorldInverse;s.getNormalMatrix(x),(v===null||v.length<b)&&(v=new Float32Array(b));for(let A=0,S=m;A!==_;++A,S+=4)r.copy(p[A]).applyMatrix4(x,s),r.normal.toArray(v,S),v[S+3]=r.constant}u.value=v,u.needsUpdate=!0}return e.numPlanes=_,e.numIntersection=0,v}}function Xw(o){let e=new WeakMap;function t(r,s){return s===we?r.mapping=G9:s===Ce&&(r.mapping=V9),r}function n(r){if(r&&r.isTexture&&r.isRenderTargetTexture===!1){const s=r.mapping;if(s===we||s===Ce)if(e.has(r)){const u=e.get(r).texture;return t(u,r.mapping)}else{const u=r.image;if(u&&u.height>0){const c=new fM(u.height/2);return c.fromEquirectangularTexture(o,r),e.set(r,c),r.addEventListener("dispose",i),t(c.texture,r.mapping)}else return null}}return r}function i(r){const s=r.target;s.removeEventListener("dispose",i);const u=e.get(s);u!==void 0&&(e.delete(s),u.dispose())}function a(){e=new WeakMap}return{get:n,dispose:a}}class Qw extends Or{constructor(e=-1,t=1,n=1,i=-1,a=.1,r=2e3){super(),this.isOrthographicCamera=!0,this.type="OrthographicCamera",this.zoom=1,this.view=null,this.left=e,this.right=t,this.top=n,this.bottom=i,this.near=a,this.far=r,this.updateProjectionMatrix()}copy(e,t){return super.copy(e,t),this.left=e.left,this.right=e.right,this.top=e.top,this.bottom=e.bottom,this.near=e.near,this.far=e.far,this.zoom=e.zoom,this.view=e.view===null?null:Object.assign({},e.view),this}setViewOffset(e,t,n,i,a,r){this.view===null&&(this.view={enabled:!0,fullWidth:1,fullHeight:1,offsetX:0,offsetY:0,width:1,height:1}),this.view.enabled=!0,this.view.fullWidth=e,this.view.fullHeight=t,this.view.offsetX=n,this.view.offsetY=i,this.view.width=a,this.view.height=r,this.updateProjectionMatrix()}clearViewOffset(){this.view!==null&&(this.view.enabled=!1),this.updateProjectionMatrix()}updateProjectionMatrix(){const e=(this.right-this.left)/(2*this.zoom),t=(this.top-this.bottom)/(2*this.zoom),n=(this.right+this.left)/2,i=(this.top+this.bottom)/2;let a=n-e,r=n+e,s=i+t,u=i-t;if(this.view!==null&&this.view.enabled){const c=(this.right-this.left)/this.view.fullWidth/this.zoom,f=(this.top-this.bottom)/this.view.fullHeight/this.zoom;a+=c*this.view.offsetX,r=a+c*this.view.width,s-=f*this.view.offsetY,u=s-f*this.view.height}this.projectionMatrix.makeOrthographic(a,r,s,u,this.near,this.far),this.projectionMatrixInverse.copy(this.projectionMatrix).invert()}toJSON(e){const t=super.toJSON(e);return t.object.zoom=this.zoom,t.object.left=this.left,t.object.right=this.right,t.object.top=this.top,t.object.bottom=this.bottom,t.object.near=this.near,t.object.far=this.far,this.view!==null&&(t.object.view=Object.assign({},this.view)),t}}const R9=4,Ki=[.125,.215,.35,.446,.526,.582],I0=20,i7=new Qw,eo=new y2;let o7=null;const E0=(1+Math.sqrt(5))/2,g9=1/E0,to=[new Y(1,1,1),new Y(-1,1,1),new Y(1,1,-1),new Y(-1,1,-1),new Y(0,E0,g9),new Y(0,E0,-g9),new Y(g9,0,E0),new Y(-g9,0,E0),new Y(E0,g9,0),new Y(-E0,g9,0)];class no{constructor(e){this._renderer=e,this._pingPongRenderTarget=null,this._lodMax=0,this._cubeSize=0,this._lodPlanes=[],this._sizeLods=[],this._sigmas=[],this._blurMaterial=null,this._cubemapMaterial=null,this._equirectMaterial=null,this._compileMaterial(this._blurMaterial)}fromScene(e,t=0,n=.1,i=100){o7=this._renderer.getRenderTarget(),this._setSize(256);const a=this._allocateTargets();return a.depthBuffer=!0,this._sceneToCubeUV(e,n,i,a),t>0&&this._blur(a,0,0,t),this._applyPMREM(a),this._cleanup(a),a}fromEquirectangular(e,t=null){return this._fromTexture(e,t)}fromCubemap(e,t=null){return this._fromTexture(e,t)}compileCubemapShader(){this._cubemapMaterial===null&&(this._cubemapMaterial=ao(),this._compileMaterial(this._cubemapMaterial))}compileEquirectangularShader(){this._equirectMaterial===null&&(this._equirectMaterial=oo(),this._compileMaterial(this._equirectMaterial))}dispose(){this._dispose(),this._cubemapMaterial!==null&&this._cubemapMaterial.dispose(),this._equirectMaterial!==null&&this._equirectMaterial.dispose()}_setSize(e){this._lodMax=Math.floor(Math.log2(e)),this._cubeSize=Math.pow(2,this._lodMax)}_dispose(){this._blurMaterial!==null&&this._blurMaterial.dispose(),this._pingPongRenderTarget!==null&&this._pingPongRenderTarget.dispose();for(let e=0;e<this._lodPlanes.length;e++)this._lodPlanes[e].dispose()}_cleanup(e){this._renderer.setRenderTarget(o7),e.scissorTest=!1,x8(e,0,0,e.width,e.height)}_fromTexture(e,t){e.mapping===G9||e.mapping===V9?this._setSize(e.image.length===0?16:e.image[0].width||e.image[0].image.width):this._setSize(e.image.width/4),o7=this._renderer.getRenderTarget();const n=t||this._allocateTargets();return this._textureToCubeUV(e,n),this._applyPMREM(n),this._cleanup(n),n}_allocateTargets(){const e=3*Math.max(this._cubeSize,112),t=4*this._cubeSize,n={magFilter:Z3,minFilter:Z3,generateMipmaps:!1,type:C5,format:y4,encoding:W0,depthBuffer:!1},i=io(e,t,n);if(this._pingPongRenderTarget===null||this._pingPongRenderTarget.width!==e){this._pingPongRenderTarget!==null&&this._dispose(),this._pingPongRenderTarget=io(e,t,n);const{_lodMax:a}=this;({sizeLods:this._sizeLods,lodPlanes:this._lodPlanes,sigmas:this._sigmas}=Kw(a)),this._blurMaterial=eC(a,e,t)}return i}_compileMaterial(e){const t=new u3(this._lodPlanes[0],e);this._renderer.compile(t,i7)}_sceneToCubeUV(e,t,n,i){const s=new q3(90,1,t,n),u=[1,-1,1,1,1,1],c=[1,1,1,-1,-1,-1],f=this._renderer,p=f.autoClear,h=f.toneMapping;f.getClearColor(eo),f.toneMapping=F4,f.autoClear=!1;const m=new k4({name:"PMREM.Background",side:n4,depthWrite:!1,depthTest:!1}),y=new u3(new W5,m);let _=!1;const v=e.background;v?v.isColor&&(m.color.copy(v),e.background=null,_=!0):(m.color.copy(eo),_=!0);for(let b=0;b<6;b++){const x=b%3;x===0?(s.up.set(0,u[b],0),s.lookAt(c[b],0,0)):x===1?(s.up.set(0,0,u[b]),s.lookAt(0,c[b],0)):(s.up.set(0,u[b],0),s.lookAt(0,0,c[b]));const A=this._cubeSize;x8(i,x*A,b>2?A:0,A,A),f.setRenderTarget(i),_&&f.render(y,s),f.render(e,s)}y.geometry.dispose(),y.material.dispose(),f.toneMapping=h,f.autoClear=p,e.background=v}_textureToCubeUV(e,t){const n=this._renderer,i=e.mapping===G9||e.mapping===V9;i?(this._cubemapMaterial===null&&(this._cubemapMaterial=ao()),this._cubemapMaterial.uniforms.flipEnvMap.value=e.isRenderTargetTexture===!1?-1:1):this._equirectMaterial===null&&(this._equirectMaterial=oo());const a=i?this._cubemapMaterial:this._equirectMaterial,r=new u3(this._lodPlanes[0],a),s=a.uniforms;s.envMap.value=e;const u=this._cubeSize;x8(t,0,0,3*u,2*u),n.setRenderTarget(t),n.render(r,i7)}_applyPMREM(e){const t=this._renderer,n=t.autoClear;t.autoClear=!1;for(let i=1;i<this._lodPlanes.length;i++){const a=Math.sqrt(this._sigmas[i]*this._sigmas[i]-this._sigmas[i-1]*this._sigmas[i-1]),r=to[(i-1)%to.length];this._blur(e,i-1,i,a,r)}t.autoClear=n}_blur(e,t,n,i,a){const r=this._pingPongRenderTarget;this._halfBlur(e,r,t,n,i,"latitudinal",a),this._halfBlur(r,e,n,n,i,"longitudinal",a)}_halfBlur(e,t,n,i,a,r,s){const u=this._renderer,c=this._blurMaterial;r!=="latitudinal"&&r!=="longitudinal"&&console.error("blur direction must be either latitudinal or longitudinal!");const f=3,p=new u3(this._lodPlanes[i],c),h=c.uniforms,m=this._sizeLods[n]-1,y=isFinite(a)?Math.PI/(2*m):2*Math.PI/(2*I0-1),_=a/y,v=isFinite(a)?1+Math.floor(f*_):I0;v>I0&&console.warn(`sigmaRadians, ${a}, is too large and will clip, as it requested ${v} samples when the maximum is set to ${I0}`);const b=[];let x=0;for(let D=0;D<I0;++D){const T=D/_,E=Math.exp(-T*T/2);b.push(E),D===0?x+=E:D<v&&(x+=2*E)}for(let D=0;D<b.length;D++)b[D]=b[D]/x;h.envMap.value=e.texture,h.samples.value=v,h.weights.value=b,h.latitudinal.value=r==="latitudinal",s&&(h.poleAxis.value=s);const{_lodMax:A}=this;h.dTheta.value=y,h.mipInt.value=A-n;const S=this._sizeLods[i],w=3*S*(i>A-R9?i-A+R9:0),M=4*(this._cubeSize-S);x8(t,w,M,3*S,2*S),u.setRenderTarget(t),u.render(p,i7)}}function Kw(o){const e=[],t=[],n=[];let i=o;const a=o-R9+1+Ki.length;for(let r=0;r<a;r++){const s=Math.pow(2,i);t.push(s);let u=1/s;r>o-R9?u=Ki[r-o+R9-1]:r===0&&(u=0),n.push(u);const c=1/(s-2),f=-c,p=1+c,h=[f,f,p,f,p,p,f,f,p,p,f,p],m=6,y=6,_=3,v=2,b=1,x=new Float32Array(_*y*m),A=new Float32Array(v*y*m),S=new Float32Array(b*y*m);for(let M=0;M<m;M++){const D=M%3*2/3-1,T=M>2?0:-1,E=[D,T,0,D+2/3,T,0,D+2/3,T+1,0,D,T,0,D+2/3,T+1,0,D,T+1,0];x.set(E,_*y*M),A.set(h,v*y*M);const U=[M,M,M,M,M,M];S.set(U,b*y*M)}const w=new O3;w.setAttribute("position",new F3(x,_)),w.setAttribute("uv",new F3(A,v)),w.setAttribute("faceIndex",new F3(S,b)),e.push(w),i>R9&&i--}return{lodPlanes:e,sizeLods:t,sigmas:n}}function io(o,e,t){const n=new H0(o,e,t);return n.texture.mapping=l6,n.texture.name="PMREM.cubeUv",n.scissorTest=!0,n}function x8(o,e,t,n,i){o.viewport.set(e,t,n,i),o.scissor.set(e,t,n,i)}function eC(o,e,t){const n=new Float32Array(I0),i=new Y(0,1,0);return new G4({name:"SphericalGaussianBlur",defines:{n:I0,CUBEUV_TEXEL_WIDTH:1/e,CUBEUV_TEXEL_HEIGHT:1/t,CUBEUV_MAX_MIP:`${o}.0`},uniforms:{envMap:{value:null},samples:{value:1},weights:{value:n},latitudinal:{value:!1},dTheta:{value:0},mipInt:{value:0},poleAxis:{value:i}},vertexShader:pn(),fragmentShader:`

			precision mediump float;
			precision mediump int;

			varying vec3 vOutputDirection;

			uniform sampler2D envMap;
			uniform int samples;
			uniform float weights[ n ];
			uniform bool latitudinal;
			uniform float dTheta;
			uniform float mipInt;
			uniform vec3 poleAxis;

			#define ENVMAP_TYPE_CUBE_UV
			#include <cube_uv_reflection_fragment>

			vec3 getSample( float theta, vec3 axis ) {

				float cosTheta = cos( theta );
				// Rodrigues' axis-angle rotation
				vec3 sampleDirection = vOutputDirection * cosTheta
					+ cross( axis, vOutputDirection ) * sin( theta )
					+ axis * dot( axis, vOutputDirection ) * ( 1.0 - cosTheta );

				return bilinearCubeUV( envMap, sampleDirection, mipInt );

			}

			void main() {

				vec3 axis = latitudinal ? poleAxis : cross( poleAxis, vOutputDirection );

				if ( all( equal( axis, vec3( 0.0 ) ) ) ) {

					axis = vec3( vOutputDirection.z, 0.0, - vOutputDirection.x );

				}

				axis = normalize( axis );

				gl_FragColor = vec4( 0.0, 0.0, 0.0, 1.0 );
				gl_FragColor.rgb += weights[ 0 ] * getSample( 0.0, axis );

				for ( int i = 1; i < n; i++ ) {

					if ( i >= samples ) {

						break;

					}

					float theta = dTheta * float( i );
					gl_FragColor.rgb += weights[ i ] * getSample( -1.0 * theta, axis );
					gl_FragColor.rgb += weights[ i ] * getSample( theta, axis );

				}

			}
		`,blending:u0,depthTest:!1,depthWrite:!1})}function oo(){return new G4({name:"EquirectangularToCubeUV",uniforms:{envMap:{value:null}},vertexShader:pn(),fragmentShader:`

			precision mediump float;
			precision mediump int;

			varying vec3 vOutputDirection;

			uniform sampler2D envMap;

			#include <common>

			void main() {

				vec3 outputDirection = normalize( vOutputDirection );
				vec2 uv = equirectUv( outputDirection );

				gl_FragColor = vec4( texture2D ( envMap, uv ).rgb, 1.0 );

			}
		`,blending:u0,depthTest:!1,depthWrite:!1})}function ao(){return new G4({name:"CubemapToCubeUV",uniforms:{envMap:{value:null},flipEnvMap:{value:-1}},vertexShader:pn(),fragmentShader:`

			precision mediump float;
			precision mediump int;

			uniform float flipEnvMap;

			varying vec3 vOutputDirection;

			uniform samplerCube envMap;

			void main() {

				gl_FragColor = textureCube( envMap, vec3( flipEnvMap * vOutputDirection.x, vOutputDirection.yz ) );

			}
		`,blending:u0,depthTest:!1,depthWrite:!1})}function pn(){return`

		precision mediump float;
		precision mediump int;

		attribute float faceIndex;

		varying vec3 vOutputDirection;

		// RH coordinate system; PMREM face-indexing convention
		vec3 getDirection( vec2 uv, float face ) {

			uv = 2.0 * uv - 1.0;

			vec3 direction = vec3( uv, 1.0 );

			if ( face == 0.0 ) {

				direction = direction.zyx; // ( 1, v, u ) pos x

			} else if ( face == 1.0 ) {

				direction = direction.xzy;
				direction.xz *= -1.0; // ( -u, 1, -v ) pos y

			} else if ( face == 2.0 ) {

				direction.x *= -1.0; // ( -u, v, 1 ) pos z

			} else if ( face == 3.0 ) {

				direction = direction.zyx;
				direction.xz *= -1.0; // ( -1, v, -u ) neg x

			} else if ( face == 4.0 ) {

				direction = direction.xzy;
				direction.xy *= -1.0; // ( -u, -1, v ) neg y

			} else if ( face == 5.0 ) {

				direction.z *= -1.0; // ( u, v, -1 ) neg z

			}

			return direction;

		}

		void main() {

			vOutputDirection = getDirection( uv, faceIndex );
			gl_Position = vec4( position, 1.0 );

		}
	`}function tC(o){let e=new WeakMap,t=null;function n(s){if(s&&s.isTexture){const u=s.mapping,c=u===we||u===Ce,f=u===G9||u===V9;if(c||f)if(s.isRenderTargetTexture&&s.needsPMREMUpdate===!0){s.needsPMREMUpdate=!1;let p=e.get(s);return t===null&&(t=new no(o)),p=c?t.fromEquirectangular(s,p):t.fromCubemap(s,p),e.set(s,p),p.texture}else{if(e.has(s))return e.get(s).texture;{const p=s.image;if(c&&p&&p.height>0||f&&p&&i(p)){t===null&&(t=new no(o));const h=c?t.fromEquirectangular(s):t.fromCubemap(s);return e.set(s,h),s.addEventListener("dispose",a),h.texture}else return null}}}return s}function i(s){let u=0;const c=6;for(let f=0;f<c;f++)s[f]!==void 0&&u++;return u===c}function a(s){const u=s.target;u.removeEventListener("dispose",a);const c=e.get(u);c!==void 0&&(e.delete(u),c.dispose())}function r(){e=new WeakMap,t!==null&&(t.dispose(),t=null)}return{get:n,dispose:r}}function nC(o){const e={};function t(n){if(e[n]!==void 0)return e[n];let i;switch(n){case"WEBGL_depth_texture":i=o.getExtension("WEBGL_depth_texture")||o.getExtension("MOZ_WEBGL_depth_texture")||o.getExtension("WEBKIT_WEBGL_depth_texture");break;case"EXT_texture_filter_anisotropic":i=o.getExtension("EXT_texture_filter_anisotropic")||o.getExtension("MOZ_EXT_texture_filter_anisotropic")||o.getExtension("WEBKIT_EXT_texture_filter_anisotropic");break;case"WEBGL_compressed_texture_s3tc":i=o.getExtension("WEBGL_compressed_texture_s3tc")||o.getExtension("MOZ_WEBGL_compressed_texture_s3tc")||o.getExtension("WEBKIT_WEBGL_compressed_texture_s3tc");break;case"WEBGL_compressed_texture_pvrtc":i=o.getExtension("WEBGL_compressed_texture_pvrtc")||o.getExtension("WEBKIT_WEBGL_compressed_texture_pvrtc");break;default:i=o.getExtension(n)}return e[n]=i,i}return{has:function(n){return t(n)!==null},init:function(n){n.isWebGL2?t("EXT_color_buffer_float"):(t("WEBGL_depth_texture"),t("OES_texture_float"),t("OES_texture_half_float"),t("OES_texture_half_float_linear"),t("OES_standard_derivatives"),t("OES_element_index_uint"),t("OES_vertex_array_object"),t("ANGLE_instanced_arrays")),t("OES_texture_float_linear"),t("EXT_color_buffer_half_float"),t("WEBGL_multisampled_render_to_texture")},get:function(n){const i=t(n);return i===null&&console.warn("THREE.WebGLRenderer: "+n+" extension not supported."),i}}}function iC(o,e,t,n){const i={},a=new WeakMap;function r(p){const h=p.target;h.index!==null&&e.remove(h.index);for(const y in h.attributes)e.remove(h.attributes[y]);h.removeEventListener("dispose",r),delete i[h.id];const m=a.get(h);m&&(e.remove(m),a.delete(h)),n.releaseStatesOfGeometry(h),h.isInstancedBufferGeometry===!0&&delete h._maxInstanceCount,t.memory.geometries--}function s(p,h){return i[h.id]===!0||(h.addEventListener("dispose",r),i[h.id]=!0,t.memory.geometries++),h}function u(p){const h=p.attributes;for(const y in h)e.update(h[y],34962);const m=p.morphAttributes;for(const y in m){const _=m[y];for(let v=0,b=_.length;v<b;v++)e.update(_[v],34962)}}function c(p){const h=[],m=p.index,y=p.attributes.position;let _=0;if(m!==null){const x=m.array;_=m.version;for(let A=0,S=x.length;A<S;A+=3){const w=x[A+0],M=x[A+1],D=x[A+2];h.push(w,M,M,D,D,w)}}else{const x=y.array;_=y.version;for(let A=0,S=x.length/3-1;A<S;A+=3){const w=A+0,M=A+1,D=A+2;h.push(w,M,M,D,D,w)}}const v=new(Ar(h)?Rr:Ir)(h,1);v.version=_;const b=a.get(p);b&&e.remove(b),a.set(p,v)}function f(p){const h=a.get(p);if(h){const m=p.index;m!==null&&h.version<m.version&&c(p)}else c(p);return a.get(p)}return{get:s,update:u,getWireframeAttribute:f}}function oC(o,e,t,n){const i=n.isWebGL2;let a;function r(h){a=h}let s,u;function c(h){s=h.type,u=h.bytesPerElement}function f(h,m){o.drawElements(a,m,s,h*u),t.update(m,a,1)}function p(h,m,y){if(y===0)return;let _,v;if(i)_=o,v="drawElementsInstanced";else if(_=e.get("ANGLE_instanced_arrays"),v="drawElementsInstancedANGLE",_===null){console.error("THREE.WebGLIndexedBufferRenderer: using THREE.InstancedBufferGeometry but hardware does not support extension ANGLE_instanced_arrays.");return}_[v](a,m,s,h*u,y),t.update(m,a,y)}this.setMode=r,this.setIndex=c,this.render=f,this.renderInstances=p}function aC(o){const e={geometries:0,textures:0},t={frame:0,calls:0,triangles:0,points:0,lines:0};function n(a,r,s){switch(t.calls++,r){case 4:t.triangles+=s*(a/3);break;case 1:t.lines+=s*(a/2);break;case 3:t.lines+=s*(a-1);break;case 2:t.lines+=s*a;break;case 0:t.points+=s*a;break;default:console.error("THREE.WebGLInfo: Unknown draw mode:",r);break}}function i(){t.frame++,t.calls=0,t.triangles=0,t.points=0,t.lines=0}return{memory:e,render:t,programs:null,autoReset:!0,reset:i,update:n}}function rC(o,e){return o[0]-e[0]}function sC(o,e){return Math.abs(e[1])-Math.abs(o[1])}function lC(o,e,t){const n={},i=new Float32Array(8),a=new WeakMap,r=new c3,s=[];for(let c=0;c<8;c++)s[c]=[c,0];function u(c,f,p,h){const m=c.morphTargetInfluences;if(e.isWebGL2===!0){const y=f.morphAttributes.position||f.morphAttributes.normal||f.morphAttributes.color,_=y!==void 0?y.length:0;let v=a.get(f);if(v===void 0||v.count!==_){let F=function(){$.dispose(),a.delete(f),f.removeEventListener("dispose",F)};v!==void 0&&v.texture.dispose();const A=f.morphAttributes.position!==void 0,S=f.morphAttributes.normal!==void 0,w=f.morphAttributes.color!==void 0,M=f.morphAttributes.position||[],D=f.morphAttributes.normal||[],T=f.morphAttributes.color||[];let E=0;A===!0&&(E=1),S===!0&&(E=2),w===!0&&(E=3);let U=f.attributes.position.count*E,V=1;U>e.maxTextureSize&&(V=Math.ceil(U/e.maxTextureSize),U=e.maxTextureSize);const q=new Float32Array(U*V*4*_),$=new Er(q,U,V,_);$.type=O0,$.needsUpdate=!0;const z=E*4;for(let G=0;G<_;G++){const J=M[G],O=D[G],N=T[G],B=U*V*4*G;for(let t1=0;t1<J.count;t1++){const n1=t1*z;A===!0&&(r.fromBufferAttribute(J,t1),q[B+n1+0]=r.x,q[B+n1+1]=r.y,q[B+n1+2]=r.z,q[B+n1+3]=0),S===!0&&(r.fromBufferAttribute(O,t1),q[B+n1+4]=r.x,q[B+n1+5]=r.y,q[B+n1+6]=r.z,q[B+n1+7]=0),w===!0&&(r.fromBufferAttribute(N,t1),q[B+n1+8]=r.x,q[B+n1+9]=r.y,q[B+n1+10]=r.z,q[B+n1+11]=N.itemSize===4?r.w:1)}}v={count:_,texture:$,size:new G1(U,V)},a.set(f,v),f.addEventListener("dispose",F)}let b=0;for(let A=0;A<m.length;A++)b+=m[A];const x=f.morphTargetsRelative?1:1-b;h.getUniforms().setValue(o,"morphTargetBaseInfluence",x),h.getUniforms().setValue(o,"morphTargetInfluences",m),h.getUniforms().setValue(o,"morphTargetsTexture",v.texture,t),h.getUniforms().setValue(o,"morphTargetsTextureSize",v.size)}else{const y=m===void 0?0:m.length;let _=n[f.id];if(_===void 0||_.length!==y){_=[];for(let S=0;S<y;S++)_[S]=[S,0];n[f.id]=_}for(let S=0;S<y;S++){const w=_[S];w[0]=S,w[1]=m[S]}_.sort(sC);for(let S=0;S<8;S++)S<y&&_[S][1]?(s[S][0]=_[S][0],s[S][1]=_[S][1]):(s[S][0]=Number.MAX_SAFE_INTEGER,s[S][1]=0);s.sort(rC);const v=f.morphAttributes.position,b=f.morphAttributes.normal;let x=0;for(let S=0;S<8;S++){const w=s[S],M=w[0],D=w[1];M!==Number.MAX_SAFE_INTEGER&&D?(v&&f.getAttribute("morphTarget"+S)!==v[M]&&f.setAttribute("morphTarget"+S,v[M]),b&&f.getAttribute("morphNormal"+S)!==b[M]&&f.setAttribute("morphNormal"+S,b[M]),i[S]=D,x+=D):(v&&f.hasAttribute("morphTarget"+S)===!0&&f.deleteAttribute("morphTarget"+S),b&&f.hasAttribute("morphNormal"+S)===!0&&f.deleteAttribute("morphNormal"+S),i[S]=0)}const A=f.morphTargetsRelative?1:1-x;h.getUniforms().setValue(o,"morphTargetBaseInfluence",A),h.getUniforms().setValue(o,"morphTargetInfluences",i)}}return{update:u}}function uC(o,e,t,n){let i=new WeakMap;function a(u){const c=n.render.frame,f=u.geometry,p=e.get(u,f);return i.get(p)!==c&&(e.update(p),i.set(p,c)),u.isInstancedMesh&&(u.hasEventListener("dispose",s)===!1&&u.addEventListener("dispose",s),t.update(u.instanceMatrix,34962),u.instanceColor!==null&&t.update(u.instanceColor,34962)),p}function r(){i=new WeakMap}function s(u){const c=u.target;c.removeEventListener("dispose",s),t.remove(c.instanceMatrix),c.instanceColor!==null&&t.remove(c.instanceColor)}return{update:a,dispose:r}}const kr=new i4,Nr=new Er,Ur=new qS,Fr=new Pr,ro=[],so=[],lo=new Float32Array(16),uo=new Float32Array(9),co=new Float32Array(4);function Q9(o,e,t){const n=o[0];if(n<=0||n>0)return o;const i=e*t;let a=ro[i];if(a===void 0&&(a=new Float32Array(i),ro[i]=a),e!==0){n.toArray(a,0);for(let r=1,s=0;r!==e;++r)s+=t,o[r].toArray(a,s)}return a}function t3(o,e){if(o.length!==e.length)return!1;for(let t=0,n=o.length;t<n;t++)if(o[t]!==e[t])return!1;return!0}function n3(o,e){for(let t=0,n=e.length;t<n;t++)o[t]=e[t]}function c6(o,e){let t=so[e];t===void 0&&(t=new Int32Array(e),so[e]=t);for(let n=0;n!==e;++n)t[n]=o.allocateTextureUnit();return t}function cC(o,e){const t=this.cache;t[0]!==e&&(o.uniform1f(this.addr,e),t[0]=e)}function dC(o,e){const t=this.cache;if(e.x!==void 0)(t[0]!==e.x||t[1]!==e.y)&&(o.uniform2f(this.addr,e.x,e.y),t[0]=e.x,t[1]=e.y);else{if(t3(t,e))return;o.uniform2fv(this.addr,e),n3(t,e)}}function fC(o,e){const t=this.cache;if(e.x!==void 0)(t[0]!==e.x||t[1]!==e.y||t[2]!==e.z)&&(o.uniform3f(this.addr,e.x,e.y,e.z),t[0]=e.x,t[1]=e.y,t[2]=e.z);else if(e.r!==void 0)(t[0]!==e.r||t[1]!==e.g||t[2]!==e.b)&&(o.uniform3f(this.addr,e.r,e.g,e.b),t[0]=e.r,t[1]=e.g,t[2]=e.b);else{if(t3(t,e))return;o.uniform3fv(this.addr,e),n3(t,e)}}function pC(o,e){const t=this.cache;if(e.x!==void 0)(t[0]!==e.x||t[1]!==e.y||t[2]!==e.z||t[3]!==e.w)&&(o.uniform4f(this.addr,e.x,e.y,e.z,e.w),t[0]=e.x,t[1]=e.y,t[2]=e.z,t[3]=e.w);else{if(t3(t,e))return;o.uniform4fv(this.addr,e),n3(t,e)}}function hC(o,e){const t=this.cache,n=e.elements;if(n===void 0){if(t3(t,e))return;o.uniformMatrix2fv(this.addr,!1,e),n3(t,e)}else{if(t3(t,n))return;co.set(n),o.uniformMatrix2fv(this.addr,!1,co),n3(t,n)}}function mC(o,e){const t=this.cache,n=e.elements;if(n===void 0){if(t3(t,e))return;o.uniformMatrix3fv(this.addr,!1,e),n3(t,e)}else{if(t3(t,n))return;uo.set(n),o.uniformMatrix3fv(this.addr,!1,uo),n3(t,n)}}function gC(o,e){const t=this.cache,n=e.elements;if(n===void 0){if(t3(t,e))return;o.uniformMatrix4fv(this.addr,!1,e),n3(t,e)}else{if(t3(t,n))return;lo.set(n),o.uniformMatrix4fv(this.addr,!1,lo),n3(t,n)}}function vC(o,e){const t=this.cache;t[0]!==e&&(o.uniform1i(this.addr,e),t[0]=e)}function _C(o,e){const t=this.cache;t3(t,e)||(o.uniform2iv(this.addr,e),n3(t,e))}function yC(o,e){const t=this.cache;t3(t,e)||(o.uniform3iv(this.addr,e),n3(t,e))}function bC(o,e){const t=this.cache;t3(t,e)||(o.uniform4iv(this.addr,e),n3(t,e))}function xC(o,e){const t=this.cache;t[0]!==e&&(o.uniform1ui(this.addr,e),t[0]=e)}function SC(o,e){const t=this.cache;t3(t,e)||(o.uniform2uiv(this.addr,e),n3(t,e))}function MC(o,e){const t=this.cache;t3(t,e)||(o.uniform3uiv(this.addr,e),n3(t,e))}function AC(o,e){const t=this.cache;t3(t,e)||(o.uniform4uiv(this.addr,e),n3(t,e))}function wC(o,e,t){const n=this.cache,i=t.allocateTextureUnit();n[0]!==i&&(o.uniform1i(this.addr,i),n[0]=i),t.setTexture2D(e||kr,i)}function CC(o,e,t){const n=this.cache,i=t.allocateTextureUnit();n[0]!==i&&(o.uniform1i(this.addr,i),n[0]=i),t.setTexture3D(e||Ur,i)}function TC(o,e,t){const n=this.cache,i=t.allocateTextureUnit();n[0]!==i&&(o.uniform1i(this.addr,i),n[0]=i),t.setTextureCube(e||Fr,i)}function EC(o,e,t){const n=this.cache,i=t.allocateTextureUnit();n[0]!==i&&(o.uniform1i(this.addr,i),n[0]=i),t.setTexture2DArray(e||Nr,i)}function DC(o){switch(o){case 5126:return cC;case 35664:return dC;case 35665:return fC;case 35666:return pC;case 35674:return hC;case 35675:return mC;case 35676:return gC;case 5124:case 35670:return vC;case 35667:case 35671:return _C;case 35668:case 35672:return yC;case 35669:case 35673:return bC;case 5125:return xC;case 36294:return SC;case 36295:return MC;case 36296:return AC;case 35678:case 36198:case 36298:case 36306:case 35682:return wC;case 35679:case 36299:case 36307:return CC;case 35680:case 36300:case 36308:case 36293:return TC;case 36289:case 36303:case 36311:case 36292:return EC}}function IC(o,e){o.uniform1fv(this.addr,e)}function RC(o,e){const t=Q9(e,this.size,2);o.uniform2fv(this.addr,t)}function OC(o,e){const t=Q9(e,this.size,3);o.uniform3fv(this.addr,t)}function PC(o,e){const t=Q9(e,this.size,4);o.uniform4fv(this.addr,t)}function LC(o,e){const t=Q9(e,this.size,4);o.uniformMatrix2fv(this.addr,!1,t)}function zC(o,e){const t=Q9(e,this.size,9);o.uniformMatrix3fv(this.addr,!1,t)}function kC(o,e){const t=Q9(e,this.size,16);o.uniformMatrix4fv(this.addr,!1,t)}function NC(o,e){o.uniform1iv(this.addr,e)}function UC(o,e){o.uniform2iv(this.addr,e)}function FC(o,e){o.uniform3iv(this.addr,e)}function BC(o,e){o.uniform4iv(this.addr,e)}function GC(o,e){o.uniform1uiv(this.addr,e)}function VC(o,e){o.uniform2uiv(this.addr,e)}function jC(o,e){o.uniform3uiv(this.addr,e)}function WC(o,e){o.uniform4uiv(this.addr,e)}function HC(o,e,t){const n=this.cache,i=e.length,a=c6(t,i);t3(n,a)||(o.uniform1iv(this.addr,a),n3(n,a));for(let r=0;r!==i;++r)t.setTexture2D(e[r]||kr,a[r])}function $C(o,e,t){const n=this.cache,i=e.length,a=c6(t,i);t3(n,a)||(o.uniform1iv(this.addr,a),n3(n,a));for(let r=0;r!==i;++r)t.setTexture3D(e[r]||Ur,a[r])}function YC(o,e,t){const n=this.cache,i=e.length,a=c6(t,i);t3(n,a)||(o.uniform1iv(this.addr,a),n3(n,a));for(let r=0;r!==i;++r)t.setTextureCube(e[r]||Fr,a[r])}function JC(o,e,t){const n=this.cache,i=e.length,a=c6(t,i);t3(n,a)||(o.uniform1iv(this.addr,a),n3(n,a));for(let r=0;r!==i;++r)t.setTexture2DArray(e[r]||Nr,a[r])}function ZC(o){switch(o){case 5126:return IC;case 35664:return RC;case 35665:return OC;case 35666:return PC;case 35674:return LC;case 35675:return zC;case 35676:return kC;case 5124:case 35670:return NC;case 35667:case 35671:return UC;case 35668:case 35672:return FC;case 35669:case 35673:return BC;case 5125:return GC;case 36294:return VC;case 36295:return jC;case 36296:return WC;case 35678:case 36198:case 36298:case 36306:case 35682:return HC;case 35679:case 36299:case 36307:return $C;case 35680:case 36300:case 36308:case 36293:return YC;case 36289:case 36303:case 36311:case 36292:return JC}}class qC{constructor(e,t,n){this.id=e,this.addr=n,this.cache=[],this.setValue=DC(t.type)}}class XC{constructor(e,t,n){this.id=e,this.addr=n,this.cache=[],this.size=t.size,this.setValue=ZC(t.type)}}class QC{constructor(e){this.id=e,this.seq=[],this.map={}}setValue(e,t,n){const i=this.seq;for(let a=0,r=i.length;a!==r;++a){const s=i[a];s.setValue(e,t[s.id],n)}}}const a7=/(\w+)(\])?(\[|\.)?/g;function fo(o,e){o.seq.push(e),o.map[e.id]=e}function KC(o,e,t){const n=o.name,i=n.length;for(a7.lastIndex=0;;){const a=a7.exec(n),r=a7.lastIndex;let s=a[1];const u=a[2]==="]",c=a[3];if(u&&(s=s|0),c===void 0||c==="["&&r+2===i){fo(t,c===void 0?new qC(s,o,e):new XC(s,o,e));break}else{let p=t.map[s];p===void 0&&(p=new QC(s),fo(t,p)),t=p}}}class L8{constructor(e,t){this.seq=[],this.map={};const n=e.getProgramParameter(t,35718);for(let i=0;i<n;++i){const a=e.getActiveUniform(t,i),r=e.getUniformLocation(t,a.name);KC(a,r,this)}}setValue(e,t,n,i){const a=this.map[t];a!==void 0&&a.setValue(e,n,i)}setOptional(e,t,n){const i=t[n];i!==void 0&&this.setValue(e,n,i)}static upload(e,t,n,i){for(let a=0,r=t.length;a!==r;++a){const s=t[a],u=n[s.id];u.needsUpdate!==!1&&s.setValue(e,u.value,i)}}static seqWithValue(e,t){const n=[];for(let i=0,a=e.length;i!==a;++i){const r=e[i];r.id in t&&n.push(r)}return n}}function po(o,e,t){const n=o.createShader(e);return o.shaderSource(n,t),o.compileShader(n),n}let eT=0;function tT(o,e){const t=o.split(`
`),n=[],i=Math.max(e-6,0),a=Math.min(e+6,t.length);for(let r=i;r<a;r++){const s=r+1;n.push(`${s===e?">":" "} ${s}: ${t[r]}`)}return n.join(`
`)}function nT(o){switch(o){case W0:return["Linear","( value )"];case F2:return["sRGB","( value )"];default:return console.warn("THREE.WebGLProgram: Unsupported encoding:",o),["Linear","( value )"]}}function ho(o,e,t){const n=o.getShaderParameter(e,35713),i=o.getShaderInfoLog(e).trim();if(n&&i==="")return"";const a=/ERROR: 0:(\d+)/.exec(i);if(a){const r=parseInt(a[1]);return t.toUpperCase()+`

`+i+`

`+tT(o.getShaderSource(e),r)}else return i}function iT(o,e){const t=nT(e);return"vec4 "+o+"( vec4 value ) { return LinearTo"+t[0]+t[1]+"; }"}function oT(o,e){let t;switch(e){case SS:t="Linear";break;case MS:t="Reinhard";break;case AS:t="OptimizedCineon";break;case wS:t="ACESFilmic";break;case CS:t="Custom";break;default:console.warn("THREE.WebGLProgram: Unsupported toneMapping:",e),t="Linear"}return"vec3 "+o+"( vec3 color ) { return "+t+"ToneMapping( color ); }"}function aT(o){return[o.extensionDerivatives||o.envMapCubeUVHeight||o.bumpMap||o.tangentSpaceNormalMap||o.clearcoatNormalMap||o.flatShading||o.shaderID==="physical"?"#extension GL_OES_standard_derivatives : enable":"",(o.extensionFragDepth||o.logarithmicDepthBuffer)&&o.rendererExtensionFragDepth?"#extension GL_EXT_frag_depth : enable":"",o.extensionDrawBuffers&&o.rendererExtensionDrawBuffers?"#extension GL_EXT_draw_buffers : require":"",(o.extensionShaderTextureLOD||o.envMap||o.transmission)&&o.rendererExtensionShaderTextureLod?"#extension GL_EXT_shader_texture_lod : enable":""].filter(h5).join(`
`)}function rT(o){const e=[];for(const t in o){const n=o[t];n!==!1&&e.push("#define "+t+" "+n)}return e.join(`
`)}function sT(o,e){const t={},n=o.getProgramParameter(e,35721);for(let i=0;i<n;i++){const a=o.getActiveAttrib(e,i),r=a.name;let s=1;a.type===35674&&(s=2),a.type===35675&&(s=3),a.type===35676&&(s=4),t[r]={type:a.type,location:o.getAttribLocation(e,r),locationSize:s}}return t}function h5(o){return o!==""}function mo(o,e){const t=e.numSpotLightShadows+e.numSpotLightMaps-e.numSpotLightShadowsWithMaps;return o.replace(/NUM_DIR_LIGHTS/g,e.numDirLights).replace(/NUM_SPOT_LIGHTS/g,e.numSpotLights).replace(/NUM_SPOT_LIGHT_MAPS/g,e.numSpotLightMaps).replace(/NUM_SPOT_LIGHT_COORDS/g,t).replace(/NUM_RECT_AREA_LIGHTS/g,e.numRectAreaLights).replace(/NUM_POINT_LIGHTS/g,e.numPointLights).replace(/NUM_HEMI_LIGHTS/g,e.numHemiLights).replace(/NUM_DIR_LIGHT_SHADOWS/g,e.numDirLightShadows).replace(/NUM_SPOT_LIGHT_SHADOWS_WITH_MAPS/g,e.numSpotLightShadowsWithMaps).replace(/NUM_SPOT_LIGHT_SHADOWS/g,e.numSpotLightShadows).replace(/NUM_POINT_LIGHT_SHADOWS/g,e.numPointLightShadows)}function go(o,e){return o.replace(/NUM_CLIPPING_PLANES/g,e.numClippingPlanes).replace(/UNION_CLIPPING_PLANES/g,e.numClippingPlanes-e.numClipIntersection)}const lT=/^[ \t]*#include +<([\w\d./]+)>/gm;function Oe(o){return o.replace(lT,uT)}function uT(o,e){const t=c2[e];if(t===void 0)throw new Error("Can not resolve #include <"+e+">");return Oe(t)}const cT=/#pragma unroll_loop_start\s+for\s*\(\s*int\s+i\s*=\s*(\d+)\s*;\s*i\s*<\s*(\d+)\s*;\s*i\s*\+\+\s*\)\s*{([\s\S]+?)}\s+#pragma unroll_loop_end/g;function vo(o){return o.replace(cT,dT)}function dT(o,e,t,n){let i="";for(let a=parseInt(e);a<parseInt(t);a++)i+=n.replace(/\[\s*i\s*\]/g,"[ "+a+" ]").replace(/UNROLLED_LOOP_INDEX/g,a);return i}function _o(o){let e="precision "+o.precision+` float;
precision `+o.precision+" int;";return o.precision==="highp"?e+=`
#define HIGH_PRECISION`:o.precision==="mediump"?e+=`
#define MEDIUM_PRECISION`:o.precision==="lowp"&&(e+=`
#define LOW_PRECISION`),e}function fT(o){let e="SHADOWMAP_TYPE_BASIC";return o.shadowMapType===_r?e="SHADOWMAP_TYPE_PCF":o.shadowMapType===eS?e="SHADOWMAP_TYPE_PCF_SOFT":o.shadowMapType===p5&&(e="SHADOWMAP_TYPE_VSM"),e}function pT(o){let e="ENVMAP_TYPE_CUBE";if(o.envMap)switch(o.envMapMode){case G9:case V9:e="ENVMAP_TYPE_CUBE";break;case l6:e="ENVMAP_TYPE_CUBE_UV";break}return e}function hT(o){let e="ENVMAP_MODE_REFLECTION";if(o.envMap)switch(o.envMapMode){case V9:e="ENVMAP_MODE_REFRACTION";break}return e}function mT(o){let e="ENVMAP_BLENDING_NONE";if(o.envMap)switch(o.combine){case xr:e="ENVMAP_BLENDING_MULTIPLY";break;case bS:e="ENVMAP_BLENDING_MIX";break;case xS:e="ENVMAP_BLENDING_ADD";break}return e}function gT(o){const e=o.envMapCubeUVHeight;if(e===null)return null;const t=Math.log2(e)-2,n=1/e;return{texelWidth:1/(3*Math.max(Math.pow(2,t),7*16)),texelHeight:n,maxMip:t}}function vT(o,e,t,n){const i=o.getContext(),a=t.defines;let r=t.vertexShader,s=t.fragmentShader;const u=fT(t),c=pT(t),f=hT(t),p=mT(t),h=gT(t),m=t.isWebGL2?"":aT(t),y=rT(a),_=i.createProgram();let v,b,x=t.glslVersion?"#version "+t.glslVersion+`
`:"";t.isRawShaderMaterial?(v=[y].filter(h5).join(`
`),v.length>0&&(v+=`
`),b=[m,y].filter(h5).join(`
`),b.length>0&&(b+=`
`)):(v=[_o(t),"#define SHADER_NAME "+t.shaderName,y,t.instancing?"#define USE_INSTANCING":"",t.instancingColor?"#define USE_INSTANCING_COLOR":"",t.supportsVertexTextures?"#define VERTEX_TEXTURES":"",t.useFog&&t.fog?"#define USE_FOG":"",t.useFog&&t.fogExp2?"#define FOG_EXP2":"",t.map?"#define USE_MAP":"",t.envMap?"#define USE_ENVMAP":"",t.envMap?"#define "+f:"",t.lightMap?"#define USE_LIGHTMAP":"",t.aoMap?"#define USE_AOMAP":"",t.emissiveMap?"#define USE_EMISSIVEMAP":"",t.bumpMap?"#define USE_BUMPMAP":"",t.normalMap?"#define USE_NORMALMAP":"",t.normalMap&&t.objectSpaceNormalMap?"#define OBJECTSPACE_NORMALMAP":"",t.normalMap&&t.tangentSpaceNormalMap?"#define TANGENTSPACE_NORMALMAP":"",t.clearcoatMap?"#define USE_CLEARCOATMAP":"",t.clearcoatRoughnessMap?"#define USE_CLEARCOAT_ROUGHNESSMAP":"",t.clearcoatNormalMap?"#define USE_CLEARCOAT_NORMALMAP":"",t.iridescenceMap?"#define USE_IRIDESCENCEMAP":"",t.iridescenceThicknessMap?"#define USE_IRIDESCENCE_THICKNESSMAP":"",t.displacementMap&&t.supportsVertexTextures?"#define USE_DISPLACEMENTMAP":"",t.specularMap?"#define USE_SPECULARMAP":"",t.specularIntensityMap?"#define USE_SPECULARINTENSITYMAP":"",t.specularColorMap?"#define USE_SPECULARCOLORMAP":"",t.roughnessMap?"#define USE_ROUGHNESSMAP":"",t.metalnessMap?"#define USE_METALNESSMAP":"",t.alphaMap?"#define USE_ALPHAMAP":"",t.transmission?"#define USE_TRANSMISSION":"",t.transmissionMap?"#define USE_TRANSMISSIONMAP":"",t.thicknessMap?"#define USE_THICKNESSMAP":"",t.sheenColorMap?"#define USE_SHEENCOLORMAP":"",t.sheenRoughnessMap?"#define USE_SHEENROUGHNESSMAP":"",t.vertexTangents?"#define USE_TANGENT":"",t.vertexColors?"#define USE_COLOR":"",t.vertexAlphas?"#define USE_COLOR_ALPHA":"",t.vertexUvs?"#define USE_UV":"",t.uvsVertexOnly?"#define UVS_VERTEX_ONLY":"",t.flatShading?"#define FLAT_SHADED":"",t.skinning?"#define USE_SKINNING":"",t.morphTargets?"#define USE_MORPHTARGETS":"",t.morphNormals&&t.flatShading===!1?"#define USE_MORPHNORMALS":"",t.morphColors&&t.isWebGL2?"#define USE_MORPHCOLORS":"",t.morphTargetsCount>0&&t.isWebGL2?"#define MORPHTARGETS_TEXTURE":"",t.morphTargetsCount>0&&t.isWebGL2?"#define MORPHTARGETS_TEXTURE_STRIDE "+t.morphTextureStride:"",t.morphTargetsCount>0&&t.isWebGL2?"#define MORPHTARGETS_COUNT "+t.morphTargetsCount:"",t.doubleSided?"#define DOUBLE_SIDED":"",t.flipSided?"#define FLIP_SIDED":"",t.shadowMapEnabled?"#define USE_SHADOWMAP":"",t.shadowMapEnabled?"#define "+u:"",t.sizeAttenuation?"#define USE_SIZEATTENUATION":"",t.logarithmicDepthBuffer?"#define USE_LOGDEPTHBUF":"",t.logarithmicDepthBuffer&&t.rendererExtensionFragDepth?"#define USE_LOGDEPTHBUF_EXT":"","uniform mat4 modelMatrix;","uniform mat4 modelViewMatrix;","uniform mat4 projectionMatrix;","uniform mat4 viewMatrix;","uniform mat3 normalMatrix;","uniform vec3 cameraPosition;","uniform bool isOrthographic;","#ifdef USE_INSTANCING","	attribute mat4 instanceMatrix;","#endif","#ifdef USE_INSTANCING_COLOR","	attribute vec3 instanceColor;","#endif","attribute vec3 position;","attribute vec3 normal;","attribute vec2 uv;","#ifdef USE_TANGENT","	attribute vec4 tangent;","#endif","#if defined( USE_COLOR_ALPHA )","	attribute vec4 color;","#elif defined( USE_COLOR )","	attribute vec3 color;","#endif","#if ( defined( USE_MORPHTARGETS ) && ! defined( MORPHTARGETS_TEXTURE ) )","	attribute vec3 morphTarget0;","	attribute vec3 morphTarget1;","	attribute vec3 morphTarget2;","	attribute vec3 morphTarget3;","	#ifdef USE_MORPHNORMALS","		attribute vec3 morphNormal0;","		attribute vec3 morphNormal1;","		attribute vec3 morphNormal2;","		attribute vec3 morphNormal3;","	#else","		attribute vec3 morphTarget4;","		attribute vec3 morphTarget5;","		attribute vec3 morphTarget6;","		attribute vec3 morphTarget7;","	#endif","#endif","#ifdef USE_SKINNING","	attribute vec4 skinIndex;","	attribute vec4 skinWeight;","#endif",`
`].filter(h5).join(`
`),b=[m,_o(t),"#define SHADER_NAME "+t.shaderName,y,t.useFog&&t.fog?"#define USE_FOG":"",t.useFog&&t.fogExp2?"#define FOG_EXP2":"",t.map?"#define USE_MAP":"",t.matcap?"#define USE_MATCAP":"",t.envMap?"#define USE_ENVMAP":"",t.envMap?"#define "+c:"",t.envMap?"#define "+f:"",t.envMap?"#define "+p:"",h?"#define CUBEUV_TEXEL_WIDTH "+h.texelWidth:"",h?"#define CUBEUV_TEXEL_HEIGHT "+h.texelHeight:"",h?"#define CUBEUV_MAX_MIP "+h.maxMip+".0":"",t.lightMap?"#define USE_LIGHTMAP":"",t.aoMap?"#define USE_AOMAP":"",t.emissiveMap?"#define USE_EMISSIVEMAP":"",t.bumpMap?"#define USE_BUMPMAP":"",t.normalMap?"#define USE_NORMALMAP":"",t.normalMap&&t.objectSpaceNormalMap?"#define OBJECTSPACE_NORMALMAP":"",t.normalMap&&t.tangentSpaceNormalMap?"#define TANGENTSPACE_NORMALMAP":"",t.clearcoat?"#define USE_CLEARCOAT":"",t.clearcoatMap?"#define USE_CLEARCOATMAP":"",t.clearcoatRoughnessMap?"#define USE_CLEARCOAT_ROUGHNESSMAP":"",t.clearcoatNormalMap?"#define USE_CLEARCOAT_NORMALMAP":"",t.iridescence?"#define USE_IRIDESCENCE":"",t.iridescenceMap?"#define USE_IRIDESCENCEMAP":"",t.iridescenceThicknessMap?"#define USE_IRIDESCENCE_THICKNESSMAP":"",t.specularMap?"#define USE_SPECULARMAP":"",t.specularIntensityMap?"#define USE_SPECULARINTENSITYMAP":"",t.specularColorMap?"#define USE_SPECULARCOLORMAP":"",t.roughnessMap?"#define USE_ROUGHNESSMAP":"",t.metalnessMap?"#define USE_METALNESSMAP":"",t.alphaMap?"#define USE_ALPHAMAP":"",t.alphaTest?"#define USE_ALPHATEST":"",t.sheen?"#define USE_SHEEN":"",t.sheenColorMap?"#define USE_SHEENCOLORMAP":"",t.sheenRoughnessMap?"#define USE_SHEENROUGHNESSMAP":"",t.transmission?"#define USE_TRANSMISSION":"",t.transmissionMap?"#define USE_TRANSMISSIONMAP":"",t.thicknessMap?"#define USE_THICKNESSMAP":"",t.decodeVideoTexture?"#define DECODE_VIDEO_TEXTURE":"",t.vertexTangents?"#define USE_TANGENT":"",t.vertexColors||t.instancingColor?"#define USE_COLOR":"",t.vertexAlphas?"#define USE_COLOR_ALPHA":"",t.vertexUvs?"#define USE_UV":"",t.uvsVertexOnly?"#define UVS_VERTEX_ONLY":"",t.gradientMap?"#define USE_GRADIENTMAP":"",t.flatShading?"#define FLAT_SHADED":"",t.doubleSided?"#define DOUBLE_SIDED":"",t.flipSided?"#define FLIP_SIDED":"",t.shadowMapEnabled?"#define USE_SHADOWMAP":"",t.shadowMapEnabled?"#define "+u:"",t.premultipliedAlpha?"#define PREMULTIPLIED_ALPHA":"",t.physicallyCorrectLights?"#define PHYSICALLY_CORRECT_LIGHTS":"",t.logarithmicDepthBuffer?"#define USE_LOGDEPTHBUF":"",t.logarithmicDepthBuffer&&t.rendererExtensionFragDepth?"#define USE_LOGDEPTHBUF_EXT":"","uniform mat4 viewMatrix;","uniform vec3 cameraPosition;","uniform bool isOrthographic;",t.toneMapping!==F4?"#define TONE_MAPPING":"",t.toneMapping!==F4?c2.tonemapping_pars_fragment:"",t.toneMapping!==F4?oT("toneMapping",t.toneMapping):"",t.dithering?"#define DITHERING":"",t.opaque?"#define OPAQUE":"",c2.encodings_pars_fragment,iT("linearToOutputTexel",t.outputEncoding),t.useDepthPacking?"#define DEPTH_PACKING "+t.depthPacking:"",`
`].filter(h5).join(`
`)),r=Oe(r),r=mo(r,t),r=go(r,t),s=Oe(s),s=mo(s,t),s=go(s,t),r=vo(r),s=vo(s),t.isWebGL2&&t.isRawShaderMaterial!==!0&&(x=`#version 300 es
`,v=["precision mediump sampler2DArray;","#define attribute in","#define varying out","#define texture2D texture"].join(`
`)+`
`+v,b=["#define varying in",t.glslVersion===Fi?"":"layout(location = 0) out highp vec4 pc_fragColor;",t.glslVersion===Fi?"":"#define gl_FragColor pc_fragColor","#define gl_FragDepthEXT gl_FragDepth","#define texture2D texture","#define textureCube texture","#define texture2DProj textureProj","#define texture2DLodEXT textureLod","#define texture2DProjLodEXT textureProjLod","#define textureCubeLodEXT textureLod","#define texture2DGradEXT textureGrad","#define texture2DProjGradEXT textureProjGrad","#define textureCubeGradEXT textureGrad"].join(`
`)+`
`+b);const A=x+v+r,S=x+b+s,w=po(i,35633,A),M=po(i,35632,S);if(i.attachShader(_,w),i.attachShader(_,M),t.index0AttributeName!==void 0?i.bindAttribLocation(_,0,t.index0AttributeName):t.morphTargets===!0&&i.bindAttribLocation(_,0,"position"),i.linkProgram(_),o.debug.checkShaderErrors){const E=i.getProgramInfoLog(_).trim(),U=i.getShaderInfoLog(w).trim(),V=i.getShaderInfoLog(M).trim();let q=!0,$=!0;if(i.getProgramParameter(_,35714)===!1){q=!1;const z=ho(i,w,"vertex"),F=ho(i,M,"fragment");console.error("THREE.WebGLProgram: Shader Error "+i.getError()+" - VALIDATE_STATUS "+i.getProgramParameter(_,35715)+`

Program Info Log: `+E+`
`+z+`
`+F)}else E!==""?console.warn("THREE.WebGLProgram: Program Info Log:",E):(U===""||V==="")&&($=!1);$&&(this.diagnostics={runnable:q,programLog:E,vertexShader:{log:U,prefix:v},fragmentShader:{log:V,prefix:b}})}i.deleteShader(w),i.deleteShader(M);let D;this.getUniforms=function(){return D===void 0&&(D=new L8(i,_)),D};let T;return this.getAttributes=function(){return T===void 0&&(T=sT(i,_)),T},this.destroy=function(){n.releaseStatesOfProgram(this),i.deleteProgram(_),this.program=void 0},this.name=t.shaderName,this.id=eT++,this.cacheKey=e,this.usedTimes=1,this.program=_,this.vertexShader=w,this.fragmentShader=M,this}let _T=0;class yT{constructor(){this.shaderCache=new Map,this.materialCache=new Map}update(e){const t=e.vertexShader,n=e.fragmentShader,i=this._getShaderStage(t),a=this._getShaderStage(n),r=this._getShaderCacheForMaterial(e);return r.has(i)===!1&&(r.add(i),i.usedTimes++),r.has(a)===!1&&(r.add(a),a.usedTimes++),this}remove(e){const t=this.materialCache.get(e);for(const n of t)n.usedTimes--,n.usedTimes===0&&this.shaderCache.delete(n.code);return this.materialCache.delete(e),this}getVertexShaderID(e){return this._getShaderStage(e.vertexShader).id}getFragmentShaderID(e){return this._getShaderStage(e.fragmentShader).id}dispose(){this.shaderCache.clear(),this.materialCache.clear()}_getShaderCacheForMaterial(e){const t=this.materialCache;let n=t.get(e);return n===void 0&&(n=new Set,t.set(e,n)),n}_getShaderStage(e){const t=this.shaderCache;let n=t.get(e);return n===void 0&&(n=new bT(e),t.set(e,n)),n}}class bT{constructor(e){this.id=_T++,this.code=e,this.usedTimes=0}}function xT(o,e,t,n,i,a,r){const s=new Dr,u=new yT,c=[],f=i.isWebGL2,p=i.logarithmicDepthBuffer,h=i.vertexTextures;let m=i.precision;const y={MeshDepthMaterial:"depth",MeshDistanceMaterial:"distanceRGBA",MeshNormalMaterial:"normal",MeshBasicMaterial:"basic",MeshLambertMaterial:"lambert",MeshPhongMaterial:"phong",MeshToonMaterial:"toon",MeshStandardMaterial:"physical",MeshPhysicalMaterial:"physical",MeshMatcapMaterial:"matcap",LineBasicMaterial:"basic",LineDashedMaterial:"dashed",PointsMaterial:"points",ShadowMaterial:"shadow",SpriteMaterial:"sprite"};function _(T,E,U,V,q){const $=V.fog,z=q.geometry,F=T.isMeshStandardMaterial?V.environment:null,G=(T.isMeshStandardMaterial?t:e).get(T.envMap||F),J=G&&G.mapping===l6?G.image.height:null,O=y[T.type];T.precision!==null&&(m=i.getMaxPrecision(T.precision),m!==T.precision&&console.warn("THREE.WebGLProgram.getParameters:",T.precision,"not supported, using",m,"instead."));const N=z.morphAttributes.position||z.morphAttributes.normal||z.morphAttributes.color,B=N!==void 0?N.length:0;let t1=0;z.morphAttributes.position!==void 0&&(t1=1),z.morphAttributes.normal!==void 0&&(t1=2),z.morphAttributes.color!==void 0&&(t1=3);let n1,u1,a1,Q;if(O){const L1=m4[O];n1=L1.vertexShader,u1=L1.fragmentShader}else n1=T.vertexShader,u1=T.fragmentShader,u.update(T),a1=u.getVertexShaderID(T),Q=u.getFragmentShaderID(T);const Z=o.getRenderTarget(),d1=T.alphaTest>0,m1=T.clearcoat>0,g1=T.iridescence>0;return{isWebGL2:f,shaderID:O,shaderName:T.type,vertexShader:n1,fragmentShader:u1,defines:T.defines,customVertexShaderID:a1,customFragmentShaderID:Q,isRawShaderMaterial:T.isRawShaderMaterial===!0,glslVersion:T.glslVersion,precision:m,instancing:q.isInstancedMesh===!0,instancingColor:q.isInstancedMesh===!0&&q.instanceColor!==null,supportsVertexTextures:h,outputEncoding:Z===null?o.outputEncoding:Z.isXRRenderTarget===!0?Z.texture.encoding:W0,map:!!T.map,matcap:!!T.matcap,envMap:!!G,envMapMode:G&&G.mapping,envMapCubeUVHeight:J,lightMap:!!T.lightMap,aoMap:!!T.aoMap,emissiveMap:!!T.emissiveMap,bumpMap:!!T.bumpMap,normalMap:!!T.normalMap,objectSpaceNormalMap:T.normalMapType===$S,tangentSpaceNormalMap:T.normalMapType===HS,decodeVideoTexture:!!T.map&&T.map.isVideoTexture===!0&&T.map.encoding===F2,clearcoat:m1,clearcoatMap:m1&&!!T.clearcoatMap,clearcoatRoughnessMap:m1&&!!T.clearcoatRoughnessMap,clearcoatNormalMap:m1&&!!T.clearcoatNormalMap,iridescence:g1,iridescenceMap:g1&&!!T.iridescenceMap,iridescenceThicknessMap:g1&&!!T.iridescenceThicknessMap,displacementMap:!!T.displacementMap,roughnessMap:!!T.roughnessMap,metalnessMap:!!T.metalnessMap,specularMap:!!T.specularMap,specularIntensityMap:!!T.specularIntensityMap,specularColorMap:!!T.specularColorMap,opaque:T.transparent===!1&&T.blending===k0,alphaMap:!!T.alphaMap,alphaTest:d1,gradientMap:!!T.gradientMap,sheen:T.sheen>0,sheenColorMap:!!T.sheenColorMap,sheenRoughnessMap:!!T.sheenRoughnessMap,transmission:T.transmission>0,transmissionMap:!!T.transmissionMap,thicknessMap:!!T.thicknessMap,combine:T.combine,vertexTangents:!!T.normalMap&&!!z.attributes.tangent,vertexColors:T.vertexColors,vertexAlphas:T.vertexColors===!0&&!!z.attributes.color&&z.attributes.color.itemSize===4,vertexUvs:!!T.map||!!T.bumpMap||!!T.normalMap||!!T.specularMap||!!T.alphaMap||!!T.emissiveMap||!!T.roughnessMap||!!T.metalnessMap||!!T.clearcoatMap||!!T.clearcoatRoughnessMap||!!T.clearcoatNormalMap||!!T.iridescenceMap||!!T.iridescenceThicknessMap||!!T.displacementMap||!!T.transmissionMap||!!T.thicknessMap||!!T.specularIntensityMap||!!T.specularColorMap||!!T.sheenColorMap||!!T.sheenRoughnessMap,uvsVertexOnly:!(T.map||T.bumpMap||T.normalMap||T.specularMap||T.alphaMap||T.emissiveMap||T.roughnessMap||T.metalnessMap||T.clearcoatNormalMap||T.iridescenceMap||T.iridescenceThicknessMap||T.transmission>0||T.transmissionMap||T.thicknessMap||T.specularIntensityMap||T.specularColorMap||T.sheen>0||T.sheenColorMap||T.sheenRoughnessMap)&&!!T.displacementMap,fog:!!$,useFog:T.fog===!0,fogExp2:$&&$.isFogExp2,flatShading:!!T.flatShading,sizeAttenuation:T.sizeAttenuation,logarithmicDepthBuffer:p,skinning:q.isSkinnedMesh===!0,morphTargets:z.morphAttributes.position!==void 0,morphNormals:z.morphAttributes.normal!==void 0,morphColors:z.morphAttributes.color!==void 0,morphTargetsCount:B,morphTextureStride:t1,numDirLights:E.directional.length,numPointLights:E.point.length,numSpotLights:E.spot.length,numSpotLightMaps:E.spotLightMap.length,numRectAreaLights:E.rectArea.length,numHemiLights:E.hemi.length,numDirLightShadows:E.directionalShadowMap.length,numPointLightShadows:E.pointShadowMap.length,numSpotLightShadows:E.spotShadowMap.length,numSpotLightShadowsWithMaps:E.numSpotLightShadowsWithMaps,numClippingPlanes:r.numPlanes,numClipIntersection:r.numIntersection,dithering:T.dithering,shadowMapEnabled:o.shadowMap.enabled&&U.length>0,shadowMapType:o.shadowMap.type,toneMapping:T.toneMapped?o.toneMapping:F4,physicallyCorrectLights:o.physicallyCorrectLights,premultipliedAlpha:T.premultipliedAlpha,doubleSided:T.side===_4,flipSided:T.side===n4,useDepthPacking:!!T.depthPacking,depthPacking:T.depthPacking||0,index0AttributeName:T.index0AttributeName,extensionDerivatives:T.extensions&&T.extensions.derivatives,extensionFragDepth:T.extensions&&T.extensions.fragDepth,extensionDrawBuffers:T.extensions&&T.extensions.drawBuffers,extensionShaderTextureLOD:T.extensions&&T.extensions.shaderTextureLOD,rendererExtensionFragDepth:f||n.has("EXT_frag_depth"),rendererExtensionDrawBuffers:f||n.has("WEBGL_draw_buffers"),rendererExtensionShaderTextureLod:f||n.has("EXT_shader_texture_lod"),customProgramCacheKey:T.customProgramCacheKey()}}function v(T){const E=[];if(T.shaderID?E.push(T.shaderID):(E.push(T.customVertexShaderID),E.push(T.customFragmentShaderID)),T.defines!==void 0)for(const U in T.defines)E.push(U),E.push(T.defines[U]);return T.isRawShaderMaterial===!1&&(b(E,T),x(E,T),E.push(o.outputEncoding)),E.push(T.customProgramCacheKey),E.join()}function b(T,E){T.push(E.precision),T.push(E.outputEncoding),T.push(E.envMapMode),T.push(E.envMapCubeUVHeight),T.push(E.combine),T.push(E.vertexUvs),T.push(E.fogExp2),T.push(E.sizeAttenuation),T.push(E.morphTargetsCount),T.push(E.morphAttributeCount),T.push(E.numDirLights),T.push(E.numPointLights),T.push(E.numSpotLights),T.push(E.numSpotLightMaps),T.push(E.numHemiLights),T.push(E.numRectAreaLights),T.push(E.numDirLightShadows),T.push(E.numPointLightShadows),T.push(E.numSpotLightShadows),T.push(E.numSpotLightShadowsWithMaps),T.push(E.shadowMapType),T.push(E.toneMapping),T.push(E.numClippingPlanes),T.push(E.numClipIntersection),T.push(E.depthPacking)}function x(T,E){s.disableAll(),E.isWebGL2&&s.enable(0),E.supportsVertexTextures&&s.enable(1),E.instancing&&s.enable(2),E.instancingColor&&s.enable(3),E.map&&s.enable(4),E.matcap&&s.enable(5),E.envMap&&s.enable(6),E.lightMap&&s.enable(7),E.aoMap&&s.enable(8),E.emissiveMap&&s.enable(9),E.bumpMap&&s.enable(10),E.normalMap&&s.enable(11),E.objectSpaceNormalMap&&s.enable(12),E.tangentSpaceNormalMap&&s.enable(13),E.clearcoat&&s.enable(14),E.clearcoatMap&&s.enable(15),E.clearcoatRoughnessMap&&s.enable(16),E.clearcoatNormalMap&&s.enable(17),E.iridescence&&s.enable(18),E.iridescenceMap&&s.enable(19),E.iridescenceThicknessMap&&s.enable(20),E.displacementMap&&s.enable(21),E.specularMap&&s.enable(22),E.roughnessMap&&s.enable(23),E.metalnessMap&&s.enable(24),E.gradientMap&&s.enable(25),E.alphaMap&&s.enable(26),E.alphaTest&&s.enable(27),E.vertexColors&&s.enable(28),E.vertexAlphas&&s.enable(29),E.vertexUvs&&s.enable(30),E.vertexTangents&&s.enable(31),E.uvsVertexOnly&&s.enable(32),T.push(s.mask),s.disableAll(),E.fog&&s.enable(0),E.useFog&&s.enable(1),E.flatShading&&s.enable(2),E.logarithmicDepthBuffer&&s.enable(3),E.skinning&&s.enable(4),E.morphTargets&&s.enable(5),E.morphNormals&&s.enable(6),E.morphColors&&s.enable(7),E.premultipliedAlpha&&s.enable(8),E.shadowMapEnabled&&s.enable(9),E.physicallyCorrectLights&&s.enable(10),E.doubleSided&&s.enable(11),E.flipSided&&s.enable(12),E.useDepthPacking&&s.enable(13),E.dithering&&s.enable(14),E.specularIntensityMap&&s.enable(15),E.specularColorMap&&s.enable(16),E.transmission&&s.enable(17),E.transmissionMap&&s.enable(18),E.thicknessMap&&s.enable(19),E.sheen&&s.enable(20),E.sheenColorMap&&s.enable(21),E.sheenRoughnessMap&&s.enable(22),E.decodeVideoTexture&&s.enable(23),E.opaque&&s.enable(24),T.push(s.mask)}function A(T){const E=y[T.type];let U;if(E){const V=m4[E];U=lM.clone(V.uniforms)}else U=T.uniforms;return U}function S(T,E){let U;for(let V=0,q=c.length;V<q;V++){const $=c[V];if($.cacheKey===E){U=$,++U.usedTimes;break}}return U===void 0&&(U=new vT(o,E,T,a),c.push(U)),U}function w(T){if(--T.usedTimes===0){const E=c.indexOf(T);c[E]=c[c.length-1],c.pop(),T.destroy()}}function M(T){u.remove(T)}function D(){u.dispose()}return{getParameters:_,getProgramCacheKey:v,getUniforms:A,acquireProgram:S,releaseProgram:w,releaseShaderCache:M,programs:c,dispose:D}}function ST(){let o=new WeakMap;function e(a){let r=o.get(a);return r===void 0&&(r={},o.set(a,r)),r}function t(a){o.delete(a)}function n(a,r,s){o.get(a)[r]=s}function i(){o=new WeakMap}return{get:e,remove:t,update:n,dispose:i}}function MT(o,e){return o.groupOrder!==e.groupOrder?o.groupOrder-e.groupOrder:o.renderOrder!==e.renderOrder?o.renderOrder-e.renderOrder:o.material.id!==e.material.id?o.material.id-e.material.id:o.z!==e.z?o.z-e.z:o.id-e.id}function yo(o,e){return o.groupOrder!==e.groupOrder?o.groupOrder-e.groupOrder:o.renderOrder!==e.renderOrder?o.renderOrder-e.renderOrder:o.z!==e.z?e.z-o.z:o.id-e.id}function bo(){const o=[];let e=0;const t=[],n=[],i=[];function a(){e=0,t.length=0,n.length=0,i.length=0}function r(p,h,m,y,_,v){let b=o[e];return b===void 0?(b={id:p.id,object:p,geometry:h,material:m,groupOrder:y,renderOrder:p.renderOrder,z:_,group:v},o[e]=b):(b.id=p.id,b.object=p,b.geometry=h,b.material=m,b.groupOrder=y,b.renderOrder=p.renderOrder,b.z=_,b.group=v),e++,b}function s(p,h,m,y,_,v){const b=r(p,h,m,y,_,v);m.transmission>0?n.push(b):m.transparent===!0?i.push(b):t.push(b)}function u(p,h,m,y,_,v){const b=r(p,h,m,y,_,v);m.transmission>0?n.unshift(b):m.transparent===!0?i.unshift(b):t.unshift(b)}function c(p,h){t.length>1&&t.sort(p||MT),n.length>1&&n.sort(h||yo),i.length>1&&i.sort(h||yo)}function f(){for(let p=e,h=o.length;p<h;p++){const m=o[p];if(m.id===null)break;m.id=null,m.object=null,m.geometry=null,m.material=null,m.group=null}}return{opaque:t,transmissive:n,transparent:i,init:a,push:s,unshift:u,finish:f,sort:c}}function AT(){let o=new WeakMap;function e(n,i){const a=o.get(n);let r;return a===void 0?(r=new bo,o.set(n,[r])):i>=a.length?(r=new bo,a.push(r)):r=a[i],r}function t(){o=new WeakMap}return{get:e,dispose:t}}function wT(){const o={};return{get:function(e){if(o[e.id]!==void 0)return o[e.id];let t;switch(e.type){case"DirectionalLight":t={direction:new Y,color:new y2};break;case"SpotLight":t={position:new Y,direction:new Y,color:new y2,distance:0,coneCos:0,penumbraCos:0,decay:0};break;case"PointLight":t={position:new Y,color:new y2,distance:0,decay:0};break;case"HemisphereLight":t={direction:new Y,skyColor:new y2,groundColor:new y2};break;case"RectAreaLight":t={color:new y2,position:new Y,halfWidth:new Y,halfHeight:new Y};break}return o[e.id]=t,t}}}function CT(){const o={};return{get:function(e){if(o[e.id]!==void 0)return o[e.id];let t;switch(e.type){case"DirectionalLight":t={shadowBias:0,shadowNormalBias:0,shadowRadius:1,shadowMapSize:new G1};break;case"SpotLight":t={shadowBias:0,shadowNormalBias:0,shadowRadius:1,shadowMapSize:new G1};break;case"PointLight":t={shadowBias:0,shadowNormalBias:0,shadowRadius:1,shadowMapSize:new G1,shadowCameraNear:1,shadowCameraFar:1e3};break}return o[e.id]=t,t}}}let TT=0;function ET(o,e){return(e.castShadow?2:0)-(o.castShadow?2:0)+(e.map?1:0)-(o.map?1:0)}function DT(o,e){const t=new wT,n=CT(),i={version:0,hash:{directionalLength:-1,pointLength:-1,spotLength:-1,rectAreaLength:-1,hemiLength:-1,numDirectionalShadows:-1,numPointShadows:-1,numSpotShadows:-1,numSpotMaps:-1},ambient:[0,0,0],probe:[],directional:[],directionalShadow:[],directionalShadowMap:[],directionalShadowMatrix:[],spot:[],spotLightMap:[],spotShadow:[],spotShadowMap:[],spotLightMatrix:[],rectArea:[],rectAreaLTC1:null,rectAreaLTC2:null,point:[],pointShadow:[],pointShadowMap:[],pointShadowMatrix:[],hemi:[],numSpotLightShadowsWithMaps:0};for(let f=0;f<9;f++)i.probe.push(new Y);const a=new Y,r=new W2,s=new W2;function u(f,p){let h=0,m=0,y=0;for(let V=0;V<9;V++)i.probe[V].set(0,0,0);let _=0,v=0,b=0,x=0,A=0,S=0,w=0,M=0,D=0,T=0;f.sort(ET);const E=p!==!0?Math.PI:1;for(let V=0,q=f.length;V<q;V++){const $=f[V],z=$.color,F=$.intensity,G=$.distance,J=$.shadow&&$.shadow.map?$.shadow.map.texture:null;if($.isAmbientLight)h+=z.r*F*E,m+=z.g*F*E,y+=z.b*F*E;else if($.isLightProbe)for(let O=0;O<9;O++)i.probe[O].addScaledVector($.sh.coefficients[O],F);else if($.isDirectionalLight){const O=t.get($);if(O.color.copy($.color).multiplyScalar($.intensity*E),$.castShadow){const N=$.shadow,B=n.get($);B.shadowBias=N.bias,B.shadowNormalBias=N.normalBias,B.shadowRadius=N.radius,B.shadowMapSize=N.mapSize,i.directionalShadow[_]=B,i.directionalShadowMap[_]=J,i.directionalShadowMatrix[_]=$.shadow.matrix,S++}i.directional[_]=O,_++}else if($.isSpotLight){const O=t.get($);O.position.setFromMatrixPosition($.matrixWorld),O.color.copy(z).multiplyScalar(F*E),O.distance=G,O.coneCos=Math.cos($.angle),O.penumbraCos=Math.cos($.angle*(1-$.penumbra)),O.decay=$.decay,i.spot[b]=O;const N=$.shadow;if($.map&&(i.spotLightMap[D]=$.map,D++,N.updateMatrices($),$.castShadow&&T++),i.spotLightMatrix[b]=N.matrix,$.castShadow){const B=n.get($);B.shadowBias=N.bias,B.shadowNormalBias=N.normalBias,B.shadowRadius=N.radius,B.shadowMapSize=N.mapSize,i.spotShadow[b]=B,i.spotShadowMap[b]=J,M++}b++}else if($.isRectAreaLight){const O=t.get($);O.color.copy(z).multiplyScalar(F),O.halfWidth.set($.width*.5,0,0),O.halfHeight.set(0,$.height*.5,0),i.rectArea[x]=O,x++}else if($.isPointLight){const O=t.get($);if(O.color.copy($.color).multiplyScalar($.intensity*E),O.distance=$.distance,O.decay=$.decay,$.castShadow){const N=$.shadow,B=n.get($);B.shadowBias=N.bias,B.shadowNormalBias=N.normalBias,B.shadowRadius=N.radius,B.shadowMapSize=N.mapSize,B.shadowCameraNear=N.camera.near,B.shadowCameraFar=N.camera.far,i.pointShadow[v]=B,i.pointShadowMap[v]=J,i.pointShadowMatrix[v]=$.shadow.matrix,w++}i.point[v]=O,v++}else if($.isHemisphereLight){const O=t.get($);O.skyColor.copy($.color).multiplyScalar(F*E),O.groundColor.copy($.groundColor).multiplyScalar(F*E),i.hemi[A]=O,A++}}x>0&&(e.isWebGL2||o.has("OES_texture_float_linear")===!0?(i.rectAreaLTC1=I1.LTC_FLOAT_1,i.rectAreaLTC2=I1.LTC_FLOAT_2):o.has("OES_texture_half_float_linear")===!0?(i.rectAreaLTC1=I1.LTC_HALF_1,i.rectAreaLTC2=I1.LTC_HALF_2):console.error("THREE.WebGLRenderer: Unable to use RectAreaLight. Missing WebGL extensions.")),i.ambient[0]=h,i.ambient[1]=m,i.ambient[2]=y;const U=i.hash;(U.directionalLength!==_||U.pointLength!==v||U.spotLength!==b||U.rectAreaLength!==x||U.hemiLength!==A||U.numDirectionalShadows!==S||U.numPointShadows!==w||U.numSpotShadows!==M||U.numSpotMaps!==D)&&(i.directional.length=_,i.spot.length=b,i.rectArea.length=x,i.point.length=v,i.hemi.length=A,i.directionalShadow.length=S,i.directionalShadowMap.length=S,i.pointShadow.length=w,i.pointShadowMap.length=w,i.spotShadow.length=M,i.spotShadowMap.length=M,i.directionalShadowMatrix.length=S,i.pointShadowMatrix.length=w,i.spotLightMatrix.length=M+D-T,i.spotLightMap.length=D,i.numSpotLightShadowsWithMaps=T,U.directionalLength=_,U.pointLength=v,U.spotLength=b,U.rectAreaLength=x,U.hemiLength=A,U.numDirectionalShadows=S,U.numPointShadows=w,U.numSpotShadows=M,U.numSpotMaps=D,i.version=TT++)}function c(f,p){let h=0,m=0,y=0,_=0,v=0;const b=p.matrixWorldInverse;for(let x=0,A=f.length;x<A;x++){const S=f[x];if(S.isDirectionalLight){const w=i.directional[h];w.direction.setFromMatrixPosition(S.matrixWorld),a.setFromMatrixPosition(S.target.matrixWorld),w.direction.sub(a),w.direction.transformDirection(b),h++}else if(S.isSpotLight){const w=i.spot[y];w.position.setFromMatrixPosition(S.matrixWorld),w.position.applyMatrix4(b),w.direction.setFromMatrixPosition(S.matrixWorld),a.setFromMatrixPosition(S.target.matrixWorld),w.direction.sub(a),w.direction.transformDirection(b),y++}else if(S.isRectAreaLight){const w=i.rectArea[_];w.position.setFromMatrixPosition(S.matrixWorld),w.position.applyMatrix4(b),s.identity(),r.copy(S.matrixWorld),r.premultiply(b),s.extractRotation(r),w.halfWidth.set(S.width*.5,0,0),w.halfHeight.set(0,S.height*.5,0),w.halfWidth.applyMatrix4(s),w.halfHeight.applyMatrix4(s),_++}else if(S.isPointLight){const w=i.point[m];w.position.setFromMatrixPosition(S.matrixWorld),w.position.applyMatrix4(b),m++}else if(S.isHemisphereLight){const w=i.hemi[v];w.direction.setFromMatrixPosition(S.matrixWorld),w.direction.transformDirection(b),v++}}}return{setup:u,setupView:c,state:i}}function xo(o,e){const t=new DT(o,e),n=[],i=[];function a(){n.length=0,i.length=0}function r(p){n.push(p)}function s(p){i.push(p)}function u(p){t.setup(n,p)}function c(p){t.setupView(n,p)}return{init:a,state:{lightsArray:n,shadowsArray:i,lights:t},setupLights:u,setupLightsView:c,pushLight:r,pushShadow:s}}function IT(o,e){let t=new WeakMap;function n(a,r=0){const s=t.get(a);let u;return s===void 0?(u=new xo(o,e),t.set(a,[u])):r>=s.length?(u=new xo(o,e),s.push(u)):u=s[r],u}function i(){t=new WeakMap}return{get:n,dispose:i}}class RT extends q0{constructor(e){super(),this.isMeshDepthMaterial=!0,this.type="MeshDepthMaterial",this.depthPacking=jS,this.map=null,this.alphaMap=null,this.displacementMap=null,this.displacementScale=1,this.displacementBias=0,this.wireframe=!1,this.wireframeLinewidth=1,this.setValues(e)}copy(e){return super.copy(e),this.depthPacking=e.depthPacking,this.map=e.map,this.alphaMap=e.alphaMap,this.displacementMap=e.displacementMap,this.displacementScale=e.displacementScale,this.displacementBias=e.displacementBias,this.wireframe=e.wireframe,this.wireframeLinewidth=e.wireframeLinewidth,this}}class OT extends q0{constructor(e){super(),this.isMeshDistanceMaterial=!0,this.type="MeshDistanceMaterial",this.referencePosition=new Y,this.nearDistance=1,this.farDistance=1e3,this.map=null,this.alphaMap=null,this.displacementMap=null,this.displacementScale=1,this.displacementBias=0,this.setValues(e)}copy(e){return super.copy(e),this.referencePosition.copy(e.referencePosition),this.nearDistance=e.nearDistance,this.farDistance=e.farDistance,this.map=e.map,this.alphaMap=e.alphaMap,this.displacementMap=e.displacementMap,this.displacementScale=e.displacementScale,this.displacementBias=e.displacementBias,this}}const PT=`void main() {
	gl_Position = vec4( position, 1.0 );
}`,LT=`uniform sampler2D shadow_pass;
uniform vec2 resolution;
uniform float radius;
#include <packing>
void main() {
	const float samples = float( VSM_SAMPLES );
	float mean = 0.0;
	float squared_mean = 0.0;
	float uvStride = samples <= 1.0 ? 0.0 : 2.0 / ( samples - 1.0 );
	float uvStart = samples <= 1.0 ? 0.0 : - 1.0;
	for ( float i = 0.0; i < samples; i ++ ) {
		float uvOffset = uvStart + i * uvStride;
		#ifdef HORIZONTAL_PASS
			vec2 distribution = unpackRGBATo2Half( texture2D( shadow_pass, ( gl_FragCoord.xy + vec2( uvOffset, 0.0 ) * radius ) / resolution ) );
			mean += distribution.x;
			squared_mean += distribution.y * distribution.y + distribution.x * distribution.x;
		#else
			float depth = unpackRGBAToDepth( texture2D( shadow_pass, ( gl_FragCoord.xy + vec2( 0.0, uvOffset ) * radius ) / resolution ) );
			mean += depth;
			squared_mean += depth * depth;
		#endif
	}
	mean = mean / samples;
	squared_mean = squared_mean / samples;
	float std_dev = sqrt( squared_mean - mean * mean );
	gl_FragColor = pack2HalfToRGBA( vec2( mean, std_dev ) );
}`;function zT(o,e,t){let n=new Lr;const i=new G1,a=new G1,r=new c3,s=new RT({depthPacking:WS}),u=new OT,c={},f=t.maxTextureSize,p={0:n4,1:B9,2:_4},h=new G4({defines:{VSM_SAMPLES:8},uniforms:{shadow_pass:{value:null},resolution:{value:new G1},radius:{value:4}},vertexShader:PT,fragmentShader:LT}),m=h.clone();m.defines.HORIZONTAL_PASS=1;const y=new O3;y.setAttribute("position",new F3(new Float32Array([-1,-1,.5,3,-1,.5,-1,3,.5]),3));const _=new u3(y,h),v=this;this.enabled=!1,this.autoUpdate=!0,this.needsUpdate=!1,this.type=_r,this.render=function(S,w,M){if(v.enabled===!1||v.autoUpdate===!1&&v.needsUpdate===!1||S.length===0)return;const D=o.getRenderTarget(),T=o.getActiveCubeFace(),E=o.getActiveMipmapLevel(),U=o.state;U.setBlending(u0),U.buffers.color.setClear(1,1,1,1),U.buffers.depth.setTest(!0),U.setScissorTest(!1);for(let V=0,q=S.length;V<q;V++){const $=S[V],z=$.shadow;if(z===void 0){console.warn("THREE.WebGLShadowMap:",$,"has no shadow.");continue}if(z.autoUpdate===!1&&z.needsUpdate===!1)continue;i.copy(z.mapSize);const F=z.getFrameExtents();if(i.multiply(F),a.copy(z.mapSize),(i.x>f||i.y>f)&&(i.x>f&&(a.x=Math.floor(f/F.x),i.x=a.x*F.x,z.mapSize.x=a.x),i.y>f&&(a.y=Math.floor(f/F.y),i.y=a.y*F.y,z.mapSize.y=a.y)),z.map===null){const J=this.type!==p5?{minFilter:M3,magFilter:M3}:{};z.map=new H0(i.x,i.y,J),z.map.texture.name=$.name+".shadowMap",z.camera.updateProjectionMatrix()}o.setRenderTarget(z.map),o.clear();const G=z.getViewportCount();for(let J=0;J<G;J++){const O=z.getViewport(J);r.set(a.x*O.x,a.y*O.y,a.x*O.z,a.y*O.w),U.viewport(r),z.updateMatrices($,J),n=z.getFrustum(),A(w,M,z.camera,$,this.type)}z.isPointLightShadow!==!0&&this.type===p5&&b(z,M),z.needsUpdate=!1}v.needsUpdate=!1,o.setRenderTarget(D,T,E)};function b(S,w){const M=e.update(_);h.defines.VSM_SAMPLES!==S.blurSamples&&(h.defines.VSM_SAMPLES=S.blurSamples,m.defines.VSM_SAMPLES=S.blurSamples,h.needsUpdate=!0,m.needsUpdate=!0),S.mapPass===null&&(S.mapPass=new H0(i.x,i.y)),h.uniforms.shadow_pass.value=S.map.texture,h.uniforms.resolution.value=S.mapSize,h.uniforms.radius.value=S.radius,o.setRenderTarget(S.mapPass),o.clear(),o.renderBufferDirect(w,null,M,h,_,null),m.uniforms.shadow_pass.value=S.mapPass.texture,m.uniforms.resolution.value=S.mapSize,m.uniforms.radius.value=S.radius,o.setRenderTarget(S.map),o.clear(),o.renderBufferDirect(w,null,M,m,_,null)}function x(S,w,M,D,T,E){let U=null;const V=M.isPointLight===!0?S.customDistanceMaterial:S.customDepthMaterial;if(V!==void 0?U=V:U=M.isPointLight===!0?u:s,o.localClippingEnabled&&w.clipShadows===!0&&Array.isArray(w.clippingPlanes)&&w.clippingPlanes.length!==0||w.displacementMap&&w.displacementScale!==0||w.alphaMap&&w.alphaTest>0){const q=U.uuid,$=w.uuid;let z=c[q];z===void 0&&(z={},c[q]=z);let F=z[$];F===void 0&&(F=U.clone(),z[$]=F),U=F}return U.visible=w.visible,U.wireframe=w.wireframe,E===p5?U.side=w.shadowSide!==null?w.shadowSide:w.side:U.side=w.shadowSide!==null?w.shadowSide:p[w.side],U.alphaMap=w.alphaMap,U.alphaTest=w.alphaTest,U.clipShadows=w.clipShadows,U.clippingPlanes=w.clippingPlanes,U.clipIntersection=w.clipIntersection,U.displacementMap=w.displacementMap,U.displacementScale=w.displacementScale,U.displacementBias=w.displacementBias,U.wireframeLinewidth=w.wireframeLinewidth,U.linewidth=w.linewidth,M.isPointLight===!0&&U.isMeshDistanceMaterial===!0&&(U.referencePosition.setFromMatrixPosition(M.matrixWorld),U.nearDistance=D,U.farDistance=T),U}function A(S,w,M,D,T){if(S.visible===!1)return;if(S.layers.test(w.layers)&&(S.isMesh||S.isLine||S.isPoints)&&(S.castShadow||S.receiveShadow&&T===p5)&&(!S.frustumCulled||n.intersectsObject(S))){S.modelViewMatrix.multiplyMatrices(M.matrixWorldInverse,S.matrixWorld);const V=e.update(S),q=S.material;if(Array.isArray(q)){const $=V.groups;for(let z=0,F=$.length;z<F;z++){const G=$[z],J=q[G.materialIndex];if(J&&J.visible){const O=x(S,J,D,M.near,M.far,T);o.renderBufferDirect(M,null,V,O,S,G)}}}else if(q.visible){const $=x(S,q,D,M.near,M.far,T);o.renderBufferDirect(M,null,V,$,S,null)}}const U=S.children;for(let V=0,q=U.length;V<q;V++)A(U[V],w,M,D,T)}}function kT(o,e,t){const n=t.isWebGL2;function i(){let K=!1;const k1=new c3;let E1=null;const v1=new c3(0,0,0,0);return{setMask:function(R1){E1!==R1&&!K&&(o.colorMask(R1,R1,R1,R1),E1=R1)},setLocked:function(R1){K=R1},setClear:function(R1,t2,E2,V2,Y4){Y4===!0&&(R1*=V2,t2*=V2,E2*=V2),k1.set(R1,t2,E2,V2),v1.equals(k1)===!1&&(o.clearColor(R1,t2,E2,V2),v1.copy(k1))},reset:function(){K=!1,E1=null,v1.set(-1,0,0,0)}}}function a(){let K=!1,k1=null,E1=null,v1=null;return{setTest:function(R1){R1?d1(2929):m1(2929)},setMask:function(R1){k1!==R1&&!K&&(o.depthMask(R1),k1=R1)},setFunc:function(R1){if(E1!==R1){switch(R1){case pS:o.depthFunc(512);break;case hS:o.depthFunc(519);break;case mS:o.depthFunc(513);break;case Ae:o.depthFunc(515);break;case gS:o.depthFunc(514);break;case vS:o.depthFunc(518);break;case _S:o.depthFunc(516);break;case yS:o.depthFunc(517);break;default:o.depthFunc(515)}E1=R1}},setLocked:function(R1){K=R1},setClear:function(R1){v1!==R1&&(o.clearDepth(R1),v1=R1)},reset:function(){K=!1,k1=null,E1=null,v1=null}}}function r(){let K=!1,k1=null,E1=null,v1=null,R1=null,t2=null,E2=null,V2=null,Y4=null;return{setTest:function(N2){K||(N2?d1(2960):m1(2960))},setMask:function(N2){k1!==N2&&!K&&(o.stencilMask(N2),k1=N2)},setFunc:function(N2,w4,W3){(E1!==N2||v1!==w4||R1!==W3)&&(o.stencilFunc(N2,w4,W3),E1=N2,v1=w4,R1=W3)},setOp:function(N2,w4,W3){(t2!==N2||E2!==w4||V2!==W3)&&(o.stencilOp(N2,w4,W3),t2=N2,E2=w4,V2=W3)},setLocked:function(N2){K=N2},setClear:function(N2){Y4!==N2&&(o.clearStencil(N2),Y4=N2)},reset:function(){K=!1,k1=null,E1=null,v1=null,R1=null,t2=null,E2=null,V2=null,Y4=null}}}const s=new i,u=new a,c=new r,f=new WeakMap,p=new WeakMap;let h={},m={},y=new WeakMap,_=[],v=null,b=!1,x=null,A=null,S=null,w=null,M=null,D=null,T=null,E=!1,U=null,V=null,q=null,$=null,z=null;const F=o.getParameter(35661);let G=!1,J=0;const O=o.getParameter(7938);O.indexOf("WebGL")!==-1?(J=parseFloat(/^WebGL (\d)/.exec(O)[1]),G=J>=1):O.indexOf("OpenGL ES")!==-1&&(J=parseFloat(/^OpenGL ES (\d)/.exec(O)[1]),G=J>=2);let N=null,B={};const t1=o.getParameter(3088),n1=o.getParameter(2978),u1=new c3().fromArray(t1),a1=new c3().fromArray(n1);function Q(K,k1,E1){const v1=new Uint8Array(4),R1=o.createTexture();o.bindTexture(K,R1),o.texParameteri(K,10241,9728),o.texParameteri(K,10240,9728);for(let t2=0;t2<E1;t2++)o.texImage2D(k1+t2,0,6408,1,1,0,6408,5121,v1);return R1}const Z={};Z[3553]=Q(3553,3553,1),Z[34067]=Q(34067,34069,6),s.setClear(0,0,0,1),u.setClear(1),c.setClear(0),d1(2929),u.setFunc(Ae),$1(!1),n2(ci),d1(2884),Q1(u0);function d1(K){h[K]!==!0&&(o.enable(K),h[K]=!0)}function m1(K){h[K]!==!1&&(o.disable(K),h[K]=!1)}function g1(K,k1){return m[K]!==k1?(o.bindFramebuffer(K,k1),m[K]=k1,n&&(K===36009&&(m[36160]=k1),K===36160&&(m[36009]=k1)),!0):!1}function c1(K,k1){let E1=_,v1=!1;if(K)if(E1=y.get(k1),E1===void 0&&(E1=[],y.set(k1,E1)),K.isWebGLMultipleRenderTargets){const R1=K.texture;if(E1.length!==R1.length||E1[0]!==36064){for(let t2=0,E2=R1.length;t2<E2;t2++)E1[t2]=36064+t2;E1.length=R1.length,v1=!0}}else E1[0]!==36064&&(E1[0]=36064,v1=!0);else E1[0]!==1029&&(E1[0]=1029,v1=!0);v1&&(t.isWebGL2?o.drawBuffers(E1):e.get("WEBGL_draw_buffers").drawBuffersWEBGL(E1))}function L1(K){return v!==K?(o.useProgram(K),v=K,!0):!1}const M1={[A9]:32774,[nS]:32778,[iS]:32779};if(n)M1[hi]=32775,M1[mi]=32776;else{const K=e.get("EXT_blend_minmax");K!==null&&(M1[hi]=K.MIN_EXT,M1[mi]=K.MAX_EXT)}const b1={[oS]:0,[aS]:1,[rS]:768,[yr]:770,[fS]:776,[cS]:774,[lS]:772,[sS]:769,[br]:771,[dS]:775,[uS]:773};function Q1(K,k1,E1,v1,R1,t2,E2,V2){if(K===u0){b===!0&&(m1(3042),b=!1);return}if(b===!1&&(d1(3042),b=!0),K!==tS){if(K!==x||V2!==E){if((A!==A9||M!==A9)&&(o.blendEquation(32774),A=A9,M=A9),V2)switch(K){case k0:o.blendFuncSeparate(1,771,1,771);break;case di:o.blendFunc(1,1);break;case fi:o.blendFuncSeparate(0,769,0,1);break;case pi:o.blendFuncSeparate(0,768,0,770);break;default:console.error("THREE.WebGLState: Invalid blending: ",K);break}else switch(K){case k0:o.blendFuncSeparate(770,771,1,771);break;case di:o.blendFunc(770,1);break;case fi:o.blendFuncSeparate(0,769,0,1);break;case pi:o.blendFunc(0,768);break;default:console.error("THREE.WebGLState: Invalid blending: ",K);break}S=null,w=null,D=null,T=null,x=K,E=V2}return}R1=R1||k1,t2=t2||E1,E2=E2||v1,(k1!==A||R1!==M)&&(o.blendEquationSeparate(M1[k1],M1[R1]),A=k1,M=R1),(E1!==S||v1!==w||t2!==D||E2!==T)&&(o.blendFuncSeparate(b1[E1],b1[v1],b1[t2],b1[E2]),S=E1,w=v1,D=t2,T=E2),x=K,E=null}function J1(K,k1){K.side===_4?m1(2884):d1(2884);let E1=K.side===n4;k1&&(E1=!E1),$1(E1),K.blending===k0&&K.transparent===!1?Q1(u0):Q1(K.blending,K.blendEquation,K.blendSrc,K.blendDst,K.blendEquationAlpha,K.blendSrcAlpha,K.blendDstAlpha,K.premultipliedAlpha),u.setFunc(K.depthFunc),u.setTest(K.depthTest),u.setMask(K.depthWrite),s.setMask(K.colorWrite);const v1=K.stencilWrite;c.setTest(v1),v1&&(c.setMask(K.stencilWriteMask),c.setFunc(K.stencilFunc,K.stencilRef,K.stencilFuncMask),c.setOp(K.stencilFail,K.stencilZFail,K.stencilZPass)),V1(K.polygonOffset,K.polygonOffsetFactor,K.polygonOffsetUnits),K.alphaToCoverage===!0?d1(32926):m1(32926)}function $1(K){U!==K&&(K?o.frontFace(2304):o.frontFace(2305),U=K)}function n2(K){K!==Qx?(d1(2884),K!==V&&(K===ci?o.cullFace(1029):K===Kx?o.cullFace(1028):o.cullFace(1032))):m1(2884),V=K}function s2(K){K!==q&&(G&&o.lineWidth(K),q=K)}function V1(K,k1,E1){K?(d1(32823),($!==k1||z!==E1)&&(o.polygonOffset(k1,E1),$=k1,z=E1)):m1(32823)}function i2(K){K?d1(3089):m1(3089)}function W1(K){K===void 0&&(K=33984+F-1),N!==K&&(o.activeTexture(K),N=K)}function P(K,k1,E1){E1===void 0&&(N===null?E1=33984+F-1:E1=N);let v1=B[E1];v1===void 0&&(v1={type:void 0,texture:void 0},B[E1]=v1),(v1.type!==K||v1.texture!==k1)&&(N!==E1&&(o.activeTexture(E1),N=E1),o.bindTexture(K,k1||Z[K]),v1.type=K,v1.texture=k1)}function I(){const K=B[N];K!==void 0&&K.type!==void 0&&(o.bindTexture(K.type,null),K.type=void 0,K.texture=void 0)}function l1(){try{o.compressedTexImage2D.apply(o,arguments)}catch(K){console.error("THREE.WebGLState:",K)}}function x1(){try{o.texSubImage2D.apply(o,arguments)}catch(K){console.error("THREE.WebGLState:",K)}}function w1(){try{o.texSubImage3D.apply(o,arguments)}catch(K){console.error("THREE.WebGLState:",K)}}function O1(){try{o.compressedTexSubImage2D.apply(o,arguments)}catch(K){console.error("THREE.WebGLState:",K)}}function H1(){try{o.texStorage2D.apply(o,arguments)}catch(K){console.error("THREE.WebGLState:",K)}}function H(){try{o.texStorage3D.apply(o,arguments)}catch(K){console.error("THREE.WebGLState:",K)}}function i1(){try{o.texImage2D.apply(o,arguments)}catch(K){console.error("THREE.WebGLState:",K)}}function P1(){try{o.texImage3D.apply(o,arguments)}catch(K){console.error("THREE.WebGLState:",K)}}function z1(K){u1.equals(K)===!1&&(o.scissor(K.x,K.y,K.z,K.w),u1.copy(K))}function F1(K){a1.equals(K)===!1&&(o.viewport(K.x,K.y,K.z,K.w),a1.copy(K))}function j1(K,k1){let E1=p.get(k1);E1===void 0&&(E1=new WeakMap,p.set(k1,E1));let v1=E1.get(K);v1===void 0&&(v1=o.getUniformBlockIndex(k1,K.name),E1.set(K,v1))}function o2(K,k1){const v1=p.get(k1).get(K);f.get(K)!==v1&&(o.uniformBlockBinding(k1,v1,K.__bindingPointIndex),f.set(K,v1))}function _2(){o.disable(3042),o.disable(2884),o.disable(2929),o.disable(32823),o.disable(3089),o.disable(2960),o.disable(32926),o.blendEquation(32774),o.blendFunc(1,0),o.blendFuncSeparate(1,0,1,0),o.colorMask(!0,!0,!0,!0),o.clearColor(0,0,0,0),o.depthMask(!0),o.depthFunc(513),o.clearDepth(1),o.stencilMask(4294967295),o.stencilFunc(519,0,4294967295),o.stencilOp(7680,7680,7680),o.clearStencil(0),o.cullFace(1029),o.frontFace(2305),o.polygonOffset(0,0),o.activeTexture(33984),o.bindFramebuffer(36160,null),n===!0&&(o.bindFramebuffer(36009,null),o.bindFramebuffer(36008,null)),o.useProgram(null),o.lineWidth(1),o.scissor(0,0,o.canvas.width,o.canvas.height),o.viewport(0,0,o.canvas.width,o.canvas.height),h={},N=null,B={},m={},y=new WeakMap,_=[],v=null,b=!1,x=null,A=null,S=null,w=null,M=null,D=null,T=null,E=!1,U=null,V=null,q=null,$=null,z=null,u1.set(0,0,o.canvas.width,o.canvas.height),a1.set(0,0,o.canvas.width,o.canvas.height),s.reset(),u.reset(),c.reset()}return{buffers:{color:s,depth:u,stencil:c},enable:d1,disable:m1,bindFramebuffer:g1,drawBuffers:c1,useProgram:L1,setBlending:Q1,setMaterial:J1,setFlipSided:$1,setCullFace:n2,setLineWidth:s2,setPolygonOffset:V1,setScissorTest:i2,activeTexture:W1,bindTexture:P,unbindTexture:I,compressedTexImage2D:l1,texImage2D:i1,texImage3D:P1,updateUBOMapping:j1,uniformBlockBinding:o2,texStorage2D:H1,texStorage3D:H,texSubImage2D:x1,texSubImage3D:w1,compressedTexSubImage2D:O1,scissor:z1,viewport:F1,reset:_2}}function NT(o,e,t,n,i,a,r){const s=i.isWebGL2,u=i.maxTextures,c=i.maxCubemapSize,f=i.maxTextureSize,p=i.maxSamples,h=e.has("WEBGL_multisampled_render_to_texture")?e.get("WEBGL_multisampled_render_to_texture"):null,m=/OculusBrowser/g.test(navigator.userAgent),y=new WeakMap;let _;const v=new WeakMap;let b=!1;try{b=typeof OffscreenCanvas!="undefined"&&new OffscreenCanvas(1,1).getContext("2d")!==null}catch(P){}function x(P,I){return b?new OffscreenCanvas(P,I):T5("canvas")}function A(P,I,l1,x1){let w1=1;if((P.width>x1||P.height>x1)&&(w1=x1/Math.max(P.width,P.height)),w1<1||I===!0)if(typeof HTMLImageElement!="undefined"&&P instanceof HTMLImageElement||typeof HTMLCanvasElement!="undefined"&&P instanceof HTMLCanvasElement||typeof ImageBitmap!="undefined"&&P instanceof ImageBitmap){const O1=I?Re:Math.floor,H1=O1(w1*P.width),H=O1(w1*P.height);_===void 0&&(_=x(H1,H));const i1=l1?x(H1,H):_;return i1.width=H1,i1.height=H,i1.getContext("2d").drawImage(P,0,0,H1,H),console.warn("THREE.WebGLRenderer: Texture has been resized from ("+P.width+"x"+P.height+") to ("+H1+"x"+H+")."),i1}else return"data"in P&&console.warn("THREE.WebGLRenderer: Image in DataTexture is too big ("+P.width+"x"+P.height+")."),P;return P}function S(P){return Gi(P.width)&&Gi(P.height)}function w(P){return s?!1:P.wrapS!==d4||P.wrapT!==d4||P.minFilter!==M3&&P.minFilter!==Z3}function M(P,I){return P.generateMipmaps&&I&&P.minFilter!==M3&&P.minFilter!==Z3}function D(P){o.generateMipmap(P)}function T(P,I,l1,x1,w1=!1){if(s===!1)return I;if(P!==null){if(o[P]!==void 0)return o[P];console.warn("THREE.WebGLRenderer: Attempt to use non-existing WebGL internal format '"+P+"'")}let O1=I;return I===6403&&(l1===5126&&(O1=33326),l1===5131&&(O1=33325),l1===5121&&(O1=33321)),I===33319&&(l1===5126&&(O1=33328),l1===5131&&(O1=33327),l1===5121&&(O1=33323)),I===6408&&(l1===5126&&(O1=34836),l1===5131&&(O1=34842),l1===5121&&(O1=x1===F2&&w1===!1?35907:32856),l1===32819&&(O1=32854),l1===32820&&(O1=32855)),(O1===33325||O1===33326||O1===33327||O1===33328||O1===34842||O1===34836)&&e.get("EXT_color_buffer_float"),O1}function E(P,I,l1){return M(P,l1)===!0||P.isFramebufferTexture&&P.minFilter!==M3&&P.minFilter!==Z3?Math.log2(Math.max(I.width,I.height))+1:P.mipmaps!==void 0&&P.mipmaps.length>0?P.mipmaps.length:P.isCompressedTexture&&Array.isArray(P.image)?I.mipmaps.length:1}function U(P){return P===M3||P===gi||P===vi?9728:9729}function V(P){const I=P.target;I.removeEventListener("dispose",V),$(I),I.isVideoTexture&&y.delete(I)}function q(P){const I=P.target;I.removeEventListener("dispose",q),F(I)}function $(P){const I=n.get(P);if(I.__webglInit===void 0)return;const l1=P.source,x1=v.get(l1);if(x1){const w1=x1[I.__cacheKey];w1.usedTimes--,w1.usedTimes===0&&z(P),Object.keys(x1).length===0&&v.delete(l1)}n.remove(P)}function z(P){const I=n.get(P);o.deleteTexture(I.__webglTexture);const l1=P.source,x1=v.get(l1);delete x1[I.__cacheKey],r.memory.textures--}function F(P){const I=P.texture,l1=n.get(P),x1=n.get(I);if(x1.__webglTexture!==void 0&&(o.deleteTexture(x1.__webglTexture),r.memory.textures--),P.depthTexture&&P.depthTexture.dispose(),P.isWebGLCubeRenderTarget)for(let w1=0;w1<6;w1++)o.deleteFramebuffer(l1.__webglFramebuffer[w1]),l1.__webglDepthbuffer&&o.deleteRenderbuffer(l1.__webglDepthbuffer[w1]);else{if(o.deleteFramebuffer(l1.__webglFramebuffer),l1.__webglDepthbuffer&&o.deleteRenderbuffer(l1.__webglDepthbuffer),l1.__webglMultisampledFramebuffer&&o.deleteFramebuffer(l1.__webglMultisampledFramebuffer),l1.__webglColorRenderbuffer)for(let w1=0;w1<l1.__webglColorRenderbuffer.length;w1++)l1.__webglColorRenderbuffer[w1]&&o.deleteRenderbuffer(l1.__webglColorRenderbuffer[w1]);l1.__webglDepthRenderbuffer&&o.deleteRenderbuffer(l1.__webglDepthRenderbuffer)}if(P.isWebGLMultipleRenderTargets)for(let w1=0,O1=I.length;w1<O1;w1++){const H1=n.get(I[w1]);H1.__webglTexture&&(o.deleteTexture(H1.__webglTexture),r.memory.textures--),n.remove(I[w1])}n.remove(I),n.remove(P)}let G=0;function J(){G=0}function O(){const P=G;return P>=u&&console.warn("THREE.WebGLTextures: Trying to use "+P+" texture units while this GPU supports only "+u),G+=1,P}function N(P){const I=[];return I.push(P.wrapS),I.push(P.wrapT),I.push(P.magFilter),I.push(P.minFilter),I.push(P.anisotropy),I.push(P.internalFormat),I.push(P.format),I.push(P.type),I.push(P.generateMipmaps),I.push(P.premultiplyAlpha),I.push(P.flipY),I.push(P.unpackAlignment),I.push(P.encoding),I.join()}function B(P,I){const l1=n.get(P);if(P.isVideoTexture&&i2(P),P.isRenderTargetTexture===!1&&P.version>0&&l1.__version!==P.version){const x1=P.image;if(x1===null)console.warn("THREE.WebGLRenderer: Texture marked for update but no image data found.");else if(x1.complete===!1)console.warn("THREE.WebGLRenderer: Texture marked for update but image is incomplete");else{m1(l1,P,I);return}}t.bindTexture(3553,l1.__webglTexture,33984+I)}function t1(P,I){const l1=n.get(P);if(P.version>0&&l1.__version!==P.version){m1(l1,P,I);return}t.bindTexture(35866,l1.__webglTexture,33984+I)}function n1(P,I){const l1=n.get(P);if(P.version>0&&l1.__version!==P.version){m1(l1,P,I);return}t.bindTexture(32879,l1.__webglTexture,33984+I)}function u1(P,I){const l1=n.get(P);if(P.version>0&&l1.__version!==P.version){g1(l1,P,I);return}t.bindTexture(34067,l1.__webglTexture,33984+I)}const a1={[Te]:10497,[d4]:33071,[Ee]:33648},Q={[M3]:9728,[gi]:9984,[vi]:9986,[Z3]:9729,[TS]:9985,[u6]:9987};function Z(P,I,l1){if(l1?(o.texParameteri(P,10242,a1[I.wrapS]),o.texParameteri(P,10243,a1[I.wrapT]),(P===32879||P===35866)&&o.texParameteri(P,32882,a1[I.wrapR]),o.texParameteri(P,10240,Q[I.magFilter]),o.texParameteri(P,10241,Q[I.minFilter])):(o.texParameteri(P,10242,33071),o.texParameteri(P,10243,33071),(P===32879||P===35866)&&o.texParameteri(P,32882,33071),(I.wrapS!==d4||I.wrapT!==d4)&&console.warn("THREE.WebGLRenderer: Texture is not power of two. Texture.wrapS and Texture.wrapT should be set to THREE.ClampToEdgeWrapping."),o.texParameteri(P,10240,U(I.magFilter)),o.texParameteri(P,10241,U(I.minFilter)),I.minFilter!==M3&&I.minFilter!==Z3&&console.warn("THREE.WebGLRenderer: Texture is not power of two. Texture.minFilter should be set to THREE.NearestFilter or THREE.LinearFilter.")),e.has("EXT_texture_filter_anisotropic")===!0){const x1=e.get("EXT_texture_filter_anisotropic");if(I.type===O0&&e.has("OES_texture_float_linear")===!1||s===!1&&I.type===C5&&e.has("OES_texture_half_float_linear")===!1)return;(I.anisotropy>1||n.get(I).__currentAnisotropy)&&(o.texParameterf(P,x1.TEXTURE_MAX_ANISOTROPY_EXT,Math.min(I.anisotropy,i.getMaxAnisotropy())),n.get(I).__currentAnisotropy=I.anisotropy)}}function d1(P,I){let l1=!1;P.__webglInit===void 0&&(P.__webglInit=!0,I.addEventListener("dispose",V));const x1=I.source;let w1=v.get(x1);w1===void 0&&(w1={},v.set(x1,w1));const O1=N(I);if(O1!==P.__cacheKey){w1[O1]===void 0&&(w1[O1]={texture:o.createTexture(),usedTimes:0},r.memory.textures++,l1=!0),w1[O1].usedTimes++;const H1=w1[P.__cacheKey];H1!==void 0&&(w1[P.__cacheKey].usedTimes--,H1.usedTimes===0&&z(I)),P.__cacheKey=O1,P.__webglTexture=w1[O1].texture}return l1}function m1(P,I,l1){let x1=3553;I.isDataArrayTexture&&(x1=35866),I.isData3DTexture&&(x1=32879);const w1=d1(P,I),O1=I.source;t.bindTexture(x1,P.__webglTexture,33984+l1);const H1=n.get(O1);if(O1.version!==H1.__version||w1===!0){t.activeTexture(33984+l1),o.pixelStorei(37440,I.flipY),o.pixelStorei(37441,I.premultiplyAlpha),o.pixelStorei(3317,I.unpackAlignment),o.pixelStorei(37443,0);const H=w(I)&&S(I.image)===!1;let i1=A(I.image,H,!1,f);i1=W1(I,i1);const P1=S(i1)||s,z1=a.convert(I.format,I.encoding);let F1=a.convert(I.type),j1=T(I.internalFormat,z1,F1,I.encoding,I.isVideoTexture);Z(x1,I,P1);let o2;const _2=I.mipmaps,K=s&&I.isVideoTexture!==!0,k1=H1.__version===void 0||w1===!0,E1=E(I,i1,P1);if(I.isDepthTexture)j1=6402,s?I.type===O0?j1=36012:I.type===R0?j1=33190:I.type===z9?j1=35056:j1=33189:I.type===O0&&console.error("WebGLRenderer: Floating point depth texture requires WebGL2."),I.format===N0&&j1===6402&&I.type!==Mr&&I.type!==R0&&(console.warn("THREE.WebGLRenderer: Use UnsignedShortType or UnsignedIntType for DepthFormat DepthTexture."),I.type=R0,F1=a.convert(I.type)),I.format===j9&&j1===6402&&(j1=34041,I.type!==z9&&(console.warn("THREE.WebGLRenderer: Use UnsignedInt248Type for DepthStencilFormat DepthTexture."),I.type=z9,F1=a.convert(I.type))),k1&&(K?t.texStorage2D(3553,1,j1,i1.width,i1.height):t.texImage2D(3553,0,j1,i1.width,i1.height,0,z1,F1,null));else if(I.isDataTexture)if(_2.length>0&&P1){K&&k1&&t.texStorage2D(3553,E1,j1,_2[0].width,_2[0].height);for(let v1=0,R1=_2.length;v1<R1;v1++)o2=_2[v1],K?t.texSubImage2D(3553,v1,0,0,o2.width,o2.height,z1,F1,o2.data):t.texImage2D(3553,v1,j1,o2.width,o2.height,0,z1,F1,o2.data);I.generateMipmaps=!1}else K?(k1&&t.texStorage2D(3553,E1,j1,i1.width,i1.height),t.texSubImage2D(3553,0,0,0,i1.width,i1.height,z1,F1,i1.data)):t.texImage2D(3553,0,j1,i1.width,i1.height,0,z1,F1,i1.data);else if(I.isCompressedTexture){K&&k1&&t.texStorage2D(3553,E1,j1,_2[0].width,_2[0].height);for(let v1=0,R1=_2.length;v1<R1;v1++)o2=_2[v1],I.format!==y4?z1!==null?K?t.compressedTexSubImage2D(3553,v1,0,0,o2.width,o2.height,z1,o2.data):t.compressedTexImage2D(3553,v1,j1,o2.width,o2.height,0,o2.data):console.warn("THREE.WebGLRenderer: Attempt to load unsupported compressed texture format in .uploadTexture()"):K?t.texSubImage2D(3553,v1,0,0,o2.width,o2.height,z1,F1,o2.data):t.texImage2D(3553,v1,j1,o2.width,o2.height,0,z1,F1,o2.data)}else if(I.isDataArrayTexture)K?(k1&&t.texStorage3D(35866,E1,j1,i1.width,i1.height,i1.depth),t.texSubImage3D(35866,0,0,0,0,i1.width,i1.height,i1.depth,z1,F1,i1.data)):t.texImage3D(35866,0,j1,i1.width,i1.height,i1.depth,0,z1,F1,i1.data);else if(I.isData3DTexture)K?(k1&&t.texStorage3D(32879,E1,j1,i1.width,i1.height,i1.depth),t.texSubImage3D(32879,0,0,0,0,i1.width,i1.height,i1.depth,z1,F1,i1.data)):t.texImage3D(32879,0,j1,i1.width,i1.height,i1.depth,0,z1,F1,i1.data);else if(I.isFramebufferTexture){if(k1)if(K)t.texStorage2D(3553,E1,j1,i1.width,i1.height);else{let v1=i1.width,R1=i1.height;for(let t2=0;t2<E1;t2++)t.texImage2D(3553,t2,j1,v1,R1,0,z1,F1,null),v1>>=1,R1>>=1}}else if(_2.length>0&&P1){K&&k1&&t.texStorage2D(3553,E1,j1,_2[0].width,_2[0].height);for(let v1=0,R1=_2.length;v1<R1;v1++)o2=_2[v1],K?t.texSubImage2D(3553,v1,0,0,z1,F1,o2):t.texImage2D(3553,v1,j1,z1,F1,o2);I.generateMipmaps=!1}else K?(k1&&t.texStorage2D(3553,E1,j1,i1.width,i1.height),t.texSubImage2D(3553,0,0,0,z1,F1,i1)):t.texImage2D(3553,0,j1,z1,F1,i1);M(I,P1)&&D(x1),H1.__version=O1.version,I.onUpdate&&I.onUpdate(I)}P.__version=I.version}function g1(P,I,l1){if(I.image.length!==6)return;const x1=d1(P,I),w1=I.source;t.bindTexture(34067,P.__webglTexture,33984+l1);const O1=n.get(w1);if(w1.version!==O1.__version||x1===!0){t.activeTexture(33984+l1),o.pixelStorei(37440,I.flipY),o.pixelStorei(37441,I.premultiplyAlpha),o.pixelStorei(3317,I.unpackAlignment),o.pixelStorei(37443,0);const H1=I.isCompressedTexture||I.image[0].isCompressedTexture,H=I.image[0]&&I.image[0].isDataTexture,i1=[];for(let v1=0;v1<6;v1++)!H1&&!H?i1[v1]=A(I.image[v1],!1,!0,c):i1[v1]=H?I.image[v1].image:I.image[v1],i1[v1]=W1(I,i1[v1]);const P1=i1[0],z1=S(P1)||s,F1=a.convert(I.format,I.encoding),j1=a.convert(I.type),o2=T(I.internalFormat,F1,j1,I.encoding),_2=s&&I.isVideoTexture!==!0,K=O1.__version===void 0||x1===!0;let k1=E(I,P1,z1);Z(34067,I,z1);let E1;if(H1){_2&&K&&t.texStorage2D(34067,k1,o2,P1.width,P1.height);for(let v1=0;v1<6;v1++){E1=i1[v1].mipmaps;for(let R1=0;R1<E1.length;R1++){const t2=E1[R1];I.format!==y4?F1!==null?_2?t.compressedTexSubImage2D(34069+v1,R1,0,0,t2.width,t2.height,F1,t2.data):t.compressedTexImage2D(34069+v1,R1,o2,t2.width,t2.height,0,t2.data):console.warn("THREE.WebGLRenderer: Attempt to load unsupported compressed texture format in .setTextureCube()"):_2?t.texSubImage2D(34069+v1,R1,0,0,t2.width,t2.height,F1,j1,t2.data):t.texImage2D(34069+v1,R1,o2,t2.width,t2.height,0,F1,j1,t2.data)}}}else{E1=I.mipmaps,_2&&K&&(E1.length>0&&k1++,t.texStorage2D(34067,k1,o2,i1[0].width,i1[0].height));for(let v1=0;v1<6;v1++)if(H){_2?t.texSubImage2D(34069+v1,0,0,0,i1[v1].width,i1[v1].height,F1,j1,i1[v1].data):t.texImage2D(34069+v1,0,o2,i1[v1].width,i1[v1].height,0,F1,j1,i1[v1].data);for(let R1=0;R1<E1.length;R1++){const E2=E1[R1].image[v1].image;_2?t.texSubImage2D(34069+v1,R1+1,0,0,E2.width,E2.height,F1,j1,E2.data):t.texImage2D(34069+v1,R1+1,o2,E2.width,E2.height,0,F1,j1,E2.data)}}else{_2?t.texSubImage2D(34069+v1,0,0,0,F1,j1,i1[v1]):t.texImage2D(34069+v1,0,o2,F1,j1,i1[v1]);for(let R1=0;R1<E1.length;R1++){const t2=E1[R1];_2?t.texSubImage2D(34069+v1,R1+1,0,0,F1,j1,t2.image[v1]):t.texImage2D(34069+v1,R1+1,o2,F1,j1,t2.image[v1])}}}M(I,z1)&&D(34067),O1.__version=w1.version,I.onUpdate&&I.onUpdate(I)}P.__version=I.version}function c1(P,I,l1,x1,w1){const O1=a.convert(l1.format,l1.encoding),H1=a.convert(l1.type),H=T(l1.internalFormat,O1,H1,l1.encoding);n.get(I).__hasExternalTextures||(w1===32879||w1===35866?t.texImage3D(w1,0,H,I.width,I.height,I.depth,0,O1,H1,null):t.texImage2D(w1,0,H,I.width,I.height,0,O1,H1,null)),t.bindFramebuffer(36160,P),V1(I)?h.framebufferTexture2DMultisampleEXT(36160,x1,w1,n.get(l1).__webglTexture,0,s2(I)):o.framebufferTexture2D(36160,x1,w1,n.get(l1).__webglTexture,0),t.bindFramebuffer(36160,null)}function L1(P,I,l1){if(o.bindRenderbuffer(36161,P),I.depthBuffer&&!I.stencilBuffer){let x1=33189;if(l1||V1(I)){const w1=I.depthTexture;w1&&w1.isDepthTexture&&(w1.type===O0?x1=36012:w1.type===R0&&(x1=33190));const O1=s2(I);V1(I)?h.renderbufferStorageMultisampleEXT(36161,O1,x1,I.width,I.height):o.renderbufferStorageMultisample(36161,O1,x1,I.width,I.height)}else o.renderbufferStorage(36161,x1,I.width,I.height);o.framebufferRenderbuffer(36160,36096,36161,P)}else if(I.depthBuffer&&I.stencilBuffer){const x1=s2(I);l1&&V1(I)===!1?o.renderbufferStorageMultisample(36161,x1,35056,I.width,I.height):V1(I)?h.renderbufferStorageMultisampleEXT(36161,x1,35056,I.width,I.height):o.renderbufferStorage(36161,34041,I.width,I.height),o.framebufferRenderbuffer(36160,33306,36161,P)}else{const x1=I.isWebGLMultipleRenderTargets===!0?I.texture:[I.texture];for(let w1=0;w1<x1.length;w1++){const O1=x1[w1],H1=a.convert(O1.format,O1.encoding),H=a.convert(O1.type),i1=T(O1.internalFormat,H1,H,O1.encoding),P1=s2(I);l1&&V1(I)===!1?o.renderbufferStorageMultisample(36161,P1,i1,I.width,I.height):V1(I)?h.renderbufferStorageMultisampleEXT(36161,P1,i1,I.width,I.height):o.renderbufferStorage(36161,i1,I.width,I.height)}}o.bindRenderbuffer(36161,null)}function M1(P,I){if(I&&I.isWebGLCubeRenderTarget)throw new Error("Depth Texture with cube render targets is not supported");if(t.bindFramebuffer(36160,P),!(I.depthTexture&&I.depthTexture.isDepthTexture))throw new Error("renderTarget.depthTexture must be an instance of THREE.DepthTexture");(!n.get(I.depthTexture).__webglTexture||I.depthTexture.image.width!==I.width||I.depthTexture.image.height!==I.height)&&(I.depthTexture.image.width=I.width,I.depthTexture.image.height=I.height,I.depthTexture.needsUpdate=!0),B(I.depthTexture,0);const x1=n.get(I.depthTexture).__webglTexture,w1=s2(I);if(I.depthTexture.format===N0)V1(I)?h.framebufferTexture2DMultisampleEXT(36160,36096,3553,x1,0,w1):o.framebufferTexture2D(36160,36096,3553,x1,0);else if(I.depthTexture.format===j9)V1(I)?h.framebufferTexture2DMultisampleEXT(36160,33306,3553,x1,0,w1):o.framebufferTexture2D(36160,33306,3553,x1,0);else throw new Error("Unknown depthTexture format")}function b1(P){const I=n.get(P),l1=P.isWebGLCubeRenderTarget===!0;if(P.depthTexture&&!I.__autoAllocateDepthBuffer){if(l1)throw new Error("target.depthTexture not supported in Cube render targets");M1(I.__webglFramebuffer,P)}else if(l1){I.__webglDepthbuffer=[];for(let x1=0;x1<6;x1++)t.bindFramebuffer(36160,I.__webglFramebuffer[x1]),I.__webglDepthbuffer[x1]=o.createRenderbuffer(),L1(I.__webglDepthbuffer[x1],P,!1)}else t.bindFramebuffer(36160,I.__webglFramebuffer),I.__webglDepthbuffer=o.createRenderbuffer(),L1(I.__webglDepthbuffer,P,!1);t.bindFramebuffer(36160,null)}function Q1(P,I,l1){const x1=n.get(P);I!==void 0&&c1(x1.__webglFramebuffer,P,P.texture,36064,3553),l1!==void 0&&b1(P)}function J1(P){const I=P.texture,l1=n.get(P),x1=n.get(I);P.addEventListener("dispose",q),P.isWebGLMultipleRenderTargets!==!0&&(x1.__webglTexture===void 0&&(x1.__webglTexture=o.createTexture()),x1.__version=I.version,r.memory.textures++);const w1=P.isWebGLCubeRenderTarget===!0,O1=P.isWebGLMultipleRenderTargets===!0,H1=S(P)||s;if(w1){l1.__webglFramebuffer=[];for(let H=0;H<6;H++)l1.__webglFramebuffer[H]=o.createFramebuffer()}else{if(l1.__webglFramebuffer=o.createFramebuffer(),O1)if(i.drawBuffers){const H=P.texture;for(let i1=0,P1=H.length;i1<P1;i1++){const z1=n.get(H[i1]);z1.__webglTexture===void 0&&(z1.__webglTexture=o.createTexture(),r.memory.textures++)}}else console.warn("THREE.WebGLRenderer: WebGLMultipleRenderTargets can only be used with WebGL2 or WEBGL_draw_buffers extension.");if(s&&P.samples>0&&V1(P)===!1){const H=O1?I:[I];l1.__webglMultisampledFramebuffer=o.createFramebuffer(),l1.__webglColorRenderbuffer=[],t.bindFramebuffer(36160,l1.__webglMultisampledFramebuffer);for(let i1=0;i1<H.length;i1++){const P1=H[i1];l1.__webglColorRenderbuffer[i1]=o.createRenderbuffer(),o.bindRenderbuffer(36161,l1.__webglColorRenderbuffer[i1]);const z1=a.convert(P1.format,P1.encoding),F1=a.convert(P1.type),j1=T(P1.internalFormat,z1,F1,P1.encoding,P.isXRRenderTarget===!0),o2=s2(P);o.renderbufferStorageMultisample(36161,o2,j1,P.width,P.height),o.framebufferRenderbuffer(36160,36064+i1,36161,l1.__webglColorRenderbuffer[i1])}o.bindRenderbuffer(36161,null),P.depthBuffer&&(l1.__webglDepthRenderbuffer=o.createRenderbuffer(),L1(l1.__webglDepthRenderbuffer,P,!0)),t.bindFramebuffer(36160,null)}}if(w1){t.bindTexture(34067,x1.__webglTexture),Z(34067,I,H1);for(let H=0;H<6;H++)c1(l1.__webglFramebuffer[H],P,I,36064,34069+H);M(I,H1)&&D(34067),t.unbindTexture()}else if(O1){const H=P.texture;for(let i1=0,P1=H.length;i1<P1;i1++){const z1=H[i1],F1=n.get(z1);t.bindTexture(3553,F1.__webglTexture),Z(3553,z1,H1),c1(l1.__webglFramebuffer,P,z1,36064+i1,3553),M(z1,H1)&&D(3553)}t.unbindTexture()}else{let H=3553;(P.isWebGL3DRenderTarget||P.isWebGLArrayRenderTarget)&&(s?H=P.isWebGL3DRenderTarget?32879:35866:console.error("THREE.WebGLTextures: THREE.Data3DTexture and THREE.DataArrayTexture only supported with WebGL2.")),t.bindTexture(H,x1.__webglTexture),Z(H,I,H1),c1(l1.__webglFramebuffer,P,I,36064,H),M(I,H1)&&D(H),t.unbindTexture()}P.depthBuffer&&b1(P)}function $1(P){const I=S(P)||s,l1=P.isWebGLMultipleRenderTargets===!0?P.texture:[P.texture];for(let x1=0,w1=l1.length;x1<w1;x1++){const O1=l1[x1];if(M(O1,I)){const H1=P.isWebGLCubeRenderTarget?34067:3553,H=n.get(O1).__webglTexture;t.bindTexture(H1,H),D(H1),t.unbindTexture()}}}function n2(P){if(s&&P.samples>0&&V1(P)===!1){const I=P.isWebGLMultipleRenderTargets?P.texture:[P.texture],l1=P.width,x1=P.height;let w1=16384;const O1=[],H1=P.stencilBuffer?33306:36096,H=n.get(P),i1=P.isWebGLMultipleRenderTargets===!0;if(i1)for(let P1=0;P1<I.length;P1++)t.bindFramebuffer(36160,H.__webglMultisampledFramebuffer),o.framebufferRenderbuffer(36160,36064+P1,36161,null),t.bindFramebuffer(36160,H.__webglFramebuffer),o.framebufferTexture2D(36009,36064+P1,3553,null,0);t.bindFramebuffer(36008,H.__webglMultisampledFramebuffer),t.bindFramebuffer(36009,H.__webglFramebuffer);for(let P1=0;P1<I.length;P1++){O1.push(36064+P1),P.depthBuffer&&O1.push(H1);const z1=H.__ignoreDepthValues!==void 0?H.__ignoreDepthValues:!1;if(z1===!1&&(P.depthBuffer&&(w1|=256),P.stencilBuffer&&(w1|=1024)),i1&&o.framebufferRenderbuffer(36008,36064,36161,H.__webglColorRenderbuffer[P1]),z1===!0&&(o.invalidateFramebuffer(36008,[H1]),o.invalidateFramebuffer(36009,[H1])),i1){const F1=n.get(I[P1]).__webglTexture;o.framebufferTexture2D(36009,36064,3553,F1,0)}o.blitFramebuffer(0,0,l1,x1,0,0,l1,x1,w1,9728),m&&o.invalidateFramebuffer(36008,O1)}if(t.bindFramebuffer(36008,null),t.bindFramebuffer(36009,null),i1)for(let P1=0;P1<I.length;P1++){t.bindFramebuffer(36160,H.__webglMultisampledFramebuffer),o.framebufferRenderbuffer(36160,36064+P1,36161,H.__webglColorRenderbuffer[P1]);const z1=n.get(I[P1]).__webglTexture;t.bindFramebuffer(36160,H.__webglFramebuffer),o.framebufferTexture2D(36009,36064+P1,3553,z1,0)}t.bindFramebuffer(36009,H.__webglMultisampledFramebuffer)}}function s2(P){return Math.min(p,P.samples)}function V1(P){const I=n.get(P);return s&&P.samples>0&&e.has("WEBGL_multisampled_render_to_texture")===!0&&I.__useRenderToTexture!==!1}function i2(P){const I=r.render.frame;y.get(P)!==I&&(y.set(P,I),P.update())}function W1(P,I){const l1=P.encoding,x1=P.format,w1=P.type;return P.isCompressedTexture===!0||P.isVideoTexture===!0||P.format===Ie||l1!==W0&&(l1===F2?s===!1?e.has("EXT_sRGB")===!0&&x1===y4?(P.format=Ie,P.minFilter=Z3,P.generateMipmaps=!1):I=Cr.sRGBToLinear(I):(x1!==y4||w1!==j0)&&console.warn("THREE.WebGLTextures: sRGB encoded textures have to use RGBAFormat and UnsignedByteType."):console.error("THREE.WebGLTextures: Unsupported texture encoding:",l1)),I}this.allocateTextureUnit=O,this.resetTextureUnits=J,this.setTexture2D=B,this.setTexture2DArray=t1,this.setTexture3D=n1,this.setTextureCube=u1,this.rebindTextures=Q1,this.setupRenderTarget=J1,this.updateRenderTargetMipmap=$1,this.updateMultisampleRenderTarget=n2,this.setupDepthRenderbuffer=b1,this.setupFrameBufferTexture=c1,this.useMultisampledRTT=V1}function UT(o,e,t){const n=t.isWebGL2;function i(a,r=null){let s;if(a===j0)return 5121;if(a===RS)return 32819;if(a===OS)return 32820;if(a===ES)return 5120;if(a===DS)return 5122;if(a===Mr)return 5123;if(a===IS)return 5124;if(a===R0)return 5125;if(a===O0)return 5126;if(a===C5)return n?5131:(s=e.get("OES_texture_half_float"),s!==null?s.HALF_FLOAT_OES:null);if(a===PS)return 6406;if(a===y4)return 6408;if(a===zS)return 6409;if(a===kS)return 6410;if(a===N0)return 6402;if(a===j9)return 34041;if(a===NS)return 6403;if(a===LS)return console.warn("THREE.WebGLRenderer: THREE.RGBFormat has been removed. Use THREE.RGBAFormat instead. https://github.com/mrdoob/three.js/pull/23228"),6408;if(a===Ie)return s=e.get("EXT_sRGB"),s!==null?s.SRGB_ALPHA_EXT:null;if(a===US)return 36244;if(a===FS)return 33319;if(a===BS)return 33320;if(a===GS)return 36249;if(a===D6||a===I6||a===R6||a===O6)if(r===F2)if(s=e.get("WEBGL_compressed_texture_s3tc_srgb"),s!==null){if(a===D6)return s.COMPRESSED_SRGB_S3TC_DXT1_EXT;if(a===I6)return s.COMPRESSED_SRGB_ALPHA_S3TC_DXT1_EXT;if(a===R6)return s.COMPRESSED_SRGB_ALPHA_S3TC_DXT3_EXT;if(a===O6)return s.COMPRESSED_SRGB_ALPHA_S3TC_DXT5_EXT}else return null;else if(s=e.get("WEBGL_compressed_texture_s3tc"),s!==null){if(a===D6)return s.COMPRESSED_RGB_S3TC_DXT1_EXT;if(a===I6)return s.COMPRESSED_RGBA_S3TC_DXT1_EXT;if(a===R6)return s.COMPRESSED_RGBA_S3TC_DXT3_EXT;if(a===O6)return s.COMPRESSED_RGBA_S3TC_DXT5_EXT}else return null;if(a===_i||a===yi||a===bi||a===xi)if(s=e.get("WEBGL_compressed_texture_pvrtc"),s!==null){if(a===_i)return s.COMPRESSED_RGB_PVRTC_4BPPV1_IMG;if(a===yi)return s.COMPRESSED_RGB_PVRTC_2BPPV1_IMG;if(a===bi)return s.COMPRESSED_RGBA_PVRTC_4BPPV1_IMG;if(a===xi)return s.COMPRESSED_RGBA_PVRTC_2BPPV1_IMG}else return null;if(a===VS)return s=e.get("WEBGL_compressed_texture_etc1"),s!==null?s.COMPRESSED_RGB_ETC1_WEBGL:null;if(a===Si||a===Mi)if(s=e.get("WEBGL_compressed_texture_etc"),s!==null){if(a===Si)return r===F2?s.COMPRESSED_SRGB8_ETC2:s.COMPRESSED_RGB8_ETC2;if(a===Mi)return r===F2?s.COMPRESSED_SRGB8_ALPHA8_ETC2_EAC:s.COMPRESSED_RGBA8_ETC2_EAC}else return null;if(a===Ai||a===wi||a===Ci||a===Ti||a===Ei||a===Di||a===Ii||a===Ri||a===Oi||a===Pi||a===Li||a===zi||a===ki||a===Ni)if(s=e.get("WEBGL_compressed_texture_astc"),s!==null){if(a===Ai)return r===F2?s.COMPRESSED_SRGB8_ALPHA8_ASTC_4x4_KHR:s.COMPRESSED_RGBA_ASTC_4x4_KHR;if(a===wi)return r===F2?s.COMPRESSED_SRGB8_ALPHA8_ASTC_5x4_KHR:s.COMPRESSED_RGBA_ASTC_5x4_KHR;if(a===Ci)return r===F2?s.COMPRESSED_SRGB8_ALPHA8_ASTC_5x5_KHR:s.COMPRESSED_RGBA_ASTC_5x5_KHR;if(a===Ti)return r===F2?s.COMPRESSED_SRGB8_ALPHA8_ASTC_6x5_KHR:s.COMPRESSED_RGBA_ASTC_6x5_KHR;if(a===Ei)return r===F2?s.COMPRESSED_SRGB8_ALPHA8_ASTC_6x6_KHR:s.COMPRESSED_RGBA_ASTC_6x6_KHR;if(a===Di)return r===F2?s.COMPRESSED_SRGB8_ALPHA8_ASTC_8x5_KHR:s.COMPRESSED_RGBA_ASTC_8x5_KHR;if(a===Ii)return r===F2?s.COMPRESSED_SRGB8_ALPHA8_ASTC_8x6_KHR:s.COMPRESSED_RGBA_ASTC_8x6_KHR;if(a===Ri)return r===F2?s.COMPRESSED_SRGB8_ALPHA8_ASTC_8x8_KHR:s.COMPRESSED_RGBA_ASTC_8x8_KHR;if(a===Oi)return r===F2?s.COMPRESSED_SRGB8_ALPHA8_ASTC_10x5_KHR:s.COMPRESSED_RGBA_ASTC_10x5_KHR;if(a===Pi)return r===F2?s.COMPRESSED_SRGB8_ALPHA8_ASTC_10x6_KHR:s.COMPRESSED_RGBA_ASTC_10x6_KHR;if(a===Li)return r===F2?s.COMPRESSED_SRGB8_ALPHA8_ASTC_10x8_KHR:s.COMPRESSED_RGBA_ASTC_10x8_KHR;if(a===zi)return r===F2?s.COMPRESSED_SRGB8_ALPHA8_ASTC_10x10_KHR:s.COMPRESSED_RGBA_ASTC_10x10_KHR;if(a===ki)return r===F2?s.COMPRESSED_SRGB8_ALPHA8_ASTC_12x10_KHR:s.COMPRESSED_RGBA_ASTC_12x10_KHR;if(a===Ni)return r===F2?s.COMPRESSED_SRGB8_ALPHA8_ASTC_12x12_KHR:s.COMPRESSED_RGBA_ASTC_12x12_KHR}else return null;if(a===Ui)if(s=e.get("EXT_texture_compression_bptc"),s!==null){if(a===Ui)return r===F2?s.COMPRESSED_SRGB_ALPHA_BPTC_UNORM_EXT:s.COMPRESSED_RGBA_BPTC_UNORM_EXT}else return null;return a===z9?n?34042:(s=e.get("WEBGL_depth_texture"),s!==null?s.UNSIGNED_INT_24_8_WEBGL:null):o[a]!==void 0?o[a]:null}return{convert:i}}class FT extends q3{constructor(e=[]){super(),this.isArrayCamera=!0,this.cameras=e}}class N4 extends v3{constructor(){super(),this.isGroup=!0,this.type="Group"}}const BT={type:"move"};class r7{constructor(){this._targetRay=null,this._grip=null,this._hand=null}getHandSpace(){return this._hand===null&&(this._hand=new N4,this._hand.matrixAutoUpdate=!1,this._hand.visible=!1,this._hand.joints={},this._hand.inputState={pinching:!1}),this._hand}getTargetRaySpace(){return this._targetRay===null&&(this._targetRay=new N4,this._targetRay.matrixAutoUpdate=!1,this._targetRay.visible=!1,this._targetRay.hasLinearVelocity=!1,this._targetRay.linearVelocity=new Y,this._targetRay.hasAngularVelocity=!1,this._targetRay.angularVelocity=new Y),this._targetRay}getGripSpace(){return this._grip===null&&(this._grip=new N4,this._grip.matrixAutoUpdate=!1,this._grip.visible=!1,this._grip.hasLinearVelocity=!1,this._grip.linearVelocity=new Y,this._grip.hasAngularVelocity=!1,this._grip.angularVelocity=new Y),this._grip}dispatchEvent(e){return this._targetRay!==null&&this._targetRay.dispatchEvent(e),this._grip!==null&&this._grip.dispatchEvent(e),this._hand!==null&&this._hand.dispatchEvent(e),this}disconnect(e){return this.dispatchEvent({type:"disconnected",data:e}),this._targetRay!==null&&(this._targetRay.visible=!1),this._grip!==null&&(this._grip.visible=!1),this._hand!==null&&(this._hand.visible=!1),this}update(e,t,n){let i=null,a=null,r=null;const s=this._targetRay,u=this._grip,c=this._hand;if(e&&t.session.visibilityState!=="visible-blurred"){if(c&&e.hand){r=!0;for(const _ of e.hand.values()){const v=t.getJointPose(_,n);if(c.joints[_.jointName]===void 0){const x=new N4;x.matrixAutoUpdate=!1,x.visible=!1,c.joints[_.jointName]=x,c.add(x)}const b=c.joints[_.jointName];v!==null&&(b.matrix.fromArray(v.transform.matrix),b.matrix.decompose(b.position,b.rotation,b.scale),b.jointRadius=v.radius),b.visible=v!==null}const f=c.joints["index-finger-tip"],p=c.joints["thumb-tip"],h=f.position.distanceTo(p.position),m=.02,y=.005;c.inputState.pinching&&h>m+y?(c.inputState.pinching=!1,this.dispatchEvent({type:"pinchend",handedness:e.handedness,target:this})):!c.inputState.pinching&&h<=m-y&&(c.inputState.pinching=!0,this.dispatchEvent({type:"pinchstart",handedness:e.handedness,target:this}))}else u!==null&&e.gripSpace&&(a=t.getPose(e.gripSpace,n),a!==null&&(u.matrix.fromArray(a.transform.matrix),u.matrix.decompose(u.position,u.rotation,u.scale),a.linearVelocity?(u.hasLinearVelocity=!0,u.linearVelocity.copy(a.linearVelocity)):u.hasLinearVelocity=!1,a.angularVelocity?(u.hasAngularVelocity=!0,u.angularVelocity.copy(a.angularVelocity)):u.hasAngularVelocity=!1));s!==null&&(i=t.getPose(e.targetRaySpace,n),i===null&&a!==null&&(i=a),i!==null&&(s.matrix.fromArray(i.transform.matrix),s.matrix.decompose(s.position,s.rotation,s.scale),i.linearVelocity?(s.hasLinearVelocity=!0,s.linearVelocity.copy(i.linearVelocity)):s.hasLinearVelocity=!1,i.angularVelocity?(s.hasAngularVelocity=!0,s.angularVelocity.copy(i.angularVelocity)):s.hasAngularVelocity=!1,this.dispatchEvent(BT)))}return s!==null&&(s.visible=i!==null),u!==null&&(u.visible=a!==null),c!==null&&(c.visible=r!==null),this}}class GT extends i4{constructor(e,t,n,i,a,r,s,u,c,f){if(f=f!==void 0?f:N0,f!==N0&&f!==j9)throw new Error("DepthTexture format must be either THREE.DepthFormat or THREE.DepthStencilFormat");n===void 0&&f===N0&&(n=R0),n===void 0&&f===j9&&(n=z9),super(null,i,a,r,s,u,f,n,c),this.isDepthTexture=!0,this.image={width:e,height:t},this.magFilter=s!==void 0?s:M3,this.minFilter=u!==void 0?u:M3,this.flipY=!1,this.generateMipmaps=!1}}class VT extends Z0{constructor(e,t){super();const n=this;let i=null,a=1,r=null,s="local-floor",u=null,c=null,f=null,p=null,h=null,m=null;const y=t.getContextAttributes();let _=null,v=null;const b=[],x=[],A=new q3;A.layers.enable(1),A.viewport=new c3;const S=new q3;S.layers.enable(2),S.viewport=new c3;const w=[A,S],M=new FT;M.layers.enable(1),M.layers.enable(2);let D=null,T=null;this.cameraAutoUpdate=!0,this.enabled=!1,this.isPresenting=!1,this.getController=function(N){let B=b[N];return B===void 0&&(B=new r7,b[N]=B),B.getTargetRaySpace()},this.getControllerGrip=function(N){let B=b[N];return B===void 0&&(B=new r7,b[N]=B),B.getGripSpace()},this.getHand=function(N){let B=b[N];return B===void 0&&(B=new r7,b[N]=B),B.getHandSpace()};function E(N){const B=x.indexOf(N.inputSource);if(B===-1)return;const t1=b[B];t1!==void 0&&t1.dispatchEvent({type:N.type,data:N.inputSource})}function U(){i.removeEventListener("select",E),i.removeEventListener("selectstart",E),i.removeEventListener("selectend",E),i.removeEventListener("squeeze",E),i.removeEventListener("squeezestart",E),i.removeEventListener("squeezeend",E),i.removeEventListener("end",U),i.removeEventListener("inputsourceschange",V);for(let N=0;N<b.length;N++){const B=x[N];B!==null&&(x[N]=null,b[N].disconnect(B))}D=null,T=null,e.setRenderTarget(_),h=null,p=null,f=null,i=null,v=null,O.stop(),n.isPresenting=!1,n.dispatchEvent({type:"sessionend"})}this.setFramebufferScaleFactor=function(N){a=N,n.isPresenting===!0&&console.warn("THREE.WebXRManager: Cannot change framebuffer scale while presenting.")},this.setReferenceSpaceType=function(N){s=N,n.isPresenting===!0&&console.warn("THREE.WebXRManager: Cannot change reference space type while presenting.")},this.getReferenceSpace=function(){return u||r},this.setReferenceSpace=function(N){u=N},this.getBaseLayer=function(){return p!==null?p:h},this.getBinding=function(){return f},this.getFrame=function(){return m},this.getSession=function(){return i},this.setSession=function(N){return P2(this,null,function*(){if(i=N,i!==null){if(_=e.getRenderTarget(),i.addEventListener("select",E),i.addEventListener("selectstart",E),i.addEventListener("selectend",E),i.addEventListener("squeeze",E),i.addEventListener("squeezestart",E),i.addEventListener("squeezeend",E),i.addEventListener("end",U),i.addEventListener("inputsourceschange",V),y.xrCompatible!==!0&&(yield t.makeXRCompatible()),i.renderState.layers===void 0||e.capabilities.isWebGL2===!1){const B={antialias:i.renderState.layers===void 0?y.antialias:!0,alpha:y.alpha,depth:y.depth,stencil:y.stencil,framebufferScaleFactor:a};h=new XRWebGLLayer(i,t,B),i.updateRenderState({baseLayer:h}),v=new H0(h.framebufferWidth,h.framebufferHeight,{format:y4,type:j0,encoding:e.outputEncoding,stencilBuffer:y.stencil})}else{let B=null,t1=null,n1=null;y.depth&&(n1=y.stencil?35056:33190,B=y.stencil?j9:N0,t1=y.stencil?z9:R0);const u1={colorFormat:32856,depthFormat:n1,scaleFactor:a};f=new XRWebGLBinding(i,t),p=f.createProjectionLayer(u1),i.updateRenderState({layers:[p]}),v=new H0(p.textureWidth,p.textureHeight,{format:y4,type:j0,depthTexture:new GT(p.textureWidth,p.textureHeight,t1,void 0,void 0,void 0,void 0,void 0,void 0,B),stencilBuffer:y.stencil,encoding:e.outputEncoding,samples:y.antialias?4:0});const a1=e.properties.get(v);a1.__ignoreDepthValues=p.ignoreDepthValues}v.isXRRenderTarget=!0,this.setFoveation(1),u=null,r=yield i.requestReferenceSpace(s),O.setContext(i),O.start(),n.isPresenting=!0,n.dispatchEvent({type:"sessionstart"})}})};function V(N){for(let B=0;B<N.removed.length;B++){const t1=N.removed[B],n1=x.indexOf(t1);n1>=0&&(x[n1]=null,b[n1].dispatchEvent({type:"disconnected",data:t1}))}for(let B=0;B<N.added.length;B++){const t1=N.added[B];let n1=x.indexOf(t1);if(n1===-1){for(let a1=0;a1<b.length;a1++)if(a1>=x.length){x.push(t1),n1=a1;break}else if(x[a1]===null){x[a1]=t1,n1=a1;break}if(n1===-1)break}const u1=b[n1];u1&&u1.dispatchEvent({type:"connected",data:t1})}}const q=new Y,$=new Y;function z(N,B,t1){q.setFromMatrixPosition(B.matrixWorld),$.setFromMatrixPosition(t1.matrixWorld);const n1=q.distanceTo($),u1=B.projectionMatrix.elements,a1=t1.projectionMatrix.elements,Q=u1[14]/(u1[10]-1),Z=u1[14]/(u1[10]+1),d1=(u1[9]+1)/u1[5],m1=(u1[9]-1)/u1[5],g1=(u1[8]-1)/u1[0],c1=(a1[8]+1)/a1[0],L1=Q*g1,M1=Q*c1,b1=n1/(-g1+c1),Q1=b1*-g1;B.matrixWorld.decompose(N.position,N.quaternion,N.scale),N.translateX(Q1),N.translateZ(b1),N.matrixWorld.compose(N.position,N.quaternion,N.scale),N.matrixWorldInverse.copy(N.matrixWorld).invert();const J1=Q+b1,$1=Z+b1,n2=L1-Q1,s2=M1+(n1-Q1),V1=d1*Z/$1*J1,i2=m1*Z/$1*J1;N.projectionMatrix.makePerspective(n2,s2,V1,i2,J1,$1)}function F(N,B){B===null?N.matrixWorld.copy(N.matrix):N.matrixWorld.multiplyMatrices(B.matrixWorld,N.matrix),N.matrixWorldInverse.copy(N.matrixWorld).invert()}this.updateCamera=function(N){if(i===null)return;M.near=S.near=A.near=N.near,M.far=S.far=A.far=N.far,(D!==M.near||T!==M.far)&&(i.updateRenderState({depthNear:M.near,depthFar:M.far}),D=M.near,T=M.far);const B=N.parent,t1=M.cameras;F(M,B);for(let u1=0;u1<t1.length;u1++)F(t1[u1],B);M.matrixWorld.decompose(M.position,M.quaternion,M.scale),N.matrix.copy(M.matrix),N.matrix.decompose(N.position,N.quaternion,N.scale);const n1=N.children;for(let u1=0,a1=n1.length;u1<a1;u1++)n1[u1].updateMatrixWorld(!0);t1.length===2?z(M,A,S):M.projectionMatrix.copy(A.projectionMatrix)},this.getCamera=function(){return M},this.getFoveation=function(){if(p!==null)return p.fixedFoveation;if(h!==null)return h.fixedFoveation},this.setFoveation=function(N){p!==null&&(p.fixedFoveation=N),h!==null&&h.fixedFoveation!==void 0&&(h.fixedFoveation=N)};let G=null;function J(N,B){if(c=B.getViewerPose(u||r),m=B,c!==null){const t1=c.views;h!==null&&(e.setRenderTargetFramebuffer(v,h.framebuffer),e.setRenderTarget(v));let n1=!1;t1.length!==M.cameras.length&&(M.cameras.length=0,n1=!0);for(let u1=0;u1<t1.length;u1++){const a1=t1[u1];let Q=null;if(h!==null)Q=h.getViewport(a1);else{const d1=f.getViewSubImage(p,a1);Q=d1.viewport,u1===0&&(e.setRenderTargetTextures(v,d1.colorTexture,p.ignoreDepthValues?void 0:d1.depthStencilTexture),e.setRenderTarget(v))}let Z=w[u1];Z===void 0&&(Z=new q3,Z.layers.enable(u1),Z.viewport=new c3,w[u1]=Z),Z.matrix.fromArray(a1.transform.matrix),Z.projectionMatrix.fromArray(a1.projectionMatrix),Z.viewport.set(Q.x,Q.y,Q.width,Q.height),u1===0&&M.matrix.copy(Z.matrix),n1===!0&&M.cameras.push(Z)}}for(let t1=0;t1<b.length;t1++){const n1=x[t1],u1=b[t1];n1!==null&&u1!==void 0&&u1.update(n1,B,u||r)}G&&G(N,B),m=null}const O=new zr;O.setAnimationLoop(J),this.setAnimationLoop=function(N){G=N},this.dispose=function(){}}}function jT(o,e){function t(_,v){_.fogColor.value.copy(v.color),v.isFog?(_.fogNear.value=v.near,_.fogFar.value=v.far):v.isFogExp2&&(_.fogDensity.value=v.density)}function n(_,v,b,x,A){v.isMeshBasicMaterial||v.isMeshLambertMaterial?i(_,v):v.isMeshToonMaterial?(i(_,v),f(_,v)):v.isMeshPhongMaterial?(i(_,v),c(_,v)):v.isMeshStandardMaterial?(i(_,v),p(_,v),v.isMeshPhysicalMaterial&&h(_,v,A)):v.isMeshMatcapMaterial?(i(_,v),m(_,v)):v.isMeshDepthMaterial?i(_,v):v.isMeshDistanceMaterial?(i(_,v),y(_,v)):v.isMeshNormalMaterial?i(_,v):v.isLineBasicMaterial?(a(_,v),v.isLineDashedMaterial&&r(_,v)):v.isPointsMaterial?s(_,v,b,x):v.isSpriteMaterial?u(_,v):v.isShadowMaterial?(_.color.value.copy(v.color),_.opacity.value=v.opacity):v.isShaderMaterial&&(v.uniformsNeedUpdate=!1)}function i(_,v){_.opacity.value=v.opacity,v.color&&_.diffuse.value.copy(v.color),v.emissive&&_.emissive.value.copy(v.emissive).multiplyScalar(v.emissiveIntensity),v.map&&(_.map.value=v.map),v.alphaMap&&(_.alphaMap.value=v.alphaMap),v.bumpMap&&(_.bumpMap.value=v.bumpMap,_.bumpScale.value=v.bumpScale,v.side===n4&&(_.bumpScale.value*=-1)),v.displacementMap&&(_.displacementMap.value=v.displacementMap,_.displacementScale.value=v.displacementScale,_.displacementBias.value=v.displacementBias),v.emissiveMap&&(_.emissiveMap.value=v.emissiveMap),v.normalMap&&(_.normalMap.value=v.normalMap,_.normalScale.value.copy(v.normalScale),v.side===n4&&_.normalScale.value.negate()),v.specularMap&&(_.specularMap.value=v.specularMap),v.alphaTest>0&&(_.alphaTest.value=v.alphaTest);const b=e.get(v).envMap;if(b&&(_.envMap.value=b,_.flipEnvMap.value=b.isCubeTexture&&b.isRenderTargetTexture===!1?-1:1,_.reflectivity.value=v.reflectivity,_.ior.value=v.ior,_.refractionRatio.value=v.refractionRatio),v.lightMap){_.lightMap.value=v.lightMap;const S=o.physicallyCorrectLights!==!0?Math.PI:1;_.lightMapIntensity.value=v.lightMapIntensity*S}v.aoMap&&(_.aoMap.value=v.aoMap,_.aoMapIntensity.value=v.aoMapIntensity);let x;v.map?x=v.map:v.specularMap?x=v.specularMap:v.displacementMap?x=v.displacementMap:v.normalMap?x=v.normalMap:v.bumpMap?x=v.bumpMap:v.roughnessMap?x=v.roughnessMap:v.metalnessMap?x=v.metalnessMap:v.alphaMap?x=v.alphaMap:v.emissiveMap?x=v.emissiveMap:v.clearcoatMap?x=v.clearcoatMap:v.clearcoatNormalMap?x=v.clearcoatNormalMap:v.clearcoatRoughnessMap?x=v.clearcoatRoughnessMap:v.iridescenceMap?x=v.iridescenceMap:v.iridescenceThicknessMap?x=v.iridescenceThicknessMap:v.specularIntensityMap?x=v.specularIntensityMap:v.specularColorMap?x=v.specularColorMap:v.transmissionMap?x=v.transmissionMap:v.thicknessMap?x=v.thicknessMap:v.sheenColorMap?x=v.sheenColorMap:v.sheenRoughnessMap&&(x=v.sheenRoughnessMap),x!==void 0&&(x.isWebGLRenderTarget&&(x=x.texture),x.matrixAutoUpdate===!0&&x.updateMatrix(),_.uvTransform.value.copy(x.matrix));let A;v.aoMap?A=v.aoMap:v.lightMap&&(A=v.lightMap),A!==void 0&&(A.isWebGLRenderTarget&&(A=A.texture),A.matrixAutoUpdate===!0&&A.updateMatrix(),_.uv2Transform.value.copy(A.matrix))}function a(_,v){_.diffuse.value.copy(v.color),_.opacity.value=v.opacity}function r(_,v){_.dashSize.value=v.dashSize,_.totalSize.value=v.dashSize+v.gapSize,_.scale.value=v.scale}function s(_,v,b,x){_.diffuse.value.copy(v.color),_.opacity.value=v.opacity,_.size.value=v.size*b,_.scale.value=x*.5,v.map&&(_.map.value=v.map),v.alphaMap&&(_.alphaMap.value=v.alphaMap),v.alphaTest>0&&(_.alphaTest.value=v.alphaTest);let A;v.map?A=v.map:v.alphaMap&&(A=v.alphaMap),A!==void 0&&(A.matrixAutoUpdate===!0&&A.updateMatrix(),_.uvTransform.value.copy(A.matrix))}function u(_,v){_.diffuse.value.copy(v.color),_.opacity.value=v.opacity,_.rotation.value=v.rotation,v.map&&(_.map.value=v.map),v.alphaMap&&(_.alphaMap.value=v.alphaMap),v.alphaTest>0&&(_.alphaTest.value=v.alphaTest);let b;v.map?b=v.map:v.alphaMap&&(b=v.alphaMap),b!==void 0&&(b.matrixAutoUpdate===!0&&b.updateMatrix(),_.uvTransform.value.copy(b.matrix))}function c(_,v){_.specular.value.copy(v.specular),_.shininess.value=Math.max(v.shininess,1e-4)}function f(_,v){v.gradientMap&&(_.gradientMap.value=v.gradientMap)}function p(_,v){_.roughness.value=v.roughness,_.metalness.value=v.metalness,v.roughnessMap&&(_.roughnessMap.value=v.roughnessMap),v.metalnessMap&&(_.metalnessMap.value=v.metalnessMap),e.get(v).envMap&&(_.envMapIntensity.value=v.envMapIntensity)}function h(_,v,b){_.ior.value=v.ior,v.sheen>0&&(_.sheenColor.value.copy(v.sheenColor).multiplyScalar(v.sheen),_.sheenRoughness.value=v.sheenRoughness,v.sheenColorMap&&(_.sheenColorMap.value=v.sheenColorMap),v.sheenRoughnessMap&&(_.sheenRoughnessMap.value=v.sheenRoughnessMap)),v.clearcoat>0&&(_.clearcoat.value=v.clearcoat,_.clearcoatRoughness.value=v.clearcoatRoughness,v.clearcoatMap&&(_.clearcoatMap.value=v.clearcoatMap),v.clearcoatRoughnessMap&&(_.clearcoatRoughnessMap.value=v.clearcoatRoughnessMap),v.clearcoatNormalMap&&(_.clearcoatNormalScale.value.copy(v.clearcoatNormalScale),_.clearcoatNormalMap.value=v.clearcoatNormalMap,v.side===n4&&_.clearcoatNormalScale.value.negate())),v.iridescence>0&&(_.iridescence.value=v.iridescence,_.iridescenceIOR.value=v.iridescenceIOR,_.iridescenceThicknessMinimum.value=v.iridescenceThicknessRange[0],_.iridescenceThicknessMaximum.value=v.iridescenceThicknessRange[1],v.iridescenceMap&&(_.iridescenceMap.value=v.iridescenceMap),v.iridescenceThicknessMap&&(_.iridescenceThicknessMap.value=v.iridescenceThicknessMap)),v.transmission>0&&(_.transmission.value=v.transmission,_.transmissionSamplerMap.value=b.texture,_.transmissionSamplerSize.value.set(b.width,b.height),v.transmissionMap&&(_.transmissionMap.value=v.transmissionMap),_.thickness.value=v.thickness,v.thicknessMap&&(_.thicknessMap.value=v.thicknessMap),_.attenuationDistance.value=v.attenuationDistance,_.attenuationColor.value.copy(v.attenuationColor)),_.specularIntensity.value=v.specularIntensity,_.specularColor.value.copy(v.specularColor),v.specularIntensityMap&&(_.specularIntensityMap.value=v.specularIntensityMap),v.specularColorMap&&(_.specularColorMap.value=v.specularColorMap)}function m(_,v){v.matcap&&(_.matcap.value=v.matcap)}function y(_,v){_.referencePosition.value.copy(v.referencePosition),_.nearDistance.value=v.nearDistance,_.farDistance.value=v.farDistance}return{refreshFogUniforms:t,refreshMaterialUniforms:n}}function WT(o,e,t,n){let i={},a={},r=[];const s=t.isWebGL2?o.getParameter(35375):0;function u(x,A){const S=A.program;n.uniformBlockBinding(x,S)}function c(x,A){let S=i[x.id];S===void 0&&(y(x),S=f(x),i[x.id]=S,x.addEventListener("dispose",v));const w=A.program;n.updateUBOMapping(x,w);const M=e.render.frame;a[x.id]!==M&&(h(x),a[x.id]=M)}function f(x){const A=p();x.__bindingPointIndex=A;const S=o.createBuffer(),w=x.__size,M=x.usage;return o.bindBuffer(35345,S),o.bufferData(35345,w,M),o.bindBuffer(35345,null),o.bindBufferBase(35345,A,S),S}function p(){for(let x=0;x<s;x++)if(r.indexOf(x)===-1)return r.push(x),x;return console.error("THREE.WebGLRenderer: Maximum number of simultaneously usable uniforms groups reached."),0}function h(x){const A=i[x.id],S=x.uniforms,w=x.__cache;o.bindBuffer(35345,A);for(let M=0,D=S.length;M<D;M++){const T=S[M];if(m(T,M,w)===!0){const E=T.value,U=T.__offset;typeof E=="number"?(T.__data[0]=E,o.bufferSubData(35345,U,T.__data)):(T.value.isMatrix3?(T.__data[0]=T.value.elements[0],T.__data[1]=T.value.elements[1],T.__data[2]=T.value.elements[2],T.__data[3]=T.value.elements[0],T.__data[4]=T.value.elements[3],T.__data[5]=T.value.elements[4],T.__data[6]=T.value.elements[5],T.__data[7]=T.value.elements[0],T.__data[8]=T.value.elements[6],T.__data[9]=T.value.elements[7],T.__data[10]=T.value.elements[8],T.__data[11]=T.value.elements[0]):E.toArray(T.__data),o.bufferSubData(35345,U,T.__data))}}o.bindBuffer(35345,null)}function m(x,A,S){const w=x.value;if(S[A]===void 0)return typeof w=="number"?S[A]=w:S[A]=w.clone(),!0;if(typeof w=="number"){if(S[A]!==w)return S[A]=w,!0}else{const M=S[A];if(M.equals(w)===!1)return M.copy(w),!0}return!1}function y(x){const A=x.uniforms;let S=0;const w=16;let M=0;for(let D=0,T=A.length;D<T;D++){const E=A[D],U=_(E);if(E.__data=new Float32Array(U.storage/Float32Array.BYTES_PER_ELEMENT),E.__offset=S,D>0){M=S%w;const V=w-M;M!==0&&V-U.boundary<0&&(S+=w-M,E.__offset=S)}S+=U.storage}return M=S%w,M>0&&(S+=w-M),x.__size=S,x.__cache={},this}function _(x){const A=x.value,S={boundary:0,storage:0};return typeof A=="number"?(S.boundary=4,S.storage=4):A.isVector2?(S.boundary=8,S.storage=8):A.isVector3||A.isColor?(S.boundary=16,S.storage=12):A.isVector4?(S.boundary=16,S.storage=16):A.isMatrix3?(S.boundary=48,S.storage=48):A.isMatrix4?(S.boundary=64,S.storage=64):A.isTexture?console.warn("THREE.WebGLRenderer: Texture samplers can not be part of an uniforms group."):console.warn("THREE.WebGLRenderer: Unsupported uniform value type.",A),S}function v(x){const A=x.target;A.removeEventListener("dispose",v);const S=r.indexOf(A.__bindingPointIndex);r.splice(S,1),o.deleteBuffer(i[A.id]),delete i[A.id],delete a[A.id]}function b(){for(const x in i)o.deleteBuffer(i[x]);r=[],i={},a={}}return{bind:u,update:c,dispose:b}}function HT(){const o=T5("canvas");return o.style.display="block",o}function Br(o={}){this.isWebGLRenderer=!0;const e=o.canvas!==void 0?o.canvas:HT(),t=o.context!==void 0?o.context:null,n=o.depth!==void 0?o.depth:!0,i=o.stencil!==void 0?o.stencil:!0,a=o.antialias!==void 0?o.antialias:!1,r=o.premultipliedAlpha!==void 0?o.premultipliedAlpha:!0,s=o.preserveDrawingBuffer!==void 0?o.preserveDrawingBuffer:!1,u=o.powerPreference!==void 0?o.powerPreference:"default",c=o.failIfMajorPerformanceCaveat!==void 0?o.failIfMajorPerformanceCaveat:!1;let f;t!==null?f=t.getContextAttributes().alpha:f=o.alpha!==void 0?o.alpha:!1;let p=null,h=null;const m=[],y=[];this.domElement=e,this.debug={checkShaderErrors:!0},this.autoClear=!0,this.autoClearColor=!0,this.autoClearDepth=!0,this.autoClearStencil=!0,this.sortObjects=!0,this.clippingPlanes=[],this.localClippingEnabled=!1,this.outputEncoding=W0,this.physicallyCorrectLights=!1,this.toneMapping=F4,this.toneMappingExposure=1,Object.defineProperties(this,{gammaFactor:{get:function(){return console.warn("THREE.WebGLRenderer: .gammaFactor has been removed."),2},set:function(){console.warn("THREE.WebGLRenderer: .gammaFactor has been removed.")}}});const _=this;let v=!1,b=0,x=0,A=null,S=-1,w=null;const M=new c3,D=new c3;let T=null,E=e.width,U=e.height,V=1,q=null,$=null;const z=new c3(0,0,E,U),F=new c3(0,0,E,U);let G=!1;const J=new Lr;let O=!1,N=!1,B=null;const t1=new W2,n1=new G1,u1=new Y,a1={background:null,fog:null,environment:null,overrideMaterial:null,isScene:!0};function Q(){return A===null?V:1}let Z=t;function d1(k,r1){for(let f1=0;f1<k.length;f1++){const o1=k[f1],h1=e.getContext(o1,r1);if(h1!==null)return h1}return null}try{const k={alpha:!0,depth:n,stencil:i,antialias:a,premultipliedAlpha:r,preserveDrawingBuffer:s,powerPreference:u,failIfMajorPerformanceCaveat:c};if("setAttribute"in e&&e.setAttribute("data-engine",`three.js r${dn}`),e.addEventListener("webglcontextlost",j1,!1),e.addEventListener("webglcontextrestored",o2,!1),e.addEventListener("webglcontextcreationerror",_2,!1),Z===null){const r1=["webgl2","webgl","experimental-webgl"];if(_.isWebGL1Renderer===!0&&r1.shift(),Z=d1(r1,k),Z===null)throw d1(r1)?new Error("Error creating WebGL context with your selected attributes."):new Error("Error creating WebGL context.")}Z.getShaderPrecisionFormat===void 0&&(Z.getShaderPrecisionFormat=function(){return{rangeMin:1,rangeMax:1,precision:1}})}catch(k){throw console.error("THREE.WebGLRenderer: "+k.message),k}let m1,g1,c1,L1,M1,b1,Q1,J1,$1,n2,s2,V1,i2,W1,P,I,l1,x1,w1,O1,H1,H,i1,P1;function z1(){m1=new nC(Z),g1=new Zw(Z,m1,o),m1.init(g1),H=new UT(Z,m1,g1),c1=new kT(Z,m1,g1),L1=new aC,M1=new ST,b1=new NT(Z,m1,c1,M1,g1,H,L1),Q1=new Xw(_),J1=new tC(_),$1=new mM(Z,g1),i1=new Yw(Z,m1,$1,g1),n2=new iC(Z,$1,L1,i1),s2=new uC(Z,n2,$1,L1),w1=new lC(Z,g1,b1),I=new qw(M1),V1=new xT(_,Q1,J1,m1,g1,i1,I),i2=new jT(_,M1),W1=new AT,P=new IT(m1,g1),x1=new $w(_,Q1,c1,s2,f,r),l1=new zT(_,s2,g1),P1=new WT(Z,L1,g1,c1),O1=new Jw(Z,m1,L1,g1),H1=new oC(Z,m1,L1,g1),L1.programs=V1.programs,_.capabilities=g1,_.extensions=m1,_.properties=M1,_.renderLists=W1,_.shadowMap=l1,_.state=c1,_.info=L1}z1();const F1=new VT(_,Z);this.xr=F1,this.getContext=function(){return Z},this.getContextAttributes=function(){return Z.getContextAttributes()},this.forceContextLoss=function(){const k=m1.get("WEBGL_lose_context");k&&k.loseContext()},this.forceContextRestore=function(){const k=m1.get("WEBGL_lose_context");k&&k.restoreContext()},this.getPixelRatio=function(){return V},this.setPixelRatio=function(k){k!==void 0&&(V=k,this.setSize(E,U,!1))},this.getSize=function(k){return k.set(E,U)},this.setSize=function(k,r1,f1){if(F1.isPresenting){console.warn("THREE.WebGLRenderer: Can't change size while VR device is presenting.");return}E=k,U=r1,e.width=Math.floor(k*V),e.height=Math.floor(r1*V),f1!==!1&&(e.style.width=k+"px",e.style.height=r1+"px"),this.setViewport(0,0,k,r1)},this.getDrawingBufferSize=function(k){return k.set(E*V,U*V).floor()},this.setDrawingBufferSize=function(k,r1,f1){E=k,U=r1,V=f1,e.width=Math.floor(k*f1),e.height=Math.floor(r1*f1),this.setViewport(0,0,k,r1)},this.getCurrentViewport=function(k){return k.copy(M)},this.getViewport=function(k){return k.copy(z)},this.setViewport=function(k,r1,f1,o1){k.isVector4?z.set(k.x,k.y,k.z,k.w):z.set(k,r1,f1,o1),c1.viewport(M.copy(z).multiplyScalar(V).floor())},this.getScissor=function(k){return k.copy(F)},this.setScissor=function(k,r1,f1,o1){k.isVector4?F.set(k.x,k.y,k.z,k.w):F.set(k,r1,f1,o1),c1.scissor(D.copy(F).multiplyScalar(V).floor())},this.getScissorTest=function(){return G},this.setScissorTest=function(k){c1.setScissorTest(G=k)},this.setOpaqueSort=function(k){q=k},this.setTransparentSort=function(k){$=k},this.getClearColor=function(k){return k.copy(x1.getClearColor())},this.setClearColor=function(){x1.setClearColor.apply(x1,arguments)},this.getClearAlpha=function(){return x1.getClearAlpha()},this.setClearAlpha=function(){x1.setClearAlpha.apply(x1,arguments)},this.clear=function(k=!0,r1=!0,f1=!0){let o1=0;k&&(o1|=16384),r1&&(o1|=256),f1&&(o1|=1024),Z.clear(o1)},this.clearColor=function(){this.clear(!0,!1,!1)},this.clearDepth=function(){this.clear(!1,!0,!1)},this.clearStencil=function(){this.clear(!1,!1,!0)},this.dispose=function(){e.removeEventListener("webglcontextlost",j1,!1),e.removeEventListener("webglcontextrestored",o2,!1),e.removeEventListener("webglcontextcreationerror",_2,!1),W1.dispose(),P.dispose(),M1.dispose(),Q1.dispose(),J1.dispose(),s2.dispose(),i1.dispose(),P1.dispose(),V1.dispose(),F1.dispose(),F1.removeEventListener("sessionstart",t2),F1.removeEventListener("sessionend",E2),B&&(B.dispose(),B=null),V2.stop()};function j1(k){k.preventDefault(),console.log("THREE.WebGLRenderer: Context Lost."),v=!0}function o2(){console.log("THREE.WebGLRenderer: Context Restored."),v=!1;const k=L1.autoReset,r1=l1.enabled,f1=l1.autoUpdate,o1=l1.needsUpdate,h1=l1.type;z1(),L1.autoReset=k,l1.enabled=r1,l1.autoUpdate=f1,l1.needsUpdate=o1,l1.type=h1}function _2(k){console.error("THREE.WebGLRenderer: A WebGL context could not be created. Reason: ",k.statusMessage)}function K(k){const r1=k.target;r1.removeEventListener("dispose",K),k1(r1)}function k1(k){E1(k),M1.remove(k)}function E1(k){const r1=M1.get(k).programs;r1!==void 0&&(r1.forEach(function(f1){V1.releaseProgram(f1)}),k.isShaderMaterial&&V1.releaseShaderCache(k))}this.renderBufferDirect=function(k,r1,f1,o1,h1,q1){r1===null&&(r1=a1);const a2=h1.isMesh&&h1.matrixWorld.determinant()<0,l2=yl(k,r1,f1,o1,h1);c1.setMaterial(o1,a2);let r2=f1.index;const x2=f1.attributes.position;if(r2===null){if(x2===void 0||x2.count===0)return}else if(r2.count===0)return;let m2=1;o1.wireframe===!0&&(r2=n2.getWireframeAttribute(f1),m2=2),i1.setup(h1,o1,l2,f1,r2);let g2,U2=O1;r2!==null&&(g2=$1.get(r2),U2=H1,U2.setIndex(g2));const b0=r2!==null?r2.count:x2.count,Q0=f1.drawRange.start*m2,K0=f1.drawRange.count*m2,h4=q1!==null?q1.start*m2:0,b2=q1!==null?q1.count*m2:1/0,e9=Math.max(Q0,h4),J2=Math.min(b0,Q0+K0,h4+b2)-1,H3=Math.max(0,J2-e9+1);if(H3!==0){if(h1.isMesh)o1.wireframe===!0?(c1.setLineWidth(o1.wireframeLinewidth*Q()),U2.setMode(1)):U2.setMode(4);else if(h1.isLine){let J4=o1.linewidth;J4===void 0&&(J4=1),c1.setLineWidth(J4*Q()),h1.isLineSegments?U2.setMode(1):h1.isLineLoop?U2.setMode(2):U2.setMode(3)}else h1.isPoints?U2.setMode(0):h1.isSprite&&U2.setMode(4);if(h1.isInstancedMesh)U2.renderInstances(e9,H3,h1.count);else if(f1.isInstancedBufferGeometry){const J4=Math.min(f1.instanceCount,f1._maxInstanceCount);U2.renderInstances(e9,H3,J4)}else U2.render(e9,H3)}},this.compile=function(k,r1){function f1(o1,h1,q1){o1.transparent===!0&&o1.side===_4?(o1.side=n4,o1.needsUpdate=!0,J5(o1,h1,q1),o1.side=B9,o1.needsUpdate=!0,J5(o1,h1,q1),o1.side=_4):J5(o1,h1,q1)}h=P.get(k),h.init(),y.push(h),k.traverseVisible(function(o1){o1.isLight&&o1.layers.test(r1.layers)&&(h.pushLight(o1),o1.castShadow&&h.pushShadow(o1))}),h.setupLights(_.physicallyCorrectLights),k.traverse(function(o1){const h1=o1.material;if(h1)if(Array.isArray(h1))for(let q1=0;q1<h1.length;q1++){const a2=h1[q1];f1(a2,k,o1)}else f1(h1,k,o1)}),y.pop(),h=null};let v1=null;function R1(k){v1&&v1(k)}function t2(){V2.stop()}function E2(){V2.start()}const V2=new zr;V2.setAnimationLoop(R1),typeof self!="undefined"&&V2.setContext(self),this.setAnimationLoop=function(k){v1=k,F1.setAnimationLoop(k),k===null?V2.stop():V2.start()},F1.addEventListener("sessionstart",t2),F1.addEventListener("sessionend",E2),this.render=function(k,r1){if(r1!==void 0&&r1.isCamera!==!0){console.error("THREE.WebGLRenderer.render: camera is not an instance of THREE.Camera.");return}if(v===!0)return;k.matrixWorldAutoUpdate===!0&&k.updateMatrixWorld(),r1.parent===null&&r1.matrixWorldAutoUpdate===!0&&r1.updateMatrixWorld(),F1.enabled===!0&&F1.isPresenting===!0&&(F1.cameraAutoUpdate===!0&&F1.updateCamera(r1),r1=F1.getCamera()),k.isScene===!0&&k.onBeforeRender(_,k,r1,A),h=P.get(k,y.length),h.init(),y.push(h),t1.multiplyMatrices(r1.projectionMatrix,r1.matrixWorldInverse),J.setFromProjectionMatrix(t1),N=this.localClippingEnabled,O=I.init(this.clippingPlanes,N,r1),p=W1.get(k,m.length),p.init(),m.push(p),Y4(k,r1,0,_.sortObjects),p.finish(),_.sortObjects===!0&&p.sort(q,$),O===!0&&I.beginShadows();const f1=h.state.shadowsArray;if(l1.render(f1,k,r1),O===!0&&I.endShadows(),this.info.autoReset===!0&&this.info.reset(),x1.render(p,k),h.setupLights(_.physicallyCorrectLights),r1.isArrayCamera){const o1=r1.cameras;for(let h1=0,q1=o1.length;h1<q1;h1++){const a2=o1[h1];N2(p,k,a2,a2.viewport)}}else N2(p,k,r1);A!==null&&(b1.updateMultisampleRenderTarget(A),b1.updateRenderTargetMipmap(A)),k.isScene===!0&&k.onAfterRender(_,k,r1),i1.resetDefaultState(),S=-1,w=null,y.pop(),y.length>0?h=y[y.length-1]:h=null,m.pop(),m.length>0?p=m[m.length-1]:p=null};function Y4(k,r1,f1,o1){if(k.visible===!1)return;if(k.layers.test(r1.layers)){if(k.isGroup)f1=k.renderOrder;else if(k.isLOD)k.autoUpdate===!0&&k.update(r1);else if(k.isLight)h.pushLight(k),k.castShadow&&h.pushShadow(k);else if(k.isSprite){if(!k.frustumCulled||J.intersectsSprite(k)){o1&&u1.setFromMatrixPosition(k.matrixWorld).applyMatrix4(t1);const a2=s2.update(k),l2=k.material;l2.visible&&p.push(k,a2,l2,f1,u1.z,null)}}else if((k.isMesh||k.isLine||k.isPoints)&&(k.isSkinnedMesh&&k.skeleton.frame!==L1.render.frame&&(k.skeleton.update(),k.skeleton.frame=L1.render.frame),!k.frustumCulled||J.intersectsObject(k))){o1&&u1.setFromMatrixPosition(k.matrixWorld).applyMatrix4(t1);const a2=s2.update(k),l2=k.material;if(Array.isArray(l2)){const r2=a2.groups;for(let x2=0,m2=r2.length;x2<m2;x2++){const g2=r2[x2],U2=l2[g2.materialIndex];U2&&U2.visible&&p.push(k,a2,U2,f1,u1.z,g2)}}else l2.visible&&p.push(k,a2,l2,f1,u1.z,null)}}const q1=k.children;for(let a2=0,l2=q1.length;a2<l2;a2++)Y4(q1[a2],r1,f1,o1)}function N2(k,r1,f1,o1){const h1=k.opaque,q1=k.transmissive,a2=k.transparent;h.setupLightsView(f1),q1.length>0&&w4(h1,r1,f1),o1&&c1.viewport(M.copy(o1)),h1.length>0&&W3(h1,r1,f1),q1.length>0&&W3(q1,r1,f1),a2.length>0&&W3(a2,r1,f1),c1.buffers.depth.setTest(!0),c1.buffers.depth.setMask(!0),c1.buffers.color.setMask(!0),c1.setPolygonOffset(!1)}function w4(k,r1,f1){const o1=g1.isWebGL2;B===null&&(B=new H0(1,1,{generateMipmaps:!0,type:m1.has("EXT_color_buffer_half_float")?C5:j0,minFilter:u6,samples:o1&&a===!0?4:0})),_.getDrawingBufferSize(n1),o1?B.setSize(n1.x,n1.y):B.setSize(Re(n1.x),Re(n1.y));const h1=_.getRenderTarget();_.setRenderTarget(B),_.clear();const q1=_.toneMapping;_.toneMapping=F4,W3(k,r1,f1),_.toneMapping=q1,b1.updateMultisampleRenderTarget(B),b1.updateRenderTargetMipmap(B),_.setRenderTarget(h1)}function W3(k,r1,f1){const o1=r1.isScene===!0?r1.overrideMaterial:null;for(let h1=0,q1=k.length;h1<q1;h1++){const a2=k[h1],l2=a2.object,r2=a2.geometry,x2=o1===null?a2.material:o1,m2=a2.group;l2.layers.test(f1.layers)&&_l(l2,r1,f1,r2,x2,m2)}}function _l(k,r1,f1,o1,h1,q1){k.onBeforeRender(_,r1,f1,o1,h1,q1),k.modelViewMatrix.multiplyMatrices(f1.matrixWorldInverse,k.matrixWorld),k.normalMatrix.getNormalMatrix(k.modelViewMatrix),h1.onBeforeRender(_,r1,f1,o1,k,q1),h1.transparent===!0&&h1.side===_4?(h1.side=n4,h1.needsUpdate=!0,_.renderBufferDirect(f1,r1,o1,h1,k,q1),h1.side=B9,h1.needsUpdate=!0,_.renderBufferDirect(f1,r1,o1,h1,k,q1),h1.side=_4):_.renderBufferDirect(f1,r1,o1,h1,k,q1),k.onAfterRender(_,r1,f1,o1,h1,q1)}function J5(k,r1,f1){r1.isScene!==!0&&(r1=a1);const o1=M1.get(k),h1=h.state.lights,q1=h.state.shadowsArray,a2=h1.state.version,l2=V1.getParameters(k,h1.state,q1,r1,f1),r2=V1.getProgramCacheKey(l2);let x2=o1.programs;o1.environment=k.isMeshStandardMaterial?r1.environment:null,o1.fog=r1.fog,o1.envMap=(k.isMeshStandardMaterial?J1:Q1).get(k.envMap||o1.environment),x2===void 0&&(k.addEventListener("dispose",K),x2=new Map,o1.programs=x2);let m2=x2.get(r2);if(m2!==void 0){if(o1.currentProgram===m2&&o1.lightsStateVersion===a2)return Hn(k,l2),m2}else l2.uniforms=V1.getUniforms(k),k.onBuild(f1,l2,_),k.onBeforeCompile(l2,_),m2=V1.acquireProgram(l2,r2),x2.set(r2,m2),o1.uniforms=l2.uniforms;const g2=o1.uniforms;(!k.isShaderMaterial&&!k.isRawShaderMaterial||k.clipping===!0)&&(g2.clippingPlanes=I.uniform),Hn(k,l2),o1.needsLights=xl(k),o1.lightsStateVersion=a2,o1.needsLights&&(g2.ambientLightColor.value=h1.state.ambient,g2.lightProbe.value=h1.state.probe,g2.directionalLights.value=h1.state.directional,g2.directionalLightShadows.value=h1.state.directionalShadow,g2.spotLights.value=h1.state.spot,g2.spotLightShadows.value=h1.state.spotShadow,g2.rectAreaLights.value=h1.state.rectArea,g2.ltc_1.value=h1.state.rectAreaLTC1,g2.ltc_2.value=h1.state.rectAreaLTC2,g2.pointLights.value=h1.state.point,g2.pointLightShadows.value=h1.state.pointShadow,g2.hemisphereLights.value=h1.state.hemi,g2.directionalShadowMap.value=h1.state.directionalShadowMap,g2.directionalShadowMatrix.value=h1.state.directionalShadowMatrix,g2.spotShadowMap.value=h1.state.spotShadowMap,g2.spotLightMatrix.value=h1.state.spotLightMatrix,g2.spotLightMap.value=h1.state.spotLightMap,g2.pointShadowMap.value=h1.state.pointShadowMap,g2.pointShadowMatrix.value=h1.state.pointShadowMatrix);const U2=m2.getUniforms(),b0=L8.seqWithValue(U2.seq,g2);return o1.currentProgram=m2,o1.uniformsList=b0,m2}function Hn(k,r1){const f1=M1.get(k);f1.outputEncoding=r1.outputEncoding,f1.instancing=r1.instancing,f1.skinning=r1.skinning,f1.morphTargets=r1.morphTargets,f1.morphNormals=r1.morphNormals,f1.morphColors=r1.morphColors,f1.morphTargetsCount=r1.morphTargetsCount,f1.numClippingPlanes=r1.numClippingPlanes,f1.numIntersection=r1.numClipIntersection,f1.vertexAlphas=r1.vertexAlphas,f1.vertexTangents=r1.vertexTangents,f1.toneMapping=r1.toneMapping}function yl(k,r1,f1,o1,h1){r1.isScene!==!0&&(r1=a1),b1.resetTextureUnits();const q1=r1.fog,a2=o1.isMeshStandardMaterial?r1.environment:null,l2=A===null?_.outputEncoding:A.isXRRenderTarget===!0?A.texture.encoding:W0,r2=(o1.isMeshStandardMaterial?J1:Q1).get(o1.envMap||a2),x2=o1.vertexColors===!0&&!!f1.attributes.color&&f1.attributes.color.itemSize===4,m2=!!o1.normalMap&&!!f1.attributes.tangent,g2=!!f1.morphAttributes.position,U2=!!f1.morphAttributes.normal,b0=!!f1.morphAttributes.color,Q0=o1.toneMapped?_.toneMapping:F4,K0=f1.morphAttributes.position||f1.morphAttributes.normal||f1.morphAttributes.color,h4=K0!==void 0?K0.length:0,b2=M1.get(o1),e9=h.state.lights;if(O===!0&&(N===!0||k!==w)){const P3=k===w&&o1.id===S;I.setState(o1,k,P3)}let J2=!1;o1.version===b2.__version?(b2.needsLights&&b2.lightsStateVersion!==e9.state.version||b2.outputEncoding!==l2||h1.isInstancedMesh&&b2.instancing===!1||!h1.isInstancedMesh&&b2.instancing===!0||h1.isSkinnedMesh&&b2.skinning===!1||!h1.isSkinnedMesh&&b2.skinning===!0||b2.envMap!==r2||o1.fog===!0&&b2.fog!==q1||b2.numClippingPlanes!==void 0&&(b2.numClippingPlanes!==I.numPlanes||b2.numIntersection!==I.numIntersection)||b2.vertexAlphas!==x2||b2.vertexTangents!==m2||b2.morphTargets!==g2||b2.morphNormals!==U2||b2.morphColors!==b0||b2.toneMapping!==Q0||g1.isWebGL2===!0&&b2.morphTargetsCount!==h4)&&(J2=!0):(J2=!0,b2.__version=o1.version);let H3=b2.currentProgram;J2===!0&&(H3=J5(o1,r1,h1));let J4=!1,K9=!1,b6=!1;const b3=H3.getUniforms(),x0=b2.uniforms;if(c1.useProgram(H3.program)&&(J4=!0,K9=!0,b6=!0),o1.id!==S&&(S=o1.id,K9=!0),J4||w!==k){if(b3.setValue(Z,"projectionMatrix",k.projectionMatrix),g1.logarithmicDepthBuffer&&b3.setValue(Z,"logDepthBufFC",2/(Math.log(k.far+1)/Math.LN2)),w!==k&&(w=k,K9=!0,b6=!0),o1.isShaderMaterial||o1.isMeshPhongMaterial||o1.isMeshToonMaterial||o1.isMeshStandardMaterial||o1.envMap){const P3=b3.map.cameraPosition;P3!==void 0&&P3.setValue(Z,u1.setFromMatrixPosition(k.matrixWorld))}(o1.isMeshPhongMaterial||o1.isMeshToonMaterial||o1.isMeshLambertMaterial||o1.isMeshBasicMaterial||o1.isMeshStandardMaterial||o1.isShaderMaterial)&&b3.setValue(Z,"isOrthographic",k.isOrthographicCamera===!0),(o1.isMeshPhongMaterial||o1.isMeshToonMaterial||o1.isMeshLambertMaterial||o1.isMeshBasicMaterial||o1.isMeshStandardMaterial||o1.isShaderMaterial||o1.isShadowMaterial||h1.isSkinnedMesh)&&b3.setValue(Z,"viewMatrix",k.matrixWorldInverse)}if(h1.isSkinnedMesh){b3.setOptional(Z,h1,"bindMatrix"),b3.setOptional(Z,h1,"bindMatrixInverse");const P3=h1.skeleton;P3&&(g1.floatVertexTextures?(P3.boneTexture===null&&P3.computeBoneTexture(),b3.setValue(Z,"boneTexture",P3.boneTexture,b1),b3.setValue(Z,"boneTextureSize",P3.boneTextureSize)):console.warn("THREE.WebGLRenderer: SkinnedMesh can only be used with WebGL 2. With WebGL 1 OES_texture_float and vertex textures support is required."))}const x6=f1.morphAttributes;if((x6.position!==void 0||x6.normal!==void 0||x6.color!==void 0&&g1.isWebGL2===!0)&&w1.update(h1,f1,o1,H3),(K9||b2.receiveShadow!==h1.receiveShadow)&&(b2.receiveShadow=h1.receiveShadow,b3.setValue(Z,"receiveShadow",h1.receiveShadow)),o1.isMeshGouraudMaterial&&o1.envMap!==null&&(x0.envMap.value=r2,x0.flipEnvMap.value=r2.isCubeTexture&&r2.isRenderTargetTexture===!1?-1:1),K9&&(b3.setValue(Z,"toneMappingExposure",_.toneMappingExposure),b2.needsLights&&bl(x0,b6),q1&&o1.fog===!0&&i2.refreshFogUniforms(x0,q1),i2.refreshMaterialUniforms(x0,o1,V,U,B),L8.upload(Z,b2.uniformsList,x0,b1)),o1.isShaderMaterial&&o1.uniformsNeedUpdate===!0&&(L8.upload(Z,b2.uniformsList,x0,b1),o1.uniformsNeedUpdate=!1),o1.isSpriteMaterial&&b3.setValue(Z,"center",h1.center),b3.setValue(Z,"modelViewMatrix",h1.modelViewMatrix),b3.setValue(Z,"normalMatrix",h1.normalMatrix),b3.setValue(Z,"modelMatrix",h1.matrixWorld),o1.isShaderMaterial||o1.isRawShaderMaterial){const P3=o1.uniformsGroups;for(let S6=0,Sl=P3.length;S6<Sl;S6++)if(g1.isWebGL2){const $n=P3[S6];P1.update($n,H3),P1.bind($n,H3)}else console.warn("THREE.WebGLRenderer: Uniform Buffer Objects can only be used with WebGL 2.")}return H3}function bl(k,r1){k.ambientLightColor.needsUpdate=r1,k.lightProbe.needsUpdate=r1,k.directionalLights.needsUpdate=r1,k.directionalLightShadows.needsUpdate=r1,k.pointLights.needsUpdate=r1,k.pointLightShadows.needsUpdate=r1,k.spotLights.needsUpdate=r1,k.spotLightShadows.needsUpdate=r1,k.rectAreaLights.needsUpdate=r1,k.hemisphereLights.needsUpdate=r1}function xl(k){return k.isMeshLambertMaterial||k.isMeshToonMaterial||k.isMeshPhongMaterial||k.isMeshStandardMaterial||k.isShadowMaterial||k.isShaderMaterial&&k.lights===!0}this.getActiveCubeFace=function(){return b},this.getActiveMipmapLevel=function(){return x},this.getRenderTarget=function(){return A},this.setRenderTargetTextures=function(k,r1,f1){M1.get(k.texture).__webglTexture=r1,M1.get(k.depthTexture).__webglTexture=f1;const o1=M1.get(k);o1.__hasExternalTextures=!0,o1.__hasExternalTextures&&(o1.__autoAllocateDepthBuffer=f1===void 0,o1.__autoAllocateDepthBuffer||m1.has("WEBGL_multisampled_render_to_texture")===!0&&(console.warn("THREE.WebGLRenderer: Render-to-texture extension was disabled because an external texture was provided"),o1.__useRenderToTexture=!1))},this.setRenderTargetFramebuffer=function(k,r1){const f1=M1.get(k);f1.__webglFramebuffer=r1,f1.__useDefaultFramebuffer=r1===void 0},this.setRenderTarget=function(k,r1=0,f1=0){A=k,b=r1,x=f1;let o1=!0;if(k){const r2=M1.get(k);r2.__useDefaultFramebuffer!==void 0?(c1.bindFramebuffer(36160,null),o1=!1):r2.__webglFramebuffer===void 0?b1.setupRenderTarget(k):r2.__hasExternalTextures&&b1.rebindTextures(k,M1.get(k.texture).__webglTexture,M1.get(k.depthTexture).__webglTexture)}let h1=null,q1=!1,a2=!1;if(k){const r2=k.texture;(r2.isData3DTexture||r2.isDataArrayTexture)&&(a2=!0);const x2=M1.get(k).__webglFramebuffer;k.isWebGLCubeRenderTarget?(h1=x2[r1],q1=!0):g1.isWebGL2&&k.samples>0&&b1.useMultisampledRTT(k)===!1?h1=M1.get(k).__webglMultisampledFramebuffer:h1=x2,M.copy(k.viewport),D.copy(k.scissor),T=k.scissorTest}else M.copy(z).multiplyScalar(V).floor(),D.copy(F).multiplyScalar(V).floor(),T=G;if(c1.bindFramebuffer(36160,h1)&&g1.drawBuffers&&o1&&c1.drawBuffers(k,h1),c1.viewport(M),c1.scissor(D),c1.setScissorTest(T),q1){const r2=M1.get(k.texture);Z.framebufferTexture2D(36160,36064,34069+r1,r2.__webglTexture,f1)}else if(a2){const r2=M1.get(k.texture),x2=r1||0;Z.framebufferTextureLayer(36160,36064,r2.__webglTexture,f1||0,x2)}S=-1},this.readRenderTargetPixels=function(k,r1,f1,o1,h1,q1,a2){if(!(k&&k.isWebGLRenderTarget)){console.error("THREE.WebGLRenderer.readRenderTargetPixels: renderTarget is not THREE.WebGLRenderTarget.");return}let l2=M1.get(k).__webglFramebuffer;if(k.isWebGLCubeRenderTarget&&a2!==void 0&&(l2=l2[a2]),l2){c1.bindFramebuffer(36160,l2);try{const r2=k.texture,x2=r2.format,m2=r2.type;if(x2!==y4&&H.convert(x2)!==Z.getParameter(35739)){console.error("THREE.WebGLRenderer.readRenderTargetPixels: renderTarget is not in RGBA or implementation defined format.");return}const g2=m2===C5&&(m1.has("EXT_color_buffer_half_float")||g1.isWebGL2&&m1.has("EXT_color_buffer_float"));if(m2!==j0&&H.convert(m2)!==Z.getParameter(35738)&&!(m2===O0&&(g1.isWebGL2||m1.has("OES_texture_float")||m1.has("WEBGL_color_buffer_float")))&&!g2){console.error("THREE.WebGLRenderer.readRenderTargetPixels: renderTarget is not in UnsignedByteType or implementation defined type.");return}r1>=0&&r1<=k.width-o1&&f1>=0&&f1<=k.height-h1&&Z.readPixels(r1,f1,o1,h1,H.convert(x2),H.convert(m2),q1)}finally{const r2=A!==null?M1.get(A).__webglFramebuffer:null;c1.bindFramebuffer(36160,r2)}}},this.copyFramebufferToTexture=function(k,r1,f1=0){const o1=Math.pow(2,-f1),h1=Math.floor(r1.image.width*o1),q1=Math.floor(r1.image.height*o1);b1.setTexture2D(r1,0),Z.copyTexSubImage2D(3553,f1,0,0,k.x,k.y,h1,q1),c1.unbindTexture()},this.copyTextureToTexture=function(k,r1,f1,o1=0){const h1=r1.image.width,q1=r1.image.height,a2=H.convert(f1.format),l2=H.convert(f1.type);b1.setTexture2D(f1,0),Z.pixelStorei(37440,f1.flipY),Z.pixelStorei(37441,f1.premultiplyAlpha),Z.pixelStorei(3317,f1.unpackAlignment),r1.isDataTexture?Z.texSubImage2D(3553,o1,k.x,k.y,h1,q1,a2,l2,r1.image.data):r1.isCompressedTexture?Z.compressedTexSubImage2D(3553,o1,k.x,k.y,r1.mipmaps[0].width,r1.mipmaps[0].height,a2,r1.mipmaps[0].data):Z.texSubImage2D(3553,o1,k.x,k.y,a2,l2,r1.image),o1===0&&f1.generateMipmaps&&Z.generateMipmap(3553),c1.unbindTexture()},this.copyTextureToTexture3D=function(k,r1,f1,o1,h1=0){if(_.isWebGL1Renderer){console.warn("THREE.WebGLRenderer.copyTextureToTexture3D: can only be used with WebGL2.");return}const q1=k.max.x-k.min.x+1,a2=k.max.y-k.min.y+1,l2=k.max.z-k.min.z+1,r2=H.convert(o1.format),x2=H.convert(o1.type);let m2;if(o1.isData3DTexture)b1.setTexture3D(o1,0),m2=32879;else if(o1.isDataArrayTexture)b1.setTexture2DArray(o1,0),m2=35866;else{console.warn("THREE.WebGLRenderer.copyTextureToTexture3D: only supports THREE.DataTexture3D and THREE.DataTexture2DArray.");return}Z.pixelStorei(37440,o1.flipY),Z.pixelStorei(37441,o1.premultiplyAlpha),Z.pixelStorei(3317,o1.unpackAlignment);const g2=Z.getParameter(3314),U2=Z.getParameter(32878),b0=Z.getParameter(3316),Q0=Z.getParameter(3315),K0=Z.getParameter(32877),h4=f1.isCompressedTexture?f1.mipmaps[0]:f1.image;Z.pixelStorei(3314,h4.width),Z.pixelStorei(32878,h4.height),Z.pixelStorei(3316,k.min.x),Z.pixelStorei(3315,k.min.y),Z.pixelStorei(32877,k.min.z),f1.isDataTexture||f1.isData3DTexture?Z.texSubImage3D(m2,h1,r1.x,r1.y,r1.z,q1,a2,l2,r2,x2,h4.data):f1.isCompressedTexture?(console.warn("THREE.WebGLRenderer.copyTextureToTexture3D: untested support for compressed srcTexture."),Z.compressedTexSubImage3D(m2,h1,r1.x,r1.y,r1.z,q1,a2,l2,r2,h4.data)):Z.texSubImage3D(m2,h1,r1.x,r1.y,r1.z,q1,a2,l2,r2,x2,h4),Z.pixelStorei(3314,g2),Z.pixelStorei(32878,U2),Z.pixelStorei(3316,b0),Z.pixelStorei(3315,Q0),Z.pixelStorei(32877,K0),h1===0&&o1.generateMipmaps&&Z.generateMipmap(m2),c1.unbindTexture()},this.initTexture=function(k){k.isCubeTexture?b1.setTextureCube(k,0):k.isData3DTexture?b1.setTexture3D(k,0):k.isDataArrayTexture?b1.setTexture2DArray(k,0):b1.setTexture2D(k,0),c1.unbindTexture()},this.resetState=function(){b=0,x=0,A=null,c1.reset(),i1.reset()},typeof __THREE_DEVTOOLS__!="undefined"&&__THREE_DEVTOOLS__.dispatchEvent(new CustomEvent("observe",{detail:this}))}class $T extends Br{}$T.prototype.isWebGL1Renderer=!0;class YT extends v3{constructor(){super(),this.isScene=!0,this.type="Scene",this.background=null,this.environment=null,this.fog=null,this.overrideMaterial=null,typeof __THREE_DEVTOOLS__!="undefined"&&__THREE_DEVTOOLS__.dispatchEvent(new CustomEvent("observe",{detail:this}))}copy(e,t){return super.copy(e,t),e.background!==null&&(this.background=e.background.clone()),e.environment!==null&&(this.environment=e.environment.clone()),e.fog!==null&&(this.fog=e.fog.clone()),e.overrideMaterial!==null&&(this.overrideMaterial=e.overrideMaterial.clone()),this.matrixAutoUpdate=e.matrixAutoUpdate,this}toJSON(e){const t=super.toJSON(e);return this.fog!==null&&(t.object.fog=this.fog.toJSON()),t}get autoUpdate(){return console.warn("THREE.Scene: autoUpdate was renamed to matrixWorldAutoUpdate in r144."),this.matrixWorldAutoUpdate}set autoUpdate(e){console.warn("THREE.Scene: autoUpdate was renamed to matrixWorldAutoUpdate in r144."),this.matrixWorldAutoUpdate=e}}class JT{constructor(e,t){this.isInterleavedBuffer=!0,this.array=e,this.stride=t,this.count=e!==void 0?e.length/t:0,this.usage=De,this.updateRange={offset:0,count:-1},this.version=0,this.uuid=c0()}onUploadCallback(){}set needsUpdate(e){e===!0&&this.version++}setUsage(e){return this.usage=e,this}copy(e){return this.array=new e.array.constructor(e.array),this.count=e.count,this.stride=e.stride,this.usage=e.usage,this}copyAt(e,t,n){e*=this.stride,n*=t.stride;for(let i=0,a=this.stride;i<a;i++)this.array[e+i]=t.array[n+i];return this}set(e,t=0){return this.array.set(e,t),this}clone(e){e.arrayBuffers===void 0&&(e.arrayBuffers={}),this.array.buffer._uuid===void 0&&(this.array.buffer._uuid=c0()),e.arrayBuffers[this.array.buffer._uuid]===void 0&&(e.arrayBuffers[this.array.buffer._uuid]=this.array.slice(0).buffer);const t=new this.array.constructor(e.arrayBuffers[this.array.buffer._uuid]),n=new this.constructor(t,this.stride);return n.setUsage(this.usage),n}onUpload(e){return this.onUploadCallback=e,this}toJSON(e){return e.arrayBuffers===void 0&&(e.arrayBuffers={}),this.array.buffer._uuid===void 0&&(this.array.buffer._uuid=c0()),e.arrayBuffers[this.array.buffer._uuid]===void 0&&(e.arrayBuffers[this.array.buffer._uuid]=Array.from(new Uint32Array(this.array.buffer))),{uuid:this.uuid,buffer:this.array.buffer._uuid,type:this.array.constructor.name,stride:this.stride}}}const x3=new Y;class $8{constructor(e,t,n,i=!1){this.isInterleavedBufferAttribute=!0,this.name="",this.data=e,this.itemSize=t,this.offset=n,this.normalized=i===!0}get count(){return this.data.count}get array(){return this.data.array}set needsUpdate(e){this.data.needsUpdate=e}applyMatrix4(e){for(let t=0,n=this.data.count;t<n;t++)x3.fromBufferAttribute(this,t),x3.applyMatrix4(e),this.setXYZ(t,x3.x,x3.y,x3.z);return this}applyNormalMatrix(e){for(let t=0,n=this.count;t<n;t++)x3.fromBufferAttribute(this,t),x3.applyNormalMatrix(e),this.setXYZ(t,x3.x,x3.y,x3.z);return this}transformDirection(e){for(let t=0,n=this.count;t<n;t++)x3.fromBufferAttribute(this,t),x3.transformDirection(e),this.setXYZ(t,x3.x,x3.y,x3.z);return this}setX(e,t){return this.normalized&&(t=T2(t,this.array)),this.data.array[e*this.data.stride+this.offset]=t,this}setY(e,t){return this.normalized&&(t=T2(t,this.array)),this.data.array[e*this.data.stride+this.offset+1]=t,this}setZ(e,t){return this.normalized&&(t=T2(t,this.array)),this.data.array[e*this.data.stride+this.offset+2]=t,this}setW(e,t){return this.normalized&&(t=T2(t,this.array)),this.data.array[e*this.data.stride+this.offset+3]=t,this}getX(e){let t=this.data.array[e*this.data.stride+this.offset];return this.normalized&&(t=a0(t,this.array)),t}getY(e){let t=this.data.array[e*this.data.stride+this.offset+1];return this.normalized&&(t=a0(t,this.array)),t}getZ(e){let t=this.data.array[e*this.data.stride+this.offset+2];return this.normalized&&(t=a0(t,this.array)),t}getW(e){let t=this.data.array[e*this.data.stride+this.offset+3];return this.normalized&&(t=a0(t,this.array)),t}setXY(e,t,n){return e=e*this.data.stride+this.offset,this.normalized&&(t=T2(t,this.array),n=T2(n,this.array)),this.data.array[e+0]=t,this.data.array[e+1]=n,this}setXYZ(e,t,n,i){return e=e*this.data.stride+this.offset,this.normalized&&(t=T2(t,this.array),n=T2(n,this.array),i=T2(i,this.array)),this.data.array[e+0]=t,this.data.array[e+1]=n,this.data.array[e+2]=i,this}setXYZW(e,t,n,i,a){return e=e*this.data.stride+this.offset,this.normalized&&(t=T2(t,this.array),n=T2(n,this.array),i=T2(i,this.array),a=T2(a,this.array)),this.data.array[e+0]=t,this.data.array[e+1]=n,this.data.array[e+2]=i,this.data.array[e+3]=a,this}clone(e){if(e===void 0){console.log("THREE.InterleavedBufferAttribute.clone(): Cloning an interleaved buffer attribute will deinterleave buffer data.");const t=[];for(let n=0;n<this.count;n++){const i=n*this.data.stride+this.offset;for(let a=0;a<this.itemSize;a++)t.push(this.data.array[i+a])}return new F3(new this.array.constructor(t),this.itemSize,this.normalized)}else return e.interleavedBuffers===void 0&&(e.interleavedBuffers={}),e.interleavedBuffers[this.data.uuid]===void 0&&(e.interleavedBuffers[this.data.uuid]=this.data.clone(e)),new $8(e.interleavedBuffers[this.data.uuid],this.itemSize,this.offset,this.normalized)}toJSON(e){if(e===void 0){console.log("THREE.InterleavedBufferAttribute.toJSON(): Serializing an interleaved buffer attribute will deinterleave buffer data.");const t=[];for(let n=0;n<this.count;n++){const i=n*this.data.stride+this.offset;for(let a=0;a<this.itemSize;a++)t.push(this.data.array[i+a])}return{itemSize:this.itemSize,type:this.array.constructor.name,array:t,normalized:this.normalized}}else return e.interleavedBuffers===void 0&&(e.interleavedBuffers={}),e.interleavedBuffers[this.data.uuid]===void 0&&(e.interleavedBuffers[this.data.uuid]=this.data.toJSON(e)),{isInterleavedBufferAttribute:!0,itemSize:this.itemSize,data:this.data.uuid,offset:this.offset,normalized:this.normalized}}}class Gr extends q0{constructor(e){super(),this.isSpriteMaterial=!0,this.type="SpriteMaterial",this.color=new y2(16777215),this.map=null,this.alphaMap=null,this.rotation=0,this.sizeAttenuation=!0,this.transparent=!0,this.fog=!0,this.setValues(e)}copy(e){return super.copy(e),this.color.copy(e.color),this.map=e.map,this.alphaMap=e.alphaMap,this.rotation=e.rotation,this.sizeAttenuation=e.sizeAttenuation,this.fog=e.fog,this}}let v9;const r5=new Y,_9=new Y,y9=new Y,b9=new G1,s5=new G1,Vr=new W2,S8=new Y,l5=new Y,M8=new Y,So=new G1,s7=new G1,Mo=new G1;class ZT extends v3{constructor(e){if(super(),this.isSprite=!0,this.type="Sprite",v9===void 0){v9=new O3;const t=new Float32Array([-.5,-.5,0,0,0,.5,-.5,0,1,0,.5,.5,0,1,1,-.5,.5,0,0,1]),n=new JT(t,5);v9.setIndex([0,1,2,0,2,3]),v9.setAttribute("position",new $8(n,3,0,!1)),v9.setAttribute("uv",new $8(n,2,3,!1))}this.geometry=v9,this.material=e!==void 0?e:new Gr,this.center=new G1(.5,.5)}raycast(e,t){e.camera===null&&console.error('THREE.Sprite: "Raycaster.camera" needs to be set in order to raycast against sprites.'),_9.setFromMatrixScale(this.matrixWorld),Vr.copy(e.camera.matrixWorld),this.modelViewMatrix.multiplyMatrices(e.camera.matrixWorldInverse,this.matrixWorld),y9.setFromMatrixPosition(this.modelViewMatrix),e.camera.isPerspectiveCamera&&this.material.sizeAttenuation===!1&&_9.multiplyScalar(-y9.z);const n=this.material.rotation;let i,a;n!==0&&(a=Math.cos(n),i=Math.sin(n));const r=this.center;A8(S8.set(-.5,-.5,0),y9,r,_9,i,a),A8(l5.set(.5,-.5,0),y9,r,_9,i,a),A8(M8.set(.5,.5,0),y9,r,_9,i,a),So.set(0,0),s7.set(1,0),Mo.set(1,1);let s=e.ray.intersectTriangle(S8,l5,M8,!1,r5);if(s===null&&(A8(l5.set(-.5,.5,0),y9,r,_9,i,a),s7.set(0,1),s=e.ray.intersectTriangle(S8,M8,l5,!1,r5),s===null))return;const u=e.ray.origin.distanceTo(r5);u<e.near||u>e.far||t.push({distance:u,point:r5.clone(),uv:g4.getUV(r5,S8,l5,M8,So,s7,Mo,new G1),face:null,object:this})}copy(e,t){return super.copy(e,t),e.center!==void 0&&this.center.copy(e.center),this.material=e.material,this}}function A8(o,e,t,n,i,a){b9.subVectors(o,t).addScalar(.5).multiply(n),i!==void 0?(s5.x=a*b9.x-i*b9.y,s5.y=i*b9.x+a*b9.y):s5.copy(b9),o.copy(e),o.x+=s5.x,o.y+=s5.y,o.applyMatrix4(Vr)}class jr extends q0{constructor(e){super(),this.isLineBasicMaterial=!0,this.type="LineBasicMaterial",this.color=new y2(16777215),this.linewidth=1,this.linecap="round",this.linejoin="round",this.fog=!0,this.setValues(e)}copy(e){return super.copy(e),this.color.copy(e.color),this.linewidth=e.linewidth,this.linecap=e.linecap,this.linejoin=e.linejoin,this.fog=e.fog,this}}const Ao=new Y,wo=new Y,Co=new W2,l7=new fn,w8=new V5;class qT extends v3{constructor(e=new O3,t=new jr){super(),this.isLine=!0,this.type="Line",this.geometry=e,this.material=t,this.updateMorphTargets()}copy(e,t){return super.copy(e,t),this.material=e.material,this.geometry=e.geometry,this}computeLineDistances(){const e=this.geometry;if(e.index===null){const t=e.attributes.position,n=[0];for(let i=1,a=t.count;i<a;i++)Ao.fromBufferAttribute(t,i-1),wo.fromBufferAttribute(t,i),n[i]=n[i-1],n[i]+=Ao.distanceTo(wo);e.setAttribute("lineDistance",new w3(n,1))}else console.warn("THREE.Line.computeLineDistances(): Computation only possible with non-indexed BufferGeometry.");return this}raycast(e,t){const n=this.geometry,i=this.matrixWorld,a=e.params.Line.threshold,r=n.drawRange;if(n.boundingSphere===null&&n.computeBoundingSphere(),w8.copy(n.boundingSphere),w8.applyMatrix4(i),w8.radius+=a,e.ray.intersectsSphere(w8)===!1)return;Co.copy(i).invert(),l7.copy(e.ray).applyMatrix4(Co);const s=a/((this.scale.x+this.scale.y+this.scale.z)/3),u=s*s,c=new Y,f=new Y,p=new Y,h=new Y,m=this.isLineSegments?2:1,y=n.index,v=n.attributes.position;if(y!==null){const b=Math.max(0,r.start),x=Math.min(y.count,r.start+r.count);for(let A=b,S=x-1;A<S;A+=m){const w=y.getX(A),M=y.getX(A+1);if(c.fromBufferAttribute(v,w),f.fromBufferAttribute(v,M),l7.distanceSqToSegment(c,f,h,p)>u)continue;h.applyMatrix4(this.matrixWorld);const T=e.ray.origin.distanceTo(h);T<e.near||T>e.far||t.push({distance:T,point:p.clone().applyMatrix4(this.matrixWorld),index:A,face:null,faceIndex:null,object:this})}}else{const b=Math.max(0,r.start),x=Math.min(v.count,r.start+r.count);for(let A=b,S=x-1;A<S;A+=m){if(c.fromBufferAttribute(v,A),f.fromBufferAttribute(v,A+1),l7.distanceSqToSegment(c,f,h,p)>u)continue;h.applyMatrix4(this.matrixWorld);const M=e.ray.origin.distanceTo(h);M<e.near||M>e.far||t.push({distance:M,point:p.clone().applyMatrix4(this.matrixWorld),index:A,face:null,faceIndex:null,object:this})}}}updateMorphTargets(){const t=this.geometry.morphAttributes,n=Object.keys(t);if(n.length>0){const i=t[n[0]];if(i!==void 0){this.morphTargetInfluences=[],this.morphTargetDictionary={};for(let a=0,r=i.length;a<r;a++){const s=i[a].name||String(a);this.morphTargetInfluences.push(0),this.morphTargetDictionary[s]=a}}}}}class hn extends q0{constructor(e){super(),this.isPointsMaterial=!0,this.type="PointsMaterial",this.color=new y2(16777215),this.map=null,this.alphaMap=null,this.size=1,this.sizeAttenuation=!0,this.fog=!0,this.setValues(e)}copy(e){return super.copy(e),this.color.copy(e.color),this.map=e.map,this.alphaMap=e.alphaMap,this.size=e.size,this.sizeAttenuation=e.sizeAttenuation,this.fog=e.fog,this}}const To=new W2,Pe=new fn,C8=new V5,T8=new Y;class Wr extends v3{constructor(e=new O3,t=new hn){super(),this.isPoints=!0,this.type="Points",this.geometry=e,this.material=t,this.updateMorphTargets()}copy(e,t){return super.copy(e,t),this.material=e.material,this.geometry=e.geometry,this}raycast(e,t){const n=this.geometry,i=this.matrixWorld,a=e.params.Points.threshold,r=n.drawRange;if(n.boundingSphere===null&&n.computeBoundingSphere(),C8.copy(n.boundingSphere),C8.applyMatrix4(i),C8.radius+=a,e.ray.intersectsSphere(C8)===!1)return;To.copy(i).invert(),Pe.copy(e.ray).applyMatrix4(To);const s=a/((this.scale.x+this.scale.y+this.scale.z)/3),u=s*s,c=n.index,p=n.attributes.position;if(c!==null){const h=Math.max(0,r.start),m=Math.min(c.count,r.start+r.count);for(let y=h,_=m;y<_;y++){const v=c.getX(y);T8.fromBufferAttribute(p,v),Eo(T8,v,u,i,e,t,this)}}else{const h=Math.max(0,r.start),m=Math.min(p.count,r.start+r.count);for(let y=h,_=m;y<_;y++)T8.fromBufferAttribute(p,y),Eo(T8,y,u,i,e,t,this)}}updateMorphTargets(){const t=this.geometry.morphAttributes,n=Object.keys(t);if(n.length>0){const i=t[n[0]];if(i!==void 0){this.morphTargetInfluences=[],this.morphTargetDictionary={};for(let a=0,r=i.length;a<r;a++){const s=i[a].name||String(a);this.morphTargetInfluences.push(0),this.morphTargetDictionary[s]=a}}}}}function Eo(o,e,t,n,i,a,r){const s=Pe.distanceSqToPoint(o);if(s<t){const u=new Y;Pe.closestPointToPoint(o,u),u.applyMatrix4(n);const c=i.ray.origin.distanceTo(u);if(c<i.near||c>i.far)return;a.push({distance:c,distanceToRay:Math.sqrt(s),point:u,index:e,face:null,object:r})}}class $4{constructor(){this.type="Curve",this.arcLengthDivisions=200}getPoint(){return console.warn("THREE.Curve: .getPoint() not implemented."),null}getPointAt(e,t){const n=this.getUtoTmapping(e);return this.getPoint(n,t)}getPoints(e=5){const t=[];for(let n=0;n<=e;n++)t.push(this.getPoint(n/e));return t}getSpacedPoints(e=5){const t=[];for(let n=0;n<=e;n++)t.push(this.getPointAt(n/e));return t}getLength(){const e=this.getLengths();return e[e.length-1]}getLengths(e=this.arcLengthDivisions){if(this.cacheArcLengths&&this.cacheArcLengths.length===e+1&&!this.needsUpdate)return this.cacheArcLengths;this.needsUpdate=!1;const t=[];let n,i=this.getPoint(0),a=0;t.push(0);for(let r=1;r<=e;r++)n=this.getPoint(r/e),a+=n.distanceTo(i),t.push(a),i=n;return this.cacheArcLengths=t,t}updateArcLengths(){this.needsUpdate=!0,this.getLengths()}getUtoTmapping(e,t){const n=this.getLengths();let i=0;const a=n.length;let r;t?r=t:r=e*n[a-1];let s=0,u=a-1,c;for(;s<=u;)if(i=Math.floor(s+(u-s)/2),c=n[i]-r,c<0)s=i+1;else if(c>0)u=i-1;else{u=i;break}if(i=u,n[i]===r)return i/(a-1);const f=n[i],h=n[i+1]-f,m=(r-f)/h;return(i+m)/(a-1)}getTangent(e,t){let i=e-1e-4,a=e+1e-4;i<0&&(i=0),a>1&&(a=1);const r=this.getPoint(i),s=this.getPoint(a),u=t||(r.isVector2?new G1:new Y);return u.copy(s).sub(r).normalize(),u}getTangentAt(e,t){const n=this.getUtoTmapping(e);return this.getTangent(n,t)}computeFrenetFrames(e,t){const n=new Y,i=[],a=[],r=[],s=new Y,u=new W2;for(let m=0;m<=e;m++){const y=m/e;i[m]=this.getTangentAt(y,new Y)}a[0]=new Y,r[0]=new Y;let c=Number.MAX_VALUE;const f=Math.abs(i[0].x),p=Math.abs(i[0].y),h=Math.abs(i[0].z);f<=c&&(c=f,n.set(1,0,0)),p<=c&&(c=p,n.set(0,1,0)),h<=c&&n.set(0,0,1),s.crossVectors(i[0],n).normalize(),a[0].crossVectors(i[0],s),r[0].crossVectors(i[0],a[0]);for(let m=1;m<=e;m++){if(a[m]=a[m-1].clone(),r[m]=r[m-1].clone(),s.crossVectors(i[m-1],i[m]),s.length()>Number.EPSILON){s.normalize();const y=Math.acos(m3(i[m-1].dot(i[m]),-1,1));a[m].applyMatrix4(u.makeRotationAxis(s,y))}r[m].crossVectors(i[m],a[m])}if(t===!0){let m=Math.acos(m3(a[0].dot(a[e]),-1,1));m/=e,i[0].dot(s.crossVectors(a[0],a[e]))>0&&(m=-m);for(let y=1;y<=e;y++)a[y].applyMatrix4(u.makeRotationAxis(i[y],m*y)),r[y].crossVectors(i[y],a[y])}return{tangents:i,normals:a,binormals:r}}clone(){return new this.constructor().copy(this)}copy(e){return this.arcLengthDivisions=e.arcLengthDivisions,this}toJSON(){const e={metadata:{version:4.5,type:"Curve",generator:"Curve.toJSON"}};return e.arcLengthDivisions=this.arcLengthDivisions,e.type=this.type,e}fromJSON(e){return this.arcLengthDivisions=e.arcLengthDivisions,this}}class Hr extends $4{constructor(e=0,t=0,n=1,i=1,a=0,r=Math.PI*2,s=!1,u=0){super(),this.isEllipseCurve=!0,this.type="EllipseCurve",this.aX=e,this.aY=t,this.xRadius=n,this.yRadius=i,this.aStartAngle=a,this.aEndAngle=r,this.aClockwise=s,this.aRotation=u}getPoint(e,t){const n=t||new G1,i=Math.PI*2;let a=this.aEndAngle-this.aStartAngle;const r=Math.abs(a)<Number.EPSILON;for(;a<0;)a+=i;for(;a>i;)a-=i;a<Number.EPSILON&&(r?a=0:a=i),this.aClockwise===!0&&!r&&(a===i?a=-i:a=a-i);const s=this.aStartAngle+e*a;let u=this.aX+this.xRadius*Math.cos(s),c=this.aY+this.yRadius*Math.sin(s);if(this.aRotation!==0){const f=Math.cos(this.aRotation),p=Math.sin(this.aRotation),h=u-this.aX,m=c-this.aY;u=h*f-m*p+this.aX,c=h*p+m*f+this.aY}return n.set(u,c)}copy(e){return super.copy(e),this.aX=e.aX,this.aY=e.aY,this.xRadius=e.xRadius,this.yRadius=e.yRadius,this.aStartAngle=e.aStartAngle,this.aEndAngle=e.aEndAngle,this.aClockwise=e.aClockwise,this.aRotation=e.aRotation,this}toJSON(){const e=super.toJSON();return e.aX=this.aX,e.aY=this.aY,e.xRadius=this.xRadius,e.yRadius=this.yRadius,e.aStartAngle=this.aStartAngle,e.aEndAngle=this.aEndAngle,e.aClockwise=this.aClockwise,e.aRotation=this.aRotation,e}fromJSON(e){return super.fromJSON(e),this.aX=e.aX,this.aY=e.aY,this.xRadius=e.xRadius,this.yRadius=e.yRadius,this.aStartAngle=e.aStartAngle,this.aEndAngle=e.aEndAngle,this.aClockwise=e.aClockwise,this.aRotation=e.aRotation,this}}class mn extends Hr{constructor(e,t,n,i,a,r){super(e,t,n,n,i,a,r),this.isArcCurve=!0,this.type="ArcCurve"}}function gn(){let o=0,e=0,t=0,n=0;function i(a,r,s,u){o=a,e=s,t=-3*a+3*r-2*s-u,n=2*a-2*r+s+u}return{initCatmullRom:function(a,r,s,u,c){i(r,s,c*(s-a),c*(u-r))},initNonuniformCatmullRom:function(a,r,s,u,c,f,p){let h=(r-a)/c-(s-a)/(c+f)+(s-r)/f,m=(s-r)/f-(u-r)/(f+p)+(u-s)/p;h*=f,m*=f,i(r,s,h,m)},calc:function(a){const r=a*a,s=r*a;return o+e*a+t*r+n*s}}}const E8=new Y,u7=new gn,c7=new gn,d7=new gn;class $r extends $4{constructor(e=[],t=!1,n="centripetal",i=.5){super(),this.isCatmullRomCurve3=!0,this.type="CatmullRomCurve3",this.points=e,this.closed=t,this.curveType=n,this.tension=i}getPoint(e,t=new Y){const n=t,i=this.points,a=i.length,r=(a-(this.closed?0:1))*e;let s=Math.floor(r),u=r-s;this.closed?s+=s>0?0:(Math.floor(Math.abs(s)/a)+1)*a:u===0&&s===a-1&&(s=a-2,u=1);let c,f;this.closed||s>0?c=i[(s-1)%a]:(E8.subVectors(i[0],i[1]).add(i[0]),c=E8);const p=i[s%a],h=i[(s+1)%a];if(this.closed||s+2<a?f=i[(s+2)%a]:(E8.subVectors(i[a-1],i[a-2]).add(i[a-1]),f=E8),this.curveType==="centripetal"||this.curveType==="chordal"){const m=this.curveType==="chordal"?.5:.25;let y=Math.pow(c.distanceToSquared(p),m),_=Math.pow(p.distanceToSquared(h),m),v=Math.pow(h.distanceToSquared(f),m);_<1e-4&&(_=1),y<1e-4&&(y=_),v<1e-4&&(v=_),u7.initNonuniformCatmullRom(c.x,p.x,h.x,f.x,y,_,v),c7.initNonuniformCatmullRom(c.y,p.y,h.y,f.y,y,_,v),d7.initNonuniformCatmullRom(c.z,p.z,h.z,f.z,y,_,v)}else this.curveType==="catmullrom"&&(u7.initCatmullRom(c.x,p.x,h.x,f.x,this.tension),c7.initCatmullRom(c.y,p.y,h.y,f.y,this.tension),d7.initCatmullRom(c.z,p.z,h.z,f.z,this.tension));return n.set(u7.calc(u),c7.calc(u),d7.calc(u)),n}copy(e){super.copy(e),this.points=[];for(let t=0,n=e.points.length;t<n;t++){const i=e.points[t];this.points.push(i.clone())}return this.closed=e.closed,this.curveType=e.curveType,this.tension=e.tension,this}toJSON(){const e=super.toJSON();e.points=[];for(let t=0,n=this.points.length;t<n;t++){const i=this.points[t];e.points.push(i.toArray())}return e.closed=this.closed,e.curveType=this.curveType,e.tension=this.tension,e}fromJSON(e){super.fromJSON(e),this.points=[];for(let t=0,n=e.points.length;t<n;t++){const i=e.points[t];this.points.push(new Y().fromArray(i))}return this.closed=e.closed,this.curveType=e.curveType,this.tension=e.tension,this}}function Do(o,e,t,n,i){const a=(n-e)*.5,r=(i-t)*.5,s=o*o,u=o*s;return(2*t-2*n+a+r)*u+(-3*t+3*n-2*a-r)*s+a*o+t}function XT(o,e){const t=1-o;return t*t*e}function QT(o,e){return 2*(1-o)*o*e}function KT(o,e){return o*o*e}function y5(o,e,t,n){return XT(o,e)+QT(o,t)+KT(o,n)}function eE(o,e){const t=1-o;return t*t*t*e}function tE(o,e){const t=1-o;return 3*t*t*o*e}function nE(o,e){return 3*(1-o)*o*o*e}function iE(o,e){return o*o*o*e}function b5(o,e,t,n,i){return eE(o,e)+tE(o,t)+nE(o,n)+iE(o,i)}class oE extends $4{constructor(e=new G1,t=new G1,n=new G1,i=new G1){super(),this.isCubicBezierCurve=!0,this.type="CubicBezierCurve",this.v0=e,this.v1=t,this.v2=n,this.v3=i}getPoint(e,t=new G1){const n=t,i=this.v0,a=this.v1,r=this.v2,s=this.v3;return n.set(b5(e,i.x,a.x,r.x,s.x),b5(e,i.y,a.y,r.y,s.y)),n}copy(e){return super.copy(e),this.v0.copy(e.v0),this.v1.copy(e.v1),this.v2.copy(e.v2),this.v3.copy(e.v3),this}toJSON(){const e=super.toJSON();return e.v0=this.v0.toArray(),e.v1=this.v1.toArray(),e.v2=this.v2.toArray(),e.v3=this.v3.toArray(),e}fromJSON(e){return super.fromJSON(e),this.v0.fromArray(e.v0),this.v1.fromArray(e.v1),this.v2.fromArray(e.v2),this.v3.fromArray(e.v3),this}}class aE extends $4{constructor(e=new Y,t=new Y,n=new Y,i=new Y){super(),this.isCubicBezierCurve3=!0,this.type="CubicBezierCurve3",this.v0=e,this.v1=t,this.v2=n,this.v3=i}getPoint(e,t=new Y){const n=t,i=this.v0,a=this.v1,r=this.v2,s=this.v3;return n.set(b5(e,i.x,a.x,r.x,s.x),b5(e,i.y,a.y,r.y,s.y),b5(e,i.z,a.z,r.z,s.z)),n}copy(e){return super.copy(e),this.v0.copy(e.v0),this.v1.copy(e.v1),this.v2.copy(e.v2),this.v3.copy(e.v3),this}toJSON(){const e=super.toJSON();return e.v0=this.v0.toArray(),e.v1=this.v1.toArray(),e.v2=this.v2.toArray(),e.v3=this.v3.toArray(),e}fromJSON(e){return super.fromJSON(e),this.v0.fromArray(e.v0),this.v1.fromArray(e.v1),this.v2.fromArray(e.v2),this.v3.fromArray(e.v3),this}}class rE extends $4{constructor(e=new G1,t=new G1){super(),this.isLineCurve=!0,this.type="LineCurve",this.v1=e,this.v2=t}getPoint(e,t=new G1){const n=t;return e===1?n.copy(this.v2):(n.copy(this.v2).sub(this.v1),n.multiplyScalar(e).add(this.v1)),n}getPointAt(e,t){return this.getPoint(e,t)}getTangent(e,t){const n=t||new G1;return n.copy(this.v2).sub(this.v1).normalize(),n}copy(e){return super.copy(e),this.v1.copy(e.v1),this.v2.copy(e.v2),this}toJSON(){const e=super.toJSON();return e.v1=this.v1.toArray(),e.v2=this.v2.toArray(),e}fromJSON(e){return super.fromJSON(e),this.v1.fromArray(e.v1),this.v2.fromArray(e.v2),this}}class sE extends $4{constructor(e=new Y,t=new Y){super(),this.isLineCurve3=!0,this.type="LineCurve3",this.v1=e,this.v2=t}getPoint(e,t=new Y){const n=t;return e===1?n.copy(this.v2):(n.copy(this.v2).sub(this.v1),n.multiplyScalar(e).add(this.v1)),n}getPointAt(e,t){return this.getPoint(e,t)}copy(e){return super.copy(e),this.v1.copy(e.v1),this.v2.copy(e.v2),this}toJSON(){const e=super.toJSON();return e.v1=this.v1.toArray(),e.v2=this.v2.toArray(),e}fromJSON(e){return super.fromJSON(e),this.v1.fromArray(e.v1),this.v2.fromArray(e.v2),this}}class lE extends $4{constructor(e=new G1,t=new G1,n=new G1){super(),this.isQuadraticBezierCurve=!0,this.type="QuadraticBezierCurve",this.v0=e,this.v1=t,this.v2=n}getPoint(e,t=new G1){const n=t,i=this.v0,a=this.v1,r=this.v2;return n.set(y5(e,i.x,a.x,r.x),y5(e,i.y,a.y,r.y)),n}copy(e){return super.copy(e),this.v0.copy(e.v0),this.v1.copy(e.v1),this.v2.copy(e.v2),this}toJSON(){const e=super.toJSON();return e.v0=this.v0.toArray(),e.v1=this.v1.toArray(),e.v2=this.v2.toArray(),e}fromJSON(e){return super.fromJSON(e),this.v0.fromArray(e.v0),this.v1.fromArray(e.v1),this.v2.fromArray(e.v2),this}}class Yr extends $4{constructor(e=new Y,t=new Y,n=new Y){super(),this.isQuadraticBezierCurve3=!0,this.type="QuadraticBezierCurve3",this.v0=e,this.v1=t,this.v2=n}getPoint(e,t=new Y){const n=t,i=this.v0,a=this.v1,r=this.v2;return n.set(y5(e,i.x,a.x,r.x),y5(e,i.y,a.y,r.y),y5(e,i.z,a.z,r.z)),n}copy(e){return super.copy(e),this.v0.copy(e.v0),this.v1.copy(e.v1),this.v2.copy(e.v2),this}toJSON(){const e=super.toJSON();return e.v0=this.v0.toArray(),e.v1=this.v1.toArray(),e.v2=this.v2.toArray(),e}fromJSON(e){return super.fromJSON(e),this.v0.fromArray(e.v0),this.v1.fromArray(e.v1),this.v2.fromArray(e.v2),this}}class uE extends $4{constructor(e=[]){super(),this.isSplineCurve=!0,this.type="SplineCurve",this.points=e}getPoint(e,t=new G1){const n=t,i=this.points,a=(i.length-1)*e,r=Math.floor(a),s=a-r,u=i[r===0?r:r-1],c=i[r],f=i[r>i.length-2?i.length-1:r+1],p=i[r>i.length-3?i.length-1:r+2];return n.set(Do(s,u.x,c.x,f.x,p.x),Do(s,u.y,c.y,f.y,p.y)),n}copy(e){super.copy(e),this.points=[];for(let t=0,n=e.points.length;t<n;t++){const i=e.points[t];this.points.push(i.clone())}return this}toJSON(){const e=super.toJSON();e.points=[];for(let t=0,n=this.points.length;t<n;t++){const i=this.points[t];e.points.push(i.toArray())}return e}fromJSON(e){super.fromJSON(e),this.points=[];for(let t=0,n=e.points.length;t<n;t++){const i=e.points[t];this.points.push(new G1().fromArray(i))}return this}}var cE=Object.freeze({__proto__:null,ArcCurve:mn,CatmullRomCurve3:$r,CubicBezierCurve:oE,CubicBezierCurve3:aE,EllipseCurve:Hr,LineCurve:rE,LineCurve3:sE,QuadraticBezierCurve:lE,QuadraticBezierCurve3:Yr,SplineCurve:uE});class i0 extends O3{constructor(e=1,t=32,n=16,i=0,a=Math.PI*2,r=0,s=Math.PI){super(),this.type="SphereGeometry",this.parameters={radius:e,widthSegments:t,heightSegments:n,phiStart:i,phiLength:a,thetaStart:r,thetaLength:s},t=Math.max(3,Math.floor(t)),n=Math.max(2,Math.floor(n));const u=Math.min(r+s,Math.PI);let c=0;const f=[],p=new Y,h=new Y,m=[],y=[],_=[],v=[];for(let b=0;b<=n;b++){const x=[],A=b/n;let S=0;b==0&&r==0?S=.5/t:b==n&&u==Math.PI&&(S=-.5/t);for(let w=0;w<=t;w++){const M=w/t;p.x=-e*Math.cos(i+M*a)*Math.sin(r+A*s),p.y=e*Math.cos(r+A*s),p.z=e*Math.sin(i+M*a)*Math.sin(r+A*s),y.push(p.x,p.y,p.z),h.copy(p).normalize(),_.push(h.x,h.y,h.z),v.push(M+S,1-A),x.push(c++)}f.push(x)}for(let b=0;b<n;b++)for(let x=0;x<t;x++){const A=f[b][x+1],S=f[b][x],w=f[b+1][x],M=f[b+1][x+1];(b!==0||r>0)&&m.push(A,S,M),(b!==n-1||u<Math.PI)&&m.push(S,w,M)}this.setIndex(m),this.setAttribute("position",new w3(y,3)),this.setAttribute("normal",new w3(_,3)),this.setAttribute("uv",new w3(v,2))}static fromJSON(e){return new i0(e.radius,e.widthSegments,e.heightSegments,e.phiStart,e.phiLength,e.thetaStart,e.thetaLength)}}class vn extends O3{constructor(e=new Yr(new Y(-1,-1,0),new Y(-1,1,0),new Y(1,1,0)),t=64,n=1,i=8,a=!1){super(),this.type="TubeGeometry",this.parameters={path:e,tubularSegments:t,radius:n,radialSegments:i,closed:a};const r=e.computeFrenetFrames(t,a);this.tangents=r.tangents,this.normals=r.normals,this.binormals=r.binormals;const s=new Y,u=new Y,c=new G1;let f=new Y;const p=[],h=[],m=[],y=[];_(),this.setIndex(y),this.setAttribute("position",new w3(p,3)),this.setAttribute("normal",new w3(h,3)),this.setAttribute("uv",new w3(m,2));function _(){for(let A=0;A<t;A++)v(A);v(a===!1?t:0),x(),b()}function v(A){f=e.getPointAt(A/t,f);const S=r.normals[A],w=r.binormals[A];for(let M=0;M<=i;M++){const D=M/i*Math.PI*2,T=Math.sin(D),E=-Math.cos(D);u.x=E*S.x+T*w.x,u.y=E*S.y+T*w.y,u.z=E*S.z+T*w.z,u.normalize(),h.push(u.x,u.y,u.z),s.x=f.x+n*u.x,s.y=f.y+n*u.y,s.z=f.z+n*u.z,p.push(s.x,s.y,s.z)}}function b(){for(let A=1;A<=t;A++)for(let S=1;S<=i;S++){const w=(i+1)*(A-1)+(S-1),M=(i+1)*A+(S-1),D=(i+1)*A+S,T=(i+1)*(A-1)+S;y.push(w,M,T),y.push(M,D,T)}}function x(){for(let A=0;A<=t;A++)for(let S=0;S<=i;S++)c.x=A/t,c.y=S/i,m.push(c.x,c.y)}}toJSON(){const e=super.toJSON();return e.path=this.parameters.path.toJSON(),e}static fromJSON(e){return new vn(new cE[e.path.type]().fromJSON(e.path),e.tubularSegments,e.radius,e.radialSegments,e.closed)}}const Io={enabled:!1,files:{},add:function(o,e){this.enabled!==!1&&(this.files[o]=e)},get:function(o){if(this.enabled!==!1)return this.files[o]},remove:function(o){delete this.files[o]},clear:function(){this.files={}}};class Jr{constructor(e,t,n){const i=this;let a=!1,r=0,s=0,u;const c=[];this.onStart=void 0,this.onLoad=e,this.onProgress=t,this.onError=n,this.itemStart=function(f){s++,a===!1&&i.onStart!==void 0&&i.onStart(f,r,s),a=!0},this.itemEnd=function(f){r++,i.onProgress!==void 0&&i.onProgress(f,r,s),r===s&&(a=!1,i.onLoad!==void 0&&i.onLoad())},this.itemError=function(f){i.onError!==void 0&&i.onError(f)},this.resolveURL=function(f){return u?u(f):f},this.setURLModifier=function(f){return u=f,this},this.addHandler=function(f,p){return c.push(f,p),this},this.removeHandler=function(f){const p=c.indexOf(f);return p!==-1&&c.splice(p,2),this},this.getHandler=function(f){for(let p=0,h=c.length;p<h;p+=2){const m=c[p],y=c[p+1];if(m.global&&(m.lastIndex=0),m.test(f))return y}return null}}}const dE=new Jr;class Zr{constructor(e){this.manager=e!==void 0?e:dE,this.crossOrigin="anonymous",this.withCredentials=!1,this.path="",this.resourcePath="",this.requestHeader={}}load(){}loadAsync(e,t){const n=this;return new Promise(function(i,a){n.load(e,i,t,a)})}parse(){}setCrossOrigin(e){return this.crossOrigin=e,this}setWithCredentials(e){return this.withCredentials=e,this}setPath(e){return this.path=e,this}setResourcePath(e){return this.resourcePath=e,this}setRequestHeader(e){return this.requestHeader=e,this}}class fE extends Zr{constructor(e){super(e)}load(e,t,n,i){this.path!==void 0&&(e=this.path+e),e=this.manager.resolveURL(e);const a=this,r=Io.get(e);if(r!==void 0)return a.manager.itemStart(e),setTimeout(function(){t&&t(r),a.manager.itemEnd(e)},0),r;const s=T5("img");function u(){f(),Io.add(e,this),t&&t(this),a.manager.itemEnd(e)}function c(p){f(),i&&i(p),a.manager.itemError(e),a.manager.itemEnd(e)}function f(){s.removeEventListener("load",u,!1),s.removeEventListener("error",c,!1)}return s.addEventListener("load",u,!1),s.addEventListener("error",c,!1),e.slice(0,5)!=="data:"&&this.crossOrigin!==void 0&&(s.crossOrigin=this.crossOrigin),a.manager.itemStart(e),s.src=e,s}}class pE extends Zr{constructor(e){super(e)}load(e,t,n,i){const a=new i4,r=new fE(this.manager);return r.setCrossOrigin(this.crossOrigin),r.setPath(this.path),r.load(e,function(s){a.image=s,a.needsUpdate=!0,t!==void 0&&t(a)},n,i),a}}class Ro{constructor(e=1,t=0,n=0){return this.radius=e,this.phi=t,this.theta=n,this}set(e,t,n){return this.radius=e,this.phi=t,this.theta=n,this}copy(e){return this.radius=e.radius,this.phi=e.phi,this.theta=e.theta,this}makeSafe(){return this.phi=Math.max(1e-6,Math.min(Math.PI-1e-6,this.phi)),this}setFromVector3(e){return this.setFromCartesianCoords(e.x,e.y,e.z)}setFromCartesianCoords(e,t,n){return this.radius=Math.sqrt(e*e+t*t+n*n),this.radius===0?(this.theta=0,this.phi=0):(this.theta=Math.atan2(e,n),this.phi=Math.acos(m3(t/this.radius,-1,1))),this}clone(){return new this.constructor().copy(this)}}typeof __THREE_DEVTOOLS__!="undefined"&&__THREE_DEVTOOLS__.dispatchEvent(new CustomEvent("register",{detail:{revision:dn}}));typeof window!="undefined"&&(window.__THREE__?console.warn("WARNING: Multiple instances of Three.js being imported."):window.__THREE__=dn);const Oo={type:"change"},f7={type:"start"},Po={type:"end"};class hE extends Z0{constructor(e,t){super(),this.object=e,this.domElement=t,this.domElement.style.touchAction="none",this.enabled=!0,this.target=new Y,this.minDistance=0,this.maxDistance=1/0,this.minZoom=0,this.maxZoom=1/0,this.minPolarAngle=0,this.maxPolarAngle=Math.PI,this.minAzimuthAngle=-1/0,this.maxAzimuthAngle=1/0,this.enableDamping=!1,this.dampingFactor=.05,this.enableZoom=!0,this.zoomSpeed=1,this.enableRotate=!0,this.rotateSpeed=1,this.enablePan=!0,this.panSpeed=1,this.screenSpacePanning=!0,this.keyPanSpeed=7,this.autoRotate=!1,this.autoRotateSpeed=2,this.keys={LEFT:"ArrowLeft",UP:"ArrowUp",RIGHT:"ArrowRight",BOTTOM:"ArrowDown"},this.mouseButtons={LEFT:t9.ROTATE,MIDDLE:t9.DOLLY,RIGHT:t9.PAN},this.touches={ONE:n9.ROTATE,TWO:n9.DOLLY_PAN},this.target0=this.target.clone(),this.position0=this.object.position.clone(),this.zoom0=this.object.zoom,this._domElementKeyEvents=null,this.getPolarAngle=function(){return s.phi},this.getAzimuthalAngle=function(){return s.theta},this.getDistance=function(){return this.object.position.distanceTo(this.target)},this.listenToKeyEvents=function(H){H.addEventListener("keydown",W1),this._domElementKeyEvents=H},this.saveState=function(){n.target0.copy(n.target),n.position0.copy(n.object.position),n.zoom0=n.object.zoom},this.reset=function(){n.target.copy(n.target0),n.object.position.copy(n.position0),n.object.zoom=n.zoom0,n.object.updateProjectionMatrix(),n.dispatchEvent(Oo),n.update(),a=i.NONE},this.update=function(){const H=new Y,i1=new B4().setFromUnitVectors(e.up,new Y(0,1,0)),P1=i1.clone().invert(),z1=new Y,F1=new B4,j1=2*Math.PI;return function(){const _2=n.object.position;H.copy(_2).sub(n.target),H.applyQuaternion(i1),s.setFromVector3(H),n.autoRotate&&a===i.NONE&&E(D()),n.enableDamping?(s.theta+=u.theta*n.dampingFactor,s.phi+=u.phi*n.dampingFactor):(s.theta+=u.theta,s.phi+=u.phi);let K=n.minAzimuthAngle,k1=n.maxAzimuthAngle;return isFinite(K)&&isFinite(k1)&&(K<-Math.PI?K+=j1:K>Math.PI&&(K-=j1),k1<-Math.PI?k1+=j1:k1>Math.PI&&(k1-=j1),K<=k1?s.theta=Math.max(K,Math.min(k1,s.theta)):s.theta=s.theta>(K+k1)/2?Math.max(K,s.theta):Math.min(k1,s.theta)),s.phi=Math.max(n.minPolarAngle,Math.min(n.maxPolarAngle,s.phi)),s.makeSafe(),s.radius*=c,s.radius=Math.max(n.minDistance,Math.min(n.maxDistance,s.radius)),n.enableDamping===!0?n.target.addScaledVector(f,n.dampingFactor):n.target.add(f),H.setFromSpherical(s),H.applyQuaternion(P1),_2.copy(n.target).add(H),n.object.lookAt(n.target),n.enableDamping===!0?(u.theta*=1-n.dampingFactor,u.phi*=1-n.dampingFactor,f.multiplyScalar(1-n.dampingFactor)):(u.set(0,0,0),f.set(0,0,0)),c=1,p||z1.distanceToSquared(n.object.position)>r||8*(1-F1.dot(n.object.quaternion))>r?(n.dispatchEvent(Oo),z1.copy(n.object.position),F1.copy(n.object.quaternion),p=!1,!0):!1}}(),this.dispose=function(){n.domElement.removeEventListener("contextmenu",l1),n.domElement.removeEventListener("pointerdown",Q1),n.domElement.removeEventListener("pointercancel",n2),n.domElement.removeEventListener("wheel",i2),n.domElement.removeEventListener("pointermove",J1),n.domElement.removeEventListener("pointerup",$1),n._domElementKeyEvents!==null&&n._domElementKeyEvents.removeEventListener("keydown",W1)};const n=this,i={NONE:-1,ROTATE:0,DOLLY:1,PAN:2,TOUCH_ROTATE:3,TOUCH_PAN:4,TOUCH_DOLLY_PAN:5,TOUCH_DOLLY_ROTATE:6};let a=i.NONE;const r=1e-6,s=new Ro,u=new Ro;let c=1;const f=new Y;let p=!1;const h=new G1,m=new G1,y=new G1,_=new G1,v=new G1,b=new G1,x=new G1,A=new G1,S=new G1,w=[],M={};function D(){return 2*Math.PI/60/60*n.autoRotateSpeed}function T(){return Math.pow(.95,n.zoomSpeed)}function E(H){u.theta-=H}function U(H){u.phi-=H}const V=function(){const H=new Y;return function(P1,z1){H.setFromMatrixColumn(z1,0),H.multiplyScalar(-P1),f.add(H)}}(),q=function(){const H=new Y;return function(P1,z1){n.screenSpacePanning===!0?H.setFromMatrixColumn(z1,1):(H.setFromMatrixColumn(z1,0),H.crossVectors(n.object.up,H)),H.multiplyScalar(P1),f.add(H)}}(),$=function(){const H=new Y;return function(P1,z1){const F1=n.domElement;if(n.object.isPerspectiveCamera){const j1=n.object.position;H.copy(j1).sub(n.target);let o2=H.length();o2*=Math.tan(n.object.fov/2*Math.PI/180),V(2*P1*o2/F1.clientHeight,n.object.matrix),q(2*z1*o2/F1.clientHeight,n.object.matrix)}else n.object.isOrthographicCamera?(V(P1*(n.object.right-n.object.left)/n.object.zoom/F1.clientWidth,n.object.matrix),q(z1*(n.object.top-n.object.bottom)/n.object.zoom/F1.clientHeight,n.object.matrix)):(console.warn("WARNING: OrbitControls.js encountered an unknown camera type - pan disabled."),n.enablePan=!1)}}();function z(H){n.object.isPerspectiveCamera?c/=H:n.object.isOrthographicCamera?(n.object.zoom=Math.max(n.minZoom,Math.min(n.maxZoom,n.object.zoom*H)),n.object.updateProjectionMatrix(),p=!0):(console.warn("WARNING: OrbitControls.js encountered an unknown camera type - dolly/zoom disabled."),n.enableZoom=!1)}function F(H){n.object.isPerspectiveCamera?c*=H:n.object.isOrthographicCamera?(n.object.zoom=Math.max(n.minZoom,Math.min(n.maxZoom,n.object.zoom/H)),n.object.updateProjectionMatrix(),p=!0):(console.warn("WARNING: OrbitControls.js encountered an unknown camera type - dolly/zoom disabled."),n.enableZoom=!1)}function G(H){h.set(H.clientX,H.clientY)}function J(H){x.set(H.clientX,H.clientY)}function O(H){_.set(H.clientX,H.clientY)}function N(H){m.set(H.clientX,H.clientY),y.subVectors(m,h).multiplyScalar(n.rotateSpeed);const i1=n.domElement;E(2*Math.PI*y.x/i1.clientHeight),U(2*Math.PI*y.y/i1.clientHeight),h.copy(m),n.update()}function B(H){A.set(H.clientX,H.clientY),S.subVectors(A,x),S.y>0?z(T()):S.y<0&&F(T()),x.copy(A),n.update()}function t1(H){v.set(H.clientX,H.clientY),b.subVectors(v,_).multiplyScalar(n.panSpeed),$(b.x,b.y),_.copy(v),n.update()}function n1(H){H.deltaY<0?F(T()):H.deltaY>0&&z(T()),n.update()}function u1(H){let i1=!1;switch(H.code){case n.keys.UP:$(0,n.keyPanSpeed),i1=!0;break;case n.keys.BOTTOM:$(0,-n.keyPanSpeed),i1=!0;break;case n.keys.LEFT:$(n.keyPanSpeed,0),i1=!0;break;case n.keys.RIGHT:$(-n.keyPanSpeed,0),i1=!0;break}i1&&(H.preventDefault(),n.update())}function a1(){if(w.length===1)h.set(w[0].pageX,w[0].pageY);else{const H=.5*(w[0].pageX+w[1].pageX),i1=.5*(w[0].pageY+w[1].pageY);h.set(H,i1)}}function Q(){if(w.length===1)_.set(w[0].pageX,w[0].pageY);else{const H=.5*(w[0].pageX+w[1].pageX),i1=.5*(w[0].pageY+w[1].pageY);_.set(H,i1)}}function Z(){const H=w[0].pageX-w[1].pageX,i1=w[0].pageY-w[1].pageY,P1=Math.sqrt(H*H+i1*i1);x.set(0,P1)}function d1(){n.enableZoom&&Z(),n.enablePan&&Q()}function m1(){n.enableZoom&&Z(),n.enableRotate&&a1()}function g1(H){if(w.length==1)m.set(H.pageX,H.pageY);else{const P1=H1(H),z1=.5*(H.pageX+P1.x),F1=.5*(H.pageY+P1.y);m.set(z1,F1)}y.subVectors(m,h).multiplyScalar(n.rotateSpeed);const i1=n.domElement;E(2*Math.PI*y.x/i1.clientHeight),U(2*Math.PI*y.y/i1.clientHeight),h.copy(m)}function c1(H){if(w.length===1)v.set(H.pageX,H.pageY);else{const i1=H1(H),P1=.5*(H.pageX+i1.x),z1=.5*(H.pageY+i1.y);v.set(P1,z1)}b.subVectors(v,_).multiplyScalar(n.panSpeed),$(b.x,b.y),_.copy(v)}function L1(H){const i1=H1(H),P1=H.pageX-i1.x,z1=H.pageY-i1.y,F1=Math.sqrt(P1*P1+z1*z1);A.set(0,F1),S.set(0,Math.pow(A.y/x.y,n.zoomSpeed)),z(S.y),x.copy(A)}function M1(H){n.enableZoom&&L1(H),n.enablePan&&c1(H)}function b1(H){n.enableZoom&&L1(H),n.enableRotate&&g1(H)}function Q1(H){n.enabled!==!1&&(w.length===0&&(n.domElement.setPointerCapture(H.pointerId),n.domElement.addEventListener("pointermove",J1),n.domElement.addEventListener("pointerup",$1)),x1(H),H.pointerType==="touch"?P(H):s2(H))}function J1(H){n.enabled!==!1&&(H.pointerType==="touch"?I(H):V1(H))}function $1(H){w1(H),w.length===0&&(n.domElement.releasePointerCapture(H.pointerId),n.domElement.removeEventListener("pointermove",J1),n.domElement.removeEventListener("pointerup",$1)),n.dispatchEvent(Po),a=i.NONE}function n2(H){w1(H)}function s2(H){let i1;switch(H.button){case 0:i1=n.mouseButtons.LEFT;break;case 1:i1=n.mouseButtons.MIDDLE;break;case 2:i1=n.mouseButtons.RIGHT;break;default:i1=-1}switch(i1){case t9.DOLLY:if(n.enableZoom===!1)return;J(H),a=i.DOLLY;break;case t9.ROTATE:if(H.ctrlKey||H.metaKey||H.shiftKey){if(n.enablePan===!1)return;O(H),a=i.PAN}else{if(n.enableRotate===!1)return;G(H),a=i.ROTATE}break;case t9.PAN:if(H.ctrlKey||H.metaKey||H.shiftKey){if(n.enableRotate===!1)return;G(H),a=i.ROTATE}else{if(n.enablePan===!1)return;O(H),a=i.PAN}break;default:a=i.NONE}a!==i.NONE&&n.dispatchEvent(f7)}function V1(H){switch(a){case i.ROTATE:if(n.enableRotate===!1)return;N(H);break;case i.DOLLY:if(n.enableZoom===!1)return;B(H);break;case i.PAN:if(n.enablePan===!1)return;t1(H);break}}function i2(H){n.enabled===!1||n.enableZoom===!1||a!==i.NONE||(H.preventDefault(),n.dispatchEvent(f7),n1(H),n.dispatchEvent(Po))}function W1(H){n.enabled===!1||n.enablePan===!1||u1(H)}function P(H){switch(O1(H),w.length){case 1:switch(n.touches.ONE){case n9.ROTATE:if(n.enableRotate===!1)return;a1(),a=i.TOUCH_ROTATE;break;case n9.PAN:if(n.enablePan===!1)return;Q(),a=i.TOUCH_PAN;break;default:a=i.NONE}break;case 2:switch(n.touches.TWO){case n9.DOLLY_PAN:if(n.enableZoom===!1&&n.enablePan===!1)return;d1(),a=i.TOUCH_DOLLY_PAN;break;case n9.DOLLY_ROTATE:if(n.enableZoom===!1&&n.enableRotate===!1)return;m1(),a=i.TOUCH_DOLLY_ROTATE;break;default:a=i.NONE}break;default:a=i.NONE}a!==i.NONE&&n.dispatchEvent(f7)}function I(H){switch(O1(H),a){case i.TOUCH_ROTATE:if(n.enableRotate===!1)return;g1(H),n.update();break;case i.TOUCH_PAN:if(n.enablePan===!1)return;c1(H),n.update();break;case i.TOUCH_DOLLY_PAN:if(n.enableZoom===!1&&n.enablePan===!1)return;M1(H),n.update();break;case i.TOUCH_DOLLY_ROTATE:if(n.enableZoom===!1&&n.enableRotate===!1)return;b1(H),n.update();break;default:a=i.NONE}}function l1(H){n.enabled!==!1&&H.preventDefault()}function x1(H){w.push(H)}function w1(H){delete M[H.pointerId];for(let i1=0;i1<w.length;i1++)if(w[i1].pointerId==H.pointerId){w.splice(i1,1);return}}function O1(H){let i1=M[H.pointerId];i1===void 0&&(i1=new G1,M[H.pointerId]=i1),i1.set(H.pageX,H.pageY)}function H1(H){const i1=H.pointerId===w[0].pointerId?w[1]:w[0];return M[i1.pointerId]}n.domElement.addEventListener("contextmenu",l1),n.domElement.addEventListener("pointerdown",Q1),n.domElement.addEventListener("pointercancel",n2),n.domElement.addEventListener("wheel",i2,{passive:!1}),this.update()}}class mE{constructor(e){X(this,"scene");X(this,"camera");X(this,"renderer");X(this,"controls");X(this,"dom");this.dom=e,this.initScenes(),this.setControls()}initScenes(){this.scene=new YT,this.camera=new q3(45,window.innerWidth/window.innerHeight,1,1e5),this.camera.position.set(0,30,-250),this.renderer=new Br({alpha:!0,antialias:!0,preserveDrawingBuffer:!0}),this.renderer.setPixelRatio(window.devicePixelRatio),this.renderer.setSize(window.innerWidth,window.innerHeight),this.dom.appendChild(this.renderer.domElement)}setControls(){this.controls=new hE(this.camera,this.renderer.domElement),this.controls.autoRotateSpeed=3,this.controls.enableDamping=!0,this.controls.dampingFactor=.05,this.controls.enableZoom=!0,this.controls.minDistance=100,this.controls.maxDistance=300,this.controls.enablePan=!1}}const gE="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADwAAAA/CAYAAAC8aKvcAAAOHElEQVRogb2b268dVR3Hv3Pb+1zYqR7hGBCIgqg0LdbwgGJU5MEoMSQSfdEQJSbGBFFfjDz4D5AYDQneEh4wchENkcgDii8QUxBKEUpRaAqFFmlt6UG6z+m+zMwas37ru9asmT2zz96nhWmms8/ec1mf9fut322tCVAUeAe24Cze8qw2MD6Da2eF2gq8D9l2/ZY6YisSbmtA/ft20Ee9366Z2vD6b23nzgwxD3ATQND42QL1Gq5ZbPhuUGtw3/u72iFtn5t+b4GYDXia9MxnH9JCdbzzXp9BtS9kg8c82o6wHVDCz9MJtYZvDtwG2wyqIS1cl3/rLdkEOK3BjlBIB/jwVfAtQc8LPAlbB7WQGnCNR4h5NMd+A3iPjcxQCPgK/04FUjlwX+KT0p4Jeh7g6bAnEDrQPkIB1HAx94jXnW4AXkKB3INehKrAb0BVJG6lXYU+K8CTsFaFVwlopbqMUCS6iBADAmvIEUI56n3Aay28hVzkMRcg5TpgCCXwGrwHJbDn8bhF6GnAzbB1Fe4hFIkmAhRiAaFAdhBiTNiUx5C7vyk2TB8TKAHt8Kjhu1AOXEP70n4bagr0XMD1cTsJq1V4mbB10ByRQCYIkSEUyBghUgTyr74VAlsgg0Is6qwEXgN35LscI0IPCF4f21dCzSLpOnCTRa7CHkfoVLgvEBG6AhaJRJX8HckxF8mG7pgR2JeyEtxCrsjkqhLadJ3ZxwSvS9uq+HGoWdTbB54N1qqwHau6OYuuWQZU22SNoHcfOKe0rZQLNiQkdg6FgHdJ5exc5B7ys36Cht5Avgl0HdhBt8XSJXwbbCpgZt+QEWrsseLn2APXMoUbwWHlKQUNlIHO3TEWfcjlOnBgiIDYYbpD+tI+4AQg0D0RkPbTE6D2aVbCzb5WS9daYztmBzI+I1HhnHDgHrhjgpTQhbfrRhcO2OAqwipKN5SxmNFBZe6z3vVdR8ixjAwDSjxBXpF0acjsmPahi7qEJy1y3UCl0uAqrDZPEJkk/JwIbsbfIzomJchaOcsOjgTajFe43UKm0k0Fh1cq/wfYALAs0ZfpFr0toBCvsSr+v3AdWuObnh4uOtcTOAOVU4192EycUoJI+jsRncjlO3OOkqOVdFgzWlqyRro5YSMXdhiNyDlgQjlfx+gF3Vch3bnBqKwr3wU11a7Ax43Gypdul9FTJg80bmZIiYWeCofiSDpsRofRc8epOxiKFByLdrPqDFFno8a5dK3RCCVXGXnHDsCYO0XwBdEB5WKDUsqoSzqsgDZJt0PpDuhnc7oda6SyCmxXfjVpw4JWtINX49aHrsANn1jGqjiUAEsSTOo9kBhraVcPqw/uxFcOXo0fy3WBIJj7QEKPDiJ03LNGNI6KGjdi+/p0maWUJ3z+5BiuS9dY5cAZnCFVWnkSziVsMI0qCM5c6T0Rzv/yebjyunNxw/4NPH/XG3jq9sM4qMfiDy7Gpd88H1ftXMaOMEB0coyjMhqN9TfRWU6pFpQiOATsv658Zz1Am5SdpKeP4Q5H14BuBYIYMoqKZAQF4npiSjlxUoEZUfvW8eLnV3CBBrriHOz62Uew6ycfwpq+/UqCFf9x+zbwEoGtCwJNViGmzQLHMtojicJSOrUlIhspG9DJsVxRabiTrDontWwn5Rj2gws7hgMaKzN+u1bK1z6Dh395BA8qMwphQX3YvEB+xxH8+dq9+CuUAHedLSicAUwq9qAQ02baYpOUAWP6DveG6orvE0udt+oM5q/6hmPGxoXYYhM2+g3IKeEqvEDffAD7btyPe09lWK834FSO9Rv3475bDmC/gNrrAsLa+ykPWLfB+nircZEnHLRXWCYl7G9rvIFN8TIvRLQBBdxjSott8p2EDkSg7z2KN297FX+vP+K2Q9h933GcdGPfQvqwQU2yftgasnttZoZadaVWV5scw349SjGUy+h5lah1QEmHdCplA6pRV8JHW8l3xqaDKtvYXN9hyGhHrqLTySTIDOnHfV9uDZVi7q2vPM32DrzKS61A2CzhujpolKGXz2YCbx4WUNohpV3frZQKJKoBWJm7J57qxtSOqFGqNloLvfaMvcJC3KDKnnuartKxdyM/gbf/lJH0P6/Cd+7ejmu3L+EcRkdlNFaUDW8FVpWILPY6L7r8HPR+uwPX7L0K3yZswEA0ZBJh2hQ1j9lJpLYt8VS56s1sPuvAP7SEHbt6uO7r5yN/+TQOPHwS//75Ybx8aMDuMncJi2Kygwuwuca/iqpesoCl71+EHV96Hz522TIuDYDorbHoXfncQqKvZsjEq5bODDzH9t8hXtu2jIt1wz68hMtvWcLlN18I9a/TeO2hN/HKr47gP0cGKIp6eUcDG40ILlrEwnc/gEuvX8UHty/hojCods6xDK+6Dp+llD5uPkunh0HFLel00Oa+OttJGWCk6CAWn9hhcLCADAuIsKTLbvfsxBe/topvJNr71TZVQD23jqMnUoy+sIJL/F8fWcOhc2Ms7Orh/XVIvaUFBvccxf03vYBHxCyFUuQ5LSW+BENJGHWtJcFYYnCbaXWRN6SLxXTgIWH1rm+f0HUoxsq5lAIkHtYx8ue24YI7t+NbWspnQ3MODPDSTftx9+Nv4w2BVQQOcRrAUHbtNDOMERO4EDHlFWBT75KkY9JoDVgRtFvmZSSKhbaIJZnQpXb6b/XYW1i77An8+heH8cBI1xa3uOlrbz+CBz+6G3c+fgprrIIolzsX/Ke423Rxie1eaVd6H7h60pgZqQXORc5KHIyFtQ0JJGPNGern3zuAJz+zB3e8sI5X5kXev4FDn96D3/zwReyRlFG5aoiSCNqmk2Gt4LfINtpZjJE3TdMAXJ2s0ieOWPXvUYk77NvcA8xZobBHrySz5xTWdjyBe376Gv4y1DXHTbahwvi2V/G3nf/AH/b2JbnIK+Udv1P1nslTlcuJcwrHlg7s1t8s8PBPSL2b5HIjW5lQ8sCqZLNKacakGumPDuKZTz2Nu55fx+E25H3rOPzJp/C7Ww/iWXmKKdqlrvrRBB1RwiGj+LE3PTPmsKxPw7YGHgNvHsdCj1kcjydUuCy2hXJ2yupTah//7CmcvOJJ/PH+Y6Kmle33x/D0x5/En55bF6mOJW4yOmafnk4U9eyzQ5G00b6uVC2NVvrqXCvbhhMF67pa91j47rK+kLk4p1ThyGtYwMYGtV1hvG9dEvyqdPs4BmPgxuxWe33Ke6WuIyMPHmKbq9IdcB7KGt3+pIT9wKNwZR57or7wBKGP03BELKermhqHkorbINRWRAIXP+lqg1HTagPMdyMBNKAlfCntVFBCT8LV4rwxYtpwrUFhW8OqggbgctNS3sv+HdHsL9Iih5zcCpksKmlARkmUsLZWEbhyu4oxCRyZ8T7iuB8LvOmAMbXGSDeu1ajH4muNdG12pbz5pnKSrQJeBy4rfXUpp/TBxmCErGBmrG+lrEimXqoRsJ5s7htAxUGLhHXUVHA4WFhIFm4kXdqGdunGjKjGjcZqohBf1KqWhdSBHuXcrYlYTIpY0BEMpQKSy6i20LGrI5fJuC2nauAmlQ4o4cBBjd2YNuAppV+VbswJNivdrptCbZVum0pX4ctlBgorMseksMxxbBpgZiIsbOFmF+CSQVNFzpMGCXeMFIesSY+pLeX4DRgyBtwjWuvI8xQLMm5Viyq3+uH69KK5sE8VuZBLD+xcrZ0vLJhiGKMylt2qY87xCFHZYaNKG38tv3vjd+SptLlv7FnokNIdenPFk1OmTUyNY7hZtSGKFsgDhgIauNqDlXTBPo+oaFahI5F3nGAy4uJ3Q28uKXWQih1osyArWXj7Bg3V27Mtf5hW8SgmJL3KnuyxdweeL04o5Vjck2lo4CQsUt59Ci/WG7L7f1KLLjOfnBK2sMqZy9QBx96s4chb+oDK3iTlmdZ4wKWPexFiG0KZTVTMleHKMho5YWXYTrDFlXpygfDhXbj+s+/FV/VNH3sLD1z3HB4SQwgXtaWVYaLkrqkDXkIqE+KryLAmRzVlInwCbtZVPGC+HLbmy+AxJWDYUDi3RTh/nYc/N+yHqhY8qUVXTQm+l+9Og8UMJR5/Bs4EJI9CYRUQN2UjsGWO6dwMSrlu5M0FgXGXseYGLeAESsDz2hMRG75WrXLfCzBmWL0zK3BTb5lar/XNZs2FqTUtUjELzi4pzgkpzgVFTrpVYDB4yGrQoZsvNuM25NqODdqTQcVQbQo7D3C5GSmbgKQvbsGYG22qBvI9XHasGI4qNwFmgCNX/jXnRdIlilFTLnbZrgjwF7KYQq7t7CbpbrptZfnwpAHzlzCZWb1yrVaXk16xt2Yr9cZwwqJCIipqoFJvyVJXJGokuyALWVSLoZoJZB4JV310XxIK09snuJqm7+5qCgbLTCLhql9mGtQvx2dMTqz6Q2yCcXkxF6uUS5TK8LFs01zbVuvShRvLYP2oQ/+soTdY9Bvw+wXOPnYZj9e3gFJeZnAZsJqRcHarvh6reTXtTNtWgA2sHctgRvU6i7c9fhfTuQQiLZNIQlbwVYHtAtOI58YMYXtM6K1kR62Z0FzQ877z0L7gVI9nf720XXDatoy4Dm29b88dlatPNa+gndkVnQlwHXrzheJ9hq+JN7PnLxJvWhxuQdvXSNdB3zVgtEKD065NrwGsecD+6nd4tbT2VfBNkO8o8HRoNLzo4c83dxtUeuRVV1B7yaP5PYctweIsADd/rr/G4y8uaZrG9GcINn+TZcuwOANgzPRqD2ovZTW9x4SGcurs7yq9q8CY4eWttnOmbbO6nS01/EwnxKvZ1Bk0pOW+8/626fZOvF06r0Q3285eAwH8H5voqTrwCB3hAAAAAElFTkSuQmCC",vE=Object.freeze(Object.defineProperty({__proto__:null,default:gE},Symbol.toStringTag,{value:"Module"})),_E="/static/png/label.png",yE=Object.freeze(Object.defineProperty({__proto__:null,default:_E},Symbol.toStringTag,{value:"Module"})),bE="/static/png/earth.png",xE=Object.freeze(Object.defineProperty({__proto__:null,default:bE},Symbol.toStringTag,{value:"Module"})),SE="/static/png/glow.png",ME=Object.freeze(Object.defineProperty({__proto__:null,default:SE},Symbol.toStringTag,{value:"Module"})),AE="/static/png/gradient.png",wE=Object.freeze(Object.defineProperty({__proto__:null,default:AE},Symbol.toStringTag,{value:"Module"})),CE="/static/png/label-old.png",TE=Object.freeze(Object.defineProperty({__proto__:null,default:CE},Symbol.toStringTag,{value:"Module"})),EE="/static/png/label.png",DE=Object.freeze(Object.defineProperty({__proto__:null,default:EE},Symbol.toStringTag,{value:"Module"})),IE="/static/png/light_column.png",RE=Object.freeze(Object.defineProperty({__proto__:null,default:IE},Symbol.toStringTag,{value:"Module"})),OE="/static/png/redCircle.png",PE=Object.freeze(Object.defineProperty({__proto__:null,default:OE},Symbol.toStringTag,{value:"Module"})),qr=[],Lo=Object.assign({"../../images/earth/aircraft.png":vE,"../../images/earth/aperture.png":yE,"../../images/earth/earth.png":xE,"../../images/earth/glow.png":ME,"../../images/earth/gradient.png":wE,"../../images/earth/label-old.png":TE,"../../images/earth/label.png":DE,"../../images/earth/light_column.png":RE,"../../images/earth/redCircle.png":PE});for(let o in Lo){const e=o.split("/").pop();e&&qr.push({name:e.split(".")[0],url:Lo[o].default})}const LE={textures:qr};class zE{constructor(e){X(this,"manager");X(this,"callback");X(this,"textureLoader");X(this,"textures");this.callback=e,this.textures={},this.setLoadingManager(),this.loadResources()}setLoadingManager(){this.manager=new Jr,this.manager.onStart=()=>{ru()},this.manager.onLoad=()=>{this.callback()},this.manager.onProgress=e=>{su()},this.manager.onError=e=>{lu(),window.$message.error("数据加载失败，请刷新重试！")}}loadResources(){var e;this.textureLoader=new pE(this.manager),(e=LE.textures)==null||e.forEach(t=>{this.textureLoader.load(t.url,n=>{this.textures[t.name]=n})})}}const E5=(o,e,t)=>{let n=e*Math.PI/180;const i=t*Math.PI/180;n=-n;const a=o*Math.cos(i)*Math.cos(n),r=o*Math.sin(i),s=o*Math.cos(i)*Math.sin(n);return new Y(a,r,s)},zo=o=>{const e=new X9(1,1),t=o.textures.aperture,n=new k4({color:15310696,map:t,transparent:!0,opacity:1,depthWrite:!1}),i=new u3(e,n),a=E5(o.radius*1.001,o.lon,o.lat),r=o.radius*.12;i.scale.set(r,r,r),i.userData.size=r,i.userData.scale=Math.random()*1,i.position.set(a.x,a.y,a.z);const s=new Y(a.x,a.y,a.z).normalize(),u=new Y(0,0,1);return i.quaternion.setFromUnitVectors(u,s),i},ko=o=>{const e=o.radius*.3,t=new X9(o.radius*.05,e);t.rotateX(Math.PI/2),t.translate(0,0,e/2);const n=new k4({map:o.textures.light_column,color:o.index==0?o.punctuation.lightColumn.startColor:o.punctuation.lightColumn.endColor,transparent:!0,side:_4,depthWrite:!1}),i=new u3(t,n),a=new N4;a.add(i,i.clone().rotateZ(Math.PI/2));const r=E5(o.radius,o.lon,o.lat);a.position.set(r.x,r.y,r.z);const s=new Y(r.x,r.y,r.z).normalize(),u=new Y(0,0,1);return a.quaternion.setFromUnitVectors(u,s),a},No=o=>{const e=new X9(1,1),t=new u3(e,o.material),n=E5(o.radius*1.001,o.lon,o.lat),i=o.radius*.05;t.scale.set(i,i,i),t.position.set(n.x,n.y,n.z);const a=new Y(n.x,n.y,n.z).normalize(),r=new Y(0,0,1);return t.quaternion.setFromUnitVectors(r,a),t},kE=o=>{const e=[];for(let t=0;t<2*Math.PI-.1;t+=2*Math.PI/(o.number||100))e.push([parseFloat((Math.cos(t)*(o.radius||10)).toFixed(2)),0,parseFloat((Math.sin(t)*(o.radius||10)).toFixed(2))]);return o.closed&&e.push(e[0]),e},NE=o=>{const e=[];o.pointList.forEach(i=>e.push(new Y(i[0],i[1],i[2])));const t=new $r(e),n=new vn(t,o.number||50,o.radius||1,o.radialSegments);return new u3(n,o.material)};function P4(o){if(o===void 0)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return o}function Xr(o,e){o.prototype=Object.create(e.prototype),o.prototype.constructor=o,o.__proto__=e}/*!
 * GSAP 3.12.2
 * https://greensock.com
 *
 * @license Copyright 2008-2023, GreenSock. All rights reserved.
 * Subject to the terms at https://greensock.com/standard-license or for
 * Club GreenSock members, the agreement issued with that membership.
 * @author: Jack Doyle, jack@greensock.com
*/var B3={autoSleep:120,force3D:"auto",nullTargetWarn:1,units:{lineHeight:""}},H9={duration:.5,overwrite:!1,delay:0},_n,_3,B2,Q3=1e8,C2=1/Q3,Le=Math.PI*2,UE=Le/4,FE=0,Qr=Math.sqrt,BE=Math.cos,GE=Math.sin,s3=function(e){return typeof e=="string"},G2=function(e){return typeof e=="function"},V4=function(e){return typeof e=="number"},yn=function(e){return typeof e=="undefined"},M4=function(e){return typeof e=="object"},C3=function(e){return e!==!1},bn=function(){return typeof window!="undefined"},D8=function(e){return G2(e)||s3(e)},Kr=typeof ArrayBuffer=="function"&&ArrayBuffer.isView||function(){},y3=Array.isArray,ze=/(?:-?\.?\d|\.)+/gi,es=/[-+=.]*\d+[.e\-+]*\d*[e\-+]*\d*/g,O9=/[-+=.]*\d+[.e-]*\d*[a-z%]*/g,p7=/[-+=.]*\d+\.?\d*(?:e-|e\+)?\d*/gi,ts=/[+-]=-?[.\d]+/,ns=/[^,'"\[\]\s]+/gi,VE=/^[+\-=e\s\d]*\d+[.\d]*([a-z]*|%)\s*$/i,L2,J3,ke,xn,G3={},Y8={},is,os=function(e){return(Y8=$0(e,G3))&&D3},Sn=function(e,t){return console.warn("Invalid property",e,"set to",t,"Missing plugin? gsap.registerPlugin()")},J8=function(e,t){return!t&&console.warn(e)},as=function(e,t){return e&&(G3[e]=t)&&Y8&&(Y8[e]=t)||G3},D5=function(){return 0},jE={suppressEvents:!0,isStart:!0,kill:!1},z8={suppressEvents:!0,kill:!1},WE={suppressEvents:!0},Mn={},d0=[],Ne={},rs,N3={},h7={},Uo=30,k8=[],An="",wn=function(e){var t=e[0],n,i;if(M4(t)||G2(t)||(e=[e]),!(n=(t._gsap||{}).harness)){for(i=k8.length;i--&&!k8[i].targetTest(t););n=k8[i]}for(i=e.length;i--;)e[i]&&(e[i]._gsap||(e[i]._gsap=new Is(e[i],n)))||e.splice(i,1);return e},F0=function(e){return e._gsap||wn(K3(e))[0]._gsap},ss=function(e,t,n){return(n=e[t])&&G2(n)?e[t]():yn(n)&&e.getAttribute&&e.getAttribute(t)||n},T3=function(e,t){return(e=e.split(",")).forEach(t)||e},j2=function(e){return Math.round(e*1e5)/1e5||0},l3=function(e){return Math.round(e*1e7)/1e7||0},k9=function(e,t){var n=t.charAt(0),i=parseFloat(t.substr(2));return e=parseFloat(e),n==="+"?e+i:n==="-"?e-i:n==="*"?e*i:e/i},HE=function(e,t){for(var n=t.length,i=0;e.indexOf(t[i])<0&&++i<n;);return i<n},Z8=function(){var e=d0.length,t=d0.slice(0),n,i;for(Ne={},d0.length=0,n=0;n<e;n++)i=t[n],i&&i._lazy&&(i.render(i._lazy[0],i._lazy[1],!0)._lazy=0)},ls=function(e,t,n,i){d0.length&&!_3&&Z8(),e.render(t,n,i||_3&&t<0&&(e._initted||e._startAt)),d0.length&&!_3&&Z8()},us=function(e){var t=parseFloat(e);return(t||t===0)&&(e+"").match(ns).length<2?t:s3(e)?e.trim():e},cs=function(e){return e},o4=function(e,t){for(var n in t)n in e||(e[n]=t[n]);return e},$E=function(e){return function(t,n){for(var i in n)i in t||i==="duration"&&e||i==="ease"||(t[i]=n[i])}},$0=function(e,t){for(var n in t)e[n]=t[n];return e},Fo=function o(e,t){for(var n in t)n!=="__proto__"&&n!=="constructor"&&n!=="prototype"&&(e[n]=M4(t[n])?o(e[n]||(e[n]={}),t[n]):t[n]);return e},q8=function(e,t){var n={},i;for(i in e)i in t||(n[i]=e[i]);return n},x5=function(e){var t=e.parent||L2,n=e.keyframes?$E(y3(e.keyframes)):o4;if(C3(e.inherit))for(;t;)n(e,t.vars.defaults),t=t.parent||t._dp;return e},YE=function(e,t){for(var n=e.length,i=n===t.length;i&&n--&&e[n]===t[n];);return n<0},ds=function(e,t,n,i,a){n===void 0&&(n="_first"),i===void 0&&(i="_last");var r=e[i],s;if(a)for(s=t[a];r&&r[a]>s;)r=r._prev;return r?(t._next=r._next,r._next=t):(t._next=e[n],e[n]=t),t._next?t._next._prev=t:e[i]=t,t._prev=r,t.parent=t._dp=e,t},d6=function(e,t,n,i){n===void 0&&(n="_first"),i===void 0&&(i="_last");var a=t._prev,r=t._next;a?a._next=r:e[n]===t&&(e[n]=r),r?r._prev=a:e[i]===t&&(e[i]=a),t._next=t._prev=t.parent=null},h0=function(e,t){e.parent&&(!t||e.parent.autoRemoveChildren)&&e.parent.remove&&e.parent.remove(e),e._act=0},B0=function(e,t){if(e&&(!t||t._end>e._dur||t._start<0))for(var n=e;n;)n._dirty=1,n=n.parent;return e},JE=function(e){for(var t=e.parent;t&&t.parent;)t._dirty=1,t.totalDuration(),t=t.parent;return e},Ue=function(e,t,n,i){return e._startAt&&(_3?e._startAt.revert(z8):e.vars.immediateRender&&!e.vars.autoRevert||e._startAt.render(t,!0,i))},ZE=function o(e){return!e||e._ts&&o(e.parent)},Bo=function(e){return e._repeat?$9(e._tTime,e=e.duration()+e._rDelay)*e:0},$9=function(e,t){var n=Math.floor(e/=t);return e&&n===e?n-1:n},X8=function(e,t){return(e-t._start)*t._ts+(t._ts>=0?0:t._dirty?t.totalDuration():t._tDur)},f6=function(e){return e._end=l3(e._start+(e._tDur/Math.abs(e._ts||e._rts||C2)||0))},p6=function(e,t){var n=e._dp;return n&&n.smoothChildTiming&&e._ts&&(e._start=l3(n._time-(e._ts>0?t/e._ts:((e._dirty?e.totalDuration():e._tDur)-t)/-e._ts)),f6(e),n._dirty||B0(n,e)),e},fs=function(e,t){var n;if((t._time||!t._dur&&t._initted||t._start<e._time&&(t._dur||!t.add))&&(n=X8(e.rawTime(),t),(!t._dur||H5(0,t.totalDuration(),n)-t._tTime>C2)&&t.render(n,!0)),B0(e,t)._dp&&e._initted&&e._time>=e._dur&&e._ts){if(e._dur<e.duration())for(n=e;n._dp;)n.rawTime()>=0&&n.totalTime(n._tTime),n=n._dp;e._zTime=-C2}},v4=function(e,t,n,i){return t.parent&&h0(t),t._start=l3((V4(n)?n:n||e!==L2?Y3(e,n,t):e._time)+t._delay),t._end=l3(t._start+(t.totalDuration()/Math.abs(t.timeScale())||0)),ds(e,t,"_first","_last",e._sort?"_start":0),Fe(t)||(e._recent=t),i||fs(e,t),e._ts<0&&p6(e,e._tTime),e},ps=function(e,t){return(G3.ScrollTrigger||Sn("scrollTrigger",t))&&G3.ScrollTrigger.create(t,e)},hs=function(e,t,n,i,a){if(Tn(e,t,a),!e._initted)return 1;if(!n&&e._pt&&!_3&&(e._dur&&e.vars.lazy!==!1||!e._dur&&e.vars.lazy)&&rs!==U3.frame)return d0.push(e),e._lazy=[a,i],1},qE=function o(e){var t=e.parent;return t&&t._ts&&t._initted&&!t._lock&&(t.rawTime()<0||o(t))},Fe=function(e){var t=e.data;return t==="isFromStart"||t==="isStart"},XE=function(e,t,n,i){var a=e.ratio,r=t<0||!t&&(!e._start&&qE(e)&&!(!e._initted&&Fe(e))||(e._ts<0||e._dp._ts<0)&&!Fe(e))?0:1,s=e._rDelay,u=0,c,f,p;if(s&&e._repeat&&(u=H5(0,e._tDur,t),f=$9(u,s),e._yoyo&&f&1&&(r=1-r),f!==$9(e._tTime,s)&&(a=1-r,e.vars.repeatRefresh&&e._initted&&e.invalidate())),r!==a||_3||i||e._zTime===C2||!t&&e._zTime){if(!e._initted&&hs(e,t,i,n,u))return;for(p=e._zTime,e._zTime=t||(n?C2:0),n||(n=t&&!p),e.ratio=r,e._from&&(r=1-r),e._time=0,e._tTime=u,c=e._pt;c;)c.r(r,c.d),c=c._next;t<0&&Ue(e,t,n,!0),e._onUpdate&&!n&&e4(e,"onUpdate"),u&&e._repeat&&!n&&e.parent&&e4(e,"onRepeat"),(t>=e._tDur||t<0)&&e.ratio===r&&(r&&h0(e,1),!n&&!_3&&(e4(e,r?"onComplete":"onReverseComplete",!0),e._prom&&e._prom()))}else e._zTime||(e._zTime=t)},QE=function(e,t,n){var i;if(n>t)for(i=e._first;i&&i._start<=n;){if(i.data==="isPause"&&i._start>t)return i;i=i._next}else for(i=e._last;i&&i._start>=n;){if(i.data==="isPause"&&i._start<t)return i;i=i._prev}},Y9=function(e,t,n,i){var a=e._repeat,r=l3(t)||0,s=e._tTime/e._tDur;return s&&!i&&(e._time*=r/e._dur),e._dur=r,e._tDur=a?a<0?1e10:l3(r*(a+1)+e._rDelay*a):r,s>0&&!i&&p6(e,e._tTime=e._tDur*s),e.parent&&f6(e),n||B0(e.parent,e),e},Go=function(e){return e instanceof A3?B0(e):Y9(e,e._dur)},KE={_start:0,endTime:D5,totalDuration:D5},Y3=function o(e,t,n){var i=e.labels,a=e._recent||KE,r=e.duration()>=Q3?a.endTime(!1):e._dur,s,u,c;return s3(t)&&(isNaN(t)||t in i)?(u=t.charAt(0),c=t.substr(-1)==="%",s=t.indexOf("="),u==="<"||u===">"?(s>=0&&(t=t.replace(/=/,"")),(u==="<"?a._start:a.endTime(a._repeat>=0))+(parseFloat(t.substr(1))||0)*(c?(s<0?a:n).totalDuration()/100:1)):s<0?(t in i||(i[t]=r),i[t]):(u=parseFloat(t.charAt(s-1)+t.substr(s+1)),c&&n&&(u=u/100*(y3(n)?n[0]:n).totalDuration()),s>1?o(e,t.substr(0,s-1),n)+u:r+u)):t==null?r:+t},S5=function(e,t,n){var i=V4(t[1]),a=(i?2:1)+(e<2?0:1),r=t[a],s,u;if(i&&(r.duration=t[1]),r.parent=n,e){for(s=r,u=n;u&&!("immediateRender"in s);)s=u.vars.defaults||{},u=C3(u.vars.inherit)&&u.parent;r.immediateRender=C3(s.immediateRender),e<2?r.runBackwards=1:r.startAt=t[a-1]}return new q2(t[0],r,t[a+1])},y0=function(e,t){return e||e===0?t(e):t},H5=function(e,t,n){return n<e?e:n>t?t:n},g3=function(e,t){return!s3(e)||!(t=VE.exec(e))?"":t[1]},eD=function(e,t,n){return y0(n,function(i){return H5(e,t,i)})},Be=[].slice,ms=function(e,t){return e&&M4(e)&&"length"in e&&(!t&&!e.length||e.length-1 in e&&M4(e[0]))&&!e.nodeType&&e!==J3},tD=function(e,t,n){return n===void 0&&(n=[]),e.forEach(function(i){var a;return s3(i)&&!t||ms(i,1)?(a=n).push.apply(a,K3(i)):n.push(i)})||n},K3=function(e,t,n){return B2&&!t&&B2.selector?B2.selector(e):s3(e)&&!n&&(ke||!J9())?Be.call((t||xn).querySelectorAll(e),0):y3(e)?tD(e,n):ms(e)?Be.call(e,0):e?[e]:[]},Ge=function(e){return e=K3(e)[0]||J8("Invalid scope")||{},function(t){var n=e.current||e.nativeElement||e;return K3(t,n.querySelectorAll?n:n===e?J8("Invalid scope")||xn.createElement("div"):e)}},gs=function(e){return e.sort(function(){return .5-Math.random()})},vs=function(e){if(G2(e))return e;var t=M4(e)?e:{each:e},n=G0(t.ease),i=t.from||0,a=parseFloat(t.base)||0,r={},s=i>0&&i<1,u=isNaN(i)||s,c=t.axis,f=i,p=i;return s3(i)?f=p={center:.5,edges:.5,end:1}[i]||0:!s&&u&&(f=i[0],p=i[1]),function(h,m,y){var _=(y||t).length,v=r[_],b,x,A,S,w,M,D,T,E;if(!v){if(E=t.grid==="auto"?0:(t.grid||[1,Q3])[1],!E){for(D=-Q3;D<(D=y[E++].getBoundingClientRect().left)&&E<_;);E--}for(v=r[_]=[],b=u?Math.min(E,_)*f-.5:i%E,x=E===Q3?0:u?_*p/E-.5:i/E|0,D=0,T=Q3,M=0;M<_;M++)A=M%E-b,S=x-(M/E|0),v[M]=w=c?Math.abs(c==="y"?S:A):Qr(A*A+S*S),w>D&&(D=w),w<T&&(T=w);i==="random"&&gs(v),v.max=D-T,v.min=T,v.v=_=(parseFloat(t.amount)||parseFloat(t.each)*(E>_?_-1:c?c==="y"?_/E:E:Math.max(E,_/E))||0)*(i==="edges"?-1:1),v.b=_<0?a-_:a,v.u=g3(t.amount||t.each)||0,n=n&&_<0?Ts(n):n}return _=(v[h]-v.min)/v.max||0,l3(v.b+(n?n(_):_)*v.v)+v.u}},Ve=function(e){var t=Math.pow(10,((e+"").split(".")[1]||"").length);return function(n){var i=l3(Math.round(parseFloat(n)/e)*e*t);return(i-i%1)/t+(V4(n)?0:g3(n))}},_s=function(e,t){var n=y3(e),i,a;return!n&&M4(e)&&(i=n=e.radius||Q3,e.values?(e=K3(e.values),(a=!V4(e[0]))&&(i*=i)):e=Ve(e.increment)),y0(t,n?G2(e)?function(r){return a=e(r),Math.abs(a-r)<=i?a:r}:function(r){for(var s=parseFloat(a?r.x:r),u=parseFloat(a?r.y:0),c=Q3,f=0,p=e.length,h,m;p--;)a?(h=e[p].x-s,m=e[p].y-u,h=h*h+m*m):h=Math.abs(e[p]-s),h<c&&(c=h,f=p);return f=!i||c<=i?e[f]:r,a||f===r||V4(r)?f:f+g3(r)}:Ve(e))},ys=function(e,t,n,i){return y0(y3(e)?!t:n===!0?!!(n=0):!i,function(){return y3(e)?e[~~(Math.random()*e.length)]:(n=n||1e-5)&&(i=n<1?Math.pow(10,(n+"").length-2):1)&&Math.floor(Math.round((e-n/2+Math.random()*(t-e+n*.99))/n)*n*i)/i})},nD=function(){for(var e=arguments.length,t=new Array(e),n=0;n<e;n++)t[n]=arguments[n];return function(i){return t.reduce(function(a,r){return r(a)},i)}},iD=function(e,t){return function(n){return e(parseFloat(n))+(t||g3(n))}},oD=function(e,t,n){return xs(e,t,0,1,n)},bs=function(e,t,n){return y0(n,function(i){return e[~~t(i)]})},aD=function o(e,t,n){var i=t-e;return y3(e)?bs(e,o(0,e.length),t):y0(n,function(a){return(i+(a-e)%i)%i+e})},rD=function o(e,t,n){var i=t-e,a=i*2;return y3(e)?bs(e,o(0,e.length-1),t):y0(n,function(r){return r=(a+(r-e)%a)%a||0,e+(r>i?a-r:r)})},I5=function(e){for(var t=0,n="",i,a,r,s;~(i=e.indexOf("random(",t));)r=e.indexOf(")",i),s=e.charAt(i+7)==="[",a=e.substr(i+7,r-i-7).match(s?ns:ze),n+=e.substr(t,i-t)+ys(s?a:+a[0],s?0:+a[1],+a[2]||1e-5),t=r+1;return n+e.substr(t,e.length-t)},xs=function(e,t,n,i,a){var r=t-e,s=i-n;return y0(a,function(u){return n+((u-e)/r*s||0)})},sD=function o(e,t,n,i){var a=isNaN(e+t)?0:function(m){return(1-m)*e+m*t};if(!a){var r=s3(e),s={},u,c,f,p,h;if(n===!0&&(i=1)&&(n=null),r)e={p:e},t={p:t};else if(y3(e)&&!y3(t)){for(f=[],p=e.length,h=p-2,c=1;c<p;c++)f.push(o(e[c-1],e[c]));p--,a=function(y){y*=p;var _=Math.min(h,~~y);return f[_](y-_)},n=t}else i||(e=$0(y3(e)?[]:{},e));if(!f){for(u in t)Cn.call(s,e,u,"get",t[u]);a=function(y){return In(y,s)||(r?e.p:e)}}}return y0(n,a)},Vo=function(e,t,n){var i=e.labels,a=Q3,r,s,u;for(r in i)s=i[r]-t,s<0==!!n&&s&&a>(s=Math.abs(s))&&(u=r,a=s);return u},e4=function(e,t,n){var i=e.vars,a=i[t],r=B2,s=e._ctx,u,c,f;if(a)return u=i[t+"Params"],c=i.callbackScope||e,n&&d0.length&&Z8(),s&&(B2=s),f=u?a.apply(c,u):a.call(c),B2=r,f},m5=function(e){return h0(e),e.scrollTrigger&&e.scrollTrigger.kill(!!_3),e.progress()<1&&e4(e,"onInterrupt"),e},P9,Ss=[],Ms=function(e){if(bn()&&e){e=!e.name&&e.default||e;var t=e.name,n=G2(e),i=t&&!n&&e.init?function(){this._props=[]}:e,a={init:D5,render:In,add:Cn,kill:MD,modifier:SD,rawVars:0},r={targetTest:0,get:0,getSetter:Dn,aliases:{},register:0};if(J9(),e!==i){if(N3[t])return;o4(i,o4(q8(e,a),r)),$0(i.prototype,$0(a,q8(e,r))),N3[i.prop=t]=i,e.targetTest&&(k8.push(i),Mn[t]=1),t=(t==="css"?"CSS":t.charAt(0).toUpperCase()+t.substr(1))+"Plugin"}as(t,i),e.register&&e.register(D3,i,E3)}else e&&Ss.push(e)},w2=255,g5={aqua:[0,w2,w2],lime:[0,w2,0],silver:[192,192,192],black:[0,0,0],maroon:[128,0,0],teal:[0,128,128],blue:[0,0,w2],navy:[0,0,128],white:[w2,w2,w2],olive:[128,128,0],yellow:[w2,w2,0],orange:[w2,165,0],gray:[128,128,128],purple:[128,0,128],green:[0,128,0],red:[w2,0,0],pink:[w2,192,203],cyan:[0,w2,w2],transparent:[w2,w2,w2,0]},m7=function(e,t,n){return e+=e<0?1:e>1?-1:0,(e*6<1?t+(n-t)*e*6:e<.5?n:e*3<2?t+(n-t)*(2/3-e)*6:t)*w2+.5|0},As=function(e,t,n){var i=e?V4(e)?[e>>16,e>>8&w2,e&w2]:0:g5.black,a,r,s,u,c,f,p,h,m,y;if(!i){if(e.substr(-1)===","&&(e=e.substr(0,e.length-1)),g5[e])i=g5[e];else if(e.charAt(0)==="#"){if(e.length<6&&(a=e.charAt(1),r=e.charAt(2),s=e.charAt(3),e="#"+a+a+r+r+s+s+(e.length===5?e.charAt(4)+e.charAt(4):"")),e.length===9)return i=parseInt(e.substr(1,6),16),[i>>16,i>>8&w2,i&w2,parseInt(e.substr(7),16)/255];e=parseInt(e.substr(1),16),i=[e>>16,e>>8&w2,e&w2]}else if(e.substr(0,3)==="hsl"){if(i=y=e.match(ze),!t)u=+i[0]%360/360,c=+i[1]/100,f=+i[2]/100,r=f<=.5?f*(c+1):f+c-f*c,a=f*2-r,i.length>3&&(i[3]*=1),i[0]=m7(u+1/3,a,r),i[1]=m7(u,a,r),i[2]=m7(u-1/3,a,r);else if(~e.indexOf("="))return i=e.match(es),n&&i.length<4&&(i[3]=1),i}else i=e.match(ze)||g5.transparent;i=i.map(Number)}return t&&!y&&(a=i[0]/w2,r=i[1]/w2,s=i[2]/w2,p=Math.max(a,r,s),h=Math.min(a,r,s),f=(p+h)/2,p===h?u=c=0:(m=p-h,c=f>.5?m/(2-p-h):m/(p+h),u=p===a?(r-s)/m+(r<s?6:0):p===r?(s-a)/m+2:(a-r)/m+4,u*=60),i[0]=~~(u+.5),i[1]=~~(c*100+.5),i[2]=~~(f*100+.5)),n&&i.length<4&&(i[3]=1),i},ws=function(e){var t=[],n=[],i=-1;return e.split(f0).forEach(function(a){var r=a.match(O9)||[];t.push.apply(t,r),n.push(i+=r.length+1)}),t.c=n,t},jo=function(e,t,n){var i="",a=(e+i).match(f0),r=t?"hsla(":"rgba(",s=0,u,c,f,p;if(!a)return e;if(a=a.map(function(h){return(h=As(h,t,1))&&r+(t?h[0]+","+h[1]+"%,"+h[2]+"%,"+h[3]:h.join(","))+")"}),n&&(f=ws(e),u=n.c,u.join(i)!==f.c.join(i)))for(c=e.replace(f0,"1").split(O9),p=c.length-1;s<p;s++)i+=c[s]+(~u.indexOf(s)?a.shift()||r+"0,0,0,0)":(f.length?f:a.length?a:n).shift());if(!c)for(c=e.split(f0),p=c.length-1;s<p;s++)i+=c[s]+a[s];return i+c[p]},f0=function(){var o="(?:\\b(?:(?:rgb|rgba|hsl|hsla)\\(.+?\\))|\\B#(?:[0-9a-f]{3,4}){1,2}\\b",e;for(e in g5)o+="|"+e+"\\b";return new RegExp(o+")","gi")}(),lD=/hsl[a]?\(/,Cs=function(e){var t=e.join(" "),n;if(f0.lastIndex=0,f0.test(t))return n=lD.test(t),e[1]=jo(e[1],n),e[0]=jo(e[0],n,ws(e[1])),!0},R5,U3=function(){var o=Date.now,e=500,t=33,n=o(),i=n,a=1e3/240,r=a,s=[],u,c,f,p,h,m,y=function _(v){var b=o()-i,x=v===!0,A,S,w,M;if(b>e&&(n+=b-t),i+=b,w=i-n,A=w-r,(A>0||x)&&(M=++p.frame,h=w-p.time*1e3,p.time=w=w/1e3,r+=A+(A>=a?4:a-A),S=1),x||(u=c(_)),S)for(m=0;m<s.length;m++)s[m](w,h,M,v)};return p={time:0,frame:0,tick:function(){y(!0)},deltaRatio:function(v){return h/(1e3/(v||60))},wake:function(){is&&(!ke&&bn()&&(J3=ke=window,xn=J3.document||{},G3.gsap=D3,(J3.gsapVersions||(J3.gsapVersions=[])).push(D3.version),os(Y8||J3.GreenSockGlobals||!J3.gsap&&J3||{}),f=J3.requestAnimationFrame,Ss.forEach(Ms)),u&&p.sleep(),c=f||function(v){return setTimeout(v,r-p.time*1e3+1|0)},R5=1,y(2))},sleep:function(){(f?J3.cancelAnimationFrame:clearTimeout)(u),R5=0,c=D5},lagSmoothing:function(v,b){e=v||1/0,t=Math.min(b||33,e)},fps:function(v){a=1e3/(v||240),r=p.time*1e3+a},add:function(v,b,x){var A=b?function(S,w,M,D){v(S,w,M,D),p.remove(A)}:v;return p.remove(v),s[x?"unshift":"push"](A),J9(),A},remove:function(v,b){~(b=s.indexOf(v))&&s.splice(b,1)&&m>=b&&m--},_listeners:s},p}(),J9=function(){return!R5&&U3.wake()},h2={},uD=/^[\d.\-M][\d.\-,\s]/,cD=/["']/g,dD=function(e){for(var t={},n=e.substr(1,e.length-3).split(":"),i=n[0],a=1,r=n.length,s,u,c;a<r;a++)u=n[a],s=a!==r-1?u.lastIndexOf(","):u.length,c=u.substr(0,s),t[i]=isNaN(c)?c.replace(cD,"").trim():+c,i=u.substr(s+1).trim();return t},fD=function(e){var t=e.indexOf("(")+1,n=e.indexOf(")"),i=e.indexOf("(",t);return e.substring(t,~i&&i<n?e.indexOf(")",n+1):n)},pD=function(e){var t=(e+"").split("("),n=h2[t[0]];return n&&t.length>1&&n.config?n.config.apply(null,~e.indexOf("{")?[dD(t[1])]:fD(e).split(",").map(us)):h2._CE&&uD.test(e)?h2._CE("",e):n},Ts=function(e){return function(t){return 1-e(1-t)}},Es=function o(e,t){for(var n=e._first,i;n;)n instanceof A3?o(n,t):n.vars.yoyoEase&&(!n._yoyo||!n._repeat)&&n._yoyo!==t&&(n.timeline?o(n.timeline,t):(i=n._ease,n._ease=n._yEase,n._yEase=i,n._yoyo=t)),n=n._next},G0=function(e,t){return e&&(G2(e)?e:h2[e]||pD(e))||t},X0=function(e,t,n,i){n===void 0&&(n=function(u){return 1-t(1-u)}),i===void 0&&(i=function(u){return u<.5?t(u*2)/2:1-t((1-u)*2)/2});var a={easeIn:t,easeOut:n,easeInOut:i},r;return T3(e,function(s){h2[s]=G3[s]=a,h2[r=s.toLowerCase()]=n;for(var u in a)h2[r+(u==="easeIn"?".in":u==="easeOut"?".out":".inOut")]=h2[s+"."+u]=a[u]}),a},Ds=function(e){return function(t){return t<.5?(1-e(1-t*2))/2:.5+e((t-.5)*2)/2}},g7=function o(e,t,n){var i=t>=1?t:1,a=(n||(e?.3:.45))/(t<1?t:1),r=a/Le*(Math.asin(1/i)||0),s=function(f){return f===1?1:i*Math.pow(2,-10*f)*GE((f-r)*a)+1},u=e==="out"?s:e==="in"?function(c){return 1-s(1-c)}:Ds(s);return a=Le/a,u.config=function(c,f){return o(e,c,f)},u},v7=function o(e,t){t===void 0&&(t=1.70158);var n=function(r){return r?--r*r*((t+1)*r+t)+1:0},i=e==="out"?n:e==="in"?function(a){return 1-n(1-a)}:Ds(n);return i.config=function(a){return o(e,a)},i};T3("Linear,Quad,Cubic,Quart,Quint,Strong",function(o,e){var t=e<5?e+1:e;X0(o+",Power"+(t-1),e?function(n){return Math.pow(n,t)}:function(n){return n},function(n){return 1-Math.pow(1-n,t)},function(n){return n<.5?Math.pow(n*2,t)/2:1-Math.pow((1-n)*2,t)/2})});h2.Linear.easeNone=h2.none=h2.Linear.easeIn;X0("Elastic",g7("in"),g7("out"),g7());(function(o,e){var t=1/e,n=2*t,i=2.5*t,a=function(s){return s<t?o*s*s:s<n?o*Math.pow(s-1.5/e,2)+.75:s<i?o*(s-=2.25/e)*s+.9375:o*Math.pow(s-2.625/e,2)+.984375};X0("Bounce",function(r){return 1-a(1-r)},a)})(7.5625,2.75);X0("Expo",function(o){return o?Math.pow(2,10*(o-1)):0});X0("Circ",function(o){return-(Qr(1-o*o)-1)});X0("Sine",function(o){return o===1?1:-BE(o*UE)+1});X0("Back",v7("in"),v7("out"),v7());h2.SteppedEase=h2.steps=G3.SteppedEase={config:function(e,t){e===void 0&&(e=1);var n=1/e,i=e+(t?0:1),a=t?1:0,r=1-C2;return function(s){return((i*H5(0,r,s)|0)+a)*n}}};H9.ease=h2["quad.out"];T3("onComplete,onUpdate,onStart,onRepeat,onReverseComplete,onInterrupt",function(o){return An+=o+","+o+"Params,"});var Is=function(e,t){this.id=FE++,e._gsap=this,this.target=e,this.harness=t,this.get=t?t.get:ss,this.set=t?t.getSetter:Dn},O5=function(){function o(t){this.vars=t,this._delay=+t.delay||0,(this._repeat=t.repeat===1/0?-2:t.repeat||0)&&(this._rDelay=t.repeatDelay||0,this._yoyo=!!t.yoyo||!!t.yoyoEase),this._ts=1,Y9(this,+t.duration,1,1),this.data=t.data,B2&&(this._ctx=B2,B2.data.push(this)),R5||U3.wake()}var e=o.prototype;return e.delay=function(n){return n||n===0?(this.parent&&this.parent.smoothChildTiming&&this.startTime(this._start+n-this._delay),this._delay=n,this):this._delay},e.duration=function(n){return arguments.length?this.totalDuration(this._repeat>0?n+(n+this._rDelay)*this._repeat:n):this.totalDuration()&&this._dur},e.totalDuration=function(n){return arguments.length?(this._dirty=0,Y9(this,this._repeat<0?n:(n-this._repeat*this._rDelay)/(this._repeat+1))):this._tDur},e.totalTime=function(n,i){if(J9(),!arguments.length)return this._tTime;var a=this._dp;if(a&&a.smoothChildTiming&&this._ts){for(p6(this,n),!a._dp||a.parent||fs(a,this);a&&a.parent;)a.parent._time!==a._start+(a._ts>=0?a._tTime/a._ts:(a.totalDuration()-a._tTime)/-a._ts)&&a.totalTime(a._tTime,!0),a=a.parent;!this.parent&&this._dp.autoRemoveChildren&&(this._ts>0&&n<this._tDur||this._ts<0&&n>0||!this._tDur&&!n)&&v4(this._dp,this,this._start-this._delay)}return(this._tTime!==n||!this._dur&&!i||this._initted&&Math.abs(this._zTime)===C2||!n&&!this._initted&&(this.add||this._ptLookup))&&(this._ts||(this._pTime=n),ls(this,n,i)),this},e.time=function(n,i){return arguments.length?this.totalTime(Math.min(this.totalDuration(),n+Bo(this))%(this._dur+this._rDelay)||(n?this._dur:0),i):this._time},e.totalProgress=function(n,i){return arguments.length?this.totalTime(this.totalDuration()*n,i):this.totalDuration()?Math.min(1,this._tTime/this._tDur):this.ratio},e.progress=function(n,i){return arguments.length?this.totalTime(this.duration()*(this._yoyo&&!(this.iteration()&1)?1-n:n)+Bo(this),i):this.duration()?Math.min(1,this._time/this._dur):this.ratio},e.iteration=function(n,i){var a=this.duration()+this._rDelay;return arguments.length?this.totalTime(this._time+(n-1)*a,i):this._repeat?$9(this._tTime,a)+1:1},e.timeScale=function(n){if(!arguments.length)return this._rts===-C2?0:this._rts;if(this._rts===n)return this;var i=this.parent&&this._ts?X8(this.parent._time,this):this._tTime;return this._rts=+n||0,this._ts=this._ps||n===-C2?0:this._rts,this.totalTime(H5(-Math.abs(this._delay),this._tDur,i),!0),f6(this),JE(this)},e.paused=function(n){return arguments.length?(this._ps!==n&&(this._ps=n,n?(this._pTime=this._tTime||Math.max(-this._delay,this.rawTime()),this._ts=this._act=0):(J9(),this._ts=this._rts,this.totalTime(this.parent&&!this.parent.smoothChildTiming?this.rawTime():this._tTime||this._pTime,this.progress()===1&&Math.abs(this._zTime)!==C2&&(this._tTime-=C2)))),this):this._ps},e.startTime=function(n){if(arguments.length){this._start=n;var i=this.parent||this._dp;return i&&(i._sort||!this.parent)&&v4(i,this,n-this._delay),this}return this._start},e.endTime=function(n){return this._start+(C3(n)?this.totalDuration():this.duration())/Math.abs(this._ts||1)},e.rawTime=function(n){var i=this.parent||this._dp;return i?n&&(!this._ts||this._repeat&&this._time&&this.totalProgress()<1)?this._tTime%(this._dur+this._rDelay):this._ts?X8(i.rawTime(n),this):this._tTime:this._tTime},e.revert=function(n){n===void 0&&(n=WE);var i=_3;return _3=n,(this._initted||this._startAt)&&(this.timeline&&this.timeline.revert(n),this.totalTime(-.01,n.suppressEvents)),this.data!=="nested"&&n.kill!==!1&&this.kill(),_3=i,this},e.globalTime=function(n){for(var i=this,a=arguments.length?n:i.rawTime();i;)a=i._start+a/(i._ts||1),i=i._dp;return!this.parent&&this._sat?this._sat.vars.immediateRender?-1/0:this._sat.globalTime(n):a},e.repeat=function(n){return arguments.length?(this._repeat=n===1/0?-2:n,Go(this)):this._repeat===-2?1/0:this._repeat},e.repeatDelay=function(n){if(arguments.length){var i=this._time;return this._rDelay=n,Go(this),i?this.time(i):this}return this._rDelay},e.yoyo=function(n){return arguments.length?(this._yoyo=n,this):this._yoyo},e.seek=function(n,i){return this.totalTime(Y3(this,n),C3(i))},e.restart=function(n,i){return this.play().totalTime(n?-this._delay:0,C3(i))},e.play=function(n,i){return n!=null&&this.seek(n,i),this.reversed(!1).paused(!1)},e.reverse=function(n,i){return n!=null&&this.seek(n||this.totalDuration(),i),this.reversed(!0).paused(!1)},e.pause=function(n,i){return n!=null&&this.seek(n,i),this.paused(!0)},e.resume=function(){return this.paused(!1)},e.reversed=function(n){return arguments.length?(!!n!==this.reversed()&&this.timeScale(-this._rts||(n?-C2:0)),this):this._rts<0},e.invalidate=function(){return this._initted=this._act=0,this._zTime=-C2,this},e.isActive=function(){var n=this.parent||this._dp,i=this._start,a;return!!(!n||this._ts&&this._initted&&n.isActive()&&(a=n.rawTime(!0))>=i&&a<this.endTime(!0)-C2)},e.eventCallback=function(n,i,a){var r=this.vars;return arguments.length>1?(i?(r[n]=i,a&&(r[n+"Params"]=a),n==="onUpdate"&&(this._onUpdate=i)):delete r[n],this):r[n]},e.then=function(n){var i=this;return new Promise(function(a){var r=G2(n)?n:cs,s=function(){var c=i.then;i.then=null,G2(r)&&(r=r(i))&&(r.then||r===i)&&(i.then=c),a(r),i.then=c};i._initted&&i.totalProgress()===1&&i._ts>=0||!i._tTime&&i._ts<0?s():i._prom=s})},e.kill=function(){m5(this)},o}();o4(O5.prototype,{_time:0,_start:0,_end:0,_tTime:0,_tDur:0,_dirty:0,_repeat:0,_yoyo:!1,parent:null,_initted:!1,_rDelay:0,_ts:1,_dp:0,ratio:0,_zTime:-C2,_prom:0,_ps:!1,_rts:1});var A3=function(o){Xr(e,o);function e(n,i){var a;return n===void 0&&(n={}),a=o.call(this,n)||this,a.labels={},a.smoothChildTiming=!!n.smoothChildTiming,a.autoRemoveChildren=!!n.autoRemoveChildren,a._sort=C3(n.sortChildren),L2&&v4(n.parent||L2,P4(a),i),n.reversed&&a.reverse(),n.paused&&a.paused(!0),n.scrollTrigger&&ps(P4(a),n.scrollTrigger),a}var t=e.prototype;return t.to=function(i,a,r){return S5(0,arguments,this),this},t.from=function(i,a,r){return S5(1,arguments,this),this},t.fromTo=function(i,a,r,s){return S5(2,arguments,this),this},t.set=function(i,a,r){return a.duration=0,a.parent=this,x5(a).repeatDelay||(a.repeat=0),a.immediateRender=!!a.immediateRender,new q2(i,a,Y3(this,r),1),this},t.call=function(i,a,r){return v4(this,q2.delayedCall(0,i,a),r)},t.staggerTo=function(i,a,r,s,u,c,f){return r.duration=a,r.stagger=r.stagger||s,r.onComplete=c,r.onCompleteParams=f,r.parent=this,new q2(i,r,Y3(this,u)),this},t.staggerFrom=function(i,a,r,s,u,c,f){return r.runBackwards=1,x5(r).immediateRender=C3(r.immediateRender),this.staggerTo(i,a,r,s,u,c,f)},t.staggerFromTo=function(i,a,r,s,u,c,f,p){return s.startAt=r,x5(s).immediateRender=C3(s.immediateRender),this.staggerTo(i,a,s,u,c,f,p)},t.render=function(i,a,r){var s=this._time,u=this._dirty?this.totalDuration():this._tDur,c=this._dur,f=i<=0?0:l3(i),p=this._zTime<0!=i<0&&(this._initted||!c),h,m,y,_,v,b,x,A,S,w,M,D;if(this!==L2&&f>u&&i>=0&&(f=u),f!==this._tTime||r||p){if(s!==this._time&&c&&(f+=this._time-s,i+=this._time-s),h=f,S=this._start,A=this._ts,b=!A,p&&(c||(s=this._zTime),(i||!a)&&(this._zTime=i)),this._repeat){if(M=this._yoyo,v=c+this._rDelay,this._repeat<-1&&i<0)return this.totalTime(v*100+i,a,r);if(h=l3(f%v),f===u?(_=this._repeat,h=c):(_=~~(f/v),_&&_===f/v&&(h=c,_--),h>c&&(h=c)),w=$9(this._tTime,v),!s&&this._tTime&&w!==_&&this._tTime-w*v-this._dur<=0&&(w=_),M&&_&1&&(h=c-h,D=1),_!==w&&!this._lock){var T=M&&w&1,E=T===(M&&_&1);if(_<w&&(T=!T),s=T?0:f%c?c:f,this._lock=1,this.render(s||(D?0:l3(_*v)),a,!c)._lock=0,this._tTime=f,!a&&this.parent&&e4(this,"onRepeat"),this.vars.repeatRefresh&&!D&&(this.invalidate()._lock=1),s&&s!==this._time||b!==!this._ts||this.vars.onRepeat&&!this.parent&&!this._act)return this;if(c=this._dur,u=this._tDur,E&&(this._lock=2,s=T?c:-1e-4,this.render(s,!0),this.vars.repeatRefresh&&!D&&this.invalidate()),this._lock=0,!this._ts&&!b)return this;Es(this,D)}}if(this._hasPause&&!this._forcing&&this._lock<2&&(x=QE(this,l3(s),l3(h)),x&&(f-=h-(h=x._start))),this._tTime=f,this._time=h,this._act=!A,this._initted||(this._onUpdate=this.vars.onUpdate,this._initted=1,this._zTime=i,s=0),!s&&h&&!a&&!_&&(e4(this,"onStart"),this._tTime!==f))return this;if(h>=s&&i>=0)for(m=this._first;m;){if(y=m._next,(m._act||h>=m._start)&&m._ts&&x!==m){if(m.parent!==this)return this.render(i,a,r);if(m.render(m._ts>0?(h-m._start)*m._ts:(m._dirty?m.totalDuration():m._tDur)+(h-m._start)*m._ts,a,r),h!==this._time||!this._ts&&!b){x=0,y&&(f+=this._zTime=-C2);break}}m=y}else{m=this._last;for(var U=i<0?i:h;m;){if(y=m._prev,(m._act||U<=m._end)&&m._ts&&x!==m){if(m.parent!==this)return this.render(i,a,r);if(m.render(m._ts>0?(U-m._start)*m._ts:(m._dirty?m.totalDuration():m._tDur)+(U-m._start)*m._ts,a,r||_3&&(m._initted||m._startAt)),h!==this._time||!this._ts&&!b){x=0,y&&(f+=this._zTime=U?-C2:C2);break}}m=y}}if(x&&!a&&(this.pause(),x.render(h>=s?0:-C2)._zTime=h>=s?1:-1,this._ts))return this._start=S,f6(this),this.render(i,a,r);this._onUpdate&&!a&&e4(this,"onUpdate",!0),(f===u&&this._tTime>=this.totalDuration()||!f&&s)&&(S===this._start||Math.abs(A)!==Math.abs(this._ts))&&(this._lock||((i||!c)&&(f===u&&this._ts>0||!f&&this._ts<0)&&h0(this,1),!a&&!(i<0&&!s)&&(f||s||!u)&&(e4(this,f===u&&i>=0?"onComplete":"onReverseComplete",!0),this._prom&&!(f<u&&this.timeScale()>0)&&this._prom())))}return this},t.add=function(i,a){var r=this;if(V4(a)||(a=Y3(this,a,i)),!(i instanceof O5)){if(y3(i))return i.forEach(function(s){return r.add(s,a)}),this;if(s3(i))return this.addLabel(i,a);if(G2(i))i=q2.delayedCall(0,i);else return this}return this!==i?v4(this,i,a):this},t.getChildren=function(i,a,r,s){i===void 0&&(i=!0),a===void 0&&(a=!0),r===void 0&&(r=!0),s===void 0&&(s=-Q3);for(var u=[],c=this._first;c;)c._start>=s&&(c instanceof q2?a&&u.push(c):(r&&u.push(c),i&&u.push.apply(u,c.getChildren(!0,a,r)))),c=c._next;return u},t.getById=function(i){for(var a=this.getChildren(1,1,1),r=a.length;r--;)if(a[r].vars.id===i)return a[r]},t.remove=function(i){return s3(i)?this.removeLabel(i):G2(i)?this.killTweensOf(i):(d6(this,i),i===this._recent&&(this._recent=this._last),B0(this))},t.totalTime=function(i,a){return arguments.length?(this._forcing=1,!this._dp&&this._ts&&(this._start=l3(U3.time-(this._ts>0?i/this._ts:(this.totalDuration()-i)/-this._ts))),o.prototype.totalTime.call(this,i,a),this._forcing=0,this):this._tTime},t.addLabel=function(i,a){return this.labels[i]=Y3(this,a),this},t.removeLabel=function(i){return delete this.labels[i],this},t.addPause=function(i,a,r){var s=q2.delayedCall(0,a||D5,r);return s.data="isPause",this._hasPause=1,v4(this,s,Y3(this,i))},t.removePause=function(i){var a=this._first;for(i=Y3(this,i);a;)a._start===i&&a.data==="isPause"&&h0(a),a=a._next},t.killTweensOf=function(i,a,r){for(var s=this.getTweensOf(i,r),u=s.length;u--;)r0!==s[u]&&s[u].kill(i,a);return this},t.getTweensOf=function(i,a){for(var r=[],s=K3(i),u=this._first,c=V4(a),f;u;)u instanceof q2?HE(u._targets,s)&&(c?(!r0||u._initted&&u._ts)&&u.globalTime(0)<=a&&u.globalTime(u.totalDuration())>a:!a||u.isActive())&&r.push(u):(f=u.getTweensOf(s,a)).length&&r.push.apply(r,f),u=u._next;return r},t.tweenTo=function(i,a){a=a||{};var r=this,s=Y3(r,i),u=a,c=u.startAt,f=u.onStart,p=u.onStartParams,h=u.immediateRender,m,y=q2.to(r,o4({ease:a.ease||"none",lazy:!1,immediateRender:!1,time:s,overwrite:"auto",duration:a.duration||Math.abs((s-(c&&"time"in c?c.time:r._time))/r.timeScale())||C2,onStart:function(){if(r.pause(),!m){var v=a.duration||Math.abs((s-(c&&"time"in c?c.time:r._time))/r.timeScale());y._dur!==v&&Y9(y,v,0,1).render(y._time,!0,!0),m=1}f&&f.apply(y,p||[])}},a));return h?y.render(0):y},t.tweenFromTo=function(i,a,r){return this.tweenTo(a,o4({startAt:{time:Y3(this,i)}},r))},t.recent=function(){return this._recent},t.nextLabel=function(i){return i===void 0&&(i=this._time),Vo(this,Y3(this,i))},t.previousLabel=function(i){return i===void 0&&(i=this._time),Vo(this,Y3(this,i),1)},t.currentLabel=function(i){return arguments.length?this.seek(i,!0):this.previousLabel(this._time+C2)},t.shiftChildren=function(i,a,r){r===void 0&&(r=0);for(var s=this._first,u=this.labels,c;s;)s._start>=r&&(s._start+=i,s._end+=i),s=s._next;if(a)for(c in u)u[c]>=r&&(u[c]+=i);return B0(this)},t.invalidate=function(i){var a=this._first;for(this._lock=0;a;)a.invalidate(i),a=a._next;return o.prototype.invalidate.call(this,i)},t.clear=function(i){i===void 0&&(i=!0);for(var a=this._first,r;a;)r=a._next,this.remove(a),a=r;return this._dp&&(this._time=this._tTime=this._pTime=0),i&&(this.labels={}),B0(this)},t.totalDuration=function(i){var a=0,r=this,s=r._last,u=Q3,c,f,p;if(arguments.length)return r.timeScale((r._repeat<0?r.duration():r.totalDuration())/(r.reversed()?-i:i));if(r._dirty){for(p=r.parent;s;)c=s._prev,s._dirty&&s.totalDuration(),f=s._start,f>u&&r._sort&&s._ts&&!r._lock?(r._lock=1,v4(r,s,f-s._delay,1)._lock=0):u=f,f<0&&s._ts&&(a-=f,(!p&&!r._dp||p&&p.smoothChildTiming)&&(r._start+=f/r._ts,r._time-=f,r._tTime-=f),r.shiftChildren(-f,!1,-1/0),u=0),s._end>a&&s._ts&&(a=s._end),s=c;Y9(r,r===L2&&r._time>a?r._time:a,1,1),r._dirty=0}return r._tDur},e.updateRoot=function(i){if(L2._ts&&(ls(L2,X8(i,L2)),rs=U3.frame),U3.frame>=Uo){Uo+=B3.autoSleep||120;var a=L2._first;if((!a||!a._ts)&&B3.autoSleep&&U3._listeners.length<2){for(;a&&!a._ts;)a=a._next;a||U3.sleep()}}},e}(O5);o4(A3.prototype,{_lock:0,_hasPause:0,_forcing:0});var hD=function(e,t,n,i,a,r,s){var u=new E3(this._pt,e,t,0,1,ks,null,a),c=0,f=0,p,h,m,y,_,v,b,x;for(u.b=n,u.e=i,n+="",i+="",(b=~i.indexOf("random("))&&(i=I5(i)),r&&(x=[n,i],r(x,e,t),n=x[0],i=x[1]),h=n.match(p7)||[];p=p7.exec(i);)y=p[0],_=i.substring(c,p.index),m?m=(m+1)%5:_.substr(-5)==="rgba("&&(m=1),y!==h[f++]&&(v=parseFloat(h[f-1])||0,u._pt={_next:u._pt,p:_||f===1?_:",",s:v,c:y.charAt(1)==="="?k9(v,y)-v:parseFloat(y)-v,m:m&&m<4?Math.round:0},c=p7.lastIndex);return u.c=c<i.length?i.substring(c,i.length):"",u.fp=s,(ts.test(i)||b)&&(u.e=0),this._pt=u,u},Cn=function(e,t,n,i,a,r,s,u,c,f){G2(i)&&(i=i(a||0,e,r));var p=e[t],h=n!=="get"?n:G2(p)?c?e[t.indexOf("set")||!G2(e["get"+t.substr(3)])?t:"get"+t.substr(3)](c):e[t]():p,m=G2(p)?c?yD:Ls:En,y;if(s3(i)&&(~i.indexOf("random(")&&(i=I5(i)),i.charAt(1)==="="&&(y=k9(h,i)+(g3(h)||0),(y||y===0)&&(i=y))),!f||h!==i||je)return!isNaN(h*i)&&i!==""?(y=new E3(this._pt,e,t,+h||0,i-(h||0),typeof p=="boolean"?xD:zs,0,m),c&&(y.fp=c),s&&y.modifier(s,this,e),this._pt=y):(!p&&!(t in e)&&Sn(t,i),hD.call(this,e,t,h,i,m,u||B3.stringFilter,c))},mD=function(e,t,n,i,a){if(G2(e)&&(e=M5(e,a,t,n,i)),!M4(e)||e.style&&e.nodeType||y3(e)||Kr(e))return s3(e)?M5(e,a,t,n,i):e;var r={},s;for(s in e)r[s]=M5(e[s],a,t,n,i);return r},Rs=function(e,t,n,i,a,r){var s,u,c,f;if(N3[e]&&(s=new N3[e]).init(a,s.rawVars?t[e]:mD(t[e],i,a,r,n),n,i,r)!==!1&&(n._pt=u=new E3(n._pt,a,e,0,1,s.render,s,0,s.priority),n!==P9))for(c=n._ptLookup[n._targets.indexOf(a)],f=s._props.length;f--;)c[s._props[f]]=u;return s},r0,je,Tn=function o(e,t,n){var i=e.vars,a=i.ease,r=i.startAt,s=i.immediateRender,u=i.lazy,c=i.onUpdate,f=i.onUpdateParams,p=i.callbackScope,h=i.runBackwards,m=i.yoyoEase,y=i.keyframes,_=i.autoRevert,v=e._dur,b=e._startAt,x=e._targets,A=e.parent,S=A&&A.data==="nested"?A.vars.targets:x,w=e._overwrite==="auto"&&!_n,M=e.timeline,D,T,E,U,V,q,$,z,F,G,J,O,N;if(M&&(!y||!a)&&(a="none"),e._ease=G0(a,H9.ease),e._yEase=m?Ts(G0(m===!0?a:m,H9.ease)):0,m&&e._yoyo&&!e._repeat&&(m=e._yEase,e._yEase=e._ease,e._ease=m),e._from=!M&&!!i.runBackwards,!M||y&&!i.stagger){if(z=x[0]?F0(x[0]).harness:0,O=z&&i[z.prop],D=q8(i,Mn),b&&(b._zTime<0&&b.progress(1),t<0&&h&&s&&!_?b.render(-1,!0):b.revert(h&&v?z8:jE),b._lazy=0),r){if(h0(e._startAt=q2.set(x,o4({data:"isStart",overwrite:!1,parent:A,immediateRender:!0,lazy:!b&&C3(u),startAt:null,delay:0,onUpdate:c,onUpdateParams:f,callbackScope:p,stagger:0},r))),e._startAt._dp=0,e._startAt._sat=e,t<0&&(_3||!s&&!_)&&e._startAt.revert(z8),s&&v&&t<=0&&n<=0){t&&(e._zTime=t);return}}else if(h&&v&&!b){if(t&&(s=!1),E=o4({overwrite:!1,data:"isFromStart",lazy:s&&!b&&C3(u),immediateRender:s,stagger:0,parent:A},D),O&&(E[z.prop]=O),h0(e._startAt=q2.set(x,E)),e._startAt._dp=0,e._startAt._sat=e,t<0&&(_3?e._startAt.revert(z8):e._startAt.render(-1,!0)),e._zTime=t,!s)o(e._startAt,C2,C2);else if(!t)return}for(e._pt=e._ptCache=0,u=v&&C3(u)||u&&!v,T=0;T<x.length;T++){if(V=x[T],$=V._gsap||wn(x)[T]._gsap,e._ptLookup[T]=G={},Ne[$.id]&&d0.length&&Z8(),J=S===x?T:S.indexOf(V),z&&(F=new z).init(V,O||D,e,J,S)!==!1&&(e._pt=U=new E3(e._pt,V,F.name,0,1,F.render,F,0,F.priority),F._props.forEach(function(B){G[B]=U}),F.priority&&(q=1)),!z||O)for(E in D)N3[E]&&(F=Rs(E,D,e,J,V,S))?F.priority&&(q=1):G[E]=U=Cn.call(e,V,E,"get",D[E],J,S,0,i.stringFilter);e._op&&e._op[T]&&e.kill(V,e._op[T]),w&&e._pt&&(r0=e,L2.killTweensOf(V,G,e.globalTime(t)),N=!e.parent,r0=0),e._pt&&u&&(Ne[$.id]=1)}q&&Ns(e),e._onInit&&e._onInit(e)}e._onUpdate=c,e._initted=(!e._op||e._pt)&&!N,y&&t<=0&&M.render(Q3,!0,!0)},gD=function(e,t,n,i,a,r,s){var u=(e._pt&&e._ptCache||(e._ptCache={}))[t],c,f,p,h;if(!u)for(u=e._ptCache[t]=[],p=e._ptLookup,h=e._targets.length;h--;){if(c=p[h][t],c&&c.d&&c.d._pt)for(c=c.d._pt;c&&c.p!==t&&c.fp!==t;)c=c._next;if(!c)return je=1,e.vars[t]="+=0",Tn(e,s),je=0,1;u.push(c)}for(h=u.length;h--;)f=u[h],c=f._pt||f,c.s=(i||i===0)&&!a?i:c.s+(i||0)+r*c.c,c.c=n-c.s,f.e&&(f.e=j2(n)+g3(f.e)),f.b&&(f.b=c.s+g3(f.b))},vD=function(e,t){var n=e[0]?F0(e[0]).harness:0,i=n&&n.aliases,a,r,s,u;if(!i)return t;a=$0({},t);for(r in i)if(r in a)for(u=i[r].split(","),s=u.length;s--;)a[u[s]]=a[r];return a},_D=function(e,t,n,i){var a=t.ease||i||"power1.inOut",r,s;if(y3(t))s=n[e]||(n[e]=[]),t.forEach(function(u,c){return s.push({t:c/(t.length-1)*100,v:u,e:a})});else for(r in t)s=n[r]||(n[r]=[]),r==="ease"||s.push({t:parseFloat(e),v:t[r],e:a})},M5=function(e,t,n,i,a){return G2(e)?e.call(t,n,i,a):s3(e)&&~e.indexOf("random(")?I5(e):e},Os=An+"repeat,repeatDelay,yoyo,repeatRefresh,yoyoEase,autoRevert",Ps={};T3(Os+",id,stagger,delay,duration,paused,scrollTrigger",function(o){return Ps[o]=1});var q2=function(o){Xr(e,o);function e(n,i,a,r){var s;typeof i=="number"&&(a.duration=i,i=a,a=null),s=o.call(this,r?i:x5(i))||this;var u=s.vars,c=u.duration,f=u.delay,p=u.immediateRender,h=u.stagger,m=u.overwrite,y=u.keyframes,_=u.defaults,v=u.scrollTrigger,b=u.yoyoEase,x=i.parent||L2,A=(y3(n)||Kr(n)?V4(n[0]):"length"in i)?[n]:K3(n),S,w,M,D,T,E,U,V;if(s._targets=A.length?wn(A):J8("GSAP target "+n+" not found. https://greensock.com",!B3.nullTargetWarn)||[],s._ptLookup=[],s._overwrite=m,y||h||D8(c)||D8(f)){if(i=s.vars,S=s.timeline=new A3({data:"nested",defaults:_||{},targets:x&&x.data==="nested"?x.vars.targets:A}),S.kill(),S.parent=S._dp=P4(s),S._start=0,h||D8(c)||D8(f)){if(D=A.length,U=h&&vs(h),M4(h))for(T in h)~Os.indexOf(T)&&(V||(V={}),V[T]=h[T]);for(w=0;w<D;w++)M=q8(i,Ps),M.stagger=0,b&&(M.yoyoEase=b),V&&$0(M,V),E=A[w],M.duration=+M5(c,P4(s),w,E,A),M.delay=(+M5(f,P4(s),w,E,A)||0)-s._delay,!h&&D===1&&M.delay&&(s._delay=f=M.delay,s._start+=f,M.delay=0),S.to(E,M,U?U(w,E,A):0),S._ease=h2.none;S.duration()?c=f=0:s.timeline=0}else if(y){x5(o4(S.vars.defaults,{ease:"none"})),S._ease=G0(y.ease||i.ease||"none");var q=0,$,z,F;if(y3(y))y.forEach(function(G){return S.to(A,G,">")}),S.duration();else{M={};for(T in y)T==="ease"||T==="easeEach"||_D(T,y[T],M,y.easeEach);for(T in M)for($=M[T].sort(function(G,J){return G.t-J.t}),q=0,w=0;w<$.length;w++)z=$[w],F={ease:z.e,duration:(z.t-(w?$[w-1].t:0))/100*c},F[T]=z.v,S.to(A,F,q),q+=F.duration;S.duration()<c&&S.to({},{duration:c-S.duration()})}}c||s.duration(c=S.duration())}else s.timeline=0;return m===!0&&!_n&&(r0=P4(s),L2.killTweensOf(A),r0=0),v4(x,P4(s),a),i.reversed&&s.reverse(),i.paused&&s.paused(!0),(p||!c&&!y&&s._start===l3(x._time)&&C3(p)&&ZE(P4(s))&&x.data!=="nested")&&(s._tTime=-C2,s.render(Math.max(0,-f)||0)),v&&ps(P4(s),v),s}var t=e.prototype;return t.render=function(i,a,r){var s=this._time,u=this._tDur,c=this._dur,f=i<0,p=i>u-C2&&!f?u:i<C2?0:i,h,m,y,_,v,b,x,A,S;if(!c)XE(this,i,a,r);else if(p!==this._tTime||!i||r||!this._initted&&this._tTime||this._startAt&&this._zTime<0!==f){if(h=p,A=this.timeline,this._repeat){if(_=c+this._rDelay,this._repeat<-1&&f)return this.totalTime(_*100+i,a,r);if(h=l3(p%_),p===u?(y=this._repeat,h=c):(y=~~(p/_),y&&y===p/_&&(h=c,y--),h>c&&(h=c)),b=this._yoyo&&y&1,b&&(S=this._yEase,h=c-h),v=$9(this._tTime,_),h===s&&!r&&this._initted)return this._tTime=p,this;y!==v&&(A&&this._yEase&&Es(A,b),this.vars.repeatRefresh&&!b&&!this._lock&&(this._lock=r=1,this.render(l3(_*y),!0).invalidate()._lock=0))}if(!this._initted){if(hs(this,f?i:h,r,a,p))return this._tTime=0,this;if(s!==this._time)return this;if(c!==this._dur)return this.render(i,a,r)}if(this._tTime=p,this._time=h,!this._act&&this._ts&&(this._act=1,this._lazy=0),this.ratio=x=(S||this._ease)(h/c),this._from&&(this.ratio=x=1-x),h&&!s&&!a&&!y&&(e4(this,"onStart"),this._tTime!==p))return this;for(m=this._pt;m;)m.r(x,m.d),m=m._next;A&&A.render(i<0?i:!h&&b?-C2:A._dur*A._ease(h/this._dur),a,r)||this._startAt&&(this._zTime=i),this._onUpdate&&!a&&(f&&Ue(this,i,a,r),e4(this,"onUpdate")),this._repeat&&y!==v&&this.vars.onRepeat&&!a&&this.parent&&e4(this,"onRepeat"),(p===this._tDur||!p)&&this._tTime===p&&(f&&!this._onUpdate&&Ue(this,i,!0,!0),(i||!c)&&(p===this._tDur&&this._ts>0||!p&&this._ts<0)&&h0(this,1),!a&&!(f&&!s)&&(p||s||b)&&(e4(this,p===u?"onComplete":"onReverseComplete",!0),this._prom&&!(p<u&&this.timeScale()>0)&&this._prom()))}return this},t.targets=function(){return this._targets},t.invalidate=function(i){return(!i||!this.vars.runBackwards)&&(this._startAt=0),this._pt=this._op=this._onUpdate=this._lazy=this.ratio=0,this._ptLookup=[],this.timeline&&this.timeline.invalidate(i),o.prototype.invalidate.call(this,i)},t.resetTo=function(i,a,r,s){R5||U3.wake(),this._ts||this.play();var u=Math.min(this._dur,(this._dp._time-this._start)*this._ts),c;return this._initted||Tn(this,u),c=this._ease(u/this._dur),gD(this,i,a,r,s,c,u)?this.resetTo(i,a,r,s):(p6(this,0),this.parent||ds(this._dp,this,"_first","_last",this._dp._sort?"_start":0),this.render(0))},t.kill=function(i,a){if(a===void 0&&(a="all"),!i&&(!a||a==="all"))return this._lazy=this._pt=0,this.parent?m5(this):this;if(this.timeline){var r=this.timeline.totalDuration();return this.timeline.killTweensOf(i,a,r0&&r0.vars.overwrite!==!0)._first||m5(this),this.parent&&r!==this.timeline.totalDuration()&&Y9(this,this._dur*this.timeline._tDur/r,0,1),this}var s=this._targets,u=i?K3(i):s,c=this._ptLookup,f=this._pt,p,h,m,y,_,v,b;if((!a||a==="all")&&YE(s,u))return a==="all"&&(this._pt=0),m5(this);for(p=this._op=this._op||[],a!=="all"&&(s3(a)&&(_={},T3(a,function(x){return _[x]=1}),a=_),a=vD(s,a)),b=s.length;b--;)if(~u.indexOf(s[b])){h=c[b],a==="all"?(p[b]=a,y=h,m={}):(m=p[b]=p[b]||{},y=a);for(_ in y)v=h&&h[_],v&&((!("kill"in v.d)||v.d.kill(_)===!0)&&d6(this,v,"_pt"),delete h[_]),m!=="all"&&(m[_]=1)}return this._initted&&!this._pt&&f&&m5(this),this},e.to=function(i,a){return new e(i,a,arguments[2])},e.from=function(i,a){return S5(1,arguments)},e.delayedCall=function(i,a,r,s){return new e(a,0,{immediateRender:!1,lazy:!1,overwrite:!1,delay:i,onComplete:a,onReverseComplete:a,onCompleteParams:r,onReverseCompleteParams:r,callbackScope:s})},e.fromTo=function(i,a,r){return S5(2,arguments)},e.set=function(i,a){return a.duration=0,a.repeatDelay||(a.repeat=0),new e(i,a)},e.killTweensOf=function(i,a,r){return L2.killTweensOf(i,a,r)},e}(O5);o4(q2.prototype,{_targets:[],_lazy:0,_startAt:0,_op:0,_onInit:0});T3("staggerTo,staggerFrom,staggerFromTo",function(o){q2[o]=function(){var e=new A3,t=Be.call(arguments,0);return t.splice(o==="staggerFromTo"?5:4,0,0),e[o].apply(e,t)}});var En=function(e,t,n){return e[t]=n},Ls=function(e,t,n){return e[t](n)},yD=function(e,t,n,i){return e[t](i.fp,n)},bD=function(e,t,n){return e.setAttribute(t,n)},Dn=function(e,t){return G2(e[t])?Ls:yn(e[t])&&e.setAttribute?bD:En},zs=function(e,t){return t.set(t.t,t.p,Math.round((t.s+t.c*e)*1e6)/1e6,t)},xD=function(e,t){return t.set(t.t,t.p,!!(t.s+t.c*e),t)},ks=function(e,t){var n=t._pt,i="";if(!e&&t.b)i=t.b;else if(e===1&&t.e)i=t.e;else{for(;n;)i=n.p+(n.m?n.m(n.s+n.c*e):Math.round((n.s+n.c*e)*1e4)/1e4)+i,n=n._next;i+=t.c}t.set(t.t,t.p,i,t)},In=function(e,t){for(var n=t._pt;n;)n.r(e,n.d),n=n._next},SD=function(e,t,n,i){for(var a=this._pt,r;a;)r=a._next,a.p===i&&a.modifier(e,t,n),a=r},MD=function(e){for(var t=this._pt,n,i;t;)i=t._next,t.p===e&&!t.op||t.op===e?d6(this,t,"_pt"):t.dep||(n=1),t=i;return!n},AD=function(e,t,n,i){i.mSet(e,t,i.m.call(i.tween,n,i.mt),i)},Ns=function(e){for(var t=e._pt,n,i,a,r;t;){for(n=t._next,i=a;i&&i.pr>t.pr;)i=i._next;(t._prev=i?i._prev:r)?t._prev._next=t:a=t,(t._next=i)?i._prev=t:r=t,t=n}e._pt=a},E3=function(){function o(t,n,i,a,r,s,u,c,f){this.t=n,this.s=a,this.c=r,this.p=i,this.r=s||zs,this.d=u||this,this.set=c||En,this.pr=f||0,this._next=t,t&&(t._prev=this)}var e=o.prototype;return e.modifier=function(n,i,a){this.mSet=this.mSet||this.set,this.set=AD,this.m=n,this.mt=a,this.tween=i},o}();T3(An+"parent,duration,ease,delay,overwrite,runBackwards,startAt,yoyo,immediateRender,repeat,repeatDelay,data,paused,reversed,lazy,callbackScope,stringFilter,id,yoyoEase,stagger,inherit,repeatRefresh,keyframes,autoRevert,scrollTrigger",function(o){return Mn[o]=1});G3.TweenMax=G3.TweenLite=q2;G3.TimelineLite=G3.TimelineMax=A3;L2=new A3({sortChildren:!1,defaults:H9,autoRemoveChildren:!0,id:"root",smoothChildTiming:!0});B3.stringFilter=Cs;var V0=[],N8={},wD=[],Wo=0,CD=0,_7=function(e){return(N8[e]||wD).map(function(t){return t()})},We=function(){var e=Date.now(),t=[];e-Wo>2&&(_7("matchMediaInit"),V0.forEach(function(n){var i=n.queries,a=n.conditions,r,s,u,c;for(s in i)r=J3.matchMedia(i[s]).matches,r&&(u=1),r!==a[s]&&(a[s]=r,c=1);c&&(n.revert(),u&&t.push(n))}),_7("matchMediaRevert"),t.forEach(function(n){return n.onMatch(n)}),Wo=e,_7("matchMedia"))},Us=function(){function o(t,n){this.selector=n&&Ge(n),this.data=[],this._r=[],this.isReverted=!1,this.id=CD++,t&&this.add(t)}var e=o.prototype;return e.add=function(n,i,a){G2(n)&&(a=i,i=n,n=G2);var r=this,s=function(){var c=B2,f=r.selector,p;return c&&c!==r&&c.data.push(r),a&&(r.selector=Ge(a)),B2=r,p=i.apply(r,arguments),G2(p)&&r._r.push(p),B2=c,r.selector=f,r.isReverted=!1,p};return r.last=s,n===G2?s(r):n?r[n]=s:s},e.ignore=function(n){var i=B2;B2=null,n(this),B2=i},e.getTweens=function(){var n=[];return this.data.forEach(function(i){return i instanceof o?n.push.apply(n,i.getTweens()):i instanceof q2&&!(i.parent&&i.parent.data==="nested")&&n.push(i)}),n},e.clear=function(){this._r.length=this.data.length=0},e.kill=function(n,i){var a=this;if(n){var r=this.getTweens();this.data.forEach(function(u){u.data==="isFlip"&&(u.revert(),u.getChildren(!0,!0,!1).forEach(function(c){return r.splice(r.indexOf(c),1)}))}),r.map(function(u){return{g:u.globalTime(0),t:u}}).sort(function(u,c){return c.g-u.g||-1/0}).forEach(function(u){return u.t.revert(n)}),this.data.forEach(function(u){return!(u instanceof q2)&&u.revert&&u.revert(n)}),this._r.forEach(function(u){return u(n,a)}),this.isReverted=!0}else this.data.forEach(function(u){return u.kill&&u.kill()});if(this.clear(),i)for(var s=V0.length;s--;)V0[s].id===this.id&&V0.splice(s,1)},e.revert=function(n){this.kill(n||{})},o}(),TD=function(){function o(t){this.contexts=[],this.scope=t}var e=o.prototype;return e.add=function(n,i,a){M4(n)||(n={matches:n});var r=new Us(0,a||this.scope),s=r.conditions={},u,c,f;B2&&!r.selector&&(r.selector=B2.selector),this.contexts.push(r),i=r.add("onMatch",i),r.queries=n;for(c in n)c==="all"?f=1:(u=J3.matchMedia(n[c]),u&&(V0.indexOf(r)<0&&V0.push(r),(s[c]=u.matches)&&(f=1),u.addListener?u.addListener(We):u.addEventListener("change",We)));return f&&i(r),this},e.revert=function(n){this.kill(n||{})},e.kill=function(n){this.contexts.forEach(function(i){return i.kill(n,!0)})},o}(),Q8={registerPlugin:function(){for(var e=arguments.length,t=new Array(e),n=0;n<e;n++)t[n]=arguments[n];t.forEach(function(i){return Ms(i)})},timeline:function(e){return new A3(e)},getTweensOf:function(e,t){return L2.getTweensOf(e,t)},getProperty:function(e,t,n,i){s3(e)&&(e=K3(e)[0]);var a=F0(e||{}).get,r=n?cs:us;return n==="native"&&(n=""),e&&(t?r((N3[t]&&N3[t].get||a)(e,t,n,i)):function(s,u,c){return r((N3[s]&&N3[s].get||a)(e,s,u,c))})},quickSetter:function(e,t,n){if(e=K3(e),e.length>1){var i=e.map(function(f){return D3.quickSetter(f,t,n)}),a=i.length;return function(f){for(var p=a;p--;)i[p](f)}}e=e[0]||{};var r=N3[t],s=F0(e),u=s.harness&&(s.harness.aliases||{})[t]||t,c=r?function(f){var p=new r;P9._pt=0,p.init(e,n?f+n:f,P9,0,[e]),p.render(1,p),P9._pt&&In(1,P9)}:s.set(e,u);return r?c:function(f){return c(e,u,n?f+n:f,s,1)}},quickTo:function(e,t,n){var i,a=D3.to(e,$0((i={},i[t]="+=0.1",i.paused=!0,i),n||{})),r=function(u,c,f){return a.resetTo(t,u,c,f)};return r.tween=a,r},isTweening:function(e){return L2.getTweensOf(e,!0).length>0},defaults:function(e){return e&&e.ease&&(e.ease=G0(e.ease,H9.ease)),Fo(H9,e||{})},config:function(e){return Fo(B3,e||{})},registerEffect:function(e){var t=e.name,n=e.effect,i=e.plugins,a=e.defaults,r=e.extendTimeline;(i||"").split(",").forEach(function(s){return s&&!N3[s]&&!G3[s]&&J8(t+" effect requires "+s+" plugin.")}),h7[t]=function(s,u,c){return n(K3(s),o4(u||{},a),c)},r&&(A3.prototype[t]=function(s,u,c){return this.add(h7[t](s,M4(u)?u:(c=u)&&{},this),c)})},registerEase:function(e,t){h2[e]=G0(t)},parseEase:function(e,t){return arguments.length?G0(e,t):h2},getById:function(e){return L2.getById(e)},exportRoot:function(e,t){e===void 0&&(e={});var n=new A3(e),i,a;for(n.smoothChildTiming=C3(e.smoothChildTiming),L2.remove(n),n._dp=0,n._time=n._tTime=L2._time,i=L2._first;i;)a=i._next,(t||!(!i._dur&&i instanceof q2&&i.vars.onComplete===i._targets[0]))&&v4(n,i,i._start-i._delay),i=a;return v4(L2,n,0),n},context:function(e,t){return e?new Us(e,t):B2},matchMedia:function(e){return new TD(e)},matchMediaRefresh:function(){return V0.forEach(function(e){var t=e.conditions,n,i;for(i in t)t[i]&&(t[i]=!1,n=1);n&&e.revert()})||We()},addEventListener:function(e,t){var n=N8[e]||(N8[e]=[]);~n.indexOf(t)||n.push(t)},removeEventListener:function(e,t){var n=N8[e],i=n&&n.indexOf(t);i>=0&&n.splice(i,1)},utils:{wrap:aD,wrapYoyo:rD,distribute:vs,random:ys,snap:_s,normalize:oD,getUnit:g3,clamp:eD,splitColor:As,toArray:K3,selector:Ge,mapRange:xs,pipe:nD,unitize:iD,interpolate:sD,shuffle:gs},install:os,effects:h7,ticker:U3,updateRoot:A3.updateRoot,plugins:N3,globalTimeline:L2,core:{PropTween:E3,globals:as,Tween:q2,Timeline:A3,Animation:O5,getCache:F0,_removeLinkedListItem:d6,reverting:function(){return _3},context:function(e){return e&&B2&&(B2.data.push(e),e._ctx=B2),B2},suppressOverwrites:function(e){return _n=e}}};T3("to,from,fromTo,delayedCall,set,killTweensOf",function(o){return Q8[o]=q2[o]});U3.add(A3.updateRoot);P9=Q8.to({},{duration:0});var ED=function(e,t){for(var n=e._pt;n&&n.p!==t&&n.op!==t&&n.fp!==t;)n=n._next;return n},DD=function(e,t){var n=e._targets,i,a,r;for(i in t)for(a=n.length;a--;)r=e._ptLookup[a][i],r&&(r=r.d)&&(r._pt&&(r=ED(r,i)),r&&r.modifier&&r.modifier(t[i],e,n[a],i))},y7=function(e,t){return{name:e,rawVars:1,init:function(i,a,r){r._onInit=function(s){var u,c;if(s3(a)&&(u={},T3(a,function(f){return u[f]=1}),a=u),t){u={};for(c in a)u[c]=t(a[c]);a=u}DD(s,a)}}}},D3=Q8.registerPlugin({name:"attr",init:function(e,t,n,i,a){var r,s,u;this.tween=n;for(r in t)u=e.getAttribute(r)||"",s=this.add(e,"setAttribute",(u||0)+"",t[r],i,a,0,0,r),s.op=r,s.b=u,this._props.push(r)},render:function(e,t){for(var n=t._pt;n;)_3?n.set(n.t,n.p,n.b,n):n.r(e,n.d),n=n._next}},{name:"endArray",init:function(e,t){for(var n=t.length;n--;)this.add(e,n,e[n]||0,t[n],0,0,0,0,0,1)}},y7("roundProps",Ve),y7("modifiers"),y7("snap",_s))||Q8;q2.version=A3.version=D3.version="3.12.2";is=1;bn()&&J9();h2.Power0;h2.Power1;h2.Power2;h2.Power3;h2.Power4;h2.Linear;h2.Quad;h2.Cubic;h2.Quart;h2.Quint;h2.Strong;h2.Elastic;h2.Back;h2.SteppedEase;h2.Bounce;h2.Sine;h2.Expo;h2.Circ;/*!
 * CSSPlugin 3.12.2
 * https://greensock.com
 *
 * Copyright 2008-2023, GreenSock. All rights reserved.
 * Subject to the terms at https://greensock.com/standard-license or for
 * Club GreenSock members, the agreement issued with that membership.
 * @author: Jack Doyle, jack@greensock.com
*/var Ho,s0,N9,Rn,L0,$o,On,ID=function(){return typeof window!="undefined"},j4={},D0=180/Math.PI,U9=Math.PI/180,x9=Math.atan2,Yo=1e8,Pn=/([A-Z])/g,RD=/(left|right|width|margin|padding|x)/i,OD=/[\s,\(]\S/,b4={autoAlpha:"opacity,visibility",scale:"scaleX,scaleY",alpha:"opacity"},He=function(e,t){return t.set(t.t,t.p,Math.round((t.s+t.c*e)*1e4)/1e4+t.u,t)},PD=function(e,t){return t.set(t.t,t.p,e===1?t.e:Math.round((t.s+t.c*e)*1e4)/1e4+t.u,t)},LD=function(e,t){return t.set(t.t,t.p,e?Math.round((t.s+t.c*e)*1e4)/1e4+t.u:t.b,t)},zD=function(e,t){var n=t.s+t.c*e;t.set(t.t,t.p,~~(n+(n<0?-.5:.5))+t.u,t)},Fs=function(e,t){return t.set(t.t,t.p,e?t.e:t.b,t)},Bs=function(e,t){return t.set(t.t,t.p,e!==1?t.b:t.e,t)},kD=function(e,t,n){return e.style[t]=n},ND=function(e,t,n){return e.style.setProperty(t,n)},UD=function(e,t,n){return e._gsap[t]=n},FD=function(e,t,n){return e._gsap.scaleX=e._gsap.scaleY=n},BD=function(e,t,n,i,a){var r=e._gsap;r.scaleX=r.scaleY=n,r.renderTransform(a,r)},GD=function(e,t,n,i,a){var r=e._gsap;r[t]=n,r.renderTransform(a,r)},z2="transform",f4=z2+"Origin",VD=function o(e,t){var n=this,i=this.target,a=i.style;if(e in j4&&a){if(this.tfm=this.tfm||{},e!=="transform")e=b4[e]||e,~e.indexOf(",")?e.split(",").forEach(function(r){return n.tfm[r]=z4(i,r)}):this.tfm[e]=i._gsap.x?i._gsap[e]:z4(i,e);else return b4.transform.split(",").forEach(function(r){return o.call(n,r,t)});if(this.props.indexOf(z2)>=0)return;i._gsap.svg&&(this.svgo=i.getAttribute("data-svg-origin"),this.props.push(f4,t,"")),e=z2}(a||t)&&this.props.push(e,t,a[e])},Gs=function(e){e.translate&&(e.removeProperty("translate"),e.removeProperty("scale"),e.removeProperty("rotate"))},jD=function(){var e=this.props,t=this.target,n=t.style,i=t._gsap,a,r;for(a=0;a<e.length;a+=3)e[a+1]?t[e[a]]=e[a+2]:e[a+2]?n[e[a]]=e[a+2]:n.removeProperty(e[a].substr(0,2)==="--"?e[a]:e[a].replace(Pn,"-$1").toLowerCase());if(this.tfm){for(r in this.tfm)i[r]=this.tfm[r];i.svg&&(i.renderTransform(),t.setAttribute("data-svg-origin",this.svgo||"")),a=On(),(!a||!a.isStart)&&!n[z2]&&(Gs(n),i.uncache=1)}},Vs=function(e,t){var n={target:e,props:[],revert:jD,save:VD};return e._gsap||D3.core.getCache(e),t&&t.split(",").forEach(function(i){return n.save(i)}),n},js,$e=function(e,t){var n=s0.createElementNS?s0.createElementNS((t||"http://www.w3.org/1999/xhtml").replace(/^https/,"http"),e):s0.createElement(e);return n.style?n:s0.createElement(e)},S4=function o(e,t,n){var i=getComputedStyle(e);return i[t]||i.getPropertyValue(t.replace(Pn,"-$1").toLowerCase())||i.getPropertyValue(t)||!n&&o(e,Z9(t)||t,1)||""},Jo="O,Moz,ms,Ms,Webkit".split(","),Z9=function(e,t,n){var i=t||L0,a=i.style,r=5;if(e in a&&!n)return e;for(e=e.charAt(0).toUpperCase()+e.substr(1);r--&&!(Jo[r]+e in a););return r<0?null:(r===3?"ms":r>=0?Jo[r]:"")+e},Ye=function(){ID()&&window.document&&(Ho=window,s0=Ho.document,N9=s0.documentElement,L0=$e("div")||{style:{}},$e("div"),z2=Z9(z2),f4=z2+"Origin",L0.style.cssText="border-width:0;line-height:0;position:absolute;padding:0",js=!!Z9("perspective"),On=D3.core.reverting,Rn=1)},b7=function o(e){var t=$e("svg",this.ownerSVGElement&&this.ownerSVGElement.getAttribute("xmlns")||"http://www.w3.org/2000/svg"),n=this.parentNode,i=this.nextSibling,a=this.style.cssText,r;if(N9.appendChild(t),t.appendChild(this),this.style.display="block",e)try{r=this.getBBox(),this._gsapBBox=this.getBBox,this.getBBox=o}catch(s){}else this._gsapBBox&&(r=this._gsapBBox());return n&&(i?n.insertBefore(this,i):n.appendChild(this)),N9.removeChild(t),this.style.cssText=a,r},Zo=function(e,t){for(var n=t.length;n--;)if(e.hasAttribute(t[n]))return e.getAttribute(t[n])},Ws=function(e){var t;try{t=e.getBBox()}catch(n){t=b7.call(e,!0)}return t&&(t.width||t.height)||e.getBBox===b7||(t=b7.call(e,!0)),t&&!t.width&&!t.x&&!t.y?{x:+Zo(e,["x","cx","x1"])||0,y:+Zo(e,["y","cy","y1"])||0,width:0,height:0}:t},Hs=function(e){return!!(e.getCTM&&(!e.parentNode||e.ownerSVGElement)&&Ws(e))},P5=function(e,t){if(t){var n=e.style;t in j4&&t!==f4&&(t=z2),n.removeProperty?((t.substr(0,2)==="ms"||t.substr(0,6)==="webkit")&&(t="-"+t),n.removeProperty(t.replace(Pn,"-$1").toLowerCase())):n.removeAttribute(t)}},l0=function(e,t,n,i,a,r){var s=new E3(e._pt,t,n,0,1,r?Bs:Fs);return e._pt=s,s.b=i,s.e=a,e._props.push(n),s},qo={deg:1,rad:1,turn:1},WD={grid:1,flex:1},m0=function o(e,t,n,i){var a=parseFloat(n)||0,r=(n+"").trim().substr((a+"").length)||"px",s=L0.style,u=RD.test(t),c=e.tagName.toLowerCase()==="svg",f=(c?"client":"offset")+(u?"Width":"Height"),p=100,h=i==="px",m=i==="%",y,_,v,b;return i===r||!a||qo[i]||qo[r]?a:(r!=="px"&&!h&&(a=o(e,t,n,"px")),b=e.getCTM&&Hs(e),(m||r==="%")&&(j4[t]||~t.indexOf("adius"))?(y=b?e.getBBox()[u?"width":"height"]:e[f],j2(m?a/y*p:a/100*y)):(s[u?"width":"height"]=p+(h?r:i),_=~t.indexOf("adius")||i==="em"&&e.appendChild&&!c?e:e.parentNode,b&&(_=(e.ownerSVGElement||{}).parentNode),(!_||_===s0||!_.appendChild)&&(_=s0.body),v=_._gsap,v&&m&&v.width&&u&&v.time===U3.time&&!v.uncache?j2(a/v.width*p):((m||r==="%")&&!WD[S4(_,"display")]&&(s.position=S4(e,"position")),_===e&&(s.position="static"),_.appendChild(L0),y=L0[f],_.removeChild(L0),s.position="absolute",u&&m&&(v=F0(_),v.time=U3.time,v.width=_[f]),j2(h?y*a/p:y&&a?p/y*a:0))))},z4=function(e,t,n,i){var a;return Rn||Ye(),t in b4&&t!=="transform"&&(t=b4[t],~t.indexOf(",")&&(t=t.split(",")[0])),j4[t]&&t!=="transform"?(a=z5(e,i),a=t!=="transformOrigin"?a[t]:a.svg?a.origin:e6(S4(e,f4))+" "+a.zOrigin+"px"):(a=e.style[t],(!a||a==="auto"||i||~(a+"").indexOf("calc("))&&(a=K8[t]&&K8[t](e,t,n)||S4(e,t)||ss(e,t)||(t==="opacity"?1:0))),n&&!~(a+"").trim().indexOf(" ")?m0(e,t,a,n)+n:a},HD=function(e,t,n,i){if(!n||n==="none"){var a=Z9(t,e,1),r=a&&S4(e,a,1);r&&r!==n?(t=a,n=r):t==="borderColor"&&(n=S4(e,"borderTopColor"))}var s=new E3(this._pt,e.style,t,0,1,ks),u=0,c=0,f,p,h,m,y,_,v,b,x,A,S,w;if(s.b=n,s.e=i,n+="",i+="",i==="auto"&&(e.style[t]=i,i=S4(e,t)||i,e.style[t]=n),f=[n,i],Cs(f),n=f[0],i=f[1],h=n.match(O9)||[],w=i.match(O9)||[],w.length){for(;p=O9.exec(i);)v=p[0],x=i.substring(u,p.index),y?y=(y+1)%5:(x.substr(-5)==="rgba("||x.substr(-5)==="hsla(")&&(y=1),v!==(_=h[c++]||"")&&(m=parseFloat(_)||0,S=_.substr((m+"").length),v.charAt(1)==="="&&(v=k9(m,v)+S),b=parseFloat(v),A=v.substr((b+"").length),u=O9.lastIndex-A.length,A||(A=A||B3.units[t]||S,u===i.length&&(i+=A,s.e+=A)),S!==A&&(m=m0(e,t,_,A)||0),s._pt={_next:s._pt,p:x||c===1?x:",",s:m,c:b-m,m:y&&y<4||t==="zIndex"?Math.round:0});s.c=u<i.length?i.substring(u,i.length):""}else s.r=t==="display"&&i==="none"?Bs:Fs;return ts.test(i)&&(s.e=0),this._pt=s,s},Xo={top:"0%",bottom:"100%",left:"0%",right:"100%",center:"50%"},$D=function(e){var t=e.split(" "),n=t[0],i=t[1]||"50%";return(n==="top"||n==="bottom"||i==="left"||i==="right")&&(e=n,n=i,i=e),t[0]=Xo[n]||n,t[1]=Xo[i]||i,t.join(" ")},YD=function(e,t){if(t.tween&&t.tween._time===t.tween._dur){var n=t.t,i=n.style,a=t.u,r=n._gsap,s,u,c;if(a==="all"||a===!0)i.cssText="",u=1;else for(a=a.split(","),c=a.length;--c>-1;)s=a[c],j4[s]&&(u=1,s=s==="transformOrigin"?f4:z2),P5(n,s);u&&(P5(n,z2),r&&(r.svg&&n.removeAttribute("transform"),z5(n,1),r.uncache=1,Gs(i)))}},K8={clearProps:function(e,t,n,i,a){if(a.data!=="isFromStart"){var r=e._pt=new E3(e._pt,t,n,0,0,YD);return r.u=i,r.pr=-10,r.tween=a,e._props.push(n),1}}},L5=[1,0,0,1,0,0],$s={},Ys=function(e){return e==="matrix(1, 0, 0, 1, 0, 0)"||e==="none"||!e},Qo=function(e){var t=S4(e,z2);return Ys(t)?L5:t.substr(7).match(es).map(j2)},Ln=function(e,t){var n=e._gsap||F0(e),i=e.style,a=Qo(e),r,s,u,c;return n.svg&&e.getAttribute("transform")?(u=e.transform.baseVal.consolidate().matrix,a=[u.a,u.b,u.c,u.d,u.e,u.f],a.join(",")==="1,0,0,1,0,0"?L5:a):(a===L5&&!e.offsetParent&&e!==N9&&!n.svg&&(u=i.display,i.display="block",r=e.parentNode,(!r||!e.offsetParent)&&(c=1,s=e.nextElementSibling,N9.appendChild(e)),a=Qo(e),u?i.display=u:P5(e,"display"),c&&(s?r.insertBefore(e,s):r?r.appendChild(e):N9.removeChild(e))),t&&a.length>6?[a[0],a[1],a[4],a[5],a[12],a[13]]:a)},Je=function(e,t,n,i,a,r){var s=e._gsap,u=a||Ln(e,!0),c=s.xOrigin||0,f=s.yOrigin||0,p=s.xOffset||0,h=s.yOffset||0,m=u[0],y=u[1],_=u[2],v=u[3],b=u[4],x=u[5],A=t.split(" "),S=parseFloat(A[0])||0,w=parseFloat(A[1])||0,M,D,T,E;n?u!==L5&&(D=m*v-y*_)&&(T=S*(v/D)+w*(-_/D)+(_*x-v*b)/D,E=S*(-y/D)+w*(m/D)-(m*x-y*b)/D,S=T,w=E):(M=Ws(e),S=M.x+(~A[0].indexOf("%")?S/100*M.width:S),w=M.y+(~(A[1]||A[0]).indexOf("%")?w/100*M.height:w)),i||i!==!1&&s.smooth?(b=S-c,x=w-f,s.xOffset=p+(b*m+x*_)-b,s.yOffset=h+(b*y+x*v)-x):s.xOffset=s.yOffset=0,s.xOrigin=S,s.yOrigin=w,s.smooth=!!i,s.origin=t,s.originIsAbsolute=!!n,e.style[f4]="0px 0px",r&&(l0(r,s,"xOrigin",c,S),l0(r,s,"yOrigin",f,w),l0(r,s,"xOffset",p,s.xOffset),l0(r,s,"yOffset",h,s.yOffset)),e.setAttribute("data-svg-origin",S+" "+w)},z5=function(e,t){var n=e._gsap||new Is(e);if("x"in n&&!t&&!n.uncache)return n;var i=e.style,a=n.scaleX<0,r="px",s="deg",u=getComputedStyle(e),c=S4(e,f4)||"0",f,p,h,m,y,_,v,b,x,A,S,w,M,D,T,E,U,V,q,$,z,F,G,J,O,N,B,t1,n1,u1,a1,Q;return f=p=h=_=v=b=x=A=S=0,m=y=1,n.svg=!!(e.getCTM&&Hs(e)),u.translate&&((u.translate!=="none"||u.scale!=="none"||u.rotate!=="none")&&(i[z2]=(u.translate!=="none"?"translate3d("+(u.translate+" 0 0").split(" ").slice(0,3).join(", ")+") ":"")+(u.rotate!=="none"?"rotate("+u.rotate+") ":"")+(u.scale!=="none"?"scale("+u.scale.split(" ").join(",")+") ":"")+(u[z2]!=="none"?u[z2]:"")),i.scale=i.rotate=i.translate="none"),D=Ln(e,n.svg),n.svg&&(n.uncache?(O=e.getBBox(),c=n.xOrigin-O.x+"px "+(n.yOrigin-O.y)+"px",J=""):J=!t&&e.getAttribute("data-svg-origin"),Je(e,J||c,!!J||n.originIsAbsolute,n.smooth!==!1,D)),w=n.xOrigin||0,M=n.yOrigin||0,D!==L5&&(V=D[0],q=D[1],$=D[2],z=D[3],f=F=D[4],p=G=D[5],D.length===6?(m=Math.sqrt(V*V+q*q),y=Math.sqrt(z*z+$*$),_=V||q?x9(q,V)*D0:0,x=$||z?x9($,z)*D0+_:0,x&&(y*=Math.abs(Math.cos(x*U9))),n.svg&&(f-=w-(w*V+M*$),p-=M-(w*q+M*z))):(Q=D[6],u1=D[7],B=D[8],t1=D[9],n1=D[10],a1=D[11],f=D[12],p=D[13],h=D[14],T=x9(Q,n1),v=T*D0,T&&(E=Math.cos(-T),U=Math.sin(-T),J=F*E+B*U,O=G*E+t1*U,N=Q*E+n1*U,B=F*-U+B*E,t1=G*-U+t1*E,n1=Q*-U+n1*E,a1=u1*-U+a1*E,F=J,G=O,Q=N),T=x9(-$,n1),b=T*D0,T&&(E=Math.cos(-T),U=Math.sin(-T),J=V*E-B*U,O=q*E-t1*U,N=$*E-n1*U,a1=z*U+a1*E,V=J,q=O,$=N),T=x9(q,V),_=T*D0,T&&(E=Math.cos(T),U=Math.sin(T),J=V*E+q*U,O=F*E+G*U,q=q*E-V*U,G=G*E-F*U,V=J,F=O),v&&Math.abs(v)+Math.abs(_)>359.9&&(v=_=0,b=180-b),m=j2(Math.sqrt(V*V+q*q+$*$)),y=j2(Math.sqrt(G*G+Q*Q)),T=x9(F,G),x=Math.abs(T)>2e-4?T*D0:0,S=a1?1/(a1<0?-a1:a1):0),n.svg&&(J=e.getAttribute("transform"),n.forceCSS=e.setAttribute("transform","")||!Ys(S4(e,z2)),J&&e.setAttribute("transform",J))),Math.abs(x)>90&&Math.abs(x)<270&&(a?(m*=-1,x+=_<=0?180:-180,_+=_<=0?180:-180):(y*=-1,x+=x<=0?180:-180)),t=t||n.uncache,n.x=f-((n.xPercent=f&&(!t&&n.xPercent||(Math.round(e.offsetWidth/2)===Math.round(-f)?-50:0)))?e.offsetWidth*n.xPercent/100:0)+r,n.y=p-((n.yPercent=p&&(!t&&n.yPercent||(Math.round(e.offsetHeight/2)===Math.round(-p)?-50:0)))?e.offsetHeight*n.yPercent/100:0)+r,n.z=h+r,n.scaleX=j2(m),n.scaleY=j2(y),n.rotation=j2(_)+s,n.rotationX=j2(v)+s,n.rotationY=j2(b)+s,n.skewX=x+s,n.skewY=A+s,n.transformPerspective=S+r,(n.zOrigin=parseFloat(c.split(" ")[2])||0)&&(i[f4]=e6(c)),n.xOffset=n.yOffset=0,n.force3D=B3.force3D,n.renderTransform=n.svg?ZD:js?Js:JD,n.uncache=0,n},e6=function(e){return(e=e.split(" "))[0]+" "+e[1]},x7=function(e,t,n){var i=g3(t);return j2(parseFloat(t)+parseFloat(m0(e,"x",n+"px",i)))+i},JD=function(e,t){t.z="0px",t.rotationY=t.rotationX="0deg",t.force3D=0,Js(e,t)},w0="0deg",u5="0px",C0=") ",Js=function(e,t){var n=t||this,i=n.xPercent,a=n.yPercent,r=n.x,s=n.y,u=n.z,c=n.rotation,f=n.rotationY,p=n.rotationX,h=n.skewX,m=n.skewY,y=n.scaleX,_=n.scaleY,v=n.transformPerspective,b=n.force3D,x=n.target,A=n.zOrigin,S="",w=b==="auto"&&e&&e!==1||b===!0;if(A&&(p!==w0||f!==w0)){var M=parseFloat(f)*U9,D=Math.sin(M),T=Math.cos(M),E;M=parseFloat(p)*U9,E=Math.cos(M),r=x7(x,r,D*E*-A),s=x7(x,s,-Math.sin(M)*-A),u=x7(x,u,T*E*-A+A)}v!==u5&&(S+="perspective("+v+C0),(i||a)&&(S+="translate("+i+"%, "+a+"%) "),(w||r!==u5||s!==u5||u!==u5)&&(S+=u!==u5||w?"translate3d("+r+", "+s+", "+u+") ":"translate("+r+", "+s+C0),c!==w0&&(S+="rotate("+c+C0),f!==w0&&(S+="rotateY("+f+C0),p!==w0&&(S+="rotateX("+p+C0),(h!==w0||m!==w0)&&(S+="skew("+h+", "+m+C0),(y!==1||_!==1)&&(S+="scale("+y+", "+_+C0),x.style[z2]=S||"translate(0, 0)"},ZD=function(e,t){var n=t||this,i=n.xPercent,a=n.yPercent,r=n.x,s=n.y,u=n.rotation,c=n.skewX,f=n.skewY,p=n.scaleX,h=n.scaleY,m=n.target,y=n.xOrigin,_=n.yOrigin,v=n.xOffset,b=n.yOffset,x=n.forceCSS,A=parseFloat(r),S=parseFloat(s),w,M,D,T,E;u=parseFloat(u),c=parseFloat(c),f=parseFloat(f),f&&(f=parseFloat(f),c+=f,u+=f),u||c?(u*=U9,c*=U9,w=Math.cos(u)*p,M=Math.sin(u)*p,D=Math.sin(u-c)*-h,T=Math.cos(u-c)*h,c&&(f*=U9,E=Math.tan(c-f),E=Math.sqrt(1+E*E),D*=E,T*=E,f&&(E=Math.tan(f),E=Math.sqrt(1+E*E),w*=E,M*=E)),w=j2(w),M=j2(M),D=j2(D),T=j2(T)):(w=p,T=h,M=D=0),(A&&!~(r+"").indexOf("px")||S&&!~(s+"").indexOf("px"))&&(A=m0(m,"x",r,"px"),S=m0(m,"y",s,"px")),(y||_||v||b)&&(A=j2(A+y-(y*w+_*D)+v),S=j2(S+_-(y*M+_*T)+b)),(i||a)&&(E=m.getBBox(),A=j2(A+i/100*E.width),S=j2(S+a/100*E.height)),E="matrix("+w+","+M+","+D+","+T+","+A+","+S+")",m.setAttribute("transform",E),x&&(m.style[z2]=E)},qD=function(e,t,n,i,a){var r=360,s=s3(a),u=parseFloat(a)*(s&&~a.indexOf("rad")?D0:1),c=u-i,f=i+c+"deg",p,h;return s&&(p=a.split("_")[1],p==="short"&&(c%=r,c!==c%(r/2)&&(c+=c<0?r:-r)),p==="cw"&&c<0?c=(c+r*Yo)%r-~~(c/r)*r:p==="ccw"&&c>0&&(c=(c-r*Yo)%r-~~(c/r)*r)),e._pt=h=new E3(e._pt,t,n,i,c,PD),h.e=f,h.u="deg",e._props.push(n),h},Ko=function(e,t){for(var n in t)e[n]=t[n];return e},XD=function(e,t,n){var i=Ko({},n._gsap),a="perspective,force3D,transformOrigin,svgOrigin",r=n.style,s,u,c,f,p,h,m,y;i.svg?(c=n.getAttribute("transform"),n.setAttribute("transform",""),r[z2]=t,s=z5(n,1),P5(n,z2),n.setAttribute("transform",c)):(c=getComputedStyle(n)[z2],r[z2]=t,s=z5(n,1),r[z2]=c);for(u in j4)c=i[u],f=s[u],c!==f&&a.indexOf(u)<0&&(m=g3(c),y=g3(f),p=m!==y?m0(n,u,c,y):parseFloat(c),h=parseFloat(f),e._pt=new E3(e._pt,s,u,p,h-p,He),e._pt.u=y||0,e._props.push(u));Ko(s,i)};T3("padding,margin,Width,Radius",function(o,e){var t="Top",n="Right",i="Bottom",a="Left",r=(e<3?[t,n,i,a]:[t+a,t+n,i+n,i+a]).map(function(s){return e<2?o+s:"border"+s+o});K8[e>1?"border"+o:o]=function(s,u,c,f,p){var h,m;if(arguments.length<4)return h=r.map(function(y){return z4(s,y,c)}),m=h.join(" "),m.split(h[0]).length===5?h[0]:m;h=(f+"").split(" "),m={},r.forEach(function(y,_){return m[y]=h[_]=h[_]||h[(_-1)/2|0]}),s.init(u,m,p)}});var Zs={name:"css",register:Ye,targetTest:function(e){return e.style&&e.nodeType},init:function(e,t,n,i,a){var r=this._props,s=e.style,u=n.vars.startAt,c,f,p,h,m,y,_,v,b,x,A,S,w,M,D,T;Rn||Ye(),this.styles=this.styles||Vs(e),T=this.styles.props,this.tween=n;for(_ in t)if(_!=="autoRound"&&(f=t[_],!(N3[_]&&Rs(_,t,n,i,e,a)))){if(m=typeof f,y=K8[_],m==="function"&&(f=f.call(n,i,e,a),m=typeof f),m==="string"&&~f.indexOf("random(")&&(f=I5(f)),y)y(this,e,_,f,n)&&(D=1);else if(_.substr(0,2)==="--")c=(getComputedStyle(e).getPropertyValue(_)+"").trim(),f+="",f0.lastIndex=0,f0.test(c)||(v=g3(c),b=g3(f)),b?v!==b&&(c=m0(e,_,c,b)+b):v&&(f+=v),this.add(s,"setProperty",c,f,i,a,0,0,_),r.push(_),T.push(_,0,s[_]);else if(m!=="undefined"){if(u&&_ in u?(c=typeof u[_]=="function"?u[_].call(n,i,e,a):u[_],s3(c)&&~c.indexOf("random(")&&(c=I5(c)),g3(c+"")||(c+=B3.units[_]||g3(z4(e,_))||""),(c+"").charAt(1)==="="&&(c=z4(e,_))):c=z4(e,_),h=parseFloat(c),x=m==="string"&&f.charAt(1)==="="&&f.substr(0,2),x&&(f=f.substr(2)),p=parseFloat(f),_ in b4&&(_==="autoAlpha"&&(h===1&&z4(e,"visibility")==="hidden"&&p&&(h=0),T.push("visibility",0,s.visibility),l0(this,s,"visibility",h?"inherit":"hidden",p?"inherit":"hidden",!p)),_!=="scale"&&_!=="transform"&&(_=b4[_],~_.indexOf(",")&&(_=_.split(",")[0]))),A=_ in j4,A){if(this.styles.save(_),S||(w=e._gsap,w.renderTransform&&!t.parseTransform||z5(e,t.parseTransform),M=t.smoothOrigin!==!1&&w.smooth,S=this._pt=new E3(this._pt,s,z2,0,1,w.renderTransform,w,0,-1),S.dep=1),_==="scale")this._pt=new E3(this._pt,w,"scaleY",w.scaleY,(x?k9(w.scaleY,x+p):p)-w.scaleY||0,He),this._pt.u=0,r.push("scaleY",_),_+="X";else if(_==="transformOrigin"){T.push(f4,0,s[f4]),f=$D(f),w.svg?Je(e,f,0,M,0,this):(b=parseFloat(f.split(" ")[2])||0,b!==w.zOrigin&&l0(this,w,"zOrigin",w.zOrigin,b),l0(this,s,_,e6(c),e6(f)));continue}else if(_==="svgOrigin"){Je(e,f,1,M,0,this);continue}else if(_ in $s){qD(this,w,_,h,x?k9(h,x+f):f);continue}else if(_==="smoothOrigin"){l0(this,w,"smooth",w.smooth,f);continue}else if(_==="force3D"){w[_]=f;continue}else if(_==="transform"){XD(this,f,e);continue}}else _ in s||(_=Z9(_)||_);if(A||(p||p===0)&&(h||h===0)&&!OD.test(f)&&_ in s)v=(c+"").substr((h+"").length),p||(p=0),b=g3(f)||(_ in B3.units?B3.units[_]:v),v!==b&&(h=m0(e,_,c,b)),this._pt=new E3(this._pt,A?w:s,_,h,(x?k9(h,x+p):p)-h,!A&&(b==="px"||_==="zIndex")&&t.autoRound!==!1?zD:He),this._pt.u=b||0,v!==b&&b!=="%"&&(this._pt.b=c,this._pt.r=LD);else if(_ in s)HD.call(this,e,_,c,x?x+f:f);else if(_ in e)this.add(e,_,c||e[_],x?x+f:f,i,a);else if(_!=="parseTransform"){Sn(_,f);continue}A||(_ in s?T.push(_,0,s[_]):T.push(_,1,c||e[_])),r.push(_)}}D&&Ns(this)},render:function(e,t){if(t.tween._time||!On())for(var n=t._pt;n;)n.r(e,n.d),n=n._next;else t.styles.revert()},get:z4,aliases:b4,getSetter:function(e,t,n){var i=b4[t];return i&&i.indexOf(",")<0&&(t=i),t in j4&&t!==f4&&(e._gsap.x||z4(e,"x"))?n&&$o===n?t==="scale"?FD:UD:($o=n||{})&&(t==="scale"?BD:GD):e.style&&!yn(e.style[t])?kD:~t.indexOf("-")?ND:Dn(e,t)},core:{_removeProperty:P5,_getMatrix:Ln}};D3.utils.checkPrefix=Z9;D3.core.getStyleSaver=Vs;(function(o,e,t,n){var i=T3(o+","+e+","+t,function(a){j4[a]=1});T3(e,function(a){B3.units[a]="deg",$s[a]=1}),b4[i[13]]=o+","+e,T3(n,function(a){var r=a.split(":");b4[r[1]]=i[r[0]]})})("x,y,z,scale,scaleX,scaleY,xPercent,yPercent","rotation,rotationX,rotationY,skewX,skewY","transform,transformOrigin,svgOrigin,force3D,smoothOrigin,transformPerspective","0:translateX,1:translateY,2:translateZ,8:rotate,8:rotationZ,8:rotateZ,9:rotateX,10:rotateY");T3("x,y,z,top,right,bottom,left,width,height,fontSize,padding,margin,perspective",function(o){B3.units[o]="px"});D3.registerPlugin(Zs);var qs=D3.registerPlugin(Zs)||D3;qs.core.Tween;function QD(o,e,t,n){const i=new O3,r=new mn(0,0,o,e,t,!1).getSpacedPoints(100);i.setFromPoints(r);const s=[];for(let m=0;m<r.length;m++)s.push(m/r.length);const u=new F3(new Float32Array(s),1);i.attributes.percent=u;const c=[];for(let m=0;m<r.length;m++){const y=new y2(15503171),_=new y2(15969910),v=y.lerp(_,m/r.length);c.push(v.r,v.g,v.b)}i.attributes.color=new F3(new Float32Array(c),3);const f=1.3,p=new hn({size:f,transparent:!0,depthWrite:!1});p.onBeforeCompile=function(m){m.vertexShader=m.vertexShader.replace("void main() {",["attribute float percent;","void main() {"].join(`
`)),m.vertexShader=m.vertexShader.replace("gl_PointSize = size;",["gl_PointSize = percent * size;"].join(`
`))};const h=new Wr(i,p);return p.color=new y2(n),h.name="飞行线",h}function KD(o,e,t,n,i,a){const r=E5(o,e,t),s=new Y(r.x,r.y,r.z),u=E5(o,n,i),c=new Y(u.x,u.y,u.z),f=eI(s,c),p=tI(o,f.startPoint,f.endPoint,a);return p.quaternion.multiply(f.quaternion),p}function eI(o,e){const t=new Y(0,0,0),n=o.clone().sub(t),i=e.clone().sub(t),a=n.clone().cross(i).normalize(),r=new Y(0,0,1),s=new B4().setFromUnitVectors(a,r),u=o.clone().applyQuaternion(s),c=e.clone().applyQuaternion(s),p=u.clone().add(c).multiplyScalar(.5).clone().sub(t).normalize(),h=new Y(0,1,0),m=new B4().setFromUnitVectors(p,h),y=u.clone().applyQuaternion(m),_=c.clone().applyQuaternion(m);return{quaternion:s.clone().invert().multiply(m.clone().invert()),startPoint:y,endPoint:_}}function tI(o,e,t,n){const a=new Y().addVectors(e,t).multiplyScalar(.5).clone().normalize(),r=ea(e,t,new Y(0,0,0)),s=a.multiplyScalar(o+r*o*.2),u=iI(e,t,s),c=Math.abs(u.y-s.y),f=ea(e,new Y(0,-1,0),u),p=-Math.PI/2+f,h=Math.PI-p,m=nI(u.x,u.y,c,p,h,n.color);m.center=u,m.topCoord=s;const y=(h-p)/7,_=QD(c,p,p+y,n.flyLineColor);return _.position.y=u.y,m.add(_),_.flyEndAngle=h-p-y,_.startAngle=p,_.AngleZ=m.flyEndAngle*Math.random(),m.userData.flyLine=_,m}function ea(o,e,t){const n=o.clone().sub(t).normalize(),i=e.clone().sub(t).normalize(),a=n.clone().dot(i);return Math.acos(a)}function nI(o,e,t,n,i,a){const r=new O3,u=new mn(o,e,t,n,i,!1).getSpacedPoints(80);r.setFromPoints(u);const c=new jr({color:a||13731143});return new qT(r,c)}function iI(o,e,t){const n=o.lengthSq(),i=e.lengthSq(),a=t.lengthSq(),r=o.x,s=o.y,u=e.x,c=e.y,f=t.x,p=t.y,h=r*c+u*p+f*s-r*p-u*s-f*c,m=(i*p+n*c+a*s-i*s-a*c-n*p)/h/2,y=(a*u+i*r+n*f-n*u-i*f-a*r)/h/2;return new Y(m,y,0)}const oI=`
varying vec2 vUv;
varying vec3 vNormal;
varying vec3 vp;
varying vec3 vPositionNormal;
void main(void){
  vUv = uv;
  vNormal = normalize( normalMatrix * normal ); // 转换到视图空间
  vp = position;
  vPositionNormal = normalize(( modelViewMatrix * vec4(position, 1.0) ).xyz);
  gl_Position = projectionMatrix * modelViewMatrix * vec4( position, 1.0 );
}`,aI=`uniform vec3 glowColor;
uniform float bias;
uniform float power;
uniform float time;
varying vec3 vp;
varying vec3 vNormal;
varying vec3 vPositionNormal;
uniform float scale;
// 获取纹理
uniform sampler2D map;
// 纹理坐标
varying vec2 vUv;

void main(void){
  float a = pow( bias + scale * abs(dot(vNormal, vPositionNormal)), power );
  if(vp.y > time && vp.y < time + 20.0) {
    float t =  smoothstep(0.0, 0.8,  (1.0 - abs(0.5 - (vp.y - time) / 20.0)) / 3.0  );
    gl_FragColor = mix(gl_FragColor, vec4(glowColor, 1.0), t * t );
  }
  gl_FragColor = mix(gl_FragColor, vec4( glowColor, 1.0 ), a);
  float b = 0.8;
  gl_FragColor = gl_FragColor + texture2D( map, vUv );
}`;class rI{constructor(e){X(this,"group");X(this,"earthGroup");X(this,"around");X(this,"aroundPoints");X(this,"options");X(this,"uniforms");X(this,"timeValue");X(this,"earth");X(this,"punctuationMaterial");X(this,"markupPoint");X(this,"waveMeshArr");X(this,"circleLineList");X(this,"circleList");X(this,"x");X(this,"n");X(this,"isRotation");X(this,"flyLineArcGroup");this.options=e,this.group=new N4,this.group.name="group",this.group.scale.set(0,0,0),this.earthGroup=new N4,this.group.add(this.earthGroup),this.earthGroup.name="EarthGroup",this.markupPoint=new N4,this.markupPoint.name="markupPoint",this.waveMeshArr=[],this.circleLineList=[],this.circleList=[],this.x=0,this.n=0,this.isRotation=this.options.earth.isRotation,this.timeValue=200,this.uniforms={glowColor:{value:new y2(840171)},scale:{type:"f",value:-1},bias:{type:"f",value:1},power:{type:"f",value:3.3},time:{type:"f",value:this.timeValue},isHover:{value:!1},map:{value:void 0}}}init(){return P2(this,null,function*(){return new Promise(e=>{(()=>P2(this,null,function*(){this.createEarth(),this.createEarthGlow(),this.createEarthAperture(),yield this.createMarkupPoint(),this.createAnimateCircle(),this.createFlyLine(),this.show(),e()}))()})})}createEarth(){const e=new i0(this.options.earth.radius,50,50),t=new i0(this.options.earth.radius+10,60,60),n=new hn({color:8519679,transparent:!0,sizeAttenuation:!0,opacity:.1,vertexColors:!1,size:.2}),i=new Wr(t,n);this.earthGroup.add(i),this.uniforms.map.value=this.options.textures.earth;const a=new G4({uniforms:this.uniforms,vertexShader:oI,fragmentShader:aI});a.needsUpdate=!0,this.earth=new u3(e,a),this.earth.name="earth",this.earthGroup.add(this.earth)}createEarthGlow(){const e=this.options.earth.radius,t=this.options.textures.glow,n=new Gr({map:t,color:4427985,transparent:!0,opacity:.7,depthWrite:!1}),i=new ZT(n);i.scale.set(e*3,e*3,1),this.earthGroup.add(i)}createEarthAperture(){const e=["varying vec3	vVertexWorldPosition;","varying vec3	vVertexNormal;","varying vec4	vFragColor;","void main(){","	vVertexNormal	= normalize(normalMatrix * normal);","	vVertexWorldPosition	= (modelMatrix * vec4(position, 1.0)).xyz;","	// set gl_Position","	gl_Position	= projectionMatrix * modelViewMatrix * vec4(position, 1.0);","}"].join(`
`),t={uniforms:{coeficient:{type:"f",value:1},power:{type:"f",value:3},glowColor:{type:"c",value:new y2(4427985)}},vertexShader:e,fragmentShader:["uniform vec3	glowColor;","uniform float	coeficient;","uniform float	power;","varying vec3	vVertexNormal;","varying vec3	vVertexWorldPosition;","varying vec4	vFragColor;","void main(){","	vec3 worldCameraToVertex = vVertexWorldPosition - cameraPosition;","	vec3 viewCameraToVertex	= (viewMatrix * vec4(worldCameraToVertex, 0.0)).xyz;","	viewCameraToVertex= normalize(viewCameraToVertex);","	float intensity	= pow(coeficient + dot(vVertexNormal, viewCameraToVertex), power);","	gl_FragColor = vec4(glowColor, intensity);","}"].join(`
`)},n=new G4({uniforms:t.uniforms,vertexShader:t.vertexShader,fragmentShader:t.fragmentShader,blending:k0,transparent:!0,depthWrite:!1}),i=new i0(this.options.earth.radius+0,50,50),a=new u3(i,n);this.earthGroup.add(a)}createMarkupPoint(){return P2(this,null,function*(){yield Promise.all(this.options.data.map(e=>P2(this,null,function*(){const t=this.options.earth.radius,n=e.startArray.E,i=e.startArray.N;this.punctuationMaterial=new k4({color:this.options.punctuation.circleColor,map:this.options.textures.label,transparent:!0,depthWrite:!1});const a=No({radius:t,lon:n,lat:i,material:this.punctuationMaterial});this.markupPoint.add(a);const r=ko({radius:this.options.earth.radius,lon:n,lat:i,index:0,textures:this.options.textures,punctuation:this.options.punctuation});this.markupPoint.add(r);const s=zo({radius:t,lon:n,lat:i,textures:this.options.textures});this.markupPoint.add(s),this.waveMeshArr.push(s),yield Promise.all(e.endArray.map(u=>{const c=u.E,f=u.N,p=No({radius:t,lon:c,lat:f,material:this.punctuationMaterial});this.markupPoint.add(p);const h=ko({radius:this.options.earth.radius,lon:c,lat:f,index:1,textures:this.options.textures,punctuation:this.options.punctuation});this.markupPoint.add(h);const m=zo({radius:t,lon:c,lat:f,textures:this.options.textures});this.markupPoint.add(m),this.waveMeshArr.push(m)})),this.earthGroup.add(this.markupPoint)})))})}createAnimateCircle(){const e=kE({radius:this.options.earth.radius+15,number:150,closed:!0}),t=new k4({color:"#0c3172",transparent:!0,opacity:.4,side:_4}),n=NE({pointList:e,material:t,number:100,radius:.1});this.earthGroup.add(n);const i=n.clone();i.scale.set(1.2,1.2,1.2),i.rotateZ(Math.PI/6),this.earthGroup.add(i);const a=n.clone();a.scale.set(.8,.8,.8),a.rotateZ(-Math.PI/6),this.earthGroup.add(a);const r=new u3(new i0(this.options.satellite.size,32,32),new k4({color:"#e0b187"})),s=new u3(new i0(this.options.satellite.size,32,32),new k4({color:"#628fbb"})),u=new u3(new i0(this.options.satellite.size,32,32),new k4({color:"#806bdf"}));this.circleLineList.push(n,i,a),r.name=s.name=u.name="卫星";for(let c=0;c<this.options.satellite.number;c++){const f=r.clone(),p=Math.floor(e.length/this.options.satellite.number);f.position.set(e[p*(c+1)][0]*1,e[p*(c+1)][1]*1,e[p*(c+1)][2]*1),n.add(f);const h=s.clone(),m=Math.floor(e.length/this.options.satellite.number);h.position.set(e[m*(c+1)][0]*1,e[m*(c+1)][1]*1,e[m*(c+1)][2]*1),i.add(h);const y=s.clone(),_=Math.floor(e.length/this.options.satellite.number);y.position.set(e[_*(c+1)][0]*1,e[_*(c+1)][1]*1,e[_*(c+1)][2]*1),a.add(y)}}createFlyLine(){this.flyLineArcGroup=new N4,this.flyLineArcGroup.userData.flyLineArray=[],this.earthGroup.add(this.flyLineArcGroup),this.options.data.forEach(e=>{e.endArray.forEach(t=>{const n=KD(this.options.earth.radius,e.startArray.E,e.startArray.N,t.E,t.N,this.options.flyLine);this.flyLineArcGroup.add(n),this.flyLineArcGroup.userData.flyLineArray.push(n.userData.flyLine)})})}show(){qs.to(this.group.scale,{x:1,y:1,z:1,duration:2,ease:"Quadratic"})}render(){var e,t;(t=(e=this.flyLineArcGroup)==null?void 0:e.userData.flyLineArray)==null||t.forEach(n=>{n.rotation.z+=this.options.flyLine.speed,n.rotation.z>=n.flyEndAngle&&(n.rotation.z=0)}),this.isRotation&&(this.earthGroup.rotation.y+=this.options.earth.rotateSpeed),this.circleLineList.forEach(n=>{n.rotateY(this.options.satellite.rotateSpeed)}),this.uniforms.time.value=this.uniforms.time.value<-this.timeValue?this.timeValue:this.uniforms.time.value-1,this.waveMeshArr.length&&this.waveMeshArr.forEach(n=>{n.userData.scale+=.007,n.scale.set(n.userData.size*n.userData.scale,n.userData.size*n.userData.scale,n.userData.size*n.userData.scale),n.userData.scale<=1.5?n.material.opacity=(n.userData.scale-1)*2:n.userData.scale>1.5&&n.userData.scale<=2?n.material.opacity=1-(n.userData.scale-1.5)*2:n.userData.scale=1})}}class sI{constructor(e){X(this,"basic");X(this,"scene");X(this,"camera");X(this,"renderer");X(this,"controls");X(this,"material");X(this,"resources");X(this,"option");X(this,"earth");this.option=e,this.basic=new mE(e.dom),this.scene=this.basic.scene,this.renderer=this.basic.renderer,this.controls=this.basic.controls,this.camera=this.basic.camera,this.updateSize(),this.resources=new zE(()=>P2(this,null,function*(){yield this.createEarth(),this.render()}))}createEarth(e){return P2(this,null,function*(){this.earth=new rI({data:e||this.option.data,dom:this.option.dom,textures:this.resources.textures,earth:{radius:50,rotateSpeed:.002,isRotation:!0},satellite:{show:!0,rotateSpeed:-.01,size:1,number:2},punctuation:{circleColor:3707647,lightColumn:{startColor:14942335,endColor:16777215}},flyLine:{color:15969910,flyLineColor:16742164,speed:.004}}),this.scene.add(this.earth.group),yield this.earth.init()})}render(){requestAnimationFrame(this.render.bind(this)),this.renderer.render(this.scene,this.camera),this.controls&&this.controls.update(),this.earth&&this.earth.render()}updateSize(e,t){let n=e||this.option.width,i=t||this.option.height;n<i?i=n:n=i,this.renderer.setSize(n,i),this.camera.aspect=n/i,this.camera.updateProjectionMatrix()}updateData(e){this.earth.group&&(this.scene.remove(this.earth.group),this.earth.group.traverse(t=>{t.type==="Mesh"&&(t.geometry.dispose(),t.material.dispose())}),this.createEarth(e))}}const lI=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){const e=o,t=K1(),n=Z1(),{w:i,h:a}=A1(e.chartConfig.attr);let r;const s=()=>{const c=n.value;c&&(r=new sI({dom:c,data:e.chartConfig.option.dataset,width:i.value,height:a.value}))},u=c=>{try{r.updateData(c)}catch(f){console.log(f)}};return N1(()=>[i.value,a.value],U5(([c],[f])=>{r.updateSize(c,f)},100)),N1(()=>e.chartConfig.option.dataset,c=>{u(c)},{deep:!1}),v0(()=>{try{if(navigator.userAgent.indexOf("Chrome")<-1||navigator.userAgent.indexOf("Edg")<-1){window.$message.error("三维地图组件仅在【谷歌】浏览器上能正常展示！"),t.removeComponentList(void 0,!1);return}s()}catch(c){console.log(c)}}),d2(e.chartConfig,K1,u),(c,f)=>(R(),e1("div",{ref_key:"chartRef",ref:n},null,512))}}),uI=Object.freeze(Object.defineProperty({__proto__:null,default:lI},Symbol.toStringTag,{value:"Module"})),A5=/^[a-z0-9]+(-[a-z0-9]+)*$/,h6=(o,e,t,n="")=>{const i=o.split(":");if(o.slice(0,1)==="@"){if(i.length<2||i.length>3)return null;n=i.shift().slice(1)}if(i.length>3||!i.length)return null;if(i.length>1){const s=i.pop(),u=i.pop(),c={provider:i.length>0?i[0]:n,prefix:u,name:s};return e&&!U8(c)?null:c}const a=i[0],r=a.split("-");if(r.length>1){const s={provider:n,prefix:r.shift(),name:r.join("-")};return e&&!U8(s)?null:s}if(t&&n===""){const s={provider:n,prefix:"",name:a};return e&&!U8(s,t)?null:s}return null},U8=(o,e)=>o?!!((o.provider===""||o.provider.match(A5))&&(e&&o.prefix===""||o.prefix.match(A5))&&o.name.match(A5)):!1,Xs=Object.freeze({left:0,top:0,width:16,height:16}),t6=Object.freeze({rotate:0,vFlip:!1,hFlip:!1}),m6=Object.freeze(B1(B1({},Xs),t6)),Ze=Object.freeze(A2(B1({},m6),{body:"",hidden:!1}));function cI(o,e){const t={};!o.hFlip!=!e.hFlip&&(t.hFlip=!0),!o.vFlip!=!e.vFlip&&(t.vFlip=!0);const n=((o.rotate||0)+(e.rotate||0))%4;return n&&(t.rotate=n),t}function ta(o,e){const t=cI(o,e);for(const n in Ze)n in t6?n in o&&!(n in t)&&(t[n]=t6[n]):n in e?t[n]=e[n]:n in o&&(t[n]=o[n]);return t}function dI(o,e){const t=o.icons,n=o.aliases||Object.create(null),i=Object.create(null);function a(r){if(t[r])return i[r]=[];if(!(r in i)){i[r]=null;const s=n[r]&&n[r].parent,u=s&&a(s);u&&(i[r]=[s].concat(u))}return i[r]}return(e||Object.keys(t).concat(Object.keys(n))).forEach(a),i}function fI(o,e,t){const n=o.icons,i=o.aliases||Object.create(null);let a={};function r(s){a=ta(n[s]||i[s],a)}return r(e),t.forEach(r),ta(o,a)}function Qs(o,e){const t=[];if(typeof o!="object"||typeof o.icons!="object")return t;o.not_found instanceof Array&&o.not_found.forEach(i=>{e(i,null),t.push(i)});const n=dI(o);for(const i in n){const a=n[i];a&&(e(i,fI(o,i,a)),t.push(i))}return t}const pI=B1({provider:"",aliases:{},not_found:{}},Xs);function S7(o,e){for(const t in e)if(t in o&&typeof o[t]!=typeof e[t])return!1;return!0}function Ks(o){if(typeof o!="object"||o===null)return null;const e=o;if(typeof e.prefix!="string"||!o.icons||typeof o.icons!="object"||!S7(o,pI))return null;const t=e.icons;for(const i in t){const a=t[i];if(!i.match(A5)||typeof a.body!="string"||!S7(a,Ze))return null}const n=e.aliases||Object.create(null);for(const i in n){const a=n[i],r=a.parent;if(!i.match(A5)||typeof r!="string"||!t[r]&&!n[r]||!S7(a,Ze))return null}return e}const na=Object.create(null);function hI(o,e){return{provider:o,prefix:e,icons:Object.create(null),missing:new Set}}function Y0(o,e){const t=na[o]||(na[o]=Object.create(null));return t[e]||(t[e]=hI(o,e))}function zn(o,e){return Ks(e)?Qs(e,(t,n)=>{n?o.icons[t]=n:o.missing.add(t)}):[]}function mI(o,e,t){try{if(typeof t.body=="string")return o.icons[e]=B1({},t),!0}catch(n){}return!1}let k5=!1;function el(o){return typeof o=="boolean"&&(k5=o),k5}function gI(o){const e=typeof o=="string"?h6(o,!0,k5):o;if(e){const t=Y0(e.provider,e.prefix),n=e.name;return t.icons[n]||(t.missing.has(n)?null:void 0)}}function vI(o,e){const t=h6(o,!0,k5);if(!t)return!1;const n=Y0(t.provider,t.prefix);return mI(n,t.name,e)}function _I(o,e){if(typeof o!="object")return!1;if(typeof e!="string"&&(e=o.provider||""),k5&&!e&&!o.prefix){let i=!1;return Ks(o)&&(o.prefix="",Qs(o,(a,r)=>{r&&vI(a,r)&&(i=!0)})),i}const t=o.prefix;if(!U8({provider:e,prefix:t,name:"a"}))return!1;const n=Y0(e,t);return!!zn(n,o)}const tl=Object.freeze({width:null,height:null}),nl=Object.freeze(B1(B1({},tl),t6)),yI=/(-?[0-9.]*[0-9]+[0-9.]*)/g,bI=/^-?[0-9.]*[0-9]+[0-9.]*$/g;function ia(o,e,t){if(e===1)return o;if(t=t||100,typeof o=="number")return Math.ceil(o*e*t)/t;if(typeof o!="string")return o;const n=o.split(yI);if(n===null||!n.length)return o;const i=[];let a=n.shift(),r=bI.test(a);for(;;){if(r){const s=parseFloat(a);isNaN(s)?i.push(a):i.push(Math.ceil(s*e*t)/t)}else i.push(a);if(a=n.shift(),a===void 0)return i.join("");r=!r}}const xI=o=>o==="unset"||o==="undefined"||o==="none";function SI(o,e){const t=B1(B1({},m6),o),n=B1(B1({},nl),e),i={left:t.left,top:t.top,width:t.width,height:t.height};let a=t.body;[t,n].forEach(y=>{const _=[],v=y.hFlip,b=y.vFlip;let x=y.rotate;v?b?x+=2:(_.push("translate("+(i.width+i.left).toString()+" "+(0-i.top).toString()+")"),_.push("scale(-1 1)"),i.top=i.left=0):b&&(_.push("translate("+(0-i.left).toString()+" "+(i.height+i.top).toString()+")"),_.push("scale(1 -1)"),i.top=i.left=0);let A;switch(x<0&&(x-=Math.floor(x/4)*4),x=x%4,x){case 1:A=i.height/2+i.top,_.unshift("rotate(90 "+A.toString()+" "+A.toString()+")");break;case 2:_.unshift("rotate(180 "+(i.width/2+i.left).toString()+" "+(i.height/2+i.top).toString()+")");break;case 3:A=i.width/2+i.left,_.unshift("rotate(-90 "+A.toString()+" "+A.toString()+")");break}x%2===1&&(i.left!==i.top&&(A=i.left,i.left=i.top,i.top=A),i.width!==i.height&&(A=i.width,i.width=i.height,i.height=A)),_.length&&(a='<g transform="'+_.join(" ")+'">'+a+"</g>")});const r=n.width,s=n.height,u=i.width,c=i.height;let f,p;r===null?(p=s===null?"1em":s==="auto"?c:s,f=ia(p,u/c)):(f=r==="auto"?u:r,p=s===null?ia(f,c/u):s==="auto"?c:s);const h={},m=(y,_)=>{xI(_)||(h[y]=_.toString())};return m("width",f),m("height",p),h.viewBox=i.left.toString()+" "+i.top.toString()+" "+u.toString()+" "+c.toString(),{attributes:h,body:a}}const MI=/\sid="(\S+)"/g,AI="IconifyId"+Date.now().toString(16)+(Math.random()*16777216|0).toString(16);let wI=0;function CI(o,e=AI){const t=[];let n;for(;n=MI.exec(o);)t.push(n[1]);if(!t.length)return o;const i="suffix"+(Math.random()*16777216|Date.now()).toString(16);return t.forEach(a=>{const r=typeof e=="function"?e(a):e+(wI++).toString(),s=a.replace(/[.*+?^${}()|[\]\\]/g,"\\$&");o=o.replace(new RegExp('([#;"])('+s+')([")]|\\.[a-z])',"g"),"$1"+r+i+"$3")}),o=o.replace(new RegExp(i,"g"),""),o}const qe=Object.create(null);function TI(o,e){qe[o]=e}function Xe(o){return qe[o]||qe[""]}function kn(o){let e;if(typeof o.resources=="string")e=[o.resources];else if(e=o.resources,!(e instanceof Array)||!e.length)return null;return{resources:e,path:o.path||"/",maxURL:o.maxURL||500,rotate:o.rotate||750,timeout:o.timeout||5e3,random:o.random===!0,index:o.index||0,dataAfterTimeout:o.dataAfterTimeout!==!1}}const Nn=Object.create(null),c5=["https://api.simplesvg.com","https://api.unisvg.com"],F8=[];for(;c5.length>0;)c5.length===1||Math.random()>.5?F8.push(c5.shift()):F8.push(c5.pop());Nn[""]=kn({resources:["https://api.iconify.design"].concat(F8)});function EI(o,e){const t=kn(e);return t===null?!1:(Nn[o]=t,!0)}function Un(o){return Nn[o]}const DI=()=>{let o;try{if(o=fetch,typeof o=="function")return o}catch(e){}};let oa=DI();function II(o,e){const t=Un(o);if(!t)return 0;let n;if(!t.maxURL)n=0;else{let i=0;t.resources.forEach(r=>{i=Math.max(i,r.length)});const a=e+".json?icons=";n=t.maxURL-i-t.path.length-a.length}return n}function RI(o){return o===404}const OI=(o,e,t)=>{const n=[],i=II(o,e),a="icons";let r={type:a,provider:o,prefix:e,icons:[]},s=0;return t.forEach((u,c)=>{s+=u.length+1,s>=i&&c>0&&(n.push(r),r={type:a,provider:o,prefix:e,icons:[]},s=u.length),r.icons.push(u)}),n.push(r),n};function PI(o){if(typeof o=="string"){const e=Un(o);if(e)return e.path}return"/"}const LI=(o,e,t)=>{if(!oa){t("abort",424);return}let n=PI(e.provider);switch(e.type){case"icons":{const a=e.prefix,s=e.icons.join(","),u=new URLSearchParams({icons:s});n+=a+".json?"+u.toString();break}case"custom":{const a=e.uri;n+=a.slice(0,1)==="/"?a.slice(1):a;break}default:t("abort",400);return}let i=503;oa(o+n).then(a=>{const r=a.status;if(r!==200){setTimeout(()=>{t(RI(r)?"abort":"next",r)});return}return i=501,a.json()}).then(a=>{if(typeof a!="object"||a===null){setTimeout(()=>{a===404?t("abort",a):t("next",i)});return}setTimeout(()=>{t("success",a)})}).catch(()=>{t("next",i)})},zI={prepare:OI,send:LI};function kI(o){const e={loaded:[],missing:[],pending:[]},t=Object.create(null);o.sort((i,a)=>i.provider!==a.provider?i.provider.localeCompare(a.provider):i.prefix!==a.prefix?i.prefix.localeCompare(a.prefix):i.name.localeCompare(a.name));let n={provider:"",prefix:"",name:""};return o.forEach(i=>{if(n.name===i.name&&n.prefix===i.prefix&&n.provider===i.provider)return;n=i;const a=i.provider,r=i.prefix,s=i.name,u=t[a]||(t[a]=Object.create(null)),c=u[r]||(u[r]=Y0(a,r));let f;s in c.icons?f=e.loaded:r===""||c.missing.has(s)?f=e.missing:f=e.pending;const p={provider:a,prefix:r,name:s};f.push(p)}),e}function il(o,e){o.forEach(t=>{const n=t.loaderCallbacks;n&&(t.loaderCallbacks=n.filter(i=>i.id!==e))})}function NI(o){o.pendingCallbacksFlag||(o.pendingCallbacksFlag=!0,setTimeout(()=>{o.pendingCallbacksFlag=!1;const e=o.loaderCallbacks?o.loaderCallbacks.slice(0):[];if(!e.length)return;let t=!1;const n=o.provider,i=o.prefix;e.forEach(a=>{const r=a.icons,s=r.pending.length;r.pending=r.pending.filter(u=>{if(u.prefix!==i)return!0;const c=u.name;if(o.icons[c])r.loaded.push({provider:n,prefix:i,name:c});else if(o.missing.has(c))r.missing.push({provider:n,prefix:i,name:c});else return t=!0,!0;return!1}),r.pending.length!==s&&(t||il([o],a.id),a.callback(r.loaded.slice(0),r.missing.slice(0),r.pending.slice(0),a.abort))})}))}let UI=0;function FI(o,e,t){const n=UI++,i=il.bind(null,t,n);if(!e.pending.length)return i;const a={id:n,icons:e,callback:o,abort:i};return t.forEach(r=>{(r.loaderCallbacks||(r.loaderCallbacks=[])).push(a)}),i}function BI(o,e=!0,t=!1){const n=[];return o.forEach(i=>{const a=typeof i=="string"?h6(i,e,t):i;a&&n.push(a)}),n}var GI={resources:[],index:0,timeout:2e3,rotate:750,random:!1,dataAfterTimeout:!1};function VI(o,e,t,n){const i=o.resources.length,a=o.random?Math.floor(Math.random()*i):o.index;let r;if(o.random){let M=o.resources.slice(0);for(r=[];M.length>1;){const D=Math.floor(Math.random()*M.length);r.push(M[D]),M=M.slice(0,D).concat(M.slice(D+1))}r=r.concat(M)}else r=o.resources.slice(a).concat(o.resources.slice(0,a));const s=Date.now();let u="pending",c=0,f,p=null,h=[],m=[];typeof n=="function"&&m.push(n);function y(){p&&(clearTimeout(p),p=null)}function _(){u==="pending"&&(u="aborted"),y(),h.forEach(M=>{M.status==="pending"&&(M.status="aborted")}),h=[]}function v(M,D){D&&(m=[]),typeof M=="function"&&m.push(M)}function b(){return{startTime:s,payload:e,status:u,queriesSent:c,queriesPending:h.length,subscribe:v,abort:_}}function x(){u="failed",m.forEach(M=>{M(void 0,f)})}function A(){h.forEach(M=>{M.status==="pending"&&(M.status="aborted")}),h=[]}function S(M,D,T){const E=D!=="success";switch(h=h.filter(U=>U!==M),u){case"pending":break;case"failed":if(E||!o.dataAfterTimeout)return;break;default:return}if(D==="abort"){f=T,x();return}if(E){f=T,h.length||(r.length?w():x());return}if(y(),A(),!o.random){const U=o.resources.indexOf(M.resource);U!==-1&&U!==o.index&&(o.index=U)}u="completed",m.forEach(U=>{U(T)})}function w(){if(u!=="pending")return;y();const M=r.shift();if(M===void 0){if(h.length){p=setTimeout(()=>{y(),u==="pending"&&(A(),x())},o.timeout);return}x();return}const D={status:"pending",resource:M,callback:(T,E)=>{S(D,T,E)}};h.push(D),c++,p=setTimeout(w,o.rotate),t(M,e,D.callback)}return setTimeout(w),b}function ol(o){const e=B1(B1({},GI),o);let t=[];function n(){t=t.filter(s=>s().status==="pending")}function i(s,u,c){const f=VI(e,s,u,(p,h)=>{n(),c&&c(p,h)});return t.push(f),f}function a(s){return t.find(u=>s(u))||null}return{query:i,find:a,setIndex:s=>{e.index=s},getIndex:()=>e.index,cleanup:n}}function aa(){}const M7=Object.create(null);function jI(o){if(!M7[o]){const e=Un(o);if(!e)return;const t=ol(e),n={config:e,redundancy:t};M7[o]=n}return M7[o]}function WI(o,e,t){let n,i;if(typeof o=="string"){const a=Xe(o);if(!a)return t(void 0,424),aa;i=a.send;const r=jI(o);r&&(n=r.redundancy)}else{const a=kn(o);if(a){n=ol(a);const r=o.resources?o.resources[0]:"",s=Xe(r);s&&(i=s.send)}}return!n||!i?(t(void 0,424),aa):n.query(e,i,t)().abort}const ra="iconify2",N5="iconify",al=N5+"-count",sa=N5+"-version",rl=36e5,HI=168;function Qe(o,e){try{return o.getItem(e)}catch(t){}}function Fn(o,e,t){try{return o.setItem(e,t),!0}catch(n){}}function la(o,e){try{o.removeItem(e)}catch(t){}}function Ke(o,e){return Fn(o,al,e.toString())}function et(o){return parseInt(Qe(o,al))||0}const g6={local:!0,session:!0},sl={local:new Set,session:new Set};let Bn=!1;function $I(o){Bn=o}let I8=typeof window=="undefined"?{}:window;function ll(o){const e=o+"Storage";try{if(I8&&I8[e]&&typeof I8[e].length=="number")return I8[e]}catch(t){}g6[o]=!1}function ul(o,e){const t=ll(o);if(!t)return;const n=Qe(t,sa);if(n!==ra){if(n){const s=et(t);for(let u=0;u<s;u++)la(t,N5+u.toString())}Fn(t,sa,ra),Ke(t,0);return}const i=Math.floor(Date.now()/rl)-HI,a=s=>{const u=N5+s.toString(),c=Qe(t,u);if(typeof c=="string"){try{const f=JSON.parse(c);if(typeof f=="object"&&typeof f.cached=="number"&&f.cached>i&&typeof f.provider=="string"&&typeof f.data=="object"&&typeof f.data.prefix=="string"&&e(f,s))return!0}catch(f){}la(t,u)}};let r=et(t);for(let s=r-1;s>=0;s--)a(s)||(s===r-1?(r--,Ke(t,r)):sl[o].add(s))}function cl(){if(!Bn){$I(!0);for(const o in g6)ul(o,e=>{const t=e.data,n=e.provider,i=t.prefix,a=Y0(n,i);if(!zn(a,t).length)return!1;const r=t.lastModified||-1;return a.lastModifiedCached=a.lastModifiedCached?Math.min(a.lastModifiedCached,r):r,!0})}}function YI(o,e){const t=o.lastModifiedCached;if(t&&t>=e)return t===e;if(o.lastModifiedCached=e,t)for(const n in g6)ul(n,i=>{const a=i.data;return i.provider!==o.provider||a.prefix!==o.prefix||a.lastModified===e});return!0}function JI(o,e){Bn||cl();function t(n){let i;if(!g6[n]||!(i=ll(n)))return;const a=sl[n];let r;if(a.size)a.delete(r=Array.from(a).shift());else if(r=et(i),!Ke(i,r+1))return;const s={cached:Math.floor(Date.now()/rl),provider:o.provider,data:e};return Fn(i,N5+r.toString(),JSON.stringify(s))}e.lastModified&&!YI(o,e.lastModified)||Object.keys(e.icons).length&&(e.not_found&&(e=Object.assign({},e),delete e.not_found),t("local")||t("session"))}function ua(){}function ZI(o){o.iconsLoaderFlag||(o.iconsLoaderFlag=!0,setTimeout(()=>{o.iconsLoaderFlag=!1,NI(o)}))}function qI(o,e){o.iconsToLoad?o.iconsToLoad=o.iconsToLoad.concat(e).sort():o.iconsToLoad=e,o.iconsQueueFlag||(o.iconsQueueFlag=!0,setTimeout(()=>{o.iconsQueueFlag=!1;const{provider:t,prefix:n}=o,i=o.iconsToLoad;delete o.iconsToLoad;let a;if(!i||!(a=Xe(t)))return;a.prepare(t,n,i).forEach(s=>{WI(t,s,u=>{if(typeof u!="object")s.icons.forEach(c=>{o.missing.add(c)});else try{const c=zn(o,u);if(!c.length)return;const f=o.pendingIcons;f&&c.forEach(p=>{f.delete(p)}),JI(o,u)}catch(c){console.error(c)}ZI(o)})})}))}const XI=(o,e)=>{const t=BI(o,!0,el()),n=kI(t);if(!n.pending.length){let u=!0;return e&&setTimeout(()=>{u&&e(n.loaded,n.missing,n.pending,ua)}),()=>{u=!1}}const i=Object.create(null),a=[];let r,s;return n.pending.forEach(u=>{const{provider:c,prefix:f}=u;if(f===s&&c===r)return;r=c,s=f,a.push(Y0(c,f));const p=i[c]||(i[c]=Object.create(null));p[f]||(p[f]=[])}),n.pending.forEach(u=>{const{provider:c,prefix:f,name:p}=u,h=Y0(c,f),m=h.pendingIcons||(h.pendingIcons=new Set);m.has(p)||(m.add(p),i[c][f].push(p))}),a.forEach(u=>{const{provider:c,prefix:f}=u;i[c][f].length&&qI(u,i[c][f])}),e?FI(e,n,a):ua};function QI(o,e){const t=B1({},o);for(const n in e){const i=e[n],a=typeof i;n in tl?(i===null||i&&(a==="string"||a==="number"))&&(t[n]=i):a===typeof t[n]&&(t[n]=n==="rotate"?i%4:i)}return t}const KI=/[\s,]+/;function eR(o,e){e.split(KI).forEach(t=>{switch(t.trim()){case"horizontal":o.hFlip=!0;break;case"vertical":o.vFlip=!0;break}})}function tR(o,e=0){const t=o.replace(/^-?[0-9.]*/,"");function n(i){for(;i<0;)i+=4;return i%4}if(t===""){const i=parseInt(o);return isNaN(i)?0:n(i)}else if(t!==o){let i=0;switch(t){case"%":i=25;break;case"deg":i=90}if(i){let a=parseFloat(o.slice(0,o.length-t.length));return isNaN(a)?0:(a=a/i,a%1===0?n(a):0)}}return e}function nR(o,e){let t=o.indexOf("xlink:")===-1?"":' xmlns:xlink="http://www.w3.org/1999/xlink"';for(const n in e)t+=" "+n+'="'+e[n]+'"';return'<svg xmlns="http://www.w3.org/2000/svg"'+t+">"+o+"</svg>"}function iR(o){return o.replace(/"/g,"'").replace(/%/g,"%25").replace(/#/g,"%23").replace(/</g,"%3C").replace(/>/g,"%3E").replace(/\s+/g," ")}function oR(o){return"data:image/svg+xml,"+iR(o)}function aR(o){return'url("'+oR(o)+'")'}const ca=A2(B1({},nl),{inline:!1}),rR={xmlns:"http://www.w3.org/2000/svg","xmlns:xlink":"http://www.w3.org/1999/xlink","aria-hidden":!0,role:"img"},sR={display:"inline-block"},tt={backgroundColor:"currentColor"},dl={backgroundColor:"transparent"},da={Image:"var(--svg)",Repeat:"no-repeat",Size:"100% 100%"},fa={webkitMask:tt,mask:tt,background:dl};for(const o in fa){const e=fa[o];for(const t in da)e[o+t]=da[t]}const B8={};["horizontal","vertical"].forEach(o=>{const e=o.slice(0,1)+"Flip";B8[o+"-flip"]=e,B8[o.slice(0,1)+"-flip"]=e,B8[o+"Flip"]=e});function pa(o){return o+(o.match(/^[-0-9.]+$/)?"px":"")}const ha=(o,e)=>{const t=QI(ca,e),n=B1({},rR),i=e.mode||"svg",a={},r=e.style,s=typeof r=="object"&&!(r instanceof Array)?r:{};for(let _ in e){const v=e[_];if(v!==void 0)switch(_){case"icon":case"style":case"onLoad":case"mode":break;case"inline":case"hFlip":case"vFlip":t[_]=v===!0||v==="true"||v===1;break;case"flip":typeof v=="string"&&eR(t,v);break;case"color":a.color=v;break;case"rotate":typeof v=="string"?t[_]=tR(v):typeof v=="number"&&(t[_]=v);break;case"ariaHidden":case"aria-hidden":v!==!0&&v!=="true"&&delete n["aria-hidden"];break;default:{const b=B8[_];b?(v===!0||v==="true"||v===1)&&(t[b]=!0):ca[_]===void 0&&(n[_]=v)}}}const u=SI(o,t),c=u.attributes;if(t.inline&&(a.verticalAlign="-0.125em"),i==="svg"){n.style=B1(B1({},a),s),Object.assign(n,c);let _=0,v=e.id;return typeof v=="string"&&(v=v.replace(/-/g,"_")),n.innerHTML=CI(u.body,v?()=>v+"ID"+_++:"iconifyVue"),ni("svg",n)}const{body:f,width:p,height:h}=o,m=i==="mask"||(i==="bg"?!1:f.indexOf("currentColor")!==-1),y=nR(f,A2(B1({},c),{width:p+"",height:h+""}));return n.style=B1(B1(B1(A2(B1({},a),{"--svg":aR(y),width:pa(c.width),height:pa(c.height)}),sR),m?tt:dl),s),ni("span",n)};el(!0);TI("",zI);if(typeof document!="undefined"&&typeof window!="undefined"){cl();const o=window;if(o.IconifyPreload!==void 0){const e=o.IconifyPreload,t="Invalid IconifyPreload syntax.";typeof e=="object"&&e!==null&&(e instanceof Array?e:[e]).forEach(n=>{try{(typeof n!="object"||n===null||n instanceof Array||typeof n.icons!="object"||typeof n.prefix!="string"||!_I(n))&&console.error(t)}catch(i){console.error(t)}})}if(o.IconifyProviders!==void 0){const e=o.IconifyProviders;if(typeof e=="object"&&e!==null)for(let t in e){const n="IconifyProviders["+t+"] is invalid.";try{const i=e[t];if(typeof i!="object"||!i||i.resources===void 0)continue;EI(t,i)||console.error(n)}catch(i){console.error(n)}}}}const lR=A2(B1({},m6),{body:""}),uR=s1({inheritAttrs:!1,data(){return{iconMounted:!1,counter:0}},mounted(){this._name="",this._loadingIcon=null,this.iconMounted=!0},unmounted(){this.abortLoading()},methods:{abortLoading(){this._loadingIcon&&(this._loadingIcon.abort(),this._loadingIcon=null)},getIcon(o,e){if(typeof o=="object"&&o!==null&&typeof o.body=="string")return this._name="",this.abortLoading(),{data:o};let t;if(typeof o!="string"||(t=h6(o,!1,!0))===null)return this.abortLoading(),null;const n=gI(t);if(!n)return(!this._loadingIcon||this._loadingIcon.name!==o)&&(this.abortLoading(),this._name="",n!==null&&(this._loadingIcon={name:o,abort:XI([t],()=>{this.counter++})})),null;this.abortLoading(),this._name!==o&&(this._name=o,e&&e(o));const i=["iconify"];return t.prefix!==""&&i.push("iconify--"+t.prefix),t.provider!==""&&i.push("iconify--"+t.provider),{data:n,classes:i}}},render(){this.counter;const o=this.$attrs,e=this.iconMounted?this.getIcon(o.icon,o.onLoad):null;if(!e)return ha(lR,o);let t=o;return e.classes&&(t=A2(B1({},o),{class:(typeof o.class=="string"?o.class+" ":"")+e.classes.join(" ")})),ha(B1(B1({},m6),e.data),t)}}),cR={class:"go-icon-box"},dR=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){a4(u=>({"41d5a0df":`${l(t)}px`,41024050:`${l(n)}px`}));const e=o,{w:t,h:n}=A1(e.chartConfig.attr),{dataset:i,color:a,size:r,rotate:s}=A1(e.chartConfig.option);return(u,c)=>(R(),e1("div",cR,[d(l(uR),{icon:l(i)||"",color:l(a),width:l(r),rotate:l(s)},null,8,["icon","color","width","rotate"])]))}});const fR=u2(dR,[["__scopeId","data-v-449236d6"]]),pR=Object.freeze(Object.defineProperty({__proto__:null,default:fR},Symbol.toStringTag,{value:"Module"}));var fl={exports:{}};(function(o,e){(function(t,n){o.exports=n()})(o6,function(){var t="month",n="quarter";return function(i,a){var r=a.prototype;r.quarter=function(c){return this.$utils().u(c)?Math.ceil((this.month()+1)/3):this.month(this.month()%3+3*(c-1))};var s=r.add;r.add=function(c,f){return c=Number(c),this.$utils().p(f)===n?this.add(3*c,t):s.bind(this)(c,f)};var u=r.startOf;r.startOf=function(c,f){var p=this.$utils(),h=!!p.u(f)||f;if(p.p(c)===n){var m=this.quarter()-1;return h?this.month(3*m).startOf(t).startOf("day"):this.month(3*m+2).endOf(t).endOf("day")}return u.bind(this)(c,f)}}})})(fl);var hR=fl.exports;const mR=a6(hR),gR=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){a4(u=>({"22ea0f84":l(n)+"px"}));const e=o,{w:t,h:n}=A1(e.chartConfig.attr);Z1();const i=j3({dataset:e.chartConfig.option.dataset}),a=X1(()=>e.chartConfig.option.componentInteractEventKey.endsWith("range")),r=u=>{if(a.value){let c=null,f=null,p=null;u instanceof Array&&(c=u[0],f=u[1],p=`${u[0]}-${u[1]}`),F9(e.chartConfig,K1,{[d5.DATE_START]:c,[d5.DATE_END]:f,[d5.DATE_RANGE]:p},p4.CHANGE)}else F9(e.chartConfig,K1,{[d5.DATE]:u},p4.CHANGE)},s=(u,c)=>{switch(_5.extend(mR),u){case r3.DATE:case r3.DATE_RANGE:c=c.startOf("day");break;case r3.MONTH:case r3.MONTH_RANGE:c=c.startOf("month");break;case r3.YEAR:case r3.YEAR_RANGE:c=c.startOf("year");break;case r3.QUARTER:case r3.QUARTER_RANGE:c=c.startOf("quarter");break}return c};return N1(()=>({type:e.chartConfig.option.componentInteractEventKey,defaultType:e.chartConfig.option.defaultType,differValue:e.chartConfig.option.differValue,differUnit:e.chartConfig.option.differUnit,dataset:e.chartConfig.option.dataset}),(u,c)=>{const f=u.type!==(c==null?void 0:c.type),p=u.defaultType!==(c==null?void 0:c.defaultType),h=u.differValue!==(c==null?void 0:c.differValue),m=u.differUnit!==(c==null?void 0:c.differUnit);if(f||p||h||m){if(u.defaultType===L4.NONE)e.chartConfig.option.dataset=null;else if(u.defaultType===L4.DYNAMIC){let y=_5();a.value?e.chartConfig.option.dataset=[s(u.type,y.add(u.differValue[0],u.differUnit[0])).valueOf(),s(u.type,y.add(u.differValue[1],u.differUnit[1])).valueOf()]:e.chartConfig.option.dataset=s(u.type,y.add(u.differValue[0],u.differUnit[0])).valueOf()}}i.dataset=e.chartConfig.option.dataset,r(i.dataset)},{immediate:!0}),(u,c)=>{const f=L("n-date-picker");return R(),p1(f,{value:l(i).dataset,"onUpdate:value":[c[0]||(c[0]=p=>l(i).dataset=p),r],clearable:"",panel:!!o.chartConfig.option.isPanel,type:o.chartConfig.option.componentInteractEventKey,style:f2(`width:${l(t)}px;`)},null,8,["value","panel","type","style"])}}});const vR=u2(gR,[["__scopeId","data-v-62617a46"]]),_R=Object.freeze(Object.defineProperty({__proto__:null,default:vR},Symbol.toStringTag,{value:"Module"}));var yR=(o=>(o.DATA="data",o))(yR||{}),pl=(o=>(o.DATA="data",o))(pl||{});const _N=[{interactType:p4.CHANGE,interactName:"选择完成",componentEmitEvents:{data:[{value:"data",label:"选择项"}]}}],bR=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){const e=o,{w:t,h:n}=A1(e.chartConfig.attr),i=j3({value:{inputValue:e.chartConfig.option.inputValue,dataset:e.chartConfig.option.dataset}}),a=r=>{r!=null&&F9(e.chartConfig,K1,{[pl.DATA]:r},p4.CHANGE)};return N1(()=>e.chartConfig.option,r=>{i.value=r,a(r.inputValue)},{immediate:!0,deep:!0}),(r,s)=>{const u=L("n-input");return R(),e1("div",null,[d(u,{style:f2(`width:${l(t)}px;`),type:"text",value:l(i).value.dataset,"onUpdate:value":s[0]||(s[0]=c=>l(i).value.dataset=c),placeholder:"请输入",onChange:a},null,8,["style","value"])])}}}),xR=Object.freeze(Object.defineProperty({__proto__:null,default:bR},Symbol.toStringTag,{value:"Module"}));var SR=(o=>(o.DATA="data",o))(SR||{}),nt=(o=>(o.DATA="data",o.DATA2="data2",o))(nt||{});const yN=[{interactType:p4.CHANGE,interactName:"选择完成",componentEmitEvents:{data:[{value:"data",label:"页数"},{value:"data2",label:"每页条数"}]}}],MR=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){const e=o,{w:t,h:n}=A1(e.chartConfig.attr),i=j3({value:{pageValue:e.chartConfig.option.pageValue,dataset:e.chartConfig.option.dataset,sizeValue:e.chartConfig.option.sizeValue,pageSize:e.chartConfig.option.pageSize}}),a=(r,s)=>{r!=null&&F9(e.chartConfig,K1,{[nt.DATA]:r,[nt.DATA2]:s},p4.CHANGE)};return N1(()=>e.chartConfig.option,r=>{i.value=r,a(r.pageValue,r.pageSize)},{immediate:!0,deep:!0}),(r,s)=>{const u=L("n-pagination");return R(),e1("div",null,[d(u,{"onOnUpdate:page":a,style:f2(`width:${l(t)}px;`),page:l(i).value.pageValue,"onUpdate:page":s[0]||(s[0]=c=>l(i).value.pageValue=c),"page-count":l(i).value.dataset,"page-slot":7,"show-size-picker":"","page-sizes":l(i).value.sizeValue,"page-size":l(i).value.pageSize,"onUpdate:pageSize":s[1]||(s[1]=c=>l(i).value.pageSize=c)},null,8,["style","page","page-count","page-sizes","page-size"])])}}}),AR=Object.freeze(Object.defineProperty({__proto__:null,default:MR},Symbol.toStringTag,{value:"Module"}));var wR=(o=>(o.DATA="data",o))(wR||{}),hl=(o=>(o.DATA="data",o))(hl||{});const bN=[{interactType:p4.CHANGE,interactName:"选择完成",componentEmitEvents:{data:[{value:"data",label:"选择项"}]}}],CR=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){a4(r=>({"15a61728":l(n)+"px"}));const e=o,{w:t,h:n}=A1(e.chartConfig.attr),i=j3({value:{selectValue:e.chartConfig.option.selectValue,dataset:e.chartConfig.option.dataset}}),a=r=>{F9(e.chartConfig,K1,{[hl.DATA]:r},p4.CHANGE)};return N1(()=>e.chartConfig.option,r=>{i.value=r,a(r.selectValue)},{immediate:!0,deep:!0}),(r,s)=>{const u=L("n-select");return R(),p1(u,{value:l(i).value.selectValue,"onUpdate:value":[s[0]||(s[0]=c=>l(i).value.selectValue=c),a],options:l(i).value.dataset,style:f2(`width:${l(t)}px;`)},null,8,["value","options","style"])}}});const TR=u2(CR,[["__scopeId","data-v-8b33eaf1"]]),ER=Object.freeze(Object.defineProperty({__proto__:null,default:TR},Symbol.toStringTag,{value:"Module"}));var DR=(o=>(o.DATA="data",o))(DR||{}),ml=(o=>(o.DATA="data",o))(ml||{});const xN=[{interactType:p4.CHANGE,interactName:"选择完成",componentEmitEvents:{data:[{value:"data",label:"选择项"}]}}],IR=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){const e=o;A1(e.chartConfig.attr);const t=j3({value:C1(e.chartConfig.option)}),n=i=>{if(i===void 0)return;const a=t.value.dataset.find(r=>r.label===i);F9(e.chartConfig,K1,{[ml.DATA]:a.value},p4.CHANGE)};return N1(()=>e.chartConfig.option,i=>{t.value=i,n(i.tabValue)},{immediate:!0,deep:!0}),(i,a)=>{const r=L("n-tab"),s=L("n-tabs");return R(),p1(s,{type:l(t).value.tabType,"onUpdate:value":n,"default-value":l(t).value.tabLabel},{default:g(()=>[(R(!0),e1(S1,null,Y1(l(t).value.dataset,(u,c)=>(R(),p1(r,{name:u.label,key:c},{default:g(()=>[D1(D2(u.label),1)]),_:2},1032,["name"]))),128))]),_:1},8,["type","default-value"])}}}),RR=Object.freeze(Object.defineProperty({__proto__:null,default:IR},Symbol.toStringTag,{value:"Module"})),OR=["src","width","height"],PR=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){const e=o,{w:t,h:n}=A1(e.chartConfig.attr),{borderRadius:i}=A1(e.chartConfig.option),a=j3({dataset:""}),r=s=>({borderRadius:`${s}px`,overflow:"hidden"});return N1(()=>e.chartConfig.option.dataset,s=>{a.dataset=s},{immediate:!0}),d2(e.chartConfig,K1,s=>{a.dataset=s}),(s,u)=>(R(),e1("div",{style:f2(r(l(i)))},[j("iframe",{src:l(a).dataset,width:l(t),height:l(n),style:{"border-width":"0"}},null,8,OR)],4))}}),LR=Object.freeze(Object.defineProperty({__proto__:null,default:PR},Symbol.toStringTag,{value:"Module"})),zR=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){const e=o,{w:t,h:n}=A1(e.chartConfig.attr),{dataset:i,fit:a,borderRadius:r}=A1(e.chartConfig.option),s=j3({dataset:""}),u=c=>({borderRadius:`${c}px`,overflow:"hidden"});return N1(()=>e.chartConfig.option.dataset,c=>{s.dataset=c},{immediate:!0}),d2(e.chartConfig,K1,c=>{s.dataset=c}),(c,f)=>{const p=L("n-image");return R(),e1("div",{style:f2(u(l(r)))},[d(p,{"object-fit":l(a),"preview-disabled":"",src:l(s).dataset,"fallback-src":l(Ta)(),width:l(t),height:l(n),lazy:""},null,8,["object-fit","src","fallback-src","width","height"])],4)}}}),kR=Object.freeze(Object.defineProperty({__proto__:null,default:zR},Symbol.toStringTag,{value:"Module"})),it={key:"ImageCarousel",chartKey:"VImageCarousel",conKey:"VCImageCarousel",title:"轮播图",category:I3.MORE,categoryName:R3.MORE,package:T1.INFORMATIONS,chartFrame:U1.NAIVE_UI,image:"photo_carousel.png"},Gn={dataset:["https://naive-ui.oss-cn-beijing.aliyuncs.com/carousel-img/carousel1.jpeg","https://naive-ui.oss-cn-beijing.aliyuncs.com/carousel-img/carousel2.jpeg","https://naive-ui.oss-cn-beijing.aliyuncs.com/carousel-img/carousel3.jpeg"],autoplay:!0,interval:5e3,slidesPerview:1,direction:"horizontal",draggable:!0,centeredSlides:!1,effect:"slide",showDots:!0,dotType:"dot",dotPlacement:"bottom",showArrow:!1,fit:"contain"};let NR=class extends e2{constructor(){super(...arguments);X(this,"key",it.key);X(this,"chartConfig",C1(it));X(this,"option",C1(Gn))}};const UR=Object.freeze(Object.defineProperty({__proto__:null,default:NR,option:Gn},Symbol.toStringTag,{value:"Module"})),FR=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){const e=o,t=j3({dataset:Gn.dataset}),{w:n,h:i}=A1(e.chartConfig.attr),{autoplay:a,interval:r,slidesPerview:s,direction:u,draggable:c,centeredSlides:f,effect:p,dotType:h,dotPlacement:m,showArrow:y,showDots:_,fit:v}=A1(e.chartConfig.option);return N1(()=>e.chartConfig.option.dataset,b=>{t.dataset=b},{immediate:!0,deep:!1}),d2(e.chartConfig,K1,b=>{t.dataset=b}),(b,x)=>{const A=L("n-image"),S=L("n-carousel");return R(),e1("div",null,[d(S,{autoplay:l(a),interval:l(r),"centered-slides":l(f),direction:l(u),"dot-placement":l(m),"dot-type":l(h),draggable:l(c),effect:l(p),"slides-per-view":l(s),"show-arrow":l(y),"show-dots":l(_)},{default:g(()=>[(R(!0),e1(S1,null,Y1(l(t).dataset,(w,M)=>(R(),p1(A,{"preview-disabled":"",key:M,"object-fit":l(v),src:w,"fallback-src":l(Ta)(),width:l(n),height:l(i)},null,8,["object-fit","src","fallback-src","width","height"]))),128))]),_:1},8,["autoplay","interval","centered-slides","direction","dot-placement","dot-type","draggable","effect","slides-per-view","show-arrow","show-dots"])])}}}),BR=Object.freeze(Object.defineProperty({__proto__:null,default:FR},Symbol.toStringTag,{value:"Module"})),ot={key:"Video",chartKey:"VVideo",conKey:"VCVideo",title:"视频",category:I3.MORE,categoryName:R3.MORE,package:T1.INFORMATIONS,chartFrame:U1.COMMON,image:"video.png"},GR="/static/mp4/earth-1d58aa0e.mp4",Vn={dataset:GR,loop:!0,muted:!0,fit:"contain",borderRadius:10};let VR=class extends e2{constructor(){super(...arguments);X(this,"key",ot.key);X(this,"chartConfig",C1(ot));X(this,"option",C1(Vn))}};const jR=Object.freeze(Object.defineProperty({__proto__:null,default:VR,option:Vn},Symbol.toStringTag,{value:"Module"})),WR=["loop","muted","width","height","src"],HR=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){a4(r=>({"3a0578ea":l(i).fit}));const e=o,{w:t,h:n}=A1(e.chartConfig.attr);let i=j3(B1({},Vn));const a=Z1(null);return d2(e.chartConfig,K1,r=>{i=r}),N1(()=>e.chartConfig.option,r=>{if(i=r,!a.value)return;const s=a.value;s.loop=i.loop,s.muted=i.muted,s.play()},{immediate:!0,deep:!0}),(r,s)=>(R(),e1("video",{ref_key:"vVideoRef",ref:a,class:"go-video",preload:"auto",crossOrigin:"anonymous",playsinline:"",autoplay:"",loop:l(i).loop,muted:l(i).muted,width:l(t),height:l(n),src:l(i).dataset},null,8,WR))}});const $R=u2(HR,[["__scopeId","data-v-f66ad6f5"]]),YR=Object.freeze(Object.defineProperty({__proto__:null,default:$R},Symbol.toStringTag,{value:"Module"}));xa({type:"series.wordCloud",visualStyleAccessPath:"textStyle",visualStyleMapper:function(o){return{fill:o.get("color")}},visualDrawType:"fill",optionUpdated:function(){var o=this.option;o.gridSize=Math.max(Math.floor(o.gridSize),4)},getInitialData:function(o,e){var t=Sa(o.data,{coordDimensions:["value"]}),n=new Ma(t,this);return n.initData(o.data),n},defaultOption:{maskImage:null,shape:"circle",keepAspect:!1,left:"center",top:"center",width:"70%",height:"80%",sizeRange:[12,60],rotationRange:[-90,90],rotationStep:45,gridSize:8,drawOutOfBound:!1,shrinkToFit:!1,textStyle:{fontWeight:"normal"}}});Aa({type:"wordCloud",render:function(o,e,t){var n=this.group;n.removeAll();var i=o.getData(),a=o.get("gridSize");o.layoutInstance.ondraw=function(r,s,u,c){var f=i.getItemModel(u),p=f.getModel("textStyle"),h=new C7({style:R8(p),scaleX:1/c.info.mu,scaleY:1/c.info.mu,x:(c.gx+c.info.gw/2)*a,y:(c.gy+c.info.gh/2)*a,rotation:c.rot});h.setStyle({x:c.info.fillTextOffsetX,y:c.info.fillTextOffsetY+s*.5,text:r,verticalAlign:"middle",fill:i.getItemVisual(u,"style").fill,fontSize:s}),n.add(h),i.setItemGraphicEl(u,h),h.ensureState("emphasis").style=R8(f.getModel(["emphasis","textStyle"]),{state:"emphasis"}),h.ensureState("blur").style=R8(f.getModel(["blur","textStyle"]),{state:"blur"}),wa(h,f.get(["emphasis","focus"]),f.get(["emphasis","blurScope"])),h.stateTransition={duration:o.get("animation")?o.get(["stateAnimation","duration"]):0,easing:o.get(["stateAnimation","easing"])},h.__highDownDispatcher=!0},this._model=o},remove:function(){this.group.removeAll(),this._model.layoutInstance.dispose()},dispose:function(){this._model.layoutInstance.dispose()}});/*!
 * wordcloud2.js
 * http://timdream.org/wordcloud2.js/
 *
 * Copyright 2011 - 2019 Tim Guan-tin Chien and contributors.
 * Released under the MIT license
 */window.setImmediate||(window.setImmediate=function(){return window.msSetImmediate||window.webkitSetImmediate||window.mozSetImmediate||window.oSetImmediate||function(){if(!window.postMessage||!window.addEventListener)return null;var t=[void 0],n="zero-timeout-message",i=function(r){var s=t.length;return t.push(r),window.postMessage(n+s.toString(36),"*"),s};return window.addEventListener("message",function(r){if(!(typeof r.data!="string"||r.data.substr(0,n.length)!==n)){r.stopImmediatePropagation();var s=parseInt(r.data.substr(n.length),36);t[s]&&(t[s](),t[s]=void 0)}},!0),window.clearImmediate=function(r){t[r]&&(t[r]=void 0)},i}()||function(t){window.setTimeout(t,0)}}());window.clearImmediate||(window.clearImmediate=function(){return window.msClearImmediate||window.webkitClearImmediate||window.mozClearImmediate||window.oClearImmediate||function(t){window.clearTimeout(t)}}());var jn=function(){var e=document.createElement("canvas");if(!e||!e.getContext)return!1;var t=e.getContext("2d");return!(!t||!t.getImageData||!t.fillText||!Array.prototype.some||!Array.prototype.push)}(),at=function(){if(jn){for(var e=document.createElement("canvas").getContext("2d"),t=20,n,i;t;){if(e.font=t.toString(10)+"px sans-serif",e.measureText("Ｗ").width===n&&e.measureText("m").width===i)return t+1;n=e.measureText("Ｗ").width,i=e.measureText("m").width,t--}return 0}}(),JR=function(o){if(Array.isArray(o)){var e=o.slice();return e.splice(0,2),e}else return[]},ZR=function(e){for(var t,n,i=e.length;i;)t=Math.floor(Math.random()*i),n=e[--i],e[i]=e[t],e[t]=n;return e},S9={},v6=function(e,t){if(!jn)return;var n=Math.floor(Math.random()*Date.now());Array.isArray(e)||(e=[e]),e.forEach(function(a1,Q){if(typeof a1=="string"){if(e[Q]=document.getElementById(a1),!e[Q])throw new Error("The element id specified is not found.")}else if(!a1.tagName&&!a1.appendChild)throw new Error("You must pass valid HTML elements, or ID of the element.")});var i={list:[],fontFamily:'"Trebuchet MS", "Heiti TC", "微軟正黑體", "Arial Unicode MS", "Droid Fallback Sans", sans-serif',fontWeight:"normal",color:"random-dark",minSize:0,weightFactor:1,clearCanvas:!0,backgroundColor:"#fff",gridSize:8,drawOutOfBound:!1,shrinkToFit:!1,origin:null,drawMask:!1,maskColor:"rgba(255,0,0,0.3)",maskGapWidth:.3,layoutAnimation:!0,wait:0,abortThreshold:0,abort:function(){},minRotation:-Math.PI/2,maxRotation:Math.PI/2,rotationStep:.1,shuffle:!0,rotateRatio:.1,shape:"circle",ellipticity:.65,classes:null,hover:null,click:null};if(t)for(var a in t)a in i&&(i[a]=t[a]);if(typeof i.weightFactor!="function"){var r=i.weightFactor;i.weightFactor=function(Q){return Q*r}}if(typeof i.shape!="function")switch(i.shape){case"circle":default:i.shape="circle";break;case"cardioid":i.shape=function(Q){return 1-Math.sin(Q)};break;case"diamond":i.shape=function(Q){var Z=Q%(2*Math.PI/4);return 1/(Math.cos(Z)+Math.sin(Z))};break;case"square":i.shape=function(Q){return Math.min(1/Math.abs(Math.cos(Q)),1/Math.abs(Math.sin(Q)))};break;case"triangle-forward":i.shape=function(Q){var Z=Q%(2*Math.PI/3);return 1/(Math.cos(Z)+Math.sqrt(3)*Math.sin(Z))};break;case"triangle":case"triangle-upright":i.shape=function(Q){var Z=(Q+Math.PI*3/2)%(2*Math.PI/3);return 1/(Math.cos(Z)+Math.sqrt(3)*Math.sin(Z))};break;case"pentagon":i.shape=function(Q){var Z=(Q+.955)%(2*Math.PI/5);return 1/(Math.cos(Z)+.726543*Math.sin(Z))};break;case"star":i.shape=function(Q){var Z=(Q+.955)%(2*Math.PI/10);return(Q+.955)%(2*Math.PI/5)-2*Math.PI/10>=0?1/(Math.cos(2*Math.PI/10-Z)+3.07768*Math.sin(2*Math.PI/10-Z)):1/(Math.cos(Z)+3.07768*Math.sin(Z))};break}i.gridSize=Math.max(Math.floor(i.gridSize),4);var s=i.gridSize,u=s-i.maskGapWidth,c=Math.abs(i.maxRotation-i.minRotation),f=Math.min(i.maxRotation,i.minRotation),p=i.rotationStep,h,m,y,_,v,b,x;function A(a1,Q){return"hsl("+(Math.random()*360).toFixed()+","+(Math.random()*30+70).toFixed()+"%,"+(Math.random()*(Q-a1)+a1).toFixed()+"%)"}switch(i.color){case"random-dark":x=function(){return A(10,50)};break;case"random-light":x=function(){return A(50,90)};break;default:typeof i.color=="function"&&(x=i.color);break}var S;typeof i.fontWeight=="function"&&(S=i.fontWeight);var w=null;typeof i.classes=="function"&&(w=i.classes);var M=!1,D=[],T,E=function(Q){var Z=Q.currentTarget,d1=Z.getBoundingClientRect(),m1,g1;Q.touches?(m1=Q.touches[0].clientX,g1=Q.touches[0].clientY):(m1=Q.clientX,g1=Q.clientY);var c1=m1-d1.left,L1=g1-d1.top,M1=Math.floor(c1*(Z.width/d1.width||1)/s),b1=Math.floor(L1*(Z.height/d1.height||1)/s);return D[M1]?D[M1][b1]:null},U=function(Q){var Z=E(Q);if(T!==Z){if(T=Z,!Z){i.hover(void 0,void 0,Q);return}i.hover(Z.item,Z.dimension,Q)}},V=function(Q){var Z=E(Q);Z&&(i.click(Z.item,Z.dimension,Q),Q.preventDefault())},q=[],$=function(Q){if(q[Q])return q[Q];var Z=Q*8,d1=Z,m1=[];for(Q===0&&m1.push([_[0],_[1],0]);d1--;){var g1=1;i.shape!=="circle"&&(g1=i.shape(d1/Z*2*Math.PI)),m1.push([_[0]+Q*g1*Math.cos(-d1/Z*2*Math.PI),_[1]+Q*g1*Math.sin(-d1/Z*2*Math.PI)*i.ellipticity,d1/Z*2*Math.PI])}return q[Q]=m1,m1},z=function(){return i.abortThreshold>0&&new Date().getTime()-b>i.abortThreshold},F=function(){return i.rotateRatio===0||Math.random()>i.rotateRatio?0:c===0?f:f+Math.round(Math.random()*c/p)*p},G=function(Q,Z,d1,m1){var g1=i.weightFactor(Z);if(g1<=i.minSize)return!1;var c1=1;g1<at&&(c1=function(){for(var j1=2;j1*g1<at;)j1+=2;return j1}());var L1;S?L1=S(Q,Z,g1,m1):L1=i.fontWeight;var M1=document.createElement("canvas"),b1=M1.getContext("2d",{willReadFrequently:!0});b1.font=L1+" "+(g1*c1).toString(10)+"px "+i.fontFamily;var Q1=b1.measureText(Q).width/c1,J1=Math.max(g1*c1,b1.measureText("m").width,b1.measureText("Ｗ").width)/c1,$1=Q1+J1*2,n2=J1*3,s2=Math.ceil($1/s),V1=Math.ceil(n2/s);$1=s2*s,n2=V1*s;var i2=-Q1/2,W1=-J1*.4,P=Math.ceil(($1*Math.abs(Math.sin(d1))+n2*Math.abs(Math.cos(d1)))/s),I=Math.ceil(($1*Math.abs(Math.cos(d1))+n2*Math.abs(Math.sin(d1)))/s),l1=I*s,x1=P*s;M1.setAttribute("width",l1),M1.setAttribute("height",x1),b1.scale(1/c1,1/c1),b1.translate(l1*c1/2,x1*c1/2),b1.rotate(-d1),b1.font=L1+" "+(g1*c1).toString(10)+"px "+i.fontFamily,b1.fillStyle="#000",b1.textBaseline="middle",b1.fillText(Q,i2*c1,(W1+g1*.5)*c1);var w1=b1.getImageData(0,0,l1,x1).data;if(z())return!1;for(var O1=[],H1=I,H,i1,P1,z1=[P/2,I/2,P/2,I/2];H1--;)for(H=P;H--;){P1=s;e:for(;P1--;)for(i1=s;i1--;)if(w1[((H*s+P1)*l1+(H1*s+i1))*4+3]){O1.push([H1,H]),H1<z1[3]&&(z1[3]=H1),H1>z1[1]&&(z1[1]=H1),H<z1[0]&&(z1[0]=H),H>z1[2]&&(z1[2]=H);break e}}return{mu:c1,occupied:O1,bounds:z1,gw:I,gh:P,fillTextOffsetX:i2,fillTextOffsetY:W1,fillTextWidth:Q1,fillTextHeight:J1,fontSize:g1}},J=function(Q,Z,d1,m1,g1){for(var c1=g1.length;c1--;){var L1=Q+g1[c1][0],M1=Z+g1[c1][1];if(L1>=m||M1>=y||L1<0||M1<0){if(!i.drawOutOfBound)return!1;continue}if(!h[L1][M1])return!1}return!0},O=function(Q,Z,d1,m1,g1,c1,L1,M1,b1,Q1){var J1=d1.fontSize,$1;x?$1=x(m1,g1,J1,c1,L1,Q1):$1=i.color;var n2;S?n2=S(m1,g1,J1,Q1):n2=i.fontWeight;var s2;w?s2=w(m1,g1,J1,Q1):s2=i.classes,e.forEach(function(V1){if(V1.getContext){var i2=V1.getContext("2d"),W1=d1.mu;i2.save(),i2.scale(1/W1,1/W1),i2.font=n2+" "+(J1*W1).toString(10)+"px "+i.fontFamily,i2.fillStyle=$1,i2.translate((Q+d1.gw/2)*s*W1,(Z+d1.gh/2)*s*W1),M1!==0&&i2.rotate(-M1),i2.textBaseline="middle",i2.fillText(m1,d1.fillTextOffsetX*W1,(d1.fillTextOffsetY+J1*.5)*W1),i2.restore()}else{var P=document.createElement("span"),I="";I="rotate("+-M1/Math.PI*180+"deg) ",d1.mu!==1&&(I+="translateX(-"+d1.fillTextWidth/4+"px) scale("+1/d1.mu+")");var l1={position:"absolute",display:"block",font:n2+" "+J1*d1.mu+"px "+i.fontFamily,left:(Q+d1.gw/2)*s+d1.fillTextOffsetX+"px",top:(Z+d1.gh/2)*s+d1.fillTextOffsetY+"px",width:d1.fillTextWidth+"px",height:d1.fillTextHeight+"px",lineHeight:J1+"px",whiteSpace:"nowrap",transform:I,webkitTransform:I,msTransform:I,transformOrigin:"50% 40%",webkitTransformOrigin:"50% 40%",msTransformOrigin:"50% 40%"};$1&&(l1.color=$1),P.textContent=m1;for(var x1 in l1)P.style[x1]=l1[x1];if(b1)for(var w1 in b1)P.setAttribute(w1,b1[w1]);s2&&(P.className+=s2),V1.appendChild(P)}})},N=function(Q,Z,d1,m1,g1){if(!(Q>=m||Z>=y||Q<0||Z<0)){if(h[Q][Z]=!1,d1){var c1=e[0].getContext("2d");c1.fillRect(Q*s,Z*s,u,u)}M&&(D[Q][Z]={item:g1,dimension:m1})}},B=function(Q,Z,d1,m1,g1,c1){var L1=g1.occupied,M1=i.drawMask,b1;M1&&(b1=e[0].getContext("2d"),b1.save(),b1.fillStyle=i.maskColor);var Q1;if(M){var J1=g1.bounds;Q1={x:(Q+J1[3])*s,y:(Z+J1[0])*s,w:(J1[1]-J1[3]+1)*s,h:(J1[2]-J1[0]+1)*s}}for(var $1=L1.length;$1--;){var n2=Q+L1[$1][0],s2=Z+L1[$1][1];n2>=m||s2>=y||n2<0||s2<0||N(n2,s2,M1,Q1,c1)}M1&&b1.restore()},t1=function a1(Q,Z){if(Z>20)return null;var d1,m1,g1;Array.isArray(Q)?(d1=Q[0],m1=Q[1]):(d1=Q.word,m1=Q.weight,g1=Q.attributes);var c1=F(),L1=JR(Q),M1=G(d1,m1,c1,L1);if(!M1||z())return!1;if(!i.drawOutOfBound&&!i.shrinkToFit){var b1=M1.bounds;if(b1[1]-b1[3]+1>m||b1[2]-b1[0]+1>y)return!1}for(var Q1=v+1,J1=function(V1){var i2=Math.floor(V1[0]-M1.gw/2),W1=Math.floor(V1[1]-M1.gh/2),P=M1.gw,I=M1.gh;return J(i2,W1,P,I,M1.occupied)?(O(i2,W1,M1,d1,m1,v-Q1,V1[2],c1,g1,L1),B(i2,W1,P,I,M1,Q),{gx:i2,gy:W1,rot:c1,info:M1}):!1};Q1--;){var $1=$(v-Q1);i.shuffle&&($1=[].concat($1),ZR($1));for(var n2=0;n2<$1.length;n2++){var s2=J1($1[n2]);if(s2)return s2}}return i.shrinkToFit?(Array.isArray(Q)?Q[1]=Q[1]*3/4:Q.weight=Q.weight*3/4,a1(Q,Z+1)):null},n1=function(Q,Z,d1){if(Z)return!e.some(function(m1){var g1=new CustomEvent(Q,{detail:d1||{}});return!m1.dispatchEvent(g1)},this);e.forEach(function(m1){var g1=new CustomEvent(Q,{detail:d1||{}});m1.dispatchEvent(g1)},this)},u1=function(){var Q=e[0];if(Q.getContext)m=Math.ceil(Q.width/s),y=Math.ceil(Q.height/s);else{var Z=Q.getBoundingClientRect();m=Math.ceil(Z.width/s),y=Math.ceil(Z.height/s)}if(n1("wordcloudstart",!0)){_=i.origin?[i.origin[0]/s,i.origin[1]/s]:[m/2,y/2],v=Math.floor(Math.sqrt(m*m+y*y)),h=[];var d1,m1,g1;if(!Q.getContext||i.clearCanvas)for(e.forEach(function(W1){if(W1.getContext){var P=W1.getContext("2d");P.fillStyle=i.backgroundColor,P.clearRect(0,0,m*(s+1),y*(s+1)),P.fillRect(0,0,m*(s+1),y*(s+1))}else W1.textContent="",W1.style.backgroundColor=i.backgroundColor,W1.style.position="relative"}),d1=m;d1--;)for(h[d1]=[],m1=y;m1--;)h[d1][m1]=!0;else{var c1=document.createElement("canvas").getContext("2d");c1.fillStyle=i.backgroundColor,c1.fillRect(0,0,1,1);var L1=c1.getImageData(0,0,1,1).data,M1=Q.getContext("2d").getImageData(0,0,m*s,y*s).data;d1=m;for(var b1,Q1;d1--;)for(h[d1]=[],m1=y;m1--;){Q1=s;e:for(;Q1--;)for(b1=s;b1--;)for(g1=4;g1--;)if(M1[((m1*s+Q1)*m*s+(d1*s+b1))*4+g1]!==L1[g1]){h[d1][m1]=!1;break e}h[d1][m1]!==!1&&(h[d1][m1]=!0)}M1=c1=L1=void 0}if(i.hover||i.click){for(M=!0,d1=m+1;d1--;)D[d1]=[];i.hover&&Q.addEventListener("mousemove",U),i.click&&(Q.addEventListener("click",V),Q.addEventListener("touchstart",V),Q.addEventListener("touchend",function(W1){W1.preventDefault()}),Q.style.webkitTapHighlightColor="rgba(0, 0, 0, 0)"),Q.addEventListener("wordcloudstart",function W1(){Q.removeEventListener("wordcloudstart",W1),Q.removeEventListener("mousemove",U),Q.removeEventListener("click",V),T=void 0})}g1=0;var J1,$1,n2=!0;i.layoutAnimation?i.wait!==0?(J1=window.setTimeout,$1=window.clearTimeout):(J1=window.setImmediate,$1=window.clearImmediate):(J1=function(W1){W1()},$1=function(){n2=!1});var s2=function(P,I){e.forEach(function(l1){l1.addEventListener(P,I)},this)},V1=function(P,I){e.forEach(function(l1){l1.removeEventListener(P,I)},this)},i2=function W1(){V1("wordcloudstart",W1),$1(S9[n])};s2("wordcloudstart",i2),S9[n]=(i.layoutAnimation?J1:setTimeout)(function W1(){if(n2){if(g1>=i.list.length){$1(S9[n]),n1("wordcloudstop",!1),V1("wordcloudstart",i2),delete S9[n];return}b=new Date().getTime();var P=t1(i.list[g1],0),I=!n1("wordclouddrawn",!0,{item:i.list[g1],drawn:P});if(z()||I){$1(S9[n]),i.abort(),n1("wordcloudabort",!1),n1("wordcloudstop",!1),V1("wordcloudstart",i2);return}g1++,S9[n]=J1(W1,i.wait)}},i.wait)}};u1()};v6.isSupported=jn;v6.minFontSize=at;if(!v6.isSupported)throw new Error("Sorry your browser not support wordCloud");function qR(o){for(var e=o.getContext("2d"),t=e.getImageData(0,0,o.width,o.height),n=e.createImageData(t),i=0,a=0,r=0;r<t.data.length;r+=4){var s=t.data[r+3];if(s>128){var u=t.data[r]+t.data[r+1]+t.data[r+2];i+=u,++a}}for(var c=i/a,r=0;r<t.data.length;r+=4){var u=t.data[r]+t.data[r+1]+t.data[r+2],s=t.data[r+3];s<128||u>c?(n.data[r]=0,n.data[r+1]=0,n.data[r+2]=0,n.data[r+3]=0):(n.data[r]=255,n.data[r+1]=255,n.data[r+2]=255,n.data[r+3]=255)}e.putImageData(n,0,0)}Ql(function(o,e){o.eachSeriesByType("wordCloud",function(t){var n=nu(t.getBoxLayoutParams(),{width:e.getWidth(),height:e.getHeight()}),i=t.get("keepAspect"),a=t.get("maskImage"),r=a?a.width/a.height:1;i&&XR(n,r);var s=t.getData(),u=document.createElement("canvas");u.width=n.width,u.height=n.height;var c=u.getContext("2d");if(a)try{c.drawImage(a,0,0,u.width,u.height),qR(u)}catch(v){console.error("Invalid mask image"),console.error(v.toString())}var f=t.get("sizeRange"),p=t.get("rotationRange"),h=s.getDataExtent("value"),m=Math.PI/180,y=t.get("gridSize");v6(u,{list:s.mapArray("value",function(v,b){var x=s.getItemModel(b);return[s.getName(b),x.get("textStyle.fontSize",!0)||iu(v,h,f),b]}).sort(function(v,b){return b[1]-v[1]}),fontFamily:t.get("textStyle.fontFamily")||t.get("emphasis.textStyle.fontFamily")||o.get("textStyle.fontFamily"),fontWeight:t.get("textStyle.fontWeight")||t.get("emphasis.textStyle.fontWeight")||o.get("textStyle.fontWeight"),gridSize:y,ellipticity:n.height/n.width,minRotation:p[0]*m,maxRotation:p[1]*m,clearCanvas:!a,rotateRatio:1,rotationStep:t.get("rotationStep")*m,drawOutOfBound:t.get("drawOutOfBound"),shrinkToFit:t.get("shrinkToFit"),layoutAnimation:t.get("layoutAnimation"),shuffle:!1,shape:t.get("shape")});function _(v){var b=v.detail.item;v.detail.drawn&&t.layoutInstance.ondraw&&(v.detail.drawn.gx+=n.x/y,v.detail.drawn.gy+=n.y/y,t.layoutInstance.ondraw(b[0],b[1],b[2],v.detail.drawn))}u.addEventListener("wordclouddrawn",_),t.layoutInstance&&t.layoutInstance.dispose(),t.layoutInstance={ondraw:null,dispose:function(){u.removeEventListener("wordclouddrawn",_),u.addEventListener("wordclouddrawn",function(v){v.preventDefault()})}}})});Kl(function(o){var e=(o||{}).series;!eu(e)&&(e=e?[e]:[]);var t=["shadowColor","shadowBlur","shadowOffsetX","shadowOffsetY"];ii(e,function(i){if(i&&i.type==="wordCloud"){var a=i.textStyle||{};n(a.normal),n(a.emphasis)}});function n(i){i&&ii(t,function(a){i.hasOwnProperty(a)&&(i["text"+tu(a)]=i[a])})}});function XR(o,e){var t=o.width,n=o.height;t>n*e?(o.x+=(t-n*e)/2,o.width=n*e):(o.y+=(n-t/e)/2,o.height=t/e)}const QR=s1({__name:"index",props:{themeSetting:{type:Object,required:!0},themeColor:{type:Object,required:!0},chartConfig:{type:Object,required:!0}},setup(o){const e=o,t=Y2(e.chartConfig.option,e.themeSetting);I2([k2,X2,o3]);const n=Z1(),i=X1(()=>Q2(e.chartConfig.option,e.themeSetting,Vt)),a=s=>{try{s&&(e.chartConfig.option.series[0].data=s),r.value&&v2()&&_0(r.value,e.chartConfig.option)}catch(u){console.log(u)}};N1(()=>e.chartConfig.option.dataset,s=>{a(s)},{deep:!1});const{vChartRef:r}=d2(e.chartConfig,K1,s=>{a(s)});return(s,u)=>(R(),p1(l($2),{ref_key:"vChartRef",ref:r,"init-options":l(t),theme:o.themeColor,option:i.value,"manual-update":l(v2)(),"update-options":{replaceMerge:n.value},autoresize:""},null,8,["init-options","theme","option","manual-update","update-options"]))}}),KR=Object.freeze(Object.defineProperty({__proto__:null,default:QR},Symbol.toStringTag,{value:"Module"})),eO={class:"go-text-box"},tO={class:"content"},nO=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){a4(h=>({ad835132:l(n),"770576e0":l(i)+"px","686ae446":l(a)+"px",bfc1ca18:l(r),"32dde9de":l(c),"0b3a081c":p.value+"s","2bb5d1c0":l(s)+"s"}));const e=o,{w:t}=A1(e.chartConfig.attr),{fontColor:n,fontSize:i,letterSpacing:a,fontWeight:r,animationTime:s,animationSpeed:u,boxShadow:c}=A1(e.chartConfig.option),f=j3({dataset:jt.dataset});N1(()=>e.chartConfig.option.dataset,h=>{f.dataset=h},{immediate:!0,deep:!1}),N1(e.chartConfig.option,()=>{try{e.chartConfig.option.showShadow?c.value=`${e.chartConfig.option.hShadow}px ${e.chartConfig.option.vShadow}px ${e.chartConfig.option.blurShadow}px ${e.chartConfig.option.colorShadow}`:c.value="none"}catch(h){console.log(h)}},{immediate:!0});const p=X1(()=>Math.floor(t.value/u.value));return d2(e.chartConfig,K1,h=>{f.dataset=h}),(h,m)=>(R(),e1("div",eO,[j("div",tO,[j("span",null,D2(l(f).dataset),1)])]))}});const iO=u2(nO,[["__scopeId","data-v-95986cc2"]]),oO=Object.freeze(Object.defineProperty({__proto__:null,default:iO},Symbol.toStringTag,{value:"Module"})),aO={class:"go-text-box"},rO={class:"content"},sO={key:1,style:{"white-space":"pre-wrap"}},lO=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){a4(x=>({"3de96504":l(c),"798f780c":l(i),"1ebb38b9":`${l(s)}px ${l(u)}px`,"5e8a36e3":l(a)+"px","1ba7176a":l(r)+"px",ede18276:l(m),"753a7e7e":l(_),d62f98e6:l(f)+"px",b6135d96:l(h)+"px","5f13ecbd":l(p),"1daa4c0a":l(y)}));const e=o,{linkHead:t,link:n,fontColor:i,fontSize:a,letterSpacing:r,paddingY:s,paddingX:u,textAlign:c,borderWidth:f,borderColor:p,borderRadius:h,writingMode:m,backgroundColor:y,fontWeight:_}=A1(e.chartConfig.option),v=j3({dataset:Wt.dataset});N1(()=>e.chartConfig.option.dataset,x=>{v.dataset=x},{immediate:!0,deep:!1}),d2(e.chartConfig,K1,x=>{v.dataset=x});const b=()=>{window.open(t.value+n.value)};return(x,A)=>(R(),e1("div",aO,[j("div",rO,[l(n)?(R(),e1("span",{key:0,style:{cursor:"pointer","white-space":"pre-wrap"},onClick:b},D2(l(v).dataset),1)):(R(),e1("span",sO,D2(l(v).dataset),1))])]))}});const uO=u2(lO,[["__scopeId","data-v-323007cd"]]),cO=Object.freeze(Object.defineProperty({__proto__:null,default:uO},Symbol.toStringTag,{value:"Module"})),rt={key:"TextGradient",chartKey:"VTextGradient",conKey:"VCTextGradient",title:"渐变文字",category:I3.TEXT,categoryName:R3.TEXT,package:T1.INFORMATIONS,chartFrame:U1.NAIVE_UI,image:"text_gradient.png"},Wn={dataset:"我是渐变文本",size:20,gradient:{from:"#0000FFFF",to:"#00FF00FF",deg:45}};class dO extends e2{constructor(){super(...arguments);X(this,"key",rt.key);X(this,"chartConfig",C1(rt));X(this,"option",C1(Wn))}}const fO=Object.freeze(Object.defineProperty({__proto__:null,default:dO,option:Wn},Symbol.toStringTag,{value:"Module"})),pO={class:"go-text-box"},hO=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){const e=o,t=j3({dataset:Wn.dataset});A1(e.chartConfig.attr);const{size:n,gradient:i}=A1(e.chartConfig.option);return N1(()=>e.chartConfig.option.dataset,a=>{t.dataset=a},{immediate:!0,deep:!1}),d2(e.chartConfig,K1,a=>{t.dataset=a}),(a,r)=>{const s=L("n-gradient-text");return R(),e1("div",pO,[d(s,{size:l(n),gradient:l(i)},{default:g(()=>[D1(D2(l(t).dataset),1)]),_:1},8,["size","gradient"])])}}});const mO=u2(hO,[["__scopeId","data-v-3835bf19"]]),gO=Object.freeze(Object.defineProperty({__proto__:null,default:mO},Symbol.toStringTag,{value:"Module"})),vO=o=>(W4("data-v-c881aae3"),o=o(),H4(),o),_O={class:"ranking-info"},yO=["innerHTML"],bO=vO(()=>j("div",{class:"shine"},null,-1)),xO=[bO],SO=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){const e=o,{w:t,h:n}=A1(e.chartConfig.attr),{rowNum:i,unit:a,color:r,textColor:s,borderColor:u,indexFontSize:c,leftFontSize:f,rightFontSize:p}=A1(e.chartConfig.option),h=V3({mergedConfig:e.chartConfig.option,rowsData:[],rows:[{scroll:0,ranking:1,name:"",value:"",percent:0}],heights:[0],animationIndex:0,animationHandler:0,updater:0,avgHeight:0}),m=()=>{let{dataset:x,rowNum:A,sort:S}=h.mergedConfig;S&&x.sort(({value:V},{value:q})=>{if(V>q)return-1;if(V<q)return 1;if(V===q)return 0});const w=x.map(({value:V})=>V),M=Math.min(...w)||0,D=Math.abs(M),E=(Math.max(...w)||0)+D;x=x.map((V,q)=>A2(B1({},V),{ranking:q+1,percent:(V.value+D)/E*100}));const U=x.length;U>A&&U<2*A&&(x=[...x,...x]),x=x.map((V,q)=>A2(B1({},V),{scroll:q})),h.rowsData=x,h.rows=x},y=(x=!1)=>{const{rowNum:A,dataset:S}=h.mergedConfig,w=n.value/A;h.avgHeight=w,x||(h.heights=new Array(S.length).fill(w))},_=(x=!1)=>P2(this,null,function*(){let{avgHeight:A,animationIndex:S,mergedConfig:w,rowsData:M,updater:D}=h;const{waitTime:T,carousel:E,rowNum:U}=w,V=M.length;if(U>=V||x&&(yield new Promise(F=>setTimeout(F,T)),D!==h.updater))return;const q=E==="single"?1:U;let $=M.slice(S);if($.push(...M.slice(0,S)),h.rows=$.slice(0,U+1),h.heights=new Array(V).fill(A),yield new Promise(F=>setTimeout(F,300)),D!==h.updater)return;h.heights.splice(0,q,...new Array(q).fill(0)),S+=q;const z=S-V;z>=0&&(S=z),h.animationIndex=S,h.animationHandler=setTimeout(_,T*1e3-300)}),v=()=>{h.updater=(h.updater+1)%999999,h.animationHandler&&clearTimeout(h.animationHandler)},b=()=>P2(this,null,function*(){try{if(!h.mergedConfig)return;let{dataset:x,rowNum:A,sort:S}=h.mergedConfig;v(),m();let w=!0;x.length<=A&&(w=!1),y(w),_(w)}catch(x){console.error(x)}});return b(),N1(()=>t.value,()=>{b()}),N1(()=>n.value,()=>{b()}),N1(()=>i.value,()=>{b()}),N1(()=>e.chartConfig.option.dataset,()=>{b()},{deep:!1}),d2(e.chartConfig,K1,x=>{e.chartConfig.option.dataset=x,b()}),o0(()=>{v()}),(x,A)=>(R(),e1("div",{class:"go-tables-rank",style:f2(`color: ${l(s)}`)},[(R(!0),e1(S1,null,Y1(h.rows,(S,w)=>(R(),e1("div",{class:"row-item",key:S.toString()+S.scroll,style:f2(`height: ${h.heights[w]}px;`)},[j("div",_O,[j("div",{class:"rank",style:f2(`color: ${l(r)};font-size: ${l(c)}px`)},"No."+D2(S.ranking),5),j("div",{class:"info-name",style:f2(`font-size: ${l(f)}px`),innerHTML:S.name},null,12,yO),j("div",{class:"ranking-value",style:f2(`color: ${l(s)};font-size: ${l(p)}px`)},D2(h.mergedConfig.valueFormatter?h.mergedConfig.valueFormatter(S):S.value)+" "+D2(l(a)),5)]),j("div",{class:"ranking-column",style:f2(`border-color: ${l(u)}`)},[j("div",{class:"inside-column",style:f2(`width: ${S.percent}%;background-color: ${l(r)}`)},xO,4)],4)],4))),128))],4))}});const MO=u2(SO,[["__scopeId","data-v-c881aae3"]]),AO=Object.freeze(Object.defineProperty({__proto__:null,default:MO},Symbol.toStringTag,{value:"Module"})),wO={class:"dv-scroll-board"},CO=["align","innerHTML"],TO=["align","innerHTML"],EO=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){const e=o,{w:t,h:n}=A1(e.chartConfig.attr),i=V3({defaultConfig:{header:[],dataset:[],rowNum:5,headerBGC:"#00BAFF",oddRowBGC:"#003B51",evenRowBGC:"#0A2732",waitTime:2,headerHeight:35,columnWidth:[],align:[],index:!1,indexHeader:"#",carousel:"single",hoverPause:!0},mergedConfig:e.chartConfig.option,header:[],rowsData:[],rows:[{ceils:[],rowIndex:0,scroll:0}],widths:[],heights:[0],avgHeight:0,aligns:[],animationIndex:0,animationHandler:0,updater:0,needCalc:!1}),a=()=>{r(),s(),u(),c(),f(),p(),h(!0)};v0(()=>{a()});const r=()=>{i.mergedConfig=C6(C1(i.defaultConfig),e.chartConfig.option)},s=()=>{let{header:_,index:v,indexHeader:b}=i.mergedConfig;if(!_.length){i.header=[];return}_=[..._],v&&_.unshift(b),i.header=_},u=()=>{let{dataset:_,index:v,headerBGC:b,rowNum:x}=i.mergedConfig;v&&(_=_.map((S,w)=>{S=[...S];const M=`<span class="index" style="background-color: ${b};border-radius: 3px;padding: 0px 3px;">${w+1}</span>`;return S.unshift(M),S})),_=_.map((S,w)=>({ceils:S,rowIndex:w}));const A=_.length;A>x&&A<2*x&&(_=[..._,..._]),_=_.map((S,w)=>A2(B1({},S),{scroll:w})),i.rowsData=_,i.rows=_},c=()=>{const{mergedConfig:_,rowsData:v}=i,{columnWidth:b,header:x}=_,A=b.reduce((D,T)=>D+T,0);let S=0;v[0]?S=v[0].ceils.length:x.length&&(S=x.length);const w=(t.value-A)/(S-b.length),M=new Array(S).fill(w);i.widths=C6(M,b)},f=(_=!1)=>{const{mergedConfig:v,header:b}=i,{headerHeight:x,rowNum:A,dataset:S}=v;let w=n.value;b.length&&(w-=x);const M=w/A;i.avgHeight=M,_||(i.heights=new Array(S.length).fill(M))},p=()=>{const{header:_,mergedConfig:v}=i,b=_.length;let x=new Array(b).fill("left");const{align:A}=v;i.aligns=C6(x,A)},h=(_=!1)=>P2(this,null,function*(){const{needCalc:v}=i;v&&(u(),f(),i.needCalc=!1);let{avgHeight:b,animationIndex:x,mergedConfig:A,rowsData:S,updater:w}=i;const{waitTime:M,carousel:D,rowNum:T}=A,E=S.length;if(T>=E||_&&(yield new Promise($=>setTimeout($,M*1e3)),w!==i.updater))return;const U=D==="single"?1:T;let V=S.slice(x);if(V.push(...S.slice(0,x)),i.rows=V.slice(0,D==="page"?T*2:T+1),i.heights=new Array(E).fill(b),yield new Promise($=>setTimeout($,300)),w!==i.updater)return;i.heights.splice(0,U,...new Array(U).fill(0)),x+=U;const q=x-E;q>=0&&(x=q),i.animationIndex=x,i.animationHandler=setTimeout(h,M*1e3-300)}),m=()=>{i.updater=(i.updater+1)%999999,i.animationHandler&&clearTimeout(i.animationHandler)},y=()=>P2(this,null,function*(){try{if(!i.mergedConfig)return;m(),a()}catch(_){console.log(_)}});return N1(()=>t.value,()=>{y()}),N1(()=>n.value,()=>{y()}),N1(()=>e.chartConfig.option,()=>{y()},{deep:!0}),d2(e.chartConfig,K1,_=>{e.chartConfig.option.dataset=_,y()}),o0(()=>{m()}),(_,v)=>(R(),e1("div",wO,[i.header.length&&i.mergedConfig?(R(),e1("div",{key:0,class:"header",style:f2(`background-color: ${i.mergedConfig.headerBGC};`)},[(R(!0),e1(S1,null,Y1(i.header,(b,x)=>(R(),e1("div",{class:"header-item",key:`${b}${x}`,style:f2(`
        height: ${i.mergedConfig.headerHeight}px;
        line-height: ${i.mergedConfig.headerHeight}px;
        width: ${i.widths[x]}px;
      `),align:i.aligns[x],innerHTML:b},null,12,CO))),128))],4)):p2("",!0),i.mergedConfig?(R(),e1("div",{key:1,class:"rows",style:f2(`height: ${l(n)-(i.header.length?i.mergedConfig.headerHeight:0)}px;`)},[(R(!0),e1(S1,null,Y1(i.rows,(b,x)=>(R(),e1("div",{class:"row-item",key:`${b.toString()}${b.scroll}`,style:f2(`
        height: ${i.heights[x]}px;
        line-height: ${i.heights[x]}px;
        background-color: ${i.mergedConfig[b.rowIndex%2===0?"evenRowBGC":"oddRowBGC"]};
      `)},[(R(!0),e1(S1,null,Y1(b.ceils,(A,S)=>(R(),e1("div",{class:"ceil",key:`${A}${x}${S}`,style:f2(`width: ${i.widths[S]}px;`),align:i.aligns[S],innerHTML:A},null,12,TO))),128))],4))),128))],4)):p2("",!0)]))}});const DO=u2(EO,[["__scopeId","data-v-a6b4b361"]]),IO=Object.freeze(Object.defineProperty({__proto__:null,default:DO},Symbol.toStringTag,{value:"Module"})),RO={class:"go-tables-basic"},OO=s1({__name:"index",props:{chartConfig:{type:Object,required:!0}},setup(o){const e=o,{SearchIcon:t}=r6.ionicons5,n=Z1(""),i=X1(()=>{var p,h;return(h=(p=f==null?void 0:f.dataset)==null?void 0:p.source)==null?void 0:h.filter(m=>Object.values(m).some(y=>String(y).toLowerCase().includes(n.value.toLowerCase())))}),{align:a,pagination:r,inputShow:s}=A1(e.chartConfig.option);r.value.onChange=p=>{r.value.page=p};const{w:u,h:c}=A1(e.chartConfig.attr),f=V3({dataset:e.chartConfig.option.dataset,style:e.chartConfig.option.style});return N1(()=>e.chartConfig.option.dataset,p=>{var h,m;f.dataset=p,(m=(h=f==null?void 0:f.dataset)==null?void 0:h.dimensions)==null||m.forEach(y=>{y.align=a.value})},{immediate:!0,deep:!0}),(p,h)=>{const m=L("n-icon"),y=L("n-input"),_=L("n-data-table");return R(),e1("div",RO,[d(y,{value:n.value,"onUpdate:value":h[0]||(h[0]=v=>n.value=v),placeholder:"请输入信息",style:f2([`display: ${l(s)}`,{"margin-bottom":"5px",float:"right",width:"240px"}])},{prefix:g(()=>[d(m,{component:l(t)},null,8,["component"])]),_:1},8,["value","style"]),d(_,{style:f2(`
      width: ${l(u)}px;
      height: ${l(c)}px;
      font-size: ${f.style.fontSize}px;
      border-width: ${f.style.border==="on"?f.style.borderWidth:0}px;
      border-color: ${f.style.borderColor};