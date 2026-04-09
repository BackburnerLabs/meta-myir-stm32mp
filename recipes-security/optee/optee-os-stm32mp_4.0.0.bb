require optee-os-stm32mp-common_${PV}.inc
require optee-os-stm32mp-common.inc

SUMMARY = "OPTEE TA development kit for stm32mp"
LICENSE = "BSD-2-Clause & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c1f21c4f72f372ef38a5a4aee55ec173"

OPTEE_SRC_URI ?= "git://github.com/MYiR-Dev/myir-st-optee_os.git;protocol=https;branch=develop-ld25x-4.0"
SRC_URI = "${OPTEE_SRC_URI}"
SRCREV = "${@bb.utils.contains('SIGN_ENABLE', '1', '9841603c96fe7c8c2c00e69fa9065428e793f65e', '377a9c8d75fae6a526af8e5008eb57ac32fc5c56', d)}"

SRC_URI += " \
    file://fonts.tar.gz;subdir=git;name=fonts \
    "

SRC_URI[fonts.sha256sum] = "4941e8bb6d8ac377838e27b214bf43008c496a24a8f897e0b06433988cbd53b2"

OPTEE_VERSION = "4.0.0"
OPTEE_SUBVERSION = "stm32mp"
OPTEE_RELEASE = "r1"

PV = "${OPTEE_VERSION}-${OPTEE_SUBVERSION}-${OPTEE_RELEASE}"

ARCHIVER_ST_BRANCH = "${OPTEE_VERSION}-${OPTEE_SUBVERSION}"
ARCHIVER_ST_REVISION = "${PV}"
ARCHIVER_COMMUNITY_BRANCH = "master"
ARCHIVER_COMMUNITY_REVISION = "${OPTEE_VERSION}"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "(stm32mpcommon)"

OPTEEMACHINE ?= "stm32mp1"
OPTEEMACHINE:stm32mp1common = "stm32mp1"
OPTEEMACHINE:stm32mp2common = "stm32mp2"

OPTEEOUTPUTMACHINE ?= "stm32mp1"
OPTEEOUTPUTMACHINE:stm32mp1common = "stm32mp1"
OPTEEOUTPUTMACHINE:stm32mp2common = "stm32mp2"

# The package is empty but must be generated to avoid apt-get installation issue
ALLOW_EMPTY:${PN} = "1"

# ---------------------------------
# Configure archiver use
# ---------------------------------
include ${@oe.utils.ifelse(d.getVar('ST_ARCHIVER_ENABLE') == '1', 'optee-os-stm32mp-archiver.inc','')}
