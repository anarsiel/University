<#import "common.ftl" as c/>

<@c.page>
<div class="datatable">
    <div class="caption">Users</div>
    <table>
        <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Handle</th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td><@c.userlink user=user nameOnly=false/></td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
</@c.page>