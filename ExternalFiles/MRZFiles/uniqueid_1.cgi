#!C:\perl\bin\perl.exe -wT

use CGI qw(param);
use strict;
#use win32;


# You can optionally set this to the URL to a CSS file to
# use to style the output.
my $CSS_URL = undef;
#$CSS_URL = 'http://www.this.is.an.example.com/uniqueid.css';


$CGI::POST_MAX = 1024*64; # Ensure we don't read bunches of garbage.
$CGI::DISABLE_UPLOADS = 1; # There is no reason to accept uploads.

my $PROGRAM_NAME = 'Unique ID';
my $COPYRIGHT_YEAR = '2004';
my $VERSION;
BEGIN {
	my $ASSIGNED_VERSION = '0.5.1';
	my $CVS_VERSION = '$Revision: 1.60 $';
	my($cvs_bits) = ($CVS_VERSION =~ /Revision: ([\d.]+)/);
	$VERSION = "$ASSIGNED_VERSION/$cvs_bits";
}
#===============================================================================
my(%DLFMR_FIRST_MALE) = (
	20=>['Albert'], 40=>['Arthur'], 80=>['Bernard'], 120=>['Carl'],
	140=>['Charles'], 180=>['Donald'], 220=>['Edward'], 260=>['Frank'],
	300=>['George'], 340=>['Harold'], 360=>['Harry'], 380=>['Henry'],
	440=>['James'], 460=>['John'], 480=>['Joseph'], 560=>['Martin'],
	580=>['Marvin'], 600=>['Melvin'], 680=>['Paul'], 740=>['Richard'],
	760=>['Robert'], 820=>['Thomas'], 900=>['Walter'], 920=>['William'],
);
my(%DLFMR_FIRST_FEMALE) = (
	20=>['Alice'], 40=>['Ann', 'Anna', 'Anne', 'Annie'],
	80=>['Bette', 'Bettie', 'Betty'], 120=>['Catherine'],
	140=>['Clara'], 180=>['Dorothy'], 220=>['Elizabeth'],
	260=>['Florence'], 300=>['Grace'], 340=>['Harriet'],
	360=>['Hazel'], 380=>['Helen'], 440=>['Jane', 'Jayne'],
	460=>['Jean'], 480=>['Joan'], 580=>['Mary'], 600=>['Mildred'],
	680=>['Patricia'], 740=>['Ruby'], 760=>['Ruth'], 820=>['Thelma'],
	900=>['Wanda'], 920=>['Wilma'],
);
my(%DLFMR_FIRST_INIT) = (
	0 => 'A', 60 => 'B', 100 => 'C', 160 => 'D', 200 => 'E', 240 => 'F',
	280 => 'G', 320 => 'H', 400 => 'I', 420 => 'J', 500 => 'K', 520 => 'L',
	540 => 'M', 620 => 'N', 640 => 'O', 660 => 'P', 700 => 'Q', 720 => 'R',
	780 => 'S', 800 => 'T', 840 => 'U', 860 => 'V', 880 => 'W', 940 => 'X',
	960 => 'Y', 980 => 'Z',
);
my(%DLFMR_MIDDLE_INIT) = (
	0 => [' ', ''],
	1 => [qw(A)], 2 => [qw(B)], 3 => [qw(C)], 4 => [qw(D)],
	5 => [qw(E)], 6 => [qw(F)], 7 => [qw(G)], 8 => [qw(H)],
	9 => [qw(I)], 10 => [qw(J)], 11 => [qw(K)], 12 => [qw(L)],
	13 => [qw(M)], 14 => [qw(N O)], 15 => [qw(P Q)], 16 => [qw(R)],
	17 => [qw(S)], 18 => [qw(T U V)], 19 => [qw(W X Y Z)],
);

my(%DLFM_FIRST_MALE) = uc_hash_keys(reverse_plural_hash(%DLFMR_FIRST_MALE));
my(%DLFM_FIRST_FEMALE) = uc_hash_keys(reverse_plural_hash(%DLFMR_FIRST_FEMALE));
my(%DLFM_FIRST_INIT) = uc_hash_keys(reverse_hash(%DLFMR_FIRST_INIT));
my(%DLFM_MIDDLE_INIT) = uc_hash_keys(reverse_plural_hash(%DLFMR_MIDDLE_INIT));

# Machine Readable Travel Documents Country Codes
my(%COUNTRY_TO_ALPHA3) = (
	"Afghanistan" => 'AFG',
	"Albania" => 'ALB',
	"Algeria" => 'DZA',
	"American Samoa" => 'ASM',
	"Andorra" => 'AND',
	"Angola" => 'AGO',
	"Anguilla" => 'AIA',
	"Antarctica" => 'ATA',
	"Antigua and Barbuda" => 'ATG',
	"Argentina" => 'ARG',
	"Armenia" => 'ARM',
	"Aruba" => 'ABW',
	"Australia" => 'AUS',
	"Austria" => 'AUT',
	"Azerbaijan" => 'AZE',
	"Bahamas" => 'BHS',
	"Bahrain" => 'BHR',
	"Bangladesh" => 'BGD',
	"Barbados" => 'BRB',
	"Belarus" => 'BLR',
	"Belgium" => 'BEL',
	"Belize" => 'BLZ',
	"Benin" => 'BEN',
	"Bermuda" => 'BMU',
	"Bhutan" => 'BTN',
	"Bolivia" => 'BOL',
	"Bosnia and Herzegovina" => 'BIH',
	"Botswana" => 'BWA',
	"Bouvet Island" => 'BVT',
	"Brazil" => 'BRA',
	"British Indian Ocean Territory" => 'IOT',
	"Brunei Darussalam" => 'BRN',
	"Bulgaria" => 'BGR',
	"Burkina Faso" => 'BFA',
	"Burundi" => 'BDI',
	"Cambodia" => 'KHM',
	"Cameroon" => 'CMR',
	"Canada" => 'CAN',
	"Cape Verde" => 'CPV',
	"Cayman Islands" => 'CYM',
	"Central African Republic" => 'CAF',
	"Chad" => 'TCD',
	"Chile" => 'CHL',
	"China" => 'CHN',
	"Christmas Island" => 'CXR',
	"Cocos (Keeling) Islands" => 'CCK',
	"Colombia" => 'COL',
	"Comoros" => 'COM',
	"Congo" => 'COG',
	"Cook Islands" => 'COK',
	"Costa Rica" => 'CRI',
	"C&ocirc;te d'Ivoire" => 'CIV',
	"Croatia" => 'HRV',
	"Cuba" => 'CUB',
	"Cyprus" => 'CYP',
	"Czech Republic" => 'CZE',
	"Democratic People's Republic of Korea" => 'PRK',
	"Democratic Republic of the Congo" => 'COD',
	"Denmark" => 'DNK',
	"Djibouti" => 'DJI',
	"Dominica" => 'DMA',
	"Dominican Republic" => 'DOM',
	"East Timor" => 'TMP',
	"Ecuador" => 'ECU',
	"Egypt" => 'EGY',
	"El Salvador" => 'SLV',
	"Equatorial Guinea" => 'GNQ',
	"Eritrea" => 'ERI',
	"Estonia" => 'EST',
	"Ethiopia" => 'ETH',
	"Falkland Islands (Malvinas)" => 'FLK',
	"Faeroe Islands" => 'FRO',
	"Fiji" => 'FJI',
	"Finland" => 'FIN',
	"France" => 'FRA',
	"France, Metropolitan" => 'FXX',
	"French Guiana" => 'GUF',
	"French Polynesia" => 'PYF',
	"Gabon" => 'GAB',
	"Gambia" => 'GMB',
	"Georgia" => 'GEO',
	"Germany" => 'D',
	"Ghana" => 'GHA',
	"Gibraltar" => 'GIB',
	"Greece" => 'GRC',
	"Greenland" => 'GRL',
	"Grenada" => 'GRD',
	"Guadeloupe" => 'GLP',
	"Guam" => 'GUM',
	"Guatemala" => 'GTM',
	"Guinea" => 'GIN',
	"Guinea-Bissau" => 'GNB',
	"Guyana" => 'GUY',
	"Haiti" => 'HTI',
	"Heard and McDonald Islands" => 'HMD',
	"Holy See (Vatican City State)" => 'VAT',
	"Honduras" => 'HND',
	"Hong Kong" => 'HKG',
	"Hungary" => 'HUN',
	"Iceland" => 'ISL',
	"India" => 'IND',
	"Indonesia" => 'IDN',
	"Iran, Islamic Republic of" => 'IRN',
	"Iraq" => 'IRQ',
	"Ireland" => 'IRL',
	"Israel" => 'ISR',
	"Italy" => 'ITA',
	"Jamaica" => 'JAM',
	"Japan" => 'JPN',
	"Jordan" => 'JOR',
	"Kazakhstan" => 'KAZ',
	"Kenya" => 'KEN',
	"Kiribati" => 'KIR',
	"Kuwait" => 'KWT',
	"Kyrgyzstan" => 'KGZ',
	"Lao People's Democratic Republic" => 'LAO',
	"Latvia" => 'LVA',
	"Lebanon" => 'LBN',
	"Lesotho" => 'LSO',
	"Liberia" => 'LBR',
	"Libyan Arab Jamahiriya" => 'LBY',
	"Liechtenstein" => 'LIE',
	"Lithuania" => 'LTU',
	"Luxembourg" => 'LUX',
	"Madagascar" => 'MDG',
	"Malawi" => 'MWI',
	"Malaysia" => 'MYS',
	"Maldives" => 'MDV',
	"Mali" => 'MLI',
	"Malta" => 'MLT',
	"Marshall Islands" => 'MHL',
	"Martinique" => 'MTQ',
	"Mauritania" => 'MRT',
	"Mauritius" => 'MUS',
	"Mayotte" => 'MYT',
	"Mexico" => 'MEX',
	"Micronesia, Federated States of" => 'FSM',
	"Monaco" => 'MCO',
	"Mongolia" => 'MNG',
	"Montserrat" => 'MSR',
	"Morocco" => 'MAR',
	"Mozambique" => 'MOZ',
	"Myanmar" => 'MMR',
	"Namibia" => 'NAM',
	"Nauru" => 'NRU',
	"Nepal" => 'NPL',
	"Netherlands, Kingdom of the" => 'NLD',
	"Netherlands Antilles" => 'ANT',
	"Neutral Zone" => 'NTZ',
	"New Caledonia" => 'NCL',
	"New Zealand" => 'NZL',
	"Nicaragua" => 'NIC',
	"Niger" => 'NER',
	"Nigeria" => 'NGA',
	"Niue" => 'NIU',
	"Norfolk Island" => 'NFK',
	"Northern Mariana Islands" => 'MNP',
	"Norway" => 'NOR',
	"Oman" => 'OMN',
	"Pakistan" => 'PAK',
	"Palau" => 'PLW',
	"Panama" => 'PAN',
	"Papua New Guinea" => 'PNG',
	"Paraguay" => 'PRY',
	"Peru" => 'PER',
	"Philippines" => 'PHL',
	"Pitcairn" => 'PCN',
	"Poland" => 'POL',
	"Portugal" => 'PRT',
	"Puerto Rico" => 'PRI',
	"Qatar" => 'QAT',
	"Republic of Korea" => 'KOR',
	"Republic of Moldova" => 'MDA',
	"R&eacute;union" => 'REU',
	"Romania" => 'ROM',
	"Russian Federation" => 'RUS',
	"Rwanda" => 'RWA',
	"Saint Helena" => 'SHN',
	"Saint Kitts and Nevis" => 'KNA',
	"Saint Lucia" => 'LCA',
	"Saint Pierre and Miquelon" => 'SPM',
	"Saint Vincent and the Grenadines" => 'VCT',
	"Samoa" => 'WSM',
	"San Marino" => 'SMR',
	"Sao Tome and Principe" => 'STP',
	"Saudi Arabia" => 'SAU',
	"Senegal" => 'SEN',
	"Seychelles" => 'SYC',
	"Sierra Leone" => 'SLE',
	"Singapore" => 'SGP',
	"Slovakia" => 'SVK',
	"Slovenia" => 'SVN',
	"Solomon Islands" => 'SLB',
	"Somalia" => 'SOM',
	"South Africa" => 'ZAF',
	"South Georgia and the South Sandwich Island" => 'SGS',
	"Spain" => 'ESP',
	"Sri Lanka" => 'LKA',
	"Sudan" => 'SDN',
	"Suriname" => 'SUR',
	"Svalbard and Jan Mayen Islands" => 'SJM',
	"Swaziland" => 'SWZ',
	"Sweden" => 'SWE',
	"Switzerland" => 'CHE',
	"Syrian Arab Republic" => 'SYR',
	"Taiwan Province of China" => 'TWN',
	"Tajikistan" => 'TJK',
	"Thailand" => 'THA',
	"The former Yugoslav Republic of Macedonia" => 'MKD',
	"Togo" => 'TGO',
	"Tokelau" => 'TKL',
	"Tonga" => 'TON',
	"Trinidad and Tobago" => 'TTO',
	"Tunisia" => 'TUN',
	"Turkey" => 'TUR',
	"Turkmenistan" => 'TKM',
	"Turks and Caicos Islands" => 'TCA',
	"Tuvalu" => 'TUV',
	"Uganda" => 'UGA',
	"Ukraine" => 'UKR',
	"United Arab Emirates" => 'ARE',
	"United Kingdom of Great Britain and Northern Ireland - Citizen" => 'GBR',
	"United Kingdom of Great Britain and Northern Ireland - Dependent territories citizen" => 'GBD',
	"United Kingdom of Great Britain and Northern Ireland - National (overseas)" => 'GBN',
	"United Kingdom of Great Britain and Northern Ireland - Overseas citizen" => 'GBO',
	"United Kingdom of Great Britain and Northern Ireland - Protected Person" => 'GBP',
	"United Kingdom of Great Britain and Northern Ireland - Subject" => 'GBS',
	"United Republic of Tanzania" => 'TZA',
	"United States of America" => 'USA',
	"United States of America Minor Outlying Islands" => 'UMI',
	"Uruguay" => 'URY',
	"Uzbekistan" => 'UZB',
	"Vanuatu" => 'VUT',
	"Venezuela" => 'VEN',
	"Viet Nam" => 'VNM',
	"Virgin Islands (Great Britian)" => 'VGB',
	"Virgin Islands (United States)" => 'VIR',
	"Wallis and Futuna Islands" => 'WLF',
	"Western Sahara" => 'ESH',
	"Yemen" => 'YEM',
	"Zaire" => 'ZAR',
	"Zambia" => 'ZMB',
	"Zimbabwe" => 'ZWE',
	"United Nations" => 'UNO',
	"United Nations Specialized Agency" => 'UNA',
	"Stateless" => 'XXA',
	"Refugee (1951 convention)" => 'XXB',
	"Refugee (non-convention)" => 'XXC',
	"Unspecified / Unknown" => 'XXX',
	"Utopia (Example!)" => 'UTO',
);
my(%ALPHA3_TO_COUNTRY) = reverse_hash(%COUNTRY_TO_ALPHA3);
#===============================================================================
sub html_escape {
	local($_) = @_;
	if(not defined $_) { $_ = ''; }
	s/\&/\&amp;/g;
	s/\>/\&gt;/g;
	s/\</\&lt;/g;
	return $_;
}

#===============================================================================
sub uc_hash_keys {
	#Win32::MsgBox("Successfully copied");
	my(%hash) = @_;
	my %result;
	foreach my $key (keys %hash) {
		$result{uc($key)} = $hash{$key}
	}
	return %result;
}
#===============================================================================
sub reverse_plural_hash {
	my(%hash) = @_;
	my %result;
	foreach my $key (keys %hash) {
		foreach my $value (@{$hash{$key}}) {
			$result{$value} = $key;
		}
	}
	return %result;
}

#===============================================================================
sub reverse_hash {
	my(%hash) = @_;
	my %result;
	foreach my $key (keys %hash) {
		$result{$hash{$key}} = $key;
	}
	return %result;
}

#===============================================================================
sub english_list_join {
	my($word, @list) = @_;
	if(scalar @list == 0) {
		return '';
	}
	if(scalar @list == 1) {
		return $list[0];
	}
	if(scalar @list == 2) {
		return "$list[0] $word $list[1]";
	}
	my $tail = pop @list;
	return (join ', ', @list).", $word $tail";
}

#===============================================================================
sub dlfirstmiddle_middle_from_code {
	my($code) = @_;
	$code %= 20;
	if(not exists $DLFMR_MIDDLE_INIT{$code}) {
		return '';
	}
	my(@answer) = @{$DLFMR_MIDDLE_INIT{$code}};
	return english_list_join('or', @answer);
}

#===============================================================================
sub dlfirstmiddle_first_from_code {
	my($code, $gender) = @_;
	$code -= $code % 20;
	my(@female, @male);
	if(exists $DLFMR_FIRST_FEMALE{$code}) {
		@female = @{$DLFMR_FIRST_FEMALE{$code}};
	}
	if(exists $DLFMR_FIRST_MALE{$code}) {
		@male = @{$DLFMR_FIRST_MALE{$code}};
	}

	my(@probable, @improbable);
	if($gender =~ /^m/i) {
		@probable = @male;
		@improbable = @female;
	} elsif($gender =~ /^f/i) {
		@probable = @female;
		@improbable = @male;
	} else {
		@probable = (@male, @female);
	}

	if(not @probable and exists $DLFMR_FIRST_INIT{$code}) {
		@probable = ($DLFMR_FIRST_INIT{$code}.'.');
	}

	my $return = english_list_join('or', @probable);
	if(scalar @improbable) {
		$return .= ' (or possibly '.
			english_list_join('or', @improbable).')';
	}
	return $return;
}

#===============================================================================
sub dlfirstmiddle_encode {
	my($FN, $MiddleInit) = @_;
	my($NameNum) = 0;

	$FN = uc($FN);
	$MiddleInit = uc($MiddleInit);
	my($first_init)= ($FN =~ /^(.)/);

	if(exists $DLFM_FIRST_MALE{$FN}) {
		$NameNum = $DLFM_FIRST_MALE{$FN};
	} elsif(exists $DLFM_FIRST_FEMALE{$FN}) {
		$NameNum = $DLFM_FIRST_FEMALE{$FN};
	} elsif(exists $DLFM_FIRST_INIT{$first_init}) {
		$NameNum = $DLFM_FIRST_INIT{$first_init};
	} else {
		return 'BAD';
	}

	if(defined($DLFM_MIDDLE_INIT{$MiddleInit}))
	{ $NameNum += $DLFM_MIDDLE_INIT{$MiddleInit}; }
	else
	{ return('BAD'); }

	return sprintf("%03d",$NameNum);
}

#===============================================================================
sub validate_options
{
	my($params, $options) = @_;
	my(%params) = %{$params};
	my(@options) = @{$options};
	my($error, %error) = ('');
	foreach my $option (@options) {
		my($name, $key) = @{$option};
		my($optional) = $option->[4]->{'optional'};
		next if defined $optional and $optional;
		next if not defined $key or not length $key;
		my($value) = $params{$key};
		if(not defined $value or not length $value) {
			if(not defined $error{$key}) {
				$error{$key} = '';
			}
			$error{$key} .= "Please specify $name. ";
		}
	}
	return ($error, %error);
}

#===============================================================================
sub path_to_script {
	return $ENV{'SCRIPT_NAME'} || '';
}

#===============================================================================
sub format_label {
	my($label) = @_;
	my($indent) = ($label =~ /^( *)/);
	$indent =~ s/ /\&nbsp;\&nbsp;\&nbsp;/g;
	$label =~ s/^ */$indent/g;
	return qq(<STRONG>$label:\&nbsp;</STRONG>);
}
#===============================================================================
sub format_key_value {
	my($key, $value) = @_;
	return format_label($key).$value."<BR>\n";
}
#===============================================================================
# Usage: &look(*FILEHANDLE,$key,$dict,$fold)
# Sets file position in FILEHANDLE to be first line greater than or equal
# (stringwise) to $key.  Pass flags for dictionary order and case folding.
# taken from look.pl, part of perl distribution.

sub look {
	local(*FH, $_);
	my($key, $dict, $fold);
	(*FH,$key,$dict,$fold) = @_;
	my($max,$min,$mid);
	my($dev,$ino,$mode,$nlink,$uid,$gid,$rdev,$size,$atime,$mtime,$ctime,
		$blksize,$blocks) = stat(FH);
	$min = 0;
	$blksize = 8192 unless $blksize;
	$key =~ s/[^\w\s]//g if $dict;
	$key = lc $key if $fold;
	$max = int($size / $blksize);
	while ($max - $min > 1) {
		$mid = int(($max + $min) / 2);
		seek(FH,$mid * $blksize,0);
		$_ = <FH> if $mid;		# probably a partial line
		$_ = <FH>;
		chop;
		s/[^\w\s]//g if $dict;
		$_ = lc $_ if $fold;
		if ($_ lt $key) {
			$min = $mid;
		}
		else {
			$max = $mid;
		}
	}
	$min *= $blksize;
	seek(FH,$min,0);
	<FH> if $min;
	while (<FH>) {
		chop;
		s/[^\w\s]//g if $dict;
		$_ = lc $_ if $fold;
		last if $_ ge $key;
		$min = tell(FH);
	}
	seek(FH,$min,0);
	$min;
}

################################################################################
################################################################################
################################################################################
################################################################################

package UniqueID::Coding::MRZPassport;

sub new {
	my $class = shift;
	$class = ref($class) if ref($class);
	bless my $self = {}, $class;
	return $self;
}
sub uid { 'mrzp' }
sub display_name { "Machine Readable Passport MRZ Calculator" }
sub describe {
	my($link) = main::path_to_script();
	return <<_EODESC;
_EODESC
	#'
}

sub options
{
	return(
		["Sub-type", 'ptype', 'text', 1],
		["Given name(s)", 'givennames', 'text', 40],
		["Surname(s)", 'surnames', 'text', 40],
		['Date of birth'],
		[' Year', 'by', 'text', 4],
		[' Month', 'bm', 'text', 2],
		[' Day', 'bd', 'text', 2],
		['Sex', 'sex', 'select', {'Male' => 'M', 'Female' => 'F', 'Unspecified' => '<'} ],
		['Issuer', 'issuer', 'select', \%COUNTRY_TO_ALPHA3],
		['Passport expiration'],
		[' Year', 'ey', 'text', 4],
		[' Month', 'em', 'text', 2],
		[' Day', 'ed', 'text', 2],
		["Passport number", 'passportnumber', 'text', 9],
		["Personal number", 'pin', 'text', 14],
		['Nationality', 'nationality', 'select', \%COUNTRY_TO_ALPHA3],
	);
}

sub process
{
	my($self) = shift;
	my(%params) = @_;
	my(%error_fields, $error_explain, $output);
	# TODO FIXME XXX NO ERROR CHECKING IN PLACE YET
	if((defined $error_explain and length $error_explain) or
		scalar keys %error_fields){
		return(\%error_fields, $error_explain, $output);
	}

	my $l1 = '';
	$l1 .= 'P';
	if(length $params{'ptype'}) { $l1 .= substr $params{'ptype'}, 0, 1; }
	else { $l1 .= '<'; }
	$l1 .= $params{'issuer'};

	my $name1 = mrz_filter($params{'surnames'});
	my $name2 = mrz_filter($params{'givennames'});
	$l1 .= padtrim_to("$name1<<$name2", 39);

	my $l2 = '';
	$l2 .= padtrim_to($params{'passportnumber'}, 9);
	$l2 .= calc_check_digit($l2);
	$l2 .= $params{'nationality'};
	$l2 .= sprintf "%02d%02d%02d", $params{'by'} % 100, $params{'bm'}, $params{'bd'};
	$l2 .= calc_check_digit(substr $l2, 13, 6);
	$l2 .= $params{'sex'};
	$l2 .= sprintf "%02d%02d%02d", $params{'ey'} % 100, $params{'em'}, $params{'ed'};
	$l2 .= calc_check_digit(substr $l2, 21, 6);
	$l2 .= padtrim_to($params{'pin'}, 14);
	$l2 .= calc_check_digit(substr $l2, 28, 14);
	$l2 .= calc_check_digit(substr($l2, 0, 10).
		substr($l2, 13, 7).substr($l2, 21, 22));

	$output = "{\"line_01\": \"".$l1."\",\"line_02\":\"".$l2."\"}";
	return(\%error_fields, $error_explain, $output);
}

sub mrz_filter {
	my($in) = @_;
	if(not defined $in) { $in = ''; }
	$in = uc($in);
	$in =~ s/\s+/ /g;
	$in =~ s/[^A-Z0-9]/</g;
	return $in;
}

sub padtrim_to {
	my($line, $length) = @_;
	$line .= "<"x$length;
	return substr $line, 0, $length;
}

sub calc_check_digit {
	return (UniqueID::Coding::MRZPassportReverse::calc_check_digit(@_));
}

################################################################################
################################################################################
################################################################################
################################################################################

package UniqueID::Coding::MRZPassportReverse;

sub new {
	my $class = shift;
	$class = ref($class) if ref($class);
	bless my $self = {}, $class;
	return $self;
}

sub calc_check_digit {
	my($input) = @_;
	my(@chunks) = split(//, $input);
	my(%map) = qw( 0 0 1 1 2 2 3 3 4 4 5 5 6 6 7 7 8 8 9 9 A 10 B 11 C 12 D 13 E 14 F 15 G 16 H 17 I 18 J 19 K 20 L 21 M 22 N 23 O 24 P 25 Q 26 R 27 S 28 T 29 U 30 V 31 W 32 X 33 Y 34 Z 35 < 0 );
	my(@weights) = qw( 7 3 1 );
	my $i = 0;
	my $sum = 0;
	foreach my $chunk (@chunks) {
		my($value) = $map{uc($chunk)};
		if(not defined $value) {
			return (undef, "Invalid character '$chunk'.");
		}
		my $step = $i % scalar @weights;
		my $mult = $value * $weights[$step];
		$sum += $mult;
		$i++;
	}

	return $sum % 10;
}

################################################################################
################################################################################
################################################################################
################################################################################

package main;

my(%CATEGORIES);
sub add_category {
	my($i) = @_;
	die $i->uid()." already exists" if exists $CATEGORIES{$i->uid()};
	$CATEGORIES{$i->uid()} = $i;
}
add_category(UniqueID::Coding::MRZPassport->new());
main();
exit;

#===============================================================================
sub main
{
	my(@params);
	# old versions of CGI just die on failure.
	eval { @params = param(); };
	{
		my $failure = $@;
		if($failure or @params == 0) {
			fatal_error("Please try again with correct parameters.");
			return;
		}
	}
	my($category) = "mrzp";
	my($impl) = $CATEGORIES{$category};
	my %params;
	foreach my $p (@params)
	{
		$params{$p} = param($p);
		#entry point ...input values from the form
		#Win32::MsgBox($params{$p});
	}
	$params{'type'} = $category;
	my($error_fields, $error_explain, $output) =
		$impl->process(%params);

	print "{\"mrz\":".$output.",\"error\":\"".$error_explain."\"}"
	# print "$output \n" if defined $output;
	# print "$error_explain\n" if defined $error_explain;
}
#===============================================================================
sub fatal_error {
	my($error) = @_;
	print "{\"mrz\": null,\"error\":\"".$error."\"}";
	exit;
}
#===============================================================================
