<#import "parts/common.ftlh" as c>

<@c.page>

    <div class="form-row">
        <div class="form-group col-md-8">
            <form method="get" action="/main" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter?ifExists}"
                       placeholder="Поиск по названию">
                <button type="submit" class="btn btn-primary ml-2">Поиск</button>
            </form>
        </div>
    </div>
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Добавить новый заказ
    </a>
    <div class="collapse" id="collapseExample">
        <div class="form-group">
            <form method="post" enctype="multipart/form-data">
                <input type="text" name="name" class="form-control mt-1" placeholder="Введите название товара"/>
                <input type="text" name="description" class="form-control mt-1" placeholder="Описание">
                <input type="text" name="count" class="form-control mt-1" placeholder="Количество">
                <p>
                    <label for="date" class="mt-1">Дата закупки: </label>
                    <input type="date" id="date" name="date" class="form-control mt-1"/>
                </p>
                <div class="custom-file">
                    <input type="file" name="file" class="form-control" id="customfile">
                    <label class="custom-file-label" for="customfile">Выберите файл</label>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-primary mt-2">Добавить</button>
            </form>
        </div>
    </div>

    <div class="card-columns">
        <#list orders as order>
            <div class="card my-3">
                <div class="card-footer text-muted">
                    <strong>${order.authorName}: </strong>
                </div>
                <div class="m-2">
                    <span><i>Название: </i><b>${order.name}</b></span>
                    <i>Описание: </i><b>${order.description}</b>
                    <i>Количество: </i><b>${order.count}</b>
                    <i>Дата расхода: </i><b>${order.printDate()}</b>
                </div>
                <div class="card-img-top m-2">
                    <#if order.filename??>
                        <img src="/img/${order.filename}">
                    </#if>
                </div>
            </div>
        <#else>
            Заказов не обнаружено...
        </#list>
    </div>
</@c.page>