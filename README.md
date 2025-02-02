راهنمای استفاده از API کش (Cache API)
این برنامه شامل سه اندپوینت برای مدیریت داده‌ها در کش (Cache) است. شما می‌توانید داده‌ای را ذخیره، بازیابی یا حذف کنید.


۱. ایجاد داده در کش (api/caches)
برای ایجاد داده در کش باید از متد post استفاده کنیم و داده های که میخواهیم کش کنیم باید داخل body درخواست ارسال شود :

http://localhost:8080/api/caches

Request body:
{
    "key":"test", (String)کلیدی که برای بازیابی داده استفاده می‌شود
    "value": "test value",  (String)مقدار یا داده‌ای که می‌خواهید ذخیره کنید
    "timeToLiveInMinutes": (Intger)مدت‌زمان معتبر بودن داده در کش (به دقیقه) بعد از این مدت داده به‌طور خودکار حذف می‌شود
}


Response:

{
  "status": "Success",
  "message": "Data cached successfully",
}

۲. خواندن داده از کش
برای خواندن داده از کش باید از  متد GET استفاده کنیم و key مورد نظر بخشی از اندپوینت ارسال کنیم
http://localhost:8080/api/caches/{YourKey}

Response:

{
  "status": "Success",
  "data": "test value"
}

۳. حذف داده از کش
برای حذف کردن داده از کش باید از متد DELETE استفاده کنیم و KEY مورد نظر خودمون رو بخشی از اندپوینت ارسال کینم 
http://localhost:8080/api/caches/{YourKey}

Response:

  {
  "status": "Success",
  "message": "Delete cached successfully",
 }


برای مشاهده تعداد درخواست به هر اندپوینت باید از url  زیر استفاده کنیم و مشخص کردن تگ (uri) و اندپوینت موردنظر خودمون میتوانیم تعداد درخواست ها به اون اندپوینت رو مشاهده کنیم
http://localhost:8080/actuator/metrics/http.server.requests?tag=uri:{YourEndpoint}

