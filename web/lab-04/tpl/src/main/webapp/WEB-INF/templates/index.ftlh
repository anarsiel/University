<#import "common.ftl" as c/>

<@c.page>
<ul>
    <#list users as u>
        <li><#if user?? && u.id == user.id>*</#if> <@c.userlink user=u/>
    </#list>
</ul>
</@c.page>
