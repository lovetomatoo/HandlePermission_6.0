package com.ghx.handlepermission_60.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.ghx.handlepermission_60.R;
import com.ghx.handlepermission_60.utils.ExtendMediaPicker;

/**
 * Created by guo_hx on 2016/10/11.13:21
 * 公司在调整，闲来无事啊~~~~
 */
public class RxPermissionActivity extends AppCompatActivity implements View.OnClickListener {

    /*
                           _ooOoo_
                          o8888888o
                          88" . "88
                          (| -_- |)
                          O\  =  /O
                       ____/`---'\____
                     .'  \\|     |//  `.
                    /  \\|||  :  |||//  \
                   /  _||||| -:- |||||-  \
                   |   | \\\  -  /// |   |
                   | \_|  ''\---/''  |   |
                   \  .-\__  `-`  ___/-. /
                 ___`. .'  /--.--\  `. . __
              ."" '<  `.___\_<|>_/___.'  >'"".
             | | :  `- \`.;`\ _ /`;.`/ - ` : | |
             \  \ `-.   \_ __\ /__ _/   .-` /  /
        ======`-.____`-.___\_____/___.-`____.-'======
                           `=---='
        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                 佛祖保佑       永无BUG
        */

    private ImageView mIvShow;
    private Button mBtnSelector;
    private ExtendMediaPicker mMediaPicker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxpermission);

        initView();
        initData();
        initListener();
    }

    private void initView() {

        mIvShow = (ImageView) findViewById(R.id.iv_show);
        mBtnSelector = (Button) findViewById(R.id.btn_select);
    }

    private void initData() {

        mMediaPicker = new ExtendMediaPicker(this);

        mIvShow.setImageResource(R.mipmap.default_null);
    }

    private void initListener() {

        mBtnSelector.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_select: {

                toPickPicture();
            }
            break;
        }
    }

    private void toPickPicture() {
        Toast.makeText(getApplicationContext(), "嘀嘀嘀", Toast.LENGTH_SHORT).show();
    }

}



/**
 *   ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 *    ┃　　　┃
 *    ┃　　　┃
 *    ┃　　　┗━━━┓
 *    ┃　　　　　　　┣┓
 *    ┃　　　　　　　┏┛
 *    ┗┓┓┏━┳┓┏┛
 *      ┃┫┫　┃┫┫
 *      ┗┻┛　┗┻┛
 *        神兽保佑
 *        代码无BUG!
 */



//                      d*##$.
// zP"""""$e.           $"    $o
//4$       '$          $"      $
//'$        '$        J$       $F
// 'b        $k       $>       $
//  $k        $r     J$       d$
//  '$         $     $"       $~
//   '$        "$   '$E       $
//    $         $L   $"      $F ...
//     $.       4B   $      $$$*"""*b
//     '$        $.  $$     $$      $F
//      "$       R$  $F     $"      $
//       $k      ?$ u*     dF      .$
//       ^$.      $$"     z$      u$$$$e
//        #$b             $E.dW@e$"    ?$
//         #$           .o$$# d$$$$c    ?F
//          $      .d$$#" . zo$>   #$r .uF
//          $L .u$*"      $&$$$k   .$$d$$F
//           $$"            ""^"$$$P"$P9$
//          JP              .o$$$$u:$P $$
//          $          ..ue$"      ""  $"
//         d$          $F              $
//         $$     ....udE             4B
//          #$    """"` $r            @$
//           ^$L        '$            $F
//             RN        4N           $
//              *$b                  d$
//               $$k                 $F
//               $$b                $F
//                 $""               $F
//                 '$                $
//                  $L               $
//                  '$               $
//                   $               $


/*
                           _
                           \"-._ _.--"~~"--._
                            \   "            ^.    ___
                            /                  \.-~_.-~
                     .-----'     /\/"\ /~-._      /
                    /  __      _/\-.__\L_.-/\     "-.
                   /.-"  \    ( ` \_o>"<o_/  \  .--._\
                  /'      \    \:     "     :/_/     "`
                          /  /\ "\    ~    /~"
                          \ I  \/]"-._ _.-"[
                       ___ \|___/ ./    l   \___   ___
                  .--v~   "v` ( `-.__   __.-' ) ~v"   ~v--.
               .-{   |     :   \_    "~"    _/   :     |   }-.
              /   \  |           ~-.,___,.-~           |  /   \
             ]     \ |                                 | /     [
             /\     \|     :                     :     |/     /\
            /  ^._  _K.___,^                     ^.___,K_  _.^  \
           /   /  "~/  "\                           /"  \~"  \   \
          /   /    /     \ _          :          _ /     \    \   \
        .^--./    /       Y___________l___________Y       \    \.--^.
        [    \   /        |        [/    ]        |        \   /    ]
        |     "v"         l________[____/]________j  -Row   }r"     /
        }------t          /                       \       /`-.     /
        |      |         Y                         Y     /    "-._/
        }-----v'         |         :               |     7-.     /
        |   |_|          |         l               |    / . "-._/
        l  .[_]          :          \              :  r[]/_.  /
         \_____]                     "--.             "-.____/

                                            "Dragonball Z"
                                                        ---Row

        */



        /*
                                MMMMM
                                  MMMMMM
                                    MMMMMMM
                                     MMMMMMMM     .
                                      MMMMMMMMM
                                      HMMMMMMMMMM
                                       MMMMMMMMMMMM  M
                                       MMMMMMMMMMMMM  M
                                        MMMMMMMMMMMMM  M
                                        MMMMMMMMMMMMM:
                                        oMMMMMMMMMMMMMM
              .MMMMMMMMMMMMMMo           MMMMMMMMMMMMMMM M
        MMMMMMMMMMMMMMMMMMMMMMMMMMM      MMMMMMMMMMMMMMMM
          MMMMMMMMMMMMMMMMMMMMMMMMMMMM.  oMMMMMMMMMMMMMMM.M
            MMMMMMMMMMMMMMMMMMMMMMMMMMMM  MMMMMMMMMMMMMMMM
              MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
                oMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
                  MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
                    MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM:                     H
                     MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM                  .         MMM
                      MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM              M       MMMMMM
                       .MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM          M   MMMMMMMMMM
                MM.      MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM       M MMMMMMMMMMMM
                    MM    MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM    .MMMMMMMMMMMMMM
                      MM  MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
                        MM MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
               .MMMMMMMMM MMMMMMMMMMMMMMMMMMMMMMMM.MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
                  HMMMMMMMMMMMMMMMMMMMMM.MMMMMMMMM.MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
                     MMMMMMMMMMMMMMM MMM.oMMMMMMM..MMMMMMMMM:MMMMMMMMMMMMMMMMMMMMMMM
                       MMMMMMMMMMMMMM MM..MMMMMMM...MMMMMMM. MMMMMMMMMMMMMMMMMMMMM
                         MMMMMMMMMMMMMMM ..MMMMMM...MMMMMM ..MMMMMMMMMMMMMMMMMMM
                          MMMMMMM:M.MMM.M.. MMMMM M..MMMMM...MMMMMMMMMMMMMMMMMM  MMM
                            MMMM. .M..MM.M...MMMMMM..MMMMM.. MMMMMMMMMMMMMMMMMMMMMMMMMMMMMM .
                             MMMM..M....M.....:MMM .MMMMMM..MMMMMMM...MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
                              MMM.M.. ...M......MM.MMMMM.......MHM.M  .MMMMMMMMMMMMMMMMMMMMMMMMM
                         MMMMMMMM..MM. . MMM.....MMMMMM.M.....M ..MM..M MMMMMMMMMMMMMMMMMMM
                            .MMMMMHMM. ..MMMM. MMM............o..... . .MMMMMMMMMMMMMMM
                               MMM. M... .........................M..:.MMMMMMMMMMMM
                                 oMMM............ .................M.M.MMMMMMMMM
                                    .....MM........................ . MMMMMM
                                   M.....M.....................o.MM.MMMMMMMM.
                                    M........................M.. ...MMMMMMMMMMMMMo
                                      :....MMM..............MMM..oMMMMMMM
                                       M...MMM.............MMMMMMM
                                          .............:MMMMMMMM
                                          M..... MMM.....M
                                          M M.............
                                          ................M
                                       ooM.................MM  MoMMMMMoooM
                                  MMoooM......................MoooooooH..oMM
                              MHooooMoM.....................MMooooooM........M
                            oooooooMoooM......... o........MoooooooM............
                            Mooooooooooo.......M.........Moooooooo:..............M
                           MooMoooooooooM...M........:Mooooooooooo:..............M
                          M..oooooooooooo .........Mooooooooooooooo..............M
                         M...Mooo:oooooooo.M....ooooooooooooooooooo..M...........M
                          ...oooooMoooooooM..Mooooooooooooo:oooooooM.M...........M.
                         M...ooooooMoo:ooooMoooooooooooooHoooooooooH:M. ...........:
                         M..MoooooooMoooooooooooooooooo:ooooooMooooMoM..............M
                         M..ooooooooooMooooooooooooooHoooooooMooHooooM...............M
                         ...ooooooooooooooooooo:MooooooooooooooMoMoooM................
                        M...oooooooooooooooooooooooooooooooooooooMooMM................M
                        ...MooooooooooooooooooooooooooooooooooooooooMo ................
                        ...MooooooooooooooooooooooooooooooooooooooooM M................M
                       M...ooooooooooooooooooooooooooooooooooooooooM   ................M
                       ...MoooooooooooooooooooooooooooooooooooooooMM   .:...............
                       .....MooooooooooooooooooooooooooooooooooooMoo       .............M
                       M...... ooooooooooooooooooooooooooooooooooooM       M..............M
                       M........MooooMMM MM MM  MMMMMMMMMooooooooM         M...............M
                       .........HM     M:  MM :MMMMMM          M           M...............
                      M..........M     M   MoM M                           M................M
                      M.........:M  MoH  M M M MooooHoooMM.   M             M...............M
                      M..........Moooo MMooM    oooooMooooooooM              M..............H
                      M.........MooooM  Mooo  : ooooooMooooMoooM              M........ . .o.M
                      H..  .....ooooo   oooo  M MooooooooooooooM               M... MMMMMMMMMMM
                      MMMMMMMMMMooooM M oooo  .  ooooooMooooooooM              .MMMMMMMMMMMMMMM
                      MMMMMMMMMMooooH : ooooH    oooooooooooooooo               MMMMMMMMMMMMMMM
                      MMMMMMMMMMoooo    ooooM    Moooooooooooooooo              .MMMMMMMMMMMMMMM
                      MMMMMMMMMMoooo    ooooM    MooooooooooooooooM              MMMMMMMMMMMMMMM
                      MMMMMMMMMMoooM    ooooM     ooooooooooooooooo               MMMMMMMMMMM:M
                      MMMMMMMMMMoooM   MooooM     oooooooooooMoooooo               MH...........
                       . ......Mooo.   MooooM     oooooooooooooooooo              M............M
                      M.M......oooo    MooooM     Moooooooooooooooooo:           .........M.....
                      M.M.....Moooo    MooooM      ooooooooooooooooooM            .M............
                      .......MooooH    MooooM      oooooooooMoooooooooo          M..o...M..o....M
                      .o....HMooooM    MooooH      MooooooooMooooooooooM          .:M...M.......M
                     M..M.....MoooM    :oooo:    .MooooooooHooMoooooooooM         M M... ..oM.M
                      M...M.:.Mooo. MMMMooooo   oooooooooooMoooooooooooooM          ....M. M
                       M:M..o.Moooooooooooooo MooooooooooooooMooooooooooooM          .Mo
                              MooooooooooooooMooooooooooooMoMoooooooooooooo
                              Mooooooooooooooo:ooooooooooooooooooooooooooooo
                              ooooooooooooooooMooooooooooMoooooooooooooooooo
                              ooooooooooooooooMoooooooooooMooooooooooooooooHo
                              ooMooooooooooooooMoooooooooooooooooooooooooooMoM
                             MooMoooooooooooooo.ooooooooooooooooooooooooooo:oM
                             MoooooooooooooooooooooooooooooooooooooooooooooooM
                             MoooMooooooooooooooMooooooooooooooooooooooooooooo.
                             MoooMooooooooooooooMoooooooooooooooooooooooooMooooM
                             MooooooooooooooooooMoooooooooooooooooooooooooMoooooM
                             MooooMoooooooooooooMoooooooooooooooooooooooooMoHooooM
                             ooooooMooooooooooooooooooooooooooooooooooooooooMoMoooM
                            MooooooooooooooooooooMooooooooooooooooooooooooooMoooooH:
                            MoooooooMooooooooooooMoooooooooooooooooooooooooooooHoooM
                            MooooooooMoooooooooooMoooooooooooooooooooooooooMoooMooooM
                            Moooooooooooooooooooooooooooooooooooooooooooooo.oooMooooo
                            MoooooooooooooooooooooooooooooooooooooooooooooMoooooooooM
                             MooooooooooooooooooooMoooooooooooooooooooooooooooooooooM
                              MooooooooooooooooooooMHooooooooooooooooooooMoooo:ooooo
                               MMooooooooooooooooooMoMHoooooooooooooooooooooooMooooo
                                MMoooooooooooooooMMooo MMooooooooooooooooooooooooooM
                                MMMoooooooooooooMooooo  oooooooooooooooooooooMooooo
                                MooMMoooooooooMoooMMoM  ooooHooooooooooooooooMooooM
                                MooooMooooooMooooMoooM  MoooooMoooooooooooooMooooo
                                ooooooMMooooooooMooooM  MoooooooooMooooooooooooooM
                                HooooooMoooooooMooooM    HoooooooHooMooooooooooooo
                                 oooMoooooooooHoooM         MoooooooooMoooooooooM
                                  HooooooooooooHM             MooooooooMMoooooooM
                                   MMMMMMMMMMMMMM                Moooooo:MooooHMM
                                    MMMMMMM: ...                  MMMMMMMMMMMMMM
                                   M............M                  MMMMMMMMM ....
                                   M.MM..........                  M.............M
                                M ..............MM                 M..............
                             MMMMM............MMMM                 ..MMMMMMMM ....M
                           MMMMMMMMMMMMMMMMMMMMMMMM               MMMMMMMMMMMMM...M
                        .MMMMMMMMMMMMMMMMMMMMMMMMMM               MMMMMMMMMMMMMMMMMM
                        MMMMMMMMMMMMMMMMMMMMMMMMM                MMMMMMMMMMMMMMMMMMM
                        :MMMMMMMMMMMMMMMMMMH                     MMMMMMMMMMMMMMMMMMM
                           By EBEN Jérôme                        MMMMMMMMMMMMMMMMMM
                                                                 MMMMMMMMMMMMMMM
                                                                  HMMMMMM

        */





        /*
        quu..__
         $$$b  `---.__
          "$$b        `--.                          ___.---uuudP
           `$$b           `.__.------.__     __.---'      $$$$"              .
             "$b          -'            `-.-'            $$$"              .'|
               ".                                       d$"             _.'  |
                 `.   /                              ..."             .'     |
                   `./                           ..::-'            _.'       |
                    /                         .:::-'            .-'         .'
                   :                          ::''\          _.'            |
                  .' .-.             .-.           `.      .'               |
                  : /'$$|           .@"$\           `.   .'              _.-'
                 .'|$u$$|          |$$,$$|           |  <            _.-'
                 | `:$$:'          :$$$$$:           `.  `.       .-'
                 :                  `"--'             |    `-.     \
                :##.       ==             .###.       `.      `.    `\
                |##:                      :###:        |        >     >
                |#'     `..'`..'          `###'        x:      /     /
                 \                                   xXX|     /    ./
                  \                                xXXX'|    /   ./
                  /`-.                                  `.  /   /
                 :    `-  ...........,                   | /  .'
                 |         ``:::::::'       .            |<    `.
                 |             ```          |           x| \ `.:``.
                 |                         .'    /'   xXX|  `:`M`M':.
                 |    |                    ;    /:' xXXX'|  -'MMMMM:'
                 `.  .'                   :    /:'       |-'MMMM.-'
                  |  |                   .'   /'        .'MMM.-'
                  `'`'                   :  ,'          |MMM<
                    |                     `'            |tbap\
                     \                                  :MM.-'
                      \                 |              .''
                       \.               `.            /
                        /     .:::::::.. :           /
                       |     .:::::::::::`.         /
                       |   .:::------------\       /
                      /   .''               >::'  /
                      `',:                 :    .'
                                           `:.:'
        */