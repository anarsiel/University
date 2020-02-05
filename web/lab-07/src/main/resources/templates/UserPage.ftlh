<#-- @ftlvariable name="userWatched" type="ru.itmo.wp.domain.User" -->
<#import "/spring.ftl" as spring>
<#import "commons.ftlh" as c>

<@c.page>
    <#if userWatched??>
        <div class="userInfo">
            <ul>
                <li class="id">Id: ${userWatched.id}</li>
                <li class="login">Login: ${userWatched.login}</li>
                <li class="creationTime">Creation time: ${userWatched.creationTime}</li>
            </ul>
        </div>
    <#else>
        <div class="no-such-user"> No such user </div>
    </#if>

</@c.page>
