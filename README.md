# AdvancedWhoisPlus-Reloaded
プレイヤー情報表示プラグイン

### 【機能】
プレイヤーに関する情報を表示します。
Essentialsの/whoisからプラグイン依存の情報を除き、
ちょっとした有用な情報を表示します。
AdminCmd等別管理プラグインを入れておりEssentialsが使えない人などにもおすすめです。
以前のものを改良しました。

### 【コマンド】
- /whoisps help ・・・ ヘルプを表示します。
- /whoisps <playerID>  ・・・ プレイヤー情報を表示します。
- /whoisps checkver ・・・ 更新がないか確認します。
- /whoisps reload ・・・ Configをリロードします。

### 【Permissions】
- advwhois.* ・・・ 下記権限を全て許可します。
- advwhois.whoisps ・・・ プレイヤー情報表示を許可します。
- advwhois.joinshow ・・・ プレイヤーがログインした時に簡易情報を表示します。
- advwhois.bypass ・・・ 上記権限を所持したプレイヤーに情報が表示されないようにします。
- advwhois.ipshow ・・・ 簡易情報にIPを表示します。
- advwhois.reload ・・・ リロードを許可します。
- advwhois.updateinfo ・・・ プラグインの更新情報が表示されるようにします。

### 【Config】
	# AdvancedWhoisPlus #

	EnableJoinMassage: true
	AdditionalWhoisInfo: true

	# プレイヤー接続時に表示される簡易Whoisのモード設定です。
	# 1でIPが、2でHostNameが、3で両方表示されます。
	SimplicityWhoisMode: 1

	# %Player%を任意の場所に入れることでプレイヤー名を入れることができます。
	# %JCountryC%を任意の場所に入れることで接続国コードを入れることができます。
	JoinMassage: "&e%Player% has joined this server! - from %JCountryC%"

	debug: false

	# ここから下の行は編集しないでください
	###################

##LICENSE
	Copyright 2016 fumyatan

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

	   http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.