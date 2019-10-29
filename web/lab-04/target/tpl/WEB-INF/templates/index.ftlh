<#import "common.ftl" as c/>

<@c.page>
<ul>
    <#list posts?reverse as post>
        <@c.makePost post=post zip=true/>
    </#list>
</ul>
</@c.page>
