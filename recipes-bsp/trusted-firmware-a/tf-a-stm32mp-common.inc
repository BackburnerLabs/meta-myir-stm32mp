FILESEXTRAPATHS:prepend := "${THISDIR}/tf-a-stm32mp:"

SECTION = "bootloaders"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://license.rst;md5=1dd070c98a281d18d9eefd938729b031"
CVE_PRODUCT = "arm:arm-trusted-firmware \
               arm:trusted_firmware-a \
               arm:arm_trusted_firmware \
               arm_trusted_firmware_project:arm_trusted_firmware"

TFA_SRC_URI ?= "git://github.com/MYiR-Dev/myir-st-arm-trusted-firmware.git;protocol=https;branch=develop-ld25x-v2.10"
SRC_URI = "${TFA_SRC_URI}"
SRCREV = "78c047116eede4c985858a97410aa4dfdb2151f2"

SRC_URI += " \
    file://0001-v2.10-stm32mp-r3.patch \
     "

TF_A_VERSION = "v2.10"
TF_A_SUBVERSION = ".24"
TF_A_TARGET = "stm32mp"
TF_A_RELEASE = "r3"
PV = "${TF_A_VERSION}${TF_A_SUBVERSION}-${TF_A_TARGET}-${TF_A_RELEASE}"

ARCHIVER_ST_BRANCH = "${TF_A_VERSION}-${TF_A_TARGET}"
ARCHIVER_ST_REVISION = "${TF_A_VERSION}-${TF_A_TARGET}-${TF_A_RELEASE}"
ARCHIVER_COMMUNITY_BRANCH = "lts-${TF_A_VERSION}"
ARCHIVER_COMMUNITY_REVISION = "lts-${TF_A_VERSION}${TF_A_SUBVERSION}"

S = "${WORKDIR}/git"

# ---------------------------------
# Configure devupstream class usage
# ---------------------------------
BBCLASSEXTEND = "devupstream:target"

SRC_URI:class-devupstream = "git://github.com/STMicroelectronics/arm-trusted-firmware.git;protocol=https;branch=${ARCHIVER_ST_BRANCH}"
SRCREV:class-devupstream = "41575b25c972b6f8958c101458d06ed23e38442b"

# ---------------------------------
# Configure default preference to manage dynamic selection between tarball and github
# ---------------------------------
STM32MP_SOURCE_SELECTION ?= "tarball"

DEFAULT_PREFERENCE = "${@bb.utils.contains('STM32MP_SOURCE_SELECTION', 'github', '-1', '1', d)}"
