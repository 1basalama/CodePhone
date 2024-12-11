package com.basalama.codephone.viewmodel

import android.content.Context

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.basalama.codephone.R

class MyViewModel(context: Context) : ViewModel() {

    private val YemenMobile: List<CodeModel> = listOf(

        CodeModel(
            context.getString(R.string.borrow_me),
            context.getString(R.string.borrow_me_DES),
            '1',
            "*100%23"
        ),

        CodeModel(
            context.getString(R.string.please_call_me),
            context.getString(R.string.please_call_me_DES), 'c', "555*", "%23"
        ),

        CodeModel(
            context.getString(R.string.balance_inquiry),
            context.getString(R.string.balance_inquiry_DES), '1', "181"
        ),
        CodeModel("خدمة الرقم غير صحيح",
            "", '2', "*72010", "*720"
        ),

        CodeModel(
            context.getString(R.string.smart_detector),
            context.getString(R.string.smart_detector_DES), '2', "*122", "*123"
        ),

        CodeModel(
            context.getString(R.string.do_not_disturb),
            context.getString(R.string.do_not_disturb_DES), '2', "*80", "*800"
        ),

        CodeModel(
            context.getString(R.string.missed_call_alert),
            context.getString(R.string.missed_call_alert_DES), '2', "*74", "*740"
        ),
        CodeModel(
            context.getString(R.string.doing_local_queries),
            context.getString(R.string.doing_local_queries_DES), '1', "118"
        ),


        CodeModel(
            context.getString(R.string.forwarding_busy),
            context.getString(R.string.forwarding_busy_DES), 'f', "*90", "*900"
        ),

        CodeModel(
            context.getString(R.string.forwarding_no_answer),
            context.getString(R.string.forwarding_no_answer_DES), 'f', "*92", "*920"
        ),
        CodeModel(
            context.getString(R.string.forwarding_off),
            context.getString(R.string.forwarding_off_DES), 'f', "*68", "*680"
        ),

        CodeModel(
            context.getString(R.string.forwarding_all_cases),
            "", 'f', "*72", "*730"
        ),
        CodeModel(
            context.getString(R.string.customers_service),
            context.getString(R.string.customers_service_DES), '1', "121"
        ),


        CodeModel(
            context.getString(R.string.mazaya_balance),
            context.getString(R.string.mazaya_balance_DES), 'm', "رصيد", "181"
        ),

        CodeModel(
            "خدمة رنات",
            "خدمة تمكنك من تحميل نغمتك المفضلة للإشتراك في الخدمة وتحميل أجمل النغمات اتصال على 9900 ب5 ر.ي للدقيقة واتبع التعليمات الصوتية.\n" +
                    "مبلغ الإشتراك 100ر.ي شهرياً .\n" +
                    "تحميل النغمة 70 ر.ي مدتها شهر واحد تتجدد تلقائياً .\n" +
                    "للمساعدة اتصل  ثم- اضغط 1 للعربية -  0 للمساعدة .\n" +
                    "لإلغاء اشتراكك اتصل ثم اضغط 1 للعربية 0 للمساعدة 2 لإلغاء الاشتراك 1 للتأكيد .\n" +
                    "\n", 'n', "رصيد", "9900"
        ),


        )


    private val SabaPhone: List<CodeModel> = listOf(

        CodeModel(
            context.getString(R.string.borrow_me),
            context.getString(R.string.borrow_me_DES),
            '1',
            "*100*1%23"
        ),
        CodeModel(
            context.getString(R.string.please_call_me),
            context.getString(R.string.please_call_me_DES), 'c', "*505*", "%23"
        ),
        CodeModel(
            context.getString(R.string.balance_inquiry),
            context.getString(R.string.balance_inquiry_DES),
            '1',
            "*102%23"
        ),


        CodeModel(
            context.getString(R.string.my_number_inquiry),
            context.getString(R.string.my_number_inquiry_SABAPHONE_DES), '1', "211"
        ),


        CodeModel(
            context.getString(R.string.call_at_my_cost),
            context.getString(R.string.call_at_my_cost_DES), 'c', "*9", ""
        ),

        CodeModel(
            context.getString(R.string.transform_balance),
            "تحويل الرصيد الى حساب رقم اخر ", 't', "*123*", "%23",
        ),
        CodeModel(
            "تغيير الرقم السري",
            "يجب الا تنسى الرقم الجديد ", 'x', "*103*", "%23",
        ),

        CodeModel(
           "خاصية إنتظار المكالمات",
            "تسمح هذه الخدمة بالتحدث مع أكثر من شخص في آنٍ واحد، إذ يستطيع المشترك إدارة مجموعة مكالمات يصل عدد المشاركين فيها إلى 5 أطراف في نفس الوقت" +
                    "\n" +
                    "بعد وضع المكالمة الحالية قيد الإنتظار.\n" +
                    "\n" +
                    "–  قم بإجراء مكالمة جديدة.\n" +
                    "\n" +
                    "–  عند الرد على المكالمة اضغط الرقم 3 ثم زرالتأكيد، أو من الخيارات اختر مكالمة جماعية",
            '1',
            "*100*1%23"
        ),

        CodeModel(
            context.getString(R.string.bill_inquiry),
            context.getString(R.string.bill_inquiry_DES), 'm', "ف", "1055"
        ),

        CodeModel(
            context.getString(R.string.doing_local_queries),
            context.getString(R.string.doing_local_queries_DES), '1', "118"
        ),

        CodeModel(
            context.getString(R.string.customers_service),
            context.getString(R.string.customers_service_DES),
            '1',
            "211"
        ),








        )


    private val you: List<CodeModel> = listOf(

        CodeModel(
            context.getString(R.string.borrow_me),
            context.getString(R.string.borrow_me_DES),
            '1',
            "*202%23"
        ),
        CodeModel(
            context.getString(R.string.please_call_me),
            "  تتيح لمشتركي الدفع المسبق إرسال رسائل مجانية محتواها “يرجى الإتصال بي” إلى جميع الشبكات المحلية ثلاث مرات في اليوم الواحد “مجاناً”",
            'M',
            "1005"
        ),
        CodeModel(
            context.getString(R.string.balance_inquiry),
            context.getString(R.string.balance_inquiry_DES),
            '1',
            "*144%23"
        ),



        CodeModel(
            context.getString(R.string.transform_balance),
            " هذه الخدمة تمكن مشتركي خدمة الدفع المسبق من تحويل رصيد إلى أي رقم دفع مسبق آخر في نفس الشبكة،بالإمكان تحويل رصيد 410 ريال كحد أقصى وأدنى ويتم خصم 25 ريالاً عند كل عملية تحويل (لتفعيل هذه الخدمة لابد من تفعيل خدمة سوبر كاشف)"
                    , 'T', "*130*", "%23",
        ),

        CodeModel(
            "خدمة خبرهم",
            "هذه الخدمة تمكن مشتركي الدفع المسبق و الفوترة من اختيار 3 أرقام مفضلة داخل الشبكة والاتصال بهم بـ 1 ريال في الدقيقة"

            ,
            '1',
            "*135%23"
        ),


        CodeModel(
            "خدمة الأهل والأصدقاء",
            "هذه الخدمة مجانا حيث تمكن جميع مشتركي YOU بنظامي الفوترة والدفع المسبق من إشعار كل من حاول الإتصال بهم (في حال إغلاق الخط او الخروج عن نطاق التغطية) بأنهم قد عادوا للشبكة وبإمكان من إتصل بهم التواصل معهم مجدداً. ",
            '6',
            ""
        ),

        CodeModel(
            "خدمة الشاحن الذكي",
            "إشتري رصيد واستخدمه وقت ما تريد\n" +
                    "\n" +
                    "مع خدمة “الشاحن الذكي” من YOU يمكنك شراء كروت أو فئات شحن ذكية عبر خدمة الشاحن الفوري.\n" +
                    "\n" +
                    "عند شرائك أي من فئات الشاحن الذكي سوف تصلك رسالة نصية بتفاصيل رقم التعبئة (الشحن) الذكي والتي تحتوي على الرقم السري القابل للشحن",
            'v',
            "*334*","%23"
        ),

        CodeModel(
            "خدمة ثمار",
            "خدمة ثمار المجانية من YOU تقدّم فرصة تجميع نقاط بناء على نسبة الإتصالات ورسائل الـ SMS التي تم اجراؤها ضمن الشبكة خلال مدة ستة أشهر. اذا لم يستفد منها خلال هذه الفترة سيتم إلغاء كافة النقاط التي تم تجميعها في الشهر الأول والبدء من جديد بتكوين نقاط جديدة , يحصل المشترك على نسبة تتراوح بين ( 4% إلى 25%) بحسب سنة تفعيل الخط. ((للإشتراك في الخدمة أو معرفة رصيد ثمار أو استبدال الثمار اضغط على الزر  ثم اضغط الرقم 1 ثم الرقم 4 وأتبع التعليمات.))"


            ,
            '1',
            "111"
        ),
        CodeModel(
            "وضع المكالمات في الانتظار",
            "وضع المكالمة الحالية في وضع الانتظار مؤقتاً وذلك كي يتسنى لهم استقبال مكالمة أو إجراء مكالمة اخرى في الوقت نفسه."



            ,
            '2',
            "*43%23","%2343%23"
        ),



        )



    val mobile_groups = listOf(
        SabaPhone,
        YemenMobile,
        you,



    )


}


data class CodeModel(

    val title: String,
    val description: String,
    val type: Char, //[0] only description only [1] call only,  [2] for activate/deactivate,
                    // [c] select contact to call,  [m] send sms message ,

                    //[f] for forwarding messages (2 buttons + contact textField)  , [M] send contact as message  to code number
                    //[t] (SabaPHONE) transform balance (*130c*v#)2 textField a button [T] (You) transform balance [x] sabaPhone Change pin
                    //[6] 6 buttons family YOU , [v]
    val code1: String,
    val code2: String = "",


    )




data class SocialMedia(val name: String, val icon: Int)
data class MyTheme(
    val primary :Color,
    val    background   :Color,
    val    onPrimary    :Color,

        )





val sTheme = MyTheme(
    primary = Color(0xFF034B98),

    background = Color(0xFFFFFFFF),

    onPrimary = Color(0xFFFFFFFF),

)
val yemTheme = MyTheme(
    primary = Color(0xFFBE074C),
    background = Color(0xFFFCD9B4),
    onPrimary = Color(0xFFFFBF00),

)
val youTheme = MyTheme(
    primary = Color(0xFF000000),
    background = Color(0xFFFFCC4D),
    onPrimary = Color(0xFFFFBF00),
    )
val myThemes = listOf(
    sTheme, yemTheme, youTheme

)
