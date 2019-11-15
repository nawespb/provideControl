<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <@l.logout/>
    <div style='margin-bottom: 10px;'>
        <form method="post">
            <input type="text" name="name" placeholder="Введите название товара"/>
            <input type="text" name="description" placeholder="Описание">
            <input type="text" name="count" placeholder="Количество">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Добавить</button>
        </form>
    </div>
    <div style='margin-bottom: 5px;'>Список заказов</div>
    <div style='margin-bottom: 10px;'>
        <form method="get" action="/main">
            <input type="text" name="filter" value="${filter!""}">
            <button type="submit">Поиск</button>
        </form>
    </div>
    <#list orders as order>
        <div>
            <strong>${order.authorName}: </strong>
            <span><i>Название: </i><b>${order.name}</b></span>
            <i>Описание: </i><b>${order.description}</b>
            <i>Количество: </i><b>${order.count}</b>
        </div>
    <#else>
        No orders...
    </#list>
</@c.page>