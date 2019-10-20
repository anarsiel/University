<#-- @ftlvariable name="handle" type="java.lang.String" -->
<#import "common.ftl" as c/>

<@c.page>
    <#assign viewedUser=c.findBy(users, "handle", handle)!/>
    <#if viewedUser.id??>
        <div class="userTable">
            <div class="userInfo">
                <@c.userlink user=viewedUser/>
                <ul>
                    <li>id: ${viewedUser.id}</li>
                    <li>handle: ${viewedUser.handle}</li>
                    <li>name: ${viewedUser.name}</li>
                </ul>
            </div>
            <img src="../../img/user.jpg">
        </div>

        <div class = "arrows">
            <#assign rusers=users?reverse/>
            <#list rusers as user>
                <#if user.id =viewedUser.id>
                    <#if user_has_next>
                        <a href = "/user?handle=${rusers[user_index + 1].handle}"> &larr; </a>
                    <#else>
                        &larr;
                    </#if>
                </#if>
            </#list>

            <#list users as user>
                <#if user.id =viewedUser.id>
                    <#if user_has_next>
                        <a href = "/user?handle=${users[user_index + 1].handle}"> &rarr; </a>
                    <#else>
                        &rarr;
                    </#if>
                </#if>
            </#list>
        </div>
    <#else>
        <h1>No such user</h1>
    </#if>
</@c.page>
